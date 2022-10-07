import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ_17472_다리 만들기2
 * @author djunnni
 *
 */
public class Main {
	static class Bridge {
		char from, to;
		int length;
		
		Bridge(char from, char to, int length) {
			this.from = from;
			this.to = to;
			this.length = length;
		}

		@Override
		public String toString() {
			return "Bridge [from=" + from + ", to=" + to + ", length=" + length + "]";
		}
		
		
	}
	static class Land {
		static char updateNo = 'A';
		char no;
		ArrayList<int[]> areas;
		ArrayList<Bridge> bridges;
		
		Land() {
			no = (char)(updateNo++);
		}
		Land(ArrayList<int[]> areas) {
			this();
			this.areas = areas;
		}
		@Override
		public String toString() {
			return "Land [no=" + no + ", bridges=" + bridges + "]";
		}
		
		
	}
	static int N, M, answer;
	static char[][] map;
	static ArrayList<Land> lands;
	static int dr[] = { -1, 1, 0, 0 }, dc[] = { 0, 0, -1, 1 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 세로 N (10이하)
		N = Integer.parseInt(st.nextToken());
		// 가로  M (10이하)
		M = Integer.parseInt(st.nextToken());
		// 지도
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String data = br.readLine();
			for(int j = 0, index = 0; j < M; j++, index += 2) {
				map[i][j] = data.charAt(index);
			}
		}
		// 지도 출력
//		for(char[] row: map) {
//			System.out.println(Arrays.toString(row));
//		}
		// 섬들 리스트 추가
		lands = new ArrayList<>();
		findAndUpdateLands();
		updateBridgePerLand();
//		 System.out.println(lands);
		
		answer = Integer.MAX_VALUE; // 방문할 수 없다고 하자.
		
		int map[][] = new int[lands.size()][lands.size()];
		for(int row[] : map) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		for(Land land : lands) {
			for(Bridge bridge : land.bridges) {
				int from = bridge.from - 'A';
				int to = bridge.to - 'A';
				map[from][to] = Math.min(map[from][to], bridge.length);
			}
		}
		
		boolean visited[] = new boolean[lands.size()];
		int cost[] = new int[lands.size()];
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		int result = 0;
		cost[0] = 0;
		visited[0] = true;

		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for(int i = 0; i < lands.size(); i++) {
				if(!visited[i] && map[cur][i] != Integer.MAX_VALUE) {
					cost[i] = Math.min(cost[i], map[cur][i]);
				}
			}
			int minIdx = -1;
			int minCost = Integer.MAX_VALUE;
			
			for(int i = 0; i < lands.size(); i++) {
				if(!visited[i] && cost[i] < minCost) {
					minCost = cost[i];
					minIdx = i;
				}
			}
			if(minIdx == -1) break;
			result += minCost;
			visited[minIdx] = true;
			queue.add(minIdx);
		}
		for(int i = 0; i < lands.size(); i++) {
			if(!visited[i]) {
				System.out.println(-1);
				return;
			}
		}
		
		System.out.println(result);
	}
	public static void updateBridgePerLand() {
		for(Land land : lands) {
			for(int[] spot : land.areas) {
				// 4방 탐색을 계속 진행
				for(int i = 0; i < dr.length; i++) {
					int nr = spot[0], nc = spot[1];
					int bridgeLength = 0;
					boolean findOtherLand = false;
					while(true) {
						nr += dr[i];
						nc += dc[i];
						// 범위를 벗어나거나 다음위치가 현재 자신과 같은 map이면 빠져나온다.
						if(isOver(nr, nc) || map[nr][nc] == land.no) {
							break;
						} else if(map[nr][nc] == '0') {
							bridgeLength++;
						} else if(
								Character.isUpperCase(map[nr][nc]) 
								&& map[nr][nc] != land.no
							) {
							findOtherLand = true;
							break;
						}
					}
					// 섬을 찾았고 거리가 1이상이라면
					if(findOtherLand && bridgeLength > 1) {
						Bridge b = new Bridge(land.no, map[nr][nc], bridgeLength);
						land.bridges.add(b);
					}
				}
			}
		}
	}
	public static void findAndUpdateLands() { 
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == '1') {
					BFS(i, j);
				}
			}
		}
	}
	public static void BFS(int r, int c) {
		Land land = new Land();
		ArrayList<int[]> areas = new ArrayList<>();
		
		Queue<int[]> queue = new ArrayDeque();
		queue.offer(new int[] { r, c });

		map[r][c] = land.no;
		areas.add(new int[] {r , c });
		while(!queue.isEmpty()) {
			int[] spot = queue.poll();
			
			for(int i = 0; i < dr.length; i++) {
				int nr = spot[0] + dr[i];
				int nc = spot[1] + dc[i];
				
				if(!isOver(nr, nc) && map[nr][nc] == '1') {
					map[nr][nc] = land.no;
					areas.add(new int[] { nr, nc });
					queue.add(new int[] { nr, nc });
				}
			}
		}
		
		land.areas = areas;
		land.bridges = new ArrayList<>();
		lands.add(land);
		
	}
	public static boolean isOver(int r, int c) {
		if(r < 0 || c < 0 || r >= N || c >= M) {
			return true;
		}
		return false;
	}
}