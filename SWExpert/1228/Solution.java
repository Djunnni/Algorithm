import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * D3_1228_암호문1
 * 
 * 시간 20초, 메모리 256MB
 * 
 * 0 ~ 99999 사이의 수를 나열하는 암호문
 * 
 * 처리기는 다음 기능을 제공
 * I(삽입) x, y, s : x위치 바로 다음에 y 개의 숫자를 삽입한다. s는 덧붙일 숫자들이다.  
 * @author djunnni
 *
 */
public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			String data[] = br.readLine().split(" ");
			
            // 링크드리스트 사용이유 -> 중간에 N개를 넣어도 O(1)로 삽입 가능
			List<String> list = new LinkedList<>(Arrays.asList(data));
			int commands = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < commands; i++) {
				String c = st.nextToken();
				int where = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				List<String> temp = new LinkedList<>();
				for(int j = 0; j < count; j++) {
					temp.add(st.nextToken());
				}
				list.addAll(where, temp);
			}
			
			System.out.print("#" + test_case);
			for(int i = 0; i < 10; i++) {
				System.out.print(" " + list.get(i));
			}
			System.out.println();
		}
	}
}
