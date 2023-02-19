import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 비트와 배열로 문제를 풀어본다.
 * @Djunnni
 */
public class SW_10726 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		for(int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// bitAnswer(testCase, N, M);
			arrayAnswer(testCase, N, M);
		}
	}
	// toBinaryString은 
	// extra(0)을 추가로 붙이지 않으며, unsigned integer를 받는다.
	public static void arrayAnswer(int testCase, int N, int M) {
		String Mchars = Integer.toBinaryString(M); 
		boolean flag = true;
		for(int i = Mchars.length() - 1, size = Mchars.length() - N; i >= size; i--) {
			if(i < 0 || Mchars.charAt(i) - '0' == 0) {
				flag = false;
				break;
			}
		}
		
		if(flag) {
			System.out.println("#" + testCase + " " + "ON");
		} else {
			System.out.println("#" + testCase + " " + "OFF");
		}
	}
	public static void bitAnswer(int testCase, int N, int M) {
		int bits = (1 << N) - 1; // N개의 1비트 수 
		if((M & bits) == bits) {
			System.out.println("#" + testCase + " " + "ON");
		} else {
			System.out.println("#" + testCase + " " + "OFF");
		}
	}
}
