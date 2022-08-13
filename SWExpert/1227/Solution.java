import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * D4_1227_미로2
 * 
 * 배열 탐색 문제로 방문을 하면서 숫자 3에 도착할 수 있는지 여부를 판단합니다.
 * 
 * author djunnni
 */
public class Solution {
	public static int answer, SIZE = 100;
	public static int[][] matrix;
	public static boolean[][] visited;
	public static int[] startPoint;
	public static int[] dx = {1, -1, 0, 0}; // 우, 좌, 상, 하
	public static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;// Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int test_case = 1; test_case <= T; test_case++) {
			String tc = br.readLine();
			answer = 0;
			matrix = new int[SIZE][SIZE];
			visited = new boolean[SIZE][SIZE];
			startPoint = new int[2];
			
			for(int i = 0; i < SIZE; i++) {
				String[] inputs = br.readLine().split("");
				for(int j = 0; j < inputs.length; j++) {
					matrix[i][j] = Integer.parseInt(inputs[j]);
					if(matrix[i][j] == 2) {
						startPoint[0] = i;
						startPoint[1] = j;
					}
				}
			}
			trace();
			
			sb.append("#"+ test_case + " " + answer + "\n");
		}
		System.out.println(sb);
	}
	public static void trace() {
		Queue<Integer> queueX = new LinkedList();
		Queue<Integer> queueY = new LinkedList();
		
		queueX.add(startPoint[1]);
		queueY.add(startPoint[0]);
		
		while(!queueX.isEmpty()) {
			int x = queueX.poll();
			int y = queueY.poll();
			// 골일 경우, 정답 1 브레이크
			if(isGoal(x, y)) {
				answer = 1;
				break;
			}
			visited[y][x] = true; // 방문처리 
			
			for(int i = 0; i < dx.length; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				if(isOver(nextX, nextY) || isWall(nextX, nextY) || visited[nextY][nextX] == true) {
					continue;
				}
			
				queueX.add(nextX);
				queueY.add(nextY);
			}
			
		}
	}
	public static boolean isWall(int x, int y) {
		if(matrix[y][x] == 1) {
			return true;
		}
		return false;
	}
	public static boolean isGoal(int x, int y) {
		if(matrix[y][x] == 3) {
			return true;
		}
		return false;
	}
	public static boolean isOver(int x, int y) {
		if(x < 0 || y < 0 || y >= SIZE || x >= SIZE) {
			return true;
		}
		return false;
	}
}
