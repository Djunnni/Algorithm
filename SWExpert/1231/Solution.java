import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SW_1231_중위 순회
 * 테스트 케이스가 2진트리로 확인됨
 * 부모 n, 왼쪽 2n, 오른쪽 2n + 1
 * 
 * @author djunnni
 *
 */
public class Solution {
	static int N;
	static char[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc).append(" ");
			
			N = Integer.parseInt(br.readLine());
			
			arr = new char[N + 1]; // N개의 공간을 사용하기
			for(int i = 1; i <= N; i++) {
				arr[i] = br.readLine().split(" ")[1].charAt(0); // 1 W 2 3 => W만 추출
			}
			
			in_order(arr, 1);
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	public static void in_order(char[] arr, int index) {
		if(index > N) {
			return;
		}
		
		in_order(arr, index * 2);
		sb.append(arr[index]);
		in_order(arr, index * 2 + 1);
	}
}
