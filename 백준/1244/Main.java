import java.util.Scanner;

/**
 * BJ_1244_S3
 * 스위치 켜고 끄기
 * 
 * 남학생의 경우 -> 자신의 위치에서 배수만큼을 뒤집을 수 있다.
 * 여학생의 경우 -> 자신의 위치에서 좌우 대칭인 곳까지 뒤집을 수 있다.
 * 
 * 출력 케이스: 한줄에 20개씩 빈칸을 넣어 출력한다.
 * 
 * @author djunnni
 *
 */
public class Main {

	static int N;
	static int M;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		// 스위치의 개수
		N = sc.nextInt();
		
		// 범위는 1 ~ N으로 하겠다.
		int[] arr = new int[N + 1]; 
		for(int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 학생 수
		M = sc.nextInt();
		for(int m = 0; m < M; m++) {
			// sex: 1 -> 남학생, 2 -> 여학생
			int sex = sc.nextInt();
			// 학생이 받은 수 (1 <= position <= N)
			int position = sc.nextInt();
			changeLight(sex, position, arr);
		}

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(arr[i]);
			if(i % 20 == 0) {
				sb.append("\n");
			} else {
				sb.append(" ");
			}
		}
		System.out.print(sb);
	}

	public static void changeLight(int sex, int position, int[] arr) {
		if(sex == 1) {
			// 남자라면
			int cnt = 1;
			while(true) {
				// cnt를 1씩 증가해 next가 N을 넘지 않는 곳까지 값을 뒤집는다.
				int next = cnt * position;
				if(next > N) {
					break;
				}
				if(arr[next] == 0) {
					arr[next] = 1;
				} else {
					arr[next] = 0;
				}
				cnt++;
			}
		} else {
			// 여자라면
			int cnt = 1;
			while(true) {
				// 왼쪽 오른쪽을 조사해 값이 같으면 cnt를 증가한다. 조건에 맞지 않는다면 선 증가를 했기에 break시 1씩 빼준다.
				int left = position - cnt;
				int right = position + cnt;
				if(left < 1 || right > N) {
					cnt--;
					break;
				}
				if(arr[left] == arr[right]) {
					cnt++;
				} else {
					cnt--;
					break;
				}
			}
			// 위치를 잡아 해당 범위내에 값을 뒤집는다.
			for(int i = position - cnt; i <= position + cnt; i++) {
				if(arr[i] == 0) {
					arr[i] = 1;
				} else {
					arr[i] = 0;
				}
			}
		}
	}
}
