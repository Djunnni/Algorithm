import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_12891_DNA비밀번호
 * 
 * 시간제한: 2초, 메모리: 512MB
 * 
 * DNA문자열은 {'A','C','G','T'}로 구성된다.
 * 민호는 임의의 DNA 문자열을 만들고 만들어진 DNA 문자열의 부분문자열을 비밀번호로 사용하기로 했다.
 * 
 * 부분 문자열 "AAAAA"와 같이 보안에 취약한 비밀번호가 만들어 질 수 있어
 * 민호는 부분문자열에 등장하는 문자의 개수가 특정 개수 이상이여야 비밀번호로 사용할 수 있다.
 * 
 * 주어진 내용
 * 임의의 문자열, 비밀번호로 사용할 길이, A, C, G, T가 각각 몇개 이상인지
 * 단, 부분문자열이 등장하는 위치가 다르다면 부분문자열이 같다고 하더라도 다른 문자열로 취급한다.
 * 
 * @author djunnni
 *
 */
public class Main {
	public static char[] input;
	public static int S, P, A, C, G, T, answer;
	public static int matrix[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		input = br.readLine().toCharArray();
		// 세로 : 문자열, S 문자수
		matrix = new int[4][S + 1];
		
		st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		int a = 0;
		int c = 0;
		int g = 0;
		int t = 0;
		for(int i = 1; i <= S; i++) {
			switch(input[i - 1]) {
				case 'A':
					matrix[0][i] = ++a;
					if(i > 0) {
						matrix[1][i] = matrix[1][i - 1];
						matrix[2][i] = matrix[2][i - 1];
						matrix[3][i] = matrix[3][i - 1];
					}
					break;
				case 'C':
					matrix[1][i] = ++c;
					if(i > 0) {
						matrix[0][i] = matrix[0][i - 1];
						matrix[2][i] = matrix[2][i - 1];
						matrix[3][i] = matrix[3][i - 1];
					}
					break;
				case 'G':
					matrix[2][i] = ++g;
					if(i > 0) {
						matrix[1][i] = matrix[1][i - 1];
						matrix[0][i] = matrix[0][i - 1];
						matrix[3][i] = matrix[3][i - 1];
					}
					break;
				case 'T':
					matrix[3][i] = ++t;
					if(i > 0) {
						matrix[1][i] = matrix[1][i - 1];
						matrix[2][i] = matrix[2][i - 1];
						matrix[0][i] = matrix[0][i - 1];
					}
					break;
			}
		}
		for(int i = 1; i <= S; i++) {
			if(i + P - 1 > S) {
				continue;
			}
			if(Math.abs(matrix[0][i + P - 1] - matrix[0][i - 1]) < A) {
				continue;
			}
			if(Math.abs(matrix[1][i + P - 1] - matrix[1][i - 1]) < C) {
				continue;
			}
			if(Math.abs(matrix[2][i + P - 1] - matrix[2][i - 1]) < G) {
				continue;
			}
			if(Math.abs(matrix[3][i + P - 1] - matrix[3][i - 1]) < T) {
				continue;
			}
			answer++;
		}
		
		System.out.println(answer);
	}
}
