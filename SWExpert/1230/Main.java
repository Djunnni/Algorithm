import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 암호문3
 * LinkedList를 활용해서 문제를 해결한다.
 * i번째 요소에 진입해 리스트를 삽입, 삭제, 추가를 진행함.
 * linkedList의 경우 삽입에 O(1), 추가에 O(1), 삭제에 O(N)[삭제 자체는 1, search과정은 N] 
 * @author djunnni
 *
 */
public class SW_1230 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder out = new StringBuilder();
		
		for(int testCase = 1; testCase <= 10; testCase++) {
			int N = Integer.parseInt(br.readLine()); // 원본 암호문의 길이 N
			
			List<Integer> chipers = new LinkedList<>(); // 원본 암호문
			
			st = new StringTokenizer(br.readLine(), " ");
			while(st.hasMoreTokens()) { // 띄어쓰기만큼 원본 암호문을 chipers로 반영
				chipers.add(Integer.parseInt(st.nextToken()));
			}
			
			int M = Integer.parseInt(br.readLine()); // 명령어의 개수 M
			st = new StringTokenizer(br.readLine(), " ");
		
			while(st.hasMoreTokens()) {
				String command = st.nextToken();
				int x, y;
				switch(command) {
					case "I":
						x = Integer.parseInt(st.nextToken()); // x위치
						y = Integer.parseInt(st.nextToken()); // y개
						
						List<Integer> tempChipers = new LinkedList<>();
						for(int i = 1; i <= y; i++) {
							tempChipers.add(Integer.parseInt(st.nextToken()));
						}
						chipers.addAll(x, tempChipers);
						break;
					case "D":
						x = Integer.parseInt(st.nextToken()); // x위치
						y = Integer.parseInt(st.nextToken()); // y개
						
						for(int i = 1; i <= y; i++) {
							chipers.remove(x);
						}
						break;
					case "A":
						y = Integer.parseInt(st.nextToken()); // y개
						for(int i = 1; i <= y; i++) {
							chipers.add(Integer.parseInt(st.nextToken()));
						}
						break;
				}
			}
			out.append("#" + testCase);
			for(int i = 0; i < 10; i++) {
				out.append(" ").append(chipers.remove(0));
			}
			out.append("\n");
		}
		System.out.print(out);
	}
}
