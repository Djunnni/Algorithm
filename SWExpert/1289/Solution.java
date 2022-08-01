import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

/**
 * D3-1289-원재의 메모리 복구하기
 * 초기화된 메모리를 다시 원상복귀 시키는 일이다.
 * 다만, 메모리는 해당 위치를 덮어씌우면 끝까지 같은 값으로 덮어지는 이슈가 발생했다.
 * 모든게 0인 메모리에서 원래값으로 몇번이나 고쳐야 하는지 계산해보자.
 * @author djunnni
 *
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int answer = 0;
			char[] origin_bits = sc.next().toCharArray();
			char[] initial_bits = new char[origin_bits.length];
			Arrays.fill(initial_bits, '0');

            // 0번째부터 origin_bits길이까지 비교해서 다를 경우, 뒤의 비트를 전부 변경한다.
            // 이때, answer 카운트를 1증가한다.
			for(int i = 0; i < origin_bits.length; i++) {
				if(origin_bits[i] != initial_bits[i]) {
					answer++;
					Arrays.fill(initial_bits, i, initial_bits.length, origin_bits[i]);
				}
			}
			System.out.println("#" + test_case + " " + answer);
		}
	}
}