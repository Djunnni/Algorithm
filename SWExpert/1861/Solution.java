import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * D4_1861_정사각형방
 * 
 * 시간 2초, 메모리 256MB
 * 
 * N^2개의 방이 NxN으로 늘어서 있다.
 * 위에서 i번째 왼쪽에서 j번째 방은 A[i][j]에 적혀있으며 모든 방은 숫자가 다르다.
 * 
 * 어떤방에 당신이 있으면 상하좌우에 있는 다른방으로 이동할 수 있다.
 * 물론 이동할 방이 존재해야하며, 이동하려는 방에 적힌 숫자가 현재 방보다 1 더 커야한다.
 * 
 * 처음에 어떤 수가 적힌 방에 있어야 가장 많은 방의 개수를 이동할 수 있는지 구하라.
 * 
 * 출력
 * 처음에 출발해야 하는 방 번호와 최대 몇개의 방을 이동할 수 있는지 공백으로 구분해 출력하라.
 * 만약 이동할 수 있는 방의 개수가 최대인 방이 여럿이면 가장 작은걸 출력한다.
 * 
 * @author djunnni
 *
 */
public class Solution {
	public static int[][] matrix;
	public static boolean[][] visited;
	public static int answer_room, answer_visitCount, N;
	public static int dx[] = { -1, 1, 0, 0 }; // 좌, 우, 상, 하
	public static int dy[] = { 0, 0, -1, 1 };
	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("src/com/ssafy/lab/D4_1861/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			
			matrix = new int[N + 1][N + 1]; // 1 <= X,Y <= N
			visited = new boolean[N + 1][N + 1];
			
			answer_visitCount = 0;
			answer_room = Integer.MAX_VALUE;
			
			for(int y = 1; y <= N; y++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int x = 1; x <= N; x++) {
					matrix[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int y = N; y > 0; y--) {
				for(int x = N; x > 0; x--) {
					if(!visited[y][x]) {
                        // 첫번째 방문장소는 기록하지 않기위함.
						trace(x, y, 1, true);
					}
				}
			}
			
			System.out.println("#"+ test_case+ " " + answer_room + " " + answer_visitCount);
		}
	}
	public static void trace(int x, int y, int count, boolean isFirst) {
		if(answer_visitCount <= count && !isFirst) {
			answer_visitCount = count;
			if(answer_room > matrix[y][x])
				answer_room = matrix[y][x];
		}
		for(int i = 0; i < dx.length; i++) {
			int next_x = x + dx[i];
			int next_y = y + dy[i];
			// 다음 x와 y가 판을 벗어났는지 확인한다.
			if(isOver(next_x, next_y)) {
				continue;
			}
			// 먼저 방문한 곳이면 다음 지점을 확인한다.
			if(visited[next_y][next_x]) {
				continue;
			}
			// 현재지점보다 다음지점이 1 차이가 난다면 현재지점은 방문하고 다시 이전지점으로 검사를 진행한다.
			if(matrix[y][x] - 1 == matrix[next_y][next_x]) {
				visited[y][x] = true;
				trace(next_x, next_y, count + 1, false);
			}
		}
        // 방문했던 이력을 푼다.
        visited[y][x] = false;
	}
	/**
	 * 판을 벗어났는지 확인하는 함수
	 */
	public static boolean isOver(int x, int y) {
		if(x < 1 || x > N || y < 1 || y > N) {
			return true;
		}
		return false;
	}
}
