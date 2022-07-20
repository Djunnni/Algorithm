import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Robot {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/input_robot.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t = 1; t <= T; t++) {
			int N = sc.nextInt();
			char[][] matrix = new char[N][N];
			
			// i 행, j 열
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					matrix[i][j] = sc.next().charAt(0);
				}
			}
			

			int answer = 0;
			// i 행, j 열
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					// A 일 경우, 오른쪽만 가능
					if(matrix[i][j] == 'A') {
						for(int k = j + 1; k < N; k++) {
							if(matrix[i][k] != 'S')
								break;
							answer++; 
						}
					} else if(matrix[i][j] == 'B') {
						// B일 경우, 좌 우 
						for(int k = j - 1; k >= 0; k--) {
							if(matrix[i][k] != 'S')
								break;
							answer++;
						}
						for(int k = j + 1; k < N; k++) {
							if(matrix[i][k] != 'S')
								break;
							answer++;
						}
					} else if(matrix[i][j] == 'C') {
						// C일 경우, 상하좌우
						for(int k = j - 1; k >= 0; k--) {
							if(matrix[i][k] != 'S')
								break;
							answer++;
						}
						for(int k = j + 1; k < N; k++) {
							if(matrix[i][k] != 'S')
								break;
							answer++;
						}
						for(int k = i - 1; k >= 0; k--) {
							if(matrix[k][j] != 'S')
								break;
							answer++;
						}
						for(int k = i + 1; k < N; k++) {
							if(matrix[k][j] != 'S')
								break;
							answer++;
						}
					}
				}
			}
			
			System.out.println("#" + t + " " + answer);
		}
	}

}
