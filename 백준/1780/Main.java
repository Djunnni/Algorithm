import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_1780_종이의 개수
 * 
 * N크기의 행렬로 표현되는 종이가 있다. 각 칸에는 -1, 0, 1중 하나가 저장.
 * 
 * 다음과 같은 규칙이 적용된다.
 * 1. 종이가 모두 같은 수로 되어있다면 종이를 그대로 사용한다.
 * 2. 아닐경우, 종이를 같은 크기로 총 9개로 자르고, 각 잘린 종이에 대해서 (1)을 반복한다.
 * 
 * 입력: N ( 1 <= N <= 3 ^ 7), 행렬로 된 값
 * 출력 : -1, 0, 1로만 채워진 종이의 개수를 구해 출력한다.
 * 
 * @author djunnni
 *
 */
public class Main {
	public static int minusOne, zero, plusOne, N;
	public static int arr[][];
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		slice(0, 0, N);
		System.out.printf("%d\n%d\n%d", minusOne, zero, plusOne);
	}
	
	public static void slice(int startX, int startY, int size) {
		int v = arr[startX][startY];
		boolean isContinues = true; // 계속 같은 값이 반복되는지 체크
		for(int x = 0; x < size && isContinues; x++) {
			for(int y = 0; y < size && isContinues; y++) {
				if(v != arr[startX + x][startY + y]) {
					isContinues = false;
					break;
				}
			}
		}
		if(isContinues) {
			// 값이 계속 반복됐다면 해당하는 애에 맞게 변수 증가
			switch(v) {
				case 1:
					plusOne++;
					break;
				case 0:
					zero++;
					break;
				case -1:
					minusOne++;
					break;
			}
		} else {
			// 그렇지 않다면 다시 9개로 자르고 재귀를 실행하라.
			// ex) 9 ( 9 / 3 -> 3 : 한 변 길이) -> 0 3 6
			// ex) N ( N / 3 -> x : 한변 길이) -> startX, startY를 기준으로 N /3 씩 더하자.
			
			int width = size / 3;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					slice(startX + width * i, startY + width * j, width);
				}
			}
		}
	}

}
