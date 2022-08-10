import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BJ_1010_다리놓기
 * 
 * 0.5초, 128MB
 * 
 * 동쪽과 서쪽으로 나누는 큰 일직선 모양의 강이 흐르고있다.
 * 다리를 건설하려고 하는데 적합한 곳을 "사이트"라고 한다.
 * 
 * 강 주변을 조사해본 결과 강 서쪽에는 N개의 사이트가 있고
 * 동쪽에는 M개의 사이트가 있다. (N <= M)
 * 
 * 서쪽의 사이트와 동쪽의 사이트를 연결하는데 이 때, 한 사이트에는 최대 한 개의 다리만 가능
 * 다리를 최대한 많이 지으려고 하기 때문에 서쪽의 사이트 개수만큼 짓는다.
 * 다리 끼리는 겹쳐질 수 없을 때, 지을 수 있는 개수를 구하시오.
 * 
 * 입력
 * T 테스트 개수
 * N, M => 서쪽 동쪽 사이트 개수
 * 0 < N <= M < 30
 * 
 * 출력
 * 각 테스트 케이스에 대해 주어진 조건하에 다리를 지을 수 있는 경우의 수
 * 
 * 첫번째 접근(50% 시간초과)
 * 중복없는 조합임을 확인
 * 각 테스트케이스간 독립적인 조합을 검사 => 그러다보니 똑같은 요소들이 계속 나옴
 * 메모이제이션이 필요하다는 생각이 듦.
 * 조합으로 메모이제이션 구현
 * 통과
 * 
 * @author djunnni
 *
 */
public class Main {
	public static int N, M;
	public static int matrix[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		matrix = new int[30][30];
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			sb.append(combi(M, N) + "\n");
		}
		System.out.println(sb);
	}
	// mCn
	/**
	 * 1 2 3 4
	 * 1일 때, 1,2,3,4,5,6
	 * 2일 때, 2,3,4,5,6
	 * ...
	 * 단 1에서 6을 선택하면 2일때는 선택 X 
	 * 즉, 1이 3을 선택하면 2는 4부터 3은 5부터
	 * 중복 없는 조합
	 * 
	 */
	public static int combi(int m, int n) {
		if(m == n || n == 0) {
			return 1;
		}else if(matrix[m][n] != 0) {
			return matrix[m][n];
            // 메모이제이션 필요.
		} else {
			matrix[m][n] = combi(m - 1, n - 1) + combi(m - 1, n);
			return matrix[m][n];
		}
	}
}
