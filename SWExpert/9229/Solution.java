
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * D3_9229_한빈이와SportMart
 * 자바 2초, 메모리 256MB
 *  
 *  스팟마트에서 N개의 과자 봉지가 있고 각 과자는 aj그램의 무게를 가진다.
 *  한번에 용량이 큰 봉지를 고르고 싶지만 무게가 M이 넘으면 다닐 수 없다.
 *  
 *   정확히 2 봉지를 사야한다.
 *  
 * @author djunnni
 *
 */
public class Solution {
	public static int answer, N, M;
	public static int[] weights;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			answer = -1;
			weights = new int[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}
			
            // 선택을 하는데 현재 시작점, 선택 개수, 무개를 같이 받는다.
			pick(0, 0, 0);
			
			sb.append("#" + test_case + " " + answer+"\n");
		}
		System.out.print(sb);
	}
	public static void pick(int weight, int start, int count) {
        // 카운트가 2라면 무게를 확인해 업데이트
		if(count == 2) {
			if(weight <= M)
				answer = Math.max(weight, answer);
			return;
		} else if(start == N) {
            //카운트가 2가 아니고 꿑까지 탐색했다면 리턴
			return;
		}
		
        // 선택 했을 경우와 아닐경우로 재귀호출
		pick(weight + weights[start], start + 1, count + 1);
		pick(weight, start + 1, count);
	}
}
