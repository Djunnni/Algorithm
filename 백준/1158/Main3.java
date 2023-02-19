import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Josephus Problem
 * @author djunnni
 *
 */
public class JosephusProblem {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 병사 N
		int K = Integer.parseInt(st.nextToken()); // 떨어진 수 K
		
		List<Integer> list = new LinkedList<>();
		List<Integer> answer = new ArrayList<>(N + 1);
		
		for(int i = 1; i <= N; i++) {
			list.add(i); // i번에 해당하는 사람을 넣는다. 
		}
		
		int index = 0; // 인덱스 -1;
		while(list.size() > 0) { // 리스트가 빌 때까지 반복한다.
			index += (K - 1); // K번 째
			index %= list.size();
			
			answer.add(list.remove(index));
		}
		
		System.out.println(answer.toString().replace("[", "<").replace("]", ">"));
	}
}
