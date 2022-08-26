import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BJ_17144_미세먼지_안녕!
 * @author djunnni
 *
 */
public class Main {
	static int matrix[][];
	static int dx[] = { -1, 1, 0, 0 }; // 좌 우 상 하
	static int dy[] = { 0, 0, -1, 1 };
	static int R, C;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
							
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		
		// 세로 R
		R = Integer.parseInt(tokens.nextToken());
		// 가로 C
		C = Integer.parseInt(tokens.nextToken());
		// T초 후
		int T = Integer.parseInt(tokens.nextToken());
		
		// 공기청정기 위치 저장 : 0 -> 상, 1 -> 하
		List<int[]> cleaner = new ArrayList<>(2);
		// 먼지 위치 저장
		List<int[]> dusts = new ArrayList<>();
		// 공간 생성하기
		matrix = new int[R][C];
		
		for(int row = 0; row < R; row++) {
			tokens = new StringTokenizer(br.readLine(), " ");
			for(int column = 0; column < C; column++) {
				matrix[row][column] = Integer.parseInt(tokens.nextToken());
				if(matrix[row][column] == -1) {
					// 공기청정기 저장
					cleaner.add(new int[] { row, column });
				} else if(matrix[row][column] > 0) {
					// 먼지 저장
					dusts.add(new int[] { row, column });
				}
			}
		}
		
		// 공간 출력해보기
//		for(int row[] : matrix) {
//			System.out.println(Arrays.toString(row));
//		}
//		System.out.println("=======================");
//		
		// 1초동안 순서대로
		for(int time = 1; time <= T; time++) {
			spread(matrix);
			// 미세먼지 확산
//			Queue<int[]> queue = new ArrayDeque<>(dusts);
//			dusts.clear
//			for(int[] dust : dusts) {
//				
//			}
		
			// 공기청정기 작동
			run(cleaner.get(0), new int[] {0, C-1}, false);
			run(cleaner.get(1), new int[] {R-1, C-1}, true);
		}
		
		int answer = 0;
		for(int row[] : matrix) {
			for(int i : row) {
				if(i > 0) {
					answer += i;
				}
			}
		}
		System.out.println(answer);
	}
	public static void spread(int[][] original) {
		int[][] tempMatrix = new int[R][C];
		for(int row = 0; row < R; row++) {
			for(int column = 0; column < C; column++) {
				// 먼지를 가지고 있을 때, 4방 탐색을 한다.
				if(original[row][column] > 0) {
					// 확산되는 양은 duz다
					int duz = (int) Math.floor(original[row][column] / 5);
					int count = 0;
					for(int i = 0; i < dx.length; i++) {
						int nr = row + dy[i];
						int nc = column + dx[i];
						
						// 범위를 벗어나거나 해당 위치가 냉장고라면 넘어간다.
						if(nc < 0 || nr < 0 || nr >= R || nc >= C || original[nr][nc] == -1) {
							continue;
						}
						// 임시공간에 수를 저장한다.
						tempMatrix[nr][nc] += duz; 
						// 카운트 1증가
						count++;
					}
					original[row][column] -= duz * count;
				}
			}
		}
		for(int row = 0; row < R; row++) {
			for(int column = 0; column < C; column++) {
				if(original[row][column] != -1)
					original[row][column] += tempMatrix[row][column];
			}
		}
	}
//	public static int[][] copyOfMap(int[][] map) {
//		int temp[][] = new int[map.length][map[0].length];
//		
//		for(int i = 0; i < map.length; i++) {
//			temp[i] = Arrays.copyOf(map[i], map[0].length);
//		}
//		return temp;
//	}
	/**
	 * direction이 false면 반시계방향, true면 시계방향
	 * vertex는 가장 끝 지점을 알려줍니다.
	 */
	public static void run(int[] cleaner, int[] vertex, boolean direction) {
		// 공기청정기에서 나가는 바람은 미세먼지가 없다.
		matrix[cleaner[0]][cleaner[1]] = 0;
		
		if(direction) { 		// 시계방향으로 작성해보자
			// 오른쪽 방향
			for(int i = cleaner[1]; i <= vertex[1]; i++) {
				swap(cleaner[0], cleaner[1], cleaner[0], i);
			}
			// 아래방향
			for(int i = cleaner[0] + 1; i <= vertex[0]; i++) {
				swap(cleaner[0], cleaner[1], i, vertex[1]);
			}
			// 왼쪽 방향
			for(int i = vertex[1] - 1; i >= cleaner[1]; i--) {
				swap(cleaner[0], cleaner[1], vertex[0], i);
			}
			// 위 방향
			for(int i = vertex[0] - 1; i >= cleaner[0]; i--) {
				swap(cleaner[0], cleaner[1], i, cleaner[1]);
			}
		} else { // 반시계 방향으로 작성
			// 오른쪽 방향
			for(int i = cleaner[1]; i <= vertex[1]; i++) {
				swap(cleaner[0], cleaner[1], cleaner[0], i);
			}
			// 위 방향
			for(int i = cleaner[0] - 1; i >= vertex[0]; i--) {
				swap(cleaner[0], cleaner[1], i, vertex[1]);
			}
			// 왼쪽 방향
			for(int i = vertex[1] - 1; i >= cleaner[1]; i--) {
				swap(cleaner[0], cleaner[1], vertex[0], i);
			}
			// 아래방향
			for(int i = vertex[0] + 1; i <= cleaner[0]; i++) {
				swap(cleaner[0], cleaner[1], i, cleaner[1]);
			}
		}
		// 다시 공기청정기의 위치로 변경해준다.
		matrix[cleaner[0]][cleaner[1]] = -1;
	}
	public static void swap(int r, int c, int r2, int c2) {
		int temp = matrix[r][c];
		matrix[r][c] = matrix[r2][c2];
		matrix[r2][c2] = temp;
	}
}
