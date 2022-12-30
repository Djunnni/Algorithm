import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 만들 수 없는 금액
 * 1sec, 128MB
 * @author djunnni
 * 
 * [접근 방법]
 * map에다가 숫자를 넣고 만들어질 수 있는 경우의 수를 저장해 result를 1씩 더해가며 더이상 넘어갈 수 없는 부분을 찾아 return
 * 메모리 초과 발생
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 주어진 동전의 개수
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[] numbers = new int[N]; 
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers); // 오름차순으로 정렬 시키기
		
		int result = 1; // 최소값 1
		int beforeResult = 1;
		Map<Integer,Boolean> map = new HashMap<>();
		
		
		for(int number : numbers) {
			Map<Integer, Boolean> tempMap = new HashMap(map);
			Iterator<Integer> iter = new HashSet<Integer>(tempMap.keySet()).iterator();
			if(iter.hasNext()) {
				while(iter.hasNext()) {
					tempMap.put(iter.next() + number, true);
				}
			} else {
				tempMap.put(number, true);
			}
			map.putAll(tempMap);
			while(map.getOrDefault(result, false)) {
				beforeResult = result;
				result++;
			}
			if(beforeResult == result) {
				break;
			}
		}
		
		System.out.println(result);
	}

}
/*
5
3 2 1 1 9
=> 8

3
3 5 7
=> 1
*/