import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BJ_16236_아기상어
 * 
 * 풀이 방법
 * 우선순위 큐를 통해서 거리가 가까운 요소를 먼저 꺼내올 수 있게 한다.
 * 다음으로 가장 위에있는 순, 그 다음으로 가장 왼쪽 순으로 가져온다.
 * 
 * author djunnni
 */
public class Main {
	static int[][] map;
	static int[] dx = { 0, -1, 1, 0 }; // 상, 좌, 우, 하
	static int[] dy = { -1, 0, 0, 1 };
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		int[] shark = null;
		int size = 2;
		int eat = 0;
		int move = 0;
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer tokens = new StringTokenizer(in.readLine(), " ");
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tokens.nextToken());
				if(map[i][j] == 9) {
					shark = new int[] { i, j }; // r, c, distance;
					map[i][j] = 0;
				}
			}
		}
		
//		for(int[] row : map) {
//			System.out.println(Arrays.toString(row));
//		}
	
		while(true) {
			PriorityQueue<int[]> queue = new PriorityQueue<>((a, b)
					-> a[2] != b[2] ? Integer.compare(a[2], b[2]) : a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1])
			);

			queue.add(new int[] {shark[0], shark[1], 0});
			
			boolean[][] visited = new boolean[N][N];
			visited[shark[0]][shark[1]]=true;

			boolean ate = false;
			
			while(!queue.isEmpty()) {
				shark = queue.poll();
				if(map[shark[0]][shark[1]] != 0 && map[shark[0]][shark[1]] < size) {
					map[shark[0]][shark[1]] = 0;
					ate = true;
					eat++;
					move += shark[2];
					break;
				}
				
				for(int i = 0; i < dx.length; i++) {
					int nx = shark[1] + dx[i];
					int ny = shark[0] + dy[i];
					
					if(nx >= N || ny >= N || nx < 0 || ny < 0) {
						continue;
					}
					if(visited[ny][nx]) continue;
					if(map[ny][nx] > size) {
						continue;
					}			
					queue.add(new int[] {ny, nx, shark[2] + 1});
					visited[ny][nx]=true;
				}
				
			}
			
			if(!ate) {
				break;
			}
			
			if(eat == size) {
				eat = 0;
				size++;
			}
		}
		
		System.out.println(move);
	}
}
