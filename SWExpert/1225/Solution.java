import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * D3_1225_암호생성기
 * 
 * 제한시간 
 * java 20초 
 * 
 * 메모리
 * 힙, 정적메모리 : 256이내, 스택 1MB 이내
 * 
 * 주어진 조건에 따라 n개의 수를 처리하면 8자리의 암호를 생성할 수 있다.
 * 
 * - 8개의 숫자를 받는다.
 * - 첫번째 숫자를 1감소한 뒤, 맨뒤로 보낸다.
 * 다음 첫번째 수는 2 -> 그다음은 3 -> 4 -> 5 -> 감소시키며 한 사이클이라고 부른다.
 * 사이클의 크기는 5
 * 
 * 숫자가 감소할 때 0보다 작아지는 경우 0으로 유지, 프로그램은 종료된다.
 * 이때, 8자리가 암호가 된다.
 * 
 * @author djunnni
 *
 */
public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int t = sc.nextInt();
			LinkedList<Integer> queue = new LinkedList<>();
			
			for(int i = 0; i < 8; i++) {
				queue.add(sc.nextInt());
			}
			
			int count = 0;
			while(true) {
				int v = queue.poll();
                // 5마다 사이클이 도니까 % 5로 0,1,2,3,4 + 1이 되게 변경
				v -= ((count % 5) + 1);
				if(v <= 0) {
					queue.add(0);
					break;
				} else {
					queue.add(v);
				}
				
				count++;
			}
			System.out.print("#"+ test_case + " ");
			for(int x : queue) {
				System.out.print(x + " ");
			}
			System.out.println();
		}

	}

}
