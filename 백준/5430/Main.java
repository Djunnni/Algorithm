import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BJ_5430_AC
 * 
 * 1sec, 256mb
 * 
 * 리스트를 사용하면 시간초과[접근, 삭제, 뒤집기]
 * 배열에서 start, end지점을 +1, -1씩 하면서 이동하도록 변경
 * StringBuilder로 출력을 최소화.
 * @author djunnni
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			String p = br.readLine(); // 수행함수 p
			
			int N = Integer.parseInt(br.readLine());
			int[] list = new int[N];
		//	List<Integer> list = new LinkedList<Integer>();
			
			String[] stringArray = br.readLine().replaceAll("\\[|\\]", "").split(",");
			if(N > 0) {
				for(int i = 0; i < stringArray.length; i++) {
					list[i] = Integer.parseInt(stringArray[i]);
				}
			}
			boolean isReverse = false;
			boolean hasError = false;
			int first = 0;
			int last = list.length - 1;
			for(int i = 0; i < p.length(); i++) {
				if(p.charAt(i) == 'R') {
					isReverse = !isReverse;
					// Collections.reverse(list);
				} else {
					if(last < first) {
						hasError = true;
						break;
					}
					if(isReverse) {
						last--;
					} else {
						first++;
					}
				}
			}
			
			if(hasError) {
				sb.append("error\n");
			} else {
				sb.append("[");
				if(isReverse) {
					for(int i = last; i >= first; i--) {
						if(i != last) {
							sb.append(",");
						}
						sb.append(list[i]);
					}
				} else {
					for(int i = first; i <= last; i++) {
						if(i != first) {
							sb.append(",");
						}
						sb.append(list[i]);
					}
				}
				sb.append("]\n");
			}
		}
		System.out.println(sb);
	}
}
