import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_16936_나3곱2
 * 
 * 제한 2초, 메모리 512MB
 * 
 * 나3곱2 게임은 정수 하나를 이용한다.
 * 가장 먼저 정수 x로 시작하고 연산을 N-1번 적용한다.
 * 
 * 적용할 수 있는 연산은 두가지가 있다.
 * 1. 나3 : x를 3으로 나눈다. x는 3으로 나누어떨어져야한다.
 * 2. 곱2 : x에 2를 곱한다.
 * 
 * 나3곱2 게임을 진행하면서, 만든 수를 모두 가록하면 수열 A를 만들 수 있다.
 * 
 * ex)
 * x = 9, N = 6
 * 곱2, 곱2, 나3, 곱2, 나3 => 9,18,36,12,24,8
 * 
 * 수영 A의 순서를 섞은 B가 주어졌을 때, A를 구하라.
 * 
 * 문제 접근
 * 1. 조합으로 첫번째 숫자를 선택하고 나머지는 규칙에 맞는지 탐색하는 로직을 작성했습니다.
 * 
 * 중간에 발생했던 문제
 * 두번째 값을 지정해두고 첫번째와 비교하는 과정으로 문제를 풀려고 했습니다.
 * 그러기 위해서는 연산이 %가 x로 x가 %로 바꾸게 됩니다.
 * 
 * 이 경우 몫과 나머지를 볼 수 있는 연산이 추가적으로 들어갔어야 했는데 빠지게 되니
 * 코드가 100%까지 실행되다 마지막에 실패가 뜨기도 했습니다.
 * 
 * 다시 원점으로 돌아와
 * 첫번째부터 값을 선택하고 두번째 값을 넣기전에 조건에 맞게 동작하는지 비교 후(몫/나머지, 곱) 
 * 삽입했습니다.
 * 
 * 1번이라도 발견이 되면 그대로 출력할 수 있도록 했습니다.
 * 
 * @author djunnni
 *
 */
public class Main {
	public static boolean isFinish;
	public static boolean[] isSelected;
	public static long[] answer, arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 수열의 크기
		
		arr = new long[N];
		isSelected = new boolean[N];
		answer = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		/**
		 * TODO: 배열을 돌려가며 나3, 곱2가 적용이 잘 되는지 확인합니다.
		 */
		// 배열 돌려보기
		findA(N,  0, false);
		
		// 출력하기
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N && isFinish; i++) {
			sb.append(answer[i]);
			if(i == N - 1) {
				sb.append("\n");
			} else {
				sb.append(" ");
			}
		}
		System.out.println(sb);
	}
	public static void findA(int N, int spot, boolean checkBase) {
		if(answer.length == spot) {
			isFinish = true;
			return;
		}
		
		for(int i = 0; i < N && !isFinish; i++) {
			if(isSelected[i]) {
				continue;
			}
			
			// 두번째 부터인지
			if(checkBase) {
				long tmp[] = na3(answer[spot - 1]);
				if((tmp[1] == 0 && tmp[0] == arr[i]) || (gop2(answer[spot - 1]) == arr[i])) {
					answer[spot] = arr[i];
					isSelected[i] = true;
					findA(N, spot + 1, true);
					isSelected[i] = false;
				}
			} else {
				answer[spot] = arr[i];
				isSelected[i] = true;
				findA(N, spot + 1, true);
				isSelected[i] = false;
			}	
		}
	}
	public static long[] na3(long v) {
		long[] temp = new long[2];
		temp[0] = v / 3;
		temp[1] = v % 3;
		return temp;
	}
	public static long gop2(long v) {
		return v * 2;
	}
}
