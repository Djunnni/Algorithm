import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * SW_D4_1210_Ladder1
 * 
 * 사다리를 다 그리고 보니 김 대리는 어느 사다리를 고르면 X표시에 도착하게 되는지 궁금했다.
 * 사다리의 진행방향은 좌, 우, 아래
 * 나올 수 있는 케이스 방향 전환 구간이 2개가 발생했을 때, 왼쪽, 오른쪽 모두 다녀오기.
 * 만약 도착지점(2)를 발견하면 리턴.
 * 
 * @author djunnni
 *
 */
class Solution
{
	static class Spot {
		int x;
		int y;
		public Spot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static int []dx = { 0, -1, 1 };
	public static int []dy = { 1, 0, 0 };
	public static final int SIZE = 100;
	public static int[][] matrix;
	public static void main(String args[]) throws Exception
	{
		
		System.setIn(new FileInputStream("src/com/ssafy/lab/SW_1210/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = 10; // Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 테스트 넘버 받기
			int t = Integer.parseInt(br.readLine());
			
			// 매트릭스 초기화
			matrix = new int[SIZE][SIZE];
			// 스타트 할 수 있는 지점 받아두기
			HashSet<Integer> startSet = new HashSet<>();
			// 세로 y, 가로 x
			for(int y = 0; y < SIZE; y++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int x = 0; x < SIZE; x++) {
					matrix[y][x] = Integer.parseInt(st.nextToken());
					if(y == 0 && matrix[y][x] != 0) {
						startSet.add(x);
					}
				}
			}
			
			for(int start : startSet) {
				if(trace(start, 0)) {
					System.out.println("#" + test_case + " " + start);
					break;
				}
			}
		}
	}
	public static boolean trace(int spotX, int spotY) {
		boolean success = false;
		Queue<Spot> queue = new LinkedList<>();
		queue.add(new Spot(spotX, spotY));
		boolean[][] visited = new boolean[SIZE][SIZE];
		while(!queue.isEmpty()) {
			Spot spot = queue.poll();
			visited[spot.y][spot.x] = true;
			if(matrix[spot.y][spot.x] == 2) {
				success = true;
				break;
			}
			// 좌, 우 살피기
			boolean canTurn = false;
			for(int i = 1; i < dx.length; i++) {
				int nextX = spot.x + dx[i];
				int nextY = spot.y + dy[i];
				if(nextX >= 0 && nextX < SIZE && nextY >= 0 && nextY < SIZE && matrix[nextY][nextX] != 0  && !visited[nextY][nextX]) {
					canTurn = true;
				}
			}
			// 돌 수 있다면 queue에 추가
			if(canTurn) {
				for(int i = 1; i < dx.length; i++) {
					int nextX = spot.x + dx[i];
					int nextY = spot.y + dy[i];
					if(nextX >= 0 && nextX < SIZE && nextY >= 0 && nextY < SIZE && matrix[nextY][nextX] != 0 && !visited[nextY][nextX]) {
						queue.add(new Spot(nextX, nextY));
					}
				}
			} else {
				// 아니라면 아래로 내려가기
				int nextX = spot.x + dx[0];
				int nextY = spot.y + dy[0];
				if(nextX >= 0 && nextX < SIZE && nextY >= 0 && nextY < SIZE && matrix[nextY][nextX] != 0 && !visited[nextY][nextX]) {
					queue.add(new Spot(nextX, nextY));
				}
			}
		}
		return success;
	}
}
