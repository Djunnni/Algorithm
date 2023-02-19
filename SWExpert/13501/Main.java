import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWExpert_13501_수열 편집
 * LinkedList를 활용한 삽입, 삭제, 수정을 사용
 * 수정시 O(1), 삽입 O(1), 삭제 O(1) - 검색과정에서 O(N) 수행
 * @author djunnni
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int TC = Integer.parseInt(br.readLine()); // 테스트케이스 수
		
		for(int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int N = Integer.parseInt(st.nextToken()); // 수열의 길이
			int M = Integer.parseInt(st.nextToken()); // 추가 횟수
			int L = Integer.parseInt(st.nextToken()); // 출력할 인덱스 번호
			
			st = new StringTokenizer(br.readLine(), " ");
			
			List<Integer> list = new LinkedList<Integer>();
			while(st.hasMoreTokens()) { // 수열만큼 숫자를 리스트에 넣기
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			for(int i = 1; i <= M; i++) { // 명령어 수행
				st = new StringTokenizer(br.readLine(), " ");
				
				String command = st.nextToken();
				int index, value;
				switch(command) {
					case "I":
						index = Integer.parseInt(st.nextToken());
						value = Integer.parseInt(st.nextToken());
						list.add(index, value);
						break;
					case "D":
						index = Integer.parseInt(st.nextToken());
						list.remove(index);
						break;
					case "C":
						index = Integer.parseInt(st.nextToken());
						value = Integer.parseInt(st.nextToken());
						list.set(index, value);
						break;
				}
			}
			int print;
			try {
				print = list.get(L);
			} catch(Exception e) {
				print = -1;
			}
			
			System.out.println("#" + tc + " " + print);
			
		}
	}
}
