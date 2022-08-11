import java.util.Scanner;

/**
 * BJ_2961_도영이가 만든 맛있는 음식
 * 
 * 재료를 여러개 선택시 신맛은 곱, 쓴맛은 합으로 한다.
 * 요리의 신맛, 쓴맛의 값은 1억보다 작은 수다 => int 가능 
 * 재료는 적어도 하나를 사용해야 한다. => 아무것도 선택하지 않은 경우를 뺀다.(초기값 제외)
 * 
 * 입력
 * 
 * 재료들의 개수 N
 * 신맛 쓴맛 순서대로
 * 
 * 출력
 * 선택한 재료들의 신맛 - 쓴맛의 절대값
 * 
 * @author djunnni
 *
 */
public class Main {
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
		boolean[] isSelected = new boolean[N];
		for(int i = 0; i < arr.length; i++) {
			int sour =  sc.nextInt();
			int bitter = sc.nextInt();
			
			arr[i] = new Source(sour, bitter);
		}
		
		answer = Integer.MAX_VALUE;
		
		selectSource(N, arr, isSelected, 0, 0, 1);
		
		
		System.out.println(answer);
	}
	public static void selectSource(int N, Source[] arr, boolean[] isSelected, int cnt, int bitter, int sour) {
		if(N == cnt) {
			if(bitter != 0 || sour != 1)
			answer = Math.min(answer, Math.abs(sour - bitter));
			return;
		}
		
		
		isSelected[cnt] = true;
		selectSource(N, arr, isSelected, cnt + 1, bitter + arr[cnt].bitter, sour * arr[cnt].sour);
		isSelected[cnt] = false;
		selectSource(N, arr, isSelected, cnt + 1, bitter, sour);
	}
}
