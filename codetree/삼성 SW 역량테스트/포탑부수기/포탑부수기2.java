import java.util.*;
import java.io.*;

public class Main {
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
	public static int[] strongSpot, weakSpot;
	public static int[][] d = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, {1, 1}, {1, -1},{-1, 1}, {-1, -1} }; // 우하좌상
	public static int N, M, K;
	public static Tower[][] MATRIX;
	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("./src/a2.txt")); // 제출 시 제거
		initialize(); //초기화
		
		for(int k = 1; k <= K; k++) { // 턴을 진행한다.
			weakSpot = getWeakSpot();
			strongSpot = getStrongSpot();
			
			if(strongSpot[0] == weakSpot[0] && strongSpot[1] == weakSpot[1]) break;
			Tower[][] temp = copyOfMatrix();
			// 약한 포탑에 공격한 턴수 기입 + 파워 증강
			temp[weakSpot[0]][weakSpot[1]].power += N + M;
			temp[weakSpot[0]][weakSpot[1]].attackTime = k;
			
			// 레이저 공격이 가능한가?
			if(!canlaserAttack(temp, weakSpot, strongSpot)) {
				bombAttack(temp, weakSpot, strongSpot);
			}
			clear(temp);
		}
		strongSpot = getStrongSpot();
		printAnswer(); // 결과 출력
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
	public static boolean canlaserAttack(Tower[][] map, int[] start, int[] destination) {
		int[][] route = new int[N][M]; // 최단거리부터 있나 찾아보기
		int[][][] coms = new int[N][M][2];
		for(int[] row : route) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { start[0], start[1] });
		route[start[0]][start[1]] = 0; // 시작점은 0으로
		while(!queue.isEmpty()) {
			int[] spot = queue.poll();

			for(int i = 0; i < 4; i++) {
				int ny = spot[0] + d[i][0];
				int nx = spot[1] + d[i][1];

				nx = (nx + M) % M;
				ny = (ny + N) % N;

				if(map[ny][nx].power > 0 && route[ny][nx] > route[spot[0]][spot[1]] + 1) {
					route[ny][nx] = route[spot[0]][spot[1]] + 1;
					coms[ny][nx][0] = spot[0]; // ny, nx를 방문하는데 spot[0], spot[1]에서 왔다.
					coms[ny][nx][1] = spot[1];
					queue.add(new int[] { ny, nx });
				}
			}
		}

		if(route[destination[0]][destination[1]] == Integer.MAX_VALUE) return false;

		int ty = destination[0];
		int tx = destination[1];
		int power = map[start[0]][start[1]].power;
		// 백트래킹
		while(start[0] != ty || start[1] != tx) {
			if(ty == destination[0] && tx == destination[1]) {
				map[ty][tx].power -= power;
			} else {
				map[ty][tx].power -= (power / 2);
			}
			
			int nty = coms[ty][tx][0];
			int ntx = coms[ty][tx][1];
			ty = nty;
			tx = ntx;
		}

		return true;
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
		System.out.println(MATRIX[strongSpot[0]][strongSpot[1]].power);
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
	public static int[] getWeakSpot() {
		Tower temp = new Tower(0, 0, Integer.MAX_VALUE);

		for(int n = N - 1; n >= 0; n--) {
			for(int m = M - 1; m >= 0; m--) {
				if(MATRIX[n][m].power <= 0) continue;
				if(temp.power > MATRIX[n][m].power) { // 공격력이 가장 낮은 포탑
					temp = new Tower(MATRIX[n][m]);
				} else if(temp.power == MATRIX[n][m].power) { // 2개 이상이라면
					if(temp.attackTime < MATRIX[n][m].attackTime) {
						temp = new Tower(MATRIX[n][m]);
					} else if(temp.attackTime == MATRIX[n][m].attackTime) {
						if(temp.x + temp.y < MATRIX[n][m].x + MATRIX[n][m].y) {
							temp = new Tower(MATRIX[n][m]);
						} else if(temp.x + temp.y == MATRIX[n][m].x + MATRIX[n][m].y) {
							if(temp.x < MATRIX[n][m].x) {
								temp = new Tower(MATRIX[n][m]);
							}
						}
					}
				}
			}
		}
		
		return new int[] { temp.y, temp.x };
	}
	public static int[] getStrongSpot() {
		Tower temp = new Tower(M, N, Integer.MIN_VALUE);

		for(int n = 0; n < N; n++) {
			for(int m = 0; m < M; m++) {
				if(MATRIX[n][m].power <= 0) continue;
				if(temp.power < MATRIX[n][m].power) { // 공격력이 가장 낮은 포탑
					temp = new Tower(MATRIX[n][m]);
				} else if(temp.power == MATRIX[n][m].power) { // 2개 이상이라면
					if(temp.attackTime > MATRIX[n][m].attackTime) {
						temp = new Tower(MATRIX[n][m]);
					} else if(temp.attackTime == MATRIX[n][m].attackTime) {
						if(temp.x + temp.y > MATRIX[n][m].x + MATRIX[n][m].y) {
							temp = new Tower(MATRIX[n][m]);
						} else if(temp.x + temp.y == MATRIX[n][m].x + MATRIX[n][m].y) {
							if(temp.x > MATRIX[n][m].x) {
								temp = new Tower(MATRIX[n][m]);
							}
						}
					}
				}
			}
		}
		return new int[] { temp.y, temp.x };
	}
}
