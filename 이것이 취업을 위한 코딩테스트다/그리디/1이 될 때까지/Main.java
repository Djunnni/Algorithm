package dongbin.greedy;

import java.util.Scanner;

/**
 * 1이 될 때까지
 * 1sec, 128MB
 * @author djunnni
 *
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 2 <= N <= 100_000
		int K = sc.nextInt(); // 2 <= K <= 100_000
		
		int count = 0;
		
		while(N != 1) {
			if(N % K == 0)
				N = N / K;
			else 
				N = N - 1;
			count++;
		}
		System.out.println(count);
	}
}
