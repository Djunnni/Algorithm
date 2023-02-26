import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * SW_1232_사칙연산
 * 
 * 10개 => 20초
 * 완전 이진트리 구조가 아님 => 연결정보를 알고있어야 함.
 * 
 * @author djunnni
 *
 */
public class Solution {
	static int N; // 정점의 개수
	static String[] arr; // 숫자 및 문자 데이터를 두는 곳
	static HashMap<Integer, int[]> relation; // 연산자의 관계를 나타내는 곳
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine()); // 1 <= N <= 1000
			arr = new String[N + 1];
			relation = new HashMap<Integer, int[]>();
			
			for(int i = 1; i <= N; i++) {
				String data[] = br.readLine().split(" ");
				if(data.length == 4) {
					int[] map = new int[2];
					map[0] = Integer.parseInt(data[2]);
					map[1] = Integer.parseInt(data[3]);
					relation.put(i, map);
					
					arr[i] = data[1];
				} else {
					arr[i] = data[1];
				}
			}
			sb.append((int) postOrder(arr, 1)).append("\n");
		}
		System.out.println(sb);
	}
	public static double postOrder(String[] arr, int index) {
		if(index > N) {
			return 0;
		}
		if(isAction(arr[index])) {
			int[] nodes = relation.get(index);
			return calculate(
					arr[index], 
					postOrder(arr,nodes[0]),
					postOrder(arr,nodes[1])
			);
		}
		return Double.parseDouble(arr[index]);
	}
	private static boolean isAction(String a) {
		switch(a) {
			case "+":
			case "-":
			case "/":
			case "*":
				return true;
			default: 
				return false;
		}
	}
	private static double calculate(String string, double left, double right) {
		switch(string) {
			case "+":
				return left + right;
			case "-":
				return left - right;
			case "*":
				return left * right;
			case "/":
				return left / right;
		}
		
		return Double.parseDouble(string);
	}
}
