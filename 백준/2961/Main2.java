import java.util.Scanner;

/**
 * BJ_2961_도영이가 만든 맛있는 음식
 * 
 * 비트 마스크로 변경하기
 * 
 * @author djunnni
 *
 */
public class Main2 {
	static class Source {
		int sour; // 신맛
		int bitter; // 쓴맛
		
		public Source(int sour, int bitter) {
			this.sour = sour;
			this.bitter = bitter;
		}
	}
	public static int answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		// 소스 받아오기
		Source[] arr = new Source[N];
		for(int i = 0; i < arr.length; i++) {
			int sour =  sc.nextInt();
			int bitter = sc.nextInt();
			
			arr[i] = new Source(sour, bitter);
		}
		
		answer = Integer.MAX_VALUE;
		
		// 1부터 N까지 비트 범위
		// 각 index가 지금부터 선택한 Source가 된다.
		for(int bit = 0; bit < (1 << N); bit++) {
			int bitter = 0;
			int sour = 1;
			for(int i = 0; i < N; i++) {
				// i번째가 선택 되어있다면!
				if((bit & (1 << i)) != 0) {
					bitter += arr[i].bitter;
					sour *= arr[i].sour;
				}
			}
			if(bitter != 0)
				answer = Math.min(answer, Math.abs(bitter - sour));
		}
		System.out.println(answer);
	}
}