import java.util.*;
import java.io.*;

/**
 * @author djunnni
 */
public class Main {
	public static class WeakComparator implements Comparator<int[]> {
		@Override
		public int compare(int[] spot1, int[] spot2) {
			Tower t1 = MATRIX[spot1[0]][spot1[1]];
			Tower t2 = MATRIX[spot2[0]][spot2[1]];
			if(t1.power == t2.power) {
				if(t1.attackTime == t2.attackTime) {
					if(t1.x + t1.y == t2.x + t2.y) {
						return Integer.compare(t2.x, t1.x);
					}
					return Integer.compare(t2.x + t2.y, t1.x + t1.y);
				}
				return Integer.compare(t2.attackTime, t1.attackTime);
			}
			return Integer.compare(t1.power, t2.power);
		}
		
	}
	public static class StrongComparator implements Comparator<int[]> {

		@Override
		public int compare(int[] spot1, int[] spot2) {
			Tower t1 = MATRIX[spot1[0]][spot1[1]];
			Tower t2 = MATRIX[spot2[0]][spot2[1]];
			if(t1.power == t2.power) {
				if(t1.attackTime == t2.attackTime) {
					if(t1.x + t1.y == t2.x + t2.y) {
						return Integer.compare(t1.x, t2.x);
					}
					return Integer.compare(t1.x + t1.y, t2.x + t2.y);
				}
				return Integer.compare(t1.attackTime, t2.attackTime);
			}
			return Integer.compare(t2.power, t1.power);
		}
		
	}
	public static class Tower {
		int attackTime, x, y, power;
		
		public Tower(Tower t) {
			this.x = t.x;
			this.y = t.y;
			this.power = t.power;
			this.attackTime = t.attackTime;
		}
		public Tower(int x, int y, int power) {
			this.x = x;
			this.y = y;
			this.power = power;
			this.attackTime = 0;
		}

		public Tower(int x, int y, int power, int attackTime) {
			this(x, y, power);
			this.attackTime = attackTime;
		}
		@Override
		public String toString() {
			return "Tower [attackTime=" + attackTime + ", x=" + x + ", y=" + y + ", power=" + power + "]";
		}
	}
	public static int[][] d = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, {1, 1}, {1, -1},{-1, 1}, {-1, -1} }; // 우하좌상
	public static int N, M, K;
	public static Tower[][] MATRIX;
	public static Queue<int[]> weakQueue, strongQueue;
	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("./src/a2.txt")); // 제출 시 제거
		initialize(); //초기화
		
		for(int k = 1; k <= K; k++) { // 턴을 진행한다.
			compareStrongAndWeak(); // 강한자와 약한자를 찾는다.
			
			int[] strongSpot = strongQueue.peek();
			int[] weakSpot = weakQueue.peek();
			
			if(strongQueue.size() == 1) break;
//			System.out.println("STRONG : " + Arrays.toString(strongSpot));
//			System.out.println("WEAK : " + Arrays.toString(weakSpot));
			Tower[][] temp = copyOfMatrix();
			// 약한 포탑에 공격한 턴수 기입 + 파워 증강
			temp[weakSpot[0]][weakSpot[1]].power += N + M;
			temp[weakSpot[0]][weakSpot[1]].attackTime = k;
			
			// 레이저 공격이 가능한가?
			int[][] route = new int[N][M];
			for(int[] row : route) {
				Arrays.fill(row, Integer.MAX_VALUE);
			}
			findRoute(temp, weakSpot, strongSpot, route);
			if(route[strongSpot[0]][strongSpot[1]] != Integer.MAX_VALUE) {
				// -> YES -> 공격	
				//System.out.println("LASER");
				laserAttack(route, temp, weakSpot, strongSpot, route[weakSpot[0]][weakSpot[1]], temp[weakSpot[0]][weakSpot[1]].power, true);
			} else {				
				// -> NO -> 포탄
				//System.out.println("BOMB");
				bombAttack(temp, weakSpot, strongSpot);
			}
			
			clear(temp);
			// for(Tower[] row : MATRIX) {
			// 	System.out.println(Arrays.toString(row));
			// }
			// System.out.println("==========");
		}
		
		compareStrongAndWeak(); // 마지막으로 강한자와 약한자 구하기
		printAnswer(); // 결과 출력
		
	}
	public static void clear(Tower[][] map) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j].power <= 0 || MATRIX[i][j].power <= 0) {
					MATRIX[i][j].power = 0;
				} else if(MATRIX[i][j].power == map[i][j].power) {
					MATRIX[i][j].power += 1;
				} else {
					MATRIX[i][j].power = map[i][j].power;
				}
				MATRIX[i][j].attackTime = map[i][j].attackTime;
			}
		}
	}
	public static boolean laserAttack(int[][] route, Tower[][] map, int[] start, int[] destination, int current, int energy, boolean isFirst) {
		if(
				current == route[destination[0]][destination[1]] &&
				start[0] == destination[0] &&
				start[1] == destination[1]
		) {
			map[start[0]][start[1]].power -= energy;
			return true;
		}
        if(!isFirst) {
            map[start[0]][start[1]].power -= energy / 2;		
        }
		for(int i = 0; i < 4; i++) {
			int ny = start[0] + d[i][0];
			int nx = start[1] + d[i][1];
			
			if(nx < 0) {
				nx += M;
			} else if(nx >= M) {
				nx -= M;
			}
			if(ny < 0) {
				ny += N;
			} else if(ny >= N) {
				ny -= N;
			}
			
			if(map[ny][nx].power > 0 && route[ny][nx] == current + 1) {
				if(laserAttack(route, map, new int[] { ny, nx }, destination, current + 1, energy, false)) {
					return true;
				}
			}
		}
        map[start[0]][start[1]].power += energy / 2;		
		return false;
	}
	public static void findRoute(Tower[][] map, int[] start, int[] destination, int[][] route) {
		Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		queue.add(new int[] { start[0], start[1], 0 });
		route[start[0]][start[1]] = 0;
		
		while(!queue.isEmpty()) {
			int[] spot = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int ny = spot[0] + d[i][0];
				int nx = spot[1] + d[i][1];
				// System.out.println(nx + " : " + ny);
				if(nx < 0) {
					nx += M;
				} else if(nx >= M) {
					nx -= M;
				}
				if(ny < 0) {
					ny += N;
				} else if(ny >= N) {
					ny -= N;
				}
				if(map[ny][nx].power <= 0 || route[ny][nx] <= spot[2] + 1) continue;
				route[ny][nx] = spot[2] + 1;
				queue.add(new int[] { ny, nx, route[ny][nx] });
			}
		}
		
	}
	public static void bombAttack(Tower[][] map, int[] attackSpot, int[] destinationSpot) {
		map[destinationSpot[0]][destinationSpot[1]].power -= map[attackSpot[0]][attackSpot[1]].power; // 메인 공격을 받음
		for(int i = 0; i < 8; i++) {
			int ny = destinationSpot[0] + d[i][0];
			int nx = destinationSpot[1] + d[i][1];
			
			if(nx < 0) {
				nx += M;
			} else if(nx >= M) {
				nx -= M;
			}
			if(ny < 0) {
				ny += N;
			} else if(ny >= N) {
				ny -= N;
			}
			
			if(ny == attackSpot[0] && nx == attackSpot[1]) continue;
			map[ny][nx].power -= (map[attackSpot[0]][attackSpot[1]].power / 2);
			
		}
	}
	public static Tower[][] copyOfMatrix() {
		Tower[][] temp = new Tower[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				temp[i][j] = new Tower(MATRIX[i][j]);
			}
		}
		return temp;
	}
	public static void printAnswer() {
		int[] spot = strongQueue.peek();
		System.out.println(MATRIX[spot[0]][spot[1]].power);
	}
	public static void initialize() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 세로 
		M = Integer.parseInt(st.nextToken()); // 가로
		K = Integer.parseInt(st.nextToken()); // 턴 수 
		
		MATRIX = new Tower[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				MATRIX[i][j] = new Tower(j, i, Integer.parseInt(st.nextToken()));
			}
		}
	}
	public static void compareStrongAndWeak() {
		weakQueue = new PriorityQueue<int[]>(new WeakComparator());
		strongQueue = new PriorityQueue<int[]>(new StrongComparator());
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(MATRIX[i][j].power == 0) continue; // 이미 죽은 경우라면 추가하지 않는다.
				weakQueue.add(new int[] { i, j }); // heap => NMLog(NM) * 2 => 100 * 4 => 400 * K => 400_000 => 40만 연
				strongQueue.add(new int[] { i, j });
			}
		}
	}
}
