import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac_6603_로또
 * 
 * 독일 로또는 {1, 2, ..., 49} 에서 6개의 수를 고른다.
 * 가장 유명한 전략이 k개의 수를 골라 집합 S를 만들고 그 수만을 가지고 번호를 선택하는 것
 * 
 * 주어진 데이터 : 집합 S와 K
 * 
 * 수를 고르는 모든 방법을 구해라.
 * 
 * @author djunnni
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int K = Integer.parseInt(st.nextToken());
			// K가 0이면 빠져나온다.
			if(K == 0) {
				break;
			}
			int arr[] = new int[K];
			// 배열안의 데이터는 오름차순으로 제공한다.
			for(int i = 0; i < K; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			boolean visited[] = new boolean[K];
			selectSix(arr, visited, 0, 0);
			System.out.println();
		}
	}
	public static void selectSix(int[] arr, boolean visited[], int spot, int count) {
		if(spot == arr.length) {
			if(count == 6) {
				for(int i = 0; i < visited.length; i++) {
					if(visited[i]) {
						System.out.print(arr[i] + " ");
					}
				}
				System.out.println();
			}
			return;
		}
		
		// 선택했을 때의 루프
		visited[spot] = true; 
		selectSix(arr, visited, spot + 1, count + 1);
		
		// 선택하지 않았을 때의 루프
		visited[spot] = false; 
		selectSix(arr, visited, spot + 1, count);
	}

}
