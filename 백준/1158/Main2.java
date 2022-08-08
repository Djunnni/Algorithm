import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * BJ_1158_요세푸스문제
 * 
 * 2초, 256MB
 * 
 * 1번부터 N번까지 N명의 사람이 원을 이루며 앉아있다. 순서대로 K번째 사람을 제거한다.
 * 한사람이 제거되면 남은 사람들로 다시 원을 따라 진행한다. 모두 제거될때까지 반복한다.
 * 
 * @author djunnni
 *
 */
public class Main2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<>();
		List<Integer> list = new LinkedList<>();
		// 사람 추가하기
		for(int i = 1; i <= N; i++) {
			queue.add(i);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		int cnt = 1;
		while(true) {
			int v = queue.poll();
			if(cnt % K == 0) {
				list.add(v);
			} else {
				queue.add(v);
			}
			if(queue.isEmpty()) {
				break;
			}
			cnt++;
		}
		for(int i = 0; i < N - 1; i++) {
			sb.append(list.get(i)+ ", ");
		}
		sb.append(list.get(N-1) + ">");
		System.out.println(sb);
	}

}
