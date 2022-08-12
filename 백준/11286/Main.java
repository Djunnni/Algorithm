import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * BJ_11286_절댓값_힙
 * 
 * 1초, 256MB
 * 자바 2초
 * 
 * 접근 방법
 * 
 * 절대값을 comparator로 구현해, 리턴합니다.
 * 절대값이 같을 경우, 더 작은 값이 위로 가도록
 * 절대값이 다른 경우, 더 작은 값이 위로 가도록
 * 
 * @author djunnni
 *
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				if(Integer.valueOf(Math.abs(o1)).equals(Integer.valueOf(Math.abs(o2)))) {
					return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
				}
				return Math.abs(o1) - Math.abs(o2);
			}
		});
		
		for(int n = 0; n < N; n++) {
			int input = Integer.parseInt(br.readLine());
			if(input == 0) {
				// 배열에서 절댓값이 가장 작은 값을 출력하고 그 값을 배열에서 제거한다.
				if(queue.isEmpty()) {
					System.out.println(0);
					continue;
				}
				System.out.println(queue.poll());
			} else {
				// 배열에 input 값을 넣는 연산
				queue.add(input);
			}
		}
	}
}
