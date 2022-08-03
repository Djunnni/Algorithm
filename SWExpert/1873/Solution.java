import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * D3_1873_상호의배틀필드
 * 
 * 문자	의미
.	평지(전차가 들어갈 수 있다.)
*	벽돌로 만들어진 벽
#	강철로 만들어진 벽
-	물(전차는 들어갈 수 없다.)
^	위쪽을 바라보는 전차(아래는 평지이다.)
v	아래쪽을 바라보는 전차(아래는 평지이다.)
<	왼쪽을 바라보는 전차(아래는 평지이다.)
>	오른쪽을 바라보는 전차(아래는 평지이다.)
 * 
 * 
 * 문자	동작
U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
 * 
 * 제약사항
 * 사용자의 전차는 하나뿐이며, 적이나 아군으로 만들어진 전차는 등장하지 않는다.
 * 전차가 이동할 대, 맵 밖이면 당연히 이동하지 않는다.
 * 전차가 포탄을 발사하면, 포탄은 벽돌로 만들어진 벽 또는 강철로 만들어진 벽에 충돌하거나 게임 맵 밖으로 나갈 때까지 직진한다.
 * 강철로 만들어진 벽에 포탄이 부딛히면 아무일도 일어나지 않는다.
 * 게임 맵 밖으로 포탄이 나가면 아무일도 일어나지 않는다.
 * 
 * @author djunnni
 *
 */
public class Solution {
	public static int dx[] = {0, 0, -1, 1}; // 상, 하, 좌, 우
	public static int dy[] = {-1, 1, 0, 0};
	public static int spotX, spotY;
	public static char position;
	public static int H,W;
	public static char[][] matrix;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	//	System.setIn(new FileInputStream("src/com/ssafy/lab/D3_1873/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 개수
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			H = Integer.parseInt(st.nextToken()); // 높이
			W = Integer.parseInt(st.nextToken()); // 너비
			
			matrix = new char[H][W];
			
			// 배열 초기화 x : 가로 , y: 세로
			for(int i = 0; i < H; i++) {
				char[] input = br.readLine().toCharArray();
				for(int j = 0; j < W; j++) {
					matrix[i][j] = input[j];
					setTank(j, i, matrix[i][j]);
				}
			}
			
			int N = Integer.parseInt(br.readLine());
			char[] commands = br.readLine().toCharArray();
			for(int c = 0; c < N; c++) {
				char command = commands[c];
				action(command);
			}
			
			System.out.printf("#%d ", test_case);
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					System.out.print(matrix[i][j]);
				}
				System.out.println();
			}
			
		}
	}
	public static void action(char command) {
		switch(command) {
			case 'U':
				setTank(spotX, spotY, '^');
				if(isFlat(frontMap(spotX, spotY))) {
					swap(spotX, spotY, spotX + dx[0], spotY + dy[0]);
				}
				break;
			case 'D':
				setTank(spotX, spotY, 'v');
				if(isFlat(frontMap(spotX, spotY))) {
					swap(spotX, spotY, spotX + dx[1], spotY + dy[1]);
				}
				break;
			case 'L':
				setTank(spotX, spotY, '<');
				if(isFlat(frontMap(spotX, spotY))) {
					swap(spotX, spotY, spotX + dx[2], spotY + dy[2]);
				}
				break;
			case 'R':
				setTank(spotX, spotY, '>');
				if(isFlat(frontMap(spotX, spotY))) {
					swap(spotX, spotY, spotX + dx[3], spotY + dy[3]);
				}
				break;
			case 'S':
				shoot(spotX, spotY, position);
		}
	}
	public static void shoot(int x, int y, char position) {
		switch(position) {
			case '^':
				for(int next_y = y; next_y >= 0; next_y--) {
					if(matrix[next_y][x] == '*') {
						matrix[next_y][x] = '.';
						break;
					} else if(matrix[next_y][x] == '#') {
						break;
					}
				}
				break;
			case 'v':
				for(int next_y = y; next_y < H; next_y++) {
					if(matrix[next_y][x] == '*') {
						matrix[next_y][x] = '.';
						break;
					} else if(matrix[next_y][x] == '#') {
						break;
					}
				}
				break;
			case '<':
				for(int next_x = x; next_x >= 0; next_x--) {
					if(matrix[y][next_x] == '*') {
						matrix[y][next_x] = '.';
						break;
					} else if(matrix[y][next_x] == '#') {
						break;
					}
				}
				break;
			case '>':
				for(int next_x = x; next_x < W; next_x++) {
					if(matrix[y][next_x] == '*') {
						matrix[y][next_x] = '.';
						break;
					} else if(matrix[y][next_x] == '#') {
						break;
					}
				}
				break;
		}
	}
	public static boolean isFlat(char c) {
		return c == '.';
	}
	public static void swap(int x, int y, int n_x, int n_y) {
		char temp = matrix[y][x];
		matrix[y][x] = matrix[n_y][n_x];
		matrix[n_y][n_x] = temp;
		spotX = n_x;
		spotY = n_y;
		position = temp;
	}
	/**
	 * 탱크의 위치와 현재 바라보는 방향을 지정합니다.
	 * @param i
	 * @param j
	 * @param p
	 */
	public static void setTank(int i, int j, char p) {
		if(p == '^' || p == 'v' || p == '<' || p == '>' ) {
			spotX = i;
			spotY = j;
			position = p;
			matrix[spotY][spotX] = position;
		}
	}
	public static char frontMap(int i, int j) {
		int x = 0;
		int y = 0;
		switch(position) {
		case '^':
			x = dx[0];
			y = dy[0];
			break;
		case 'v':
			x = dx[1];
			y = dy[1];
			break;
		case '<':
			x = dx[2];
			y = dy[2];
			break;
		case '>':
			x = dx[3];
			y = dy[3];
		}
		if(isMapOver(i + x, j + y)) {
			return '-'; // matrix[j][i]; // 전차가 들어갈 수 없게 막자
		}
		return matrix[j + y][i + x];
	}
	public static boolean isMapOver(int x, int y) {
		if(x < 0 || x >= W || y < 0 || y >= H) {
			return true;
		}
		return false;
	}
}
