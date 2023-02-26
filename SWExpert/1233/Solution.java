import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 사칙연산 유효성 검사
 * 
 * 중위 순회의 연산 방법을 따랐다.
 * 왼쪽 자식 -> 자기 자신 -> 오른쪽 자식
 * 
 * 풀이법
 * index가 N보다 커진다면 이건 무조건 true [맞는 수식]
 * 왼쪽자식 재귀
 * arr[index]가 숫자인데 자식이 있는 상황 무조건 false [수식이 성립 못함]
 * 오른쪽자식 재귀
 * 
 * @author djunnni
 *
 */
public class SW_1233 {
	static int N;
	static char[] arr; 
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1TC당 1초의 시간
		// N은 최대 200
		for(int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc).append(" ");
			
			N = Integer.parseInt(br.readLine()); // 트리가 갖는 정점의 총 개수
			arr = new char[N + 1];
			
			for(int i = 1; i <= N; i++) {
				arr[i] = br.readLine().split(" ")[1].charAt(0);
			}
			
			// 중위순회 시, 리프 노드를 제외한 가운데는 문자 양 옆은 문자로 되야 유효성에 통과한다.
			sb.append(inOrder(arr, 1) ? "1" : "0").append("\n");
		}
		System.out.println(sb);
	}
	// 계산이 가능하면 1, 계산이 불가능하면 0
	public static boolean inOrder(char[] arr, int index) {
		boolean isAccess = true;
		if(index > N) {
			return isAccess;
		}
		
		isAccess &= inOrder(arr, index * 2);
		if(!isAccess) { return isAccess; }
		if(isNumber(arr[index]) && index * 2 < N) return false;
		isAccess &= inOrder(arr, index * 2 + 1);
		
		return isAccess;
	}
	public static boolean isNumber(char value) {
		int v = value - '0';
		if(v < 10 && v >= 0) {
			return true;
		}
		return false;
	}
}
