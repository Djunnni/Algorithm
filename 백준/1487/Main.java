import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * BJ_1487_물건팔기
 * 2초, 128MB
 * 소요시간: 1시간
 * 
 * @author djunnni
 *
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine()); // 세준이의 물건을 구매할 의향이 있는 사람 수 (<= 50)
		
		int[] maxPrices = new int[N]; // N명의 최대 지불 금액
		int[] deliveryFees = new int[N]; // N명의 배송비
		
		for(int i = 0; i < N; i++) { // 첫번째 사람부터 N번째 사람까지 입력하는 최대지불 금액과 배송비
			st = new StringTokenizer(br.readLine(), " ");
			maxPrices[i] = Integer.parseInt(st.nextToken()); // 최대 지불 금액
			deliveryFees[i] = Integer.parseInt(st.nextToken()); // 배송비
		}
		
		// 입력값 출력해보기
		// System.out.println(Arrays.toString(maxPrices));
		// System.out.println(Arrays.toString(deliveryFees));
		
		int result = 0; // 최대 이익에 해당하는 가격
		int maxMargin = 0; // 최대 이익
		
		Map<Integer, Integer> table = new HashMap(); // 이익 테이블 
		
		for(int i = 0; i < N; i++) {
			int pickPrice = maxPrices[i]; // i번째 금액을 최대이익을 가져다주는 수로 가정해보자
			int sum = 0; // 얻을 수 있는 이익 
			
			for(int j = 0; j < N; j++) {
				if(pickPrice > maxPrices[j]) continue; // 선택된 금액보다 maxPrices가 작을 경우 continue
				int margin = pickPrice - deliveryFees[j]; // 얻을 수 있는 마진 구하기
				if(margin < 0) continue; // 마진이 음수라면 continue;
				else sum += margin; // 그렇지 않다면 sum으로 더하기
			}
			if(sum > table.getOrDefault(pickPrice, 0)) { // 기본값 0보다 크다면 sum을 table에 넣는다.
				table.put(pickPrice, sum);
			}
		}
		
		Arrays.sort(maxPrices); // 가격순으로 정렬
        // [의도] maxPrice가 오름차순으로 정렬이 되다보니 result에 반영 시 항상 작은값으로 들어가진다.
		
		for(int maxPrice : maxPrices) { // 가격마다 루프돌기
			int tempMargin = table.getOrDefault(maxPrice, 0); // 테이블에서 조회
			if(maxMargin < tempMargin) { // 마진이 더 크다면 반영
				result = maxPrice;
				maxMargin = tempMargin;
			}
		}
		
		// 결과 출력하기
		// 최대 이익을 만들어줄 가격을 출력한다. 이익이 여러개면 가장 낮은 가격으로, 또 아무런 이익이 없다면 0을 출력하라
		System.out.println(result);
	}

}

