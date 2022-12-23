import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ_5600_S3_품질검사
 * 1sec, 128MB
 * @author djunnni
 * 
 * 정상 + 정상 + 정상 => 정상
 * 정상 + 정상 + x => 비정상 / x 고장상태
 * 정상 + 고장 + x => 비정상 / x 모름
 * 고장 + 고장 + x => 비정상 / x 모름
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int a = Integer.parseInt(st.nextToken()); // 전원의 개수
		int b = Integer.parseInt(st.nextToken()); // 모터의 개수
		int c = Integer.parseInt(st.nextToken()); // 케이블의 개수
		
		int[] parts = new int[a + b + c + 1]; // 부품의 상태를 기입
		Arrays.fill(parts, -1); // 부품이 고장인지 정상인지 알 수 없는 상태로 업데이트
		
		int N = Integer.parseInt(br.readLine()); // 검사횟수 N
		
		int arrI[] = new int[N];
		int arrJ[] = new int[N];
		int arrK[] = new int[N];
		int arrR[] = new int[N];
		
		for(int test = 0; test < N; test++) {
			st = new StringTokenizer(br.readLine());
			
			arrI[test] = Integer.parseInt(st.nextToken()); // 전원번호
			arrJ[test] = Integer.parseInt(st.nextToken()); // 모터번호
			arrK[test] = Integer.parseInt(st.nextToken()); // 케이블번호
			arrR[test] = Integer.parseInt(st.nextToken()); // 검사결과
			
			if(arrR[test] == 1) {
				parts[arrI[test]] = 1; parts[arrJ[test]] = 1; parts[arrK[test]] = 1; // 모든 부품 정상
			}
		}
		
		inspect(N, parts, arrI, arrJ, arrK, arrR);
		
		// 결과 출력
		for(int i = 1; i < parts.length; i++) {
			System.out.println(parts[i]);
		}
	}
	public static void inspect(int N, int[] parts, int[] arrI, int[] arrJ, int[] arrK, int[] arrR) {
		for(int i = 0; i < N; i++) {
			if(arrR[i] == 0) {
				int idx = parts[arrI[i]] + parts[arrJ[i]] + parts[arrK[i]];
				if(idx == 1) {
					if(parts[arrI[i]] == -1) parts[arrI[i]] = 0;
					if(parts[arrJ[i]] == -1) parts[arrJ[i]] = 0;
					if(parts[arrK[i]] == -1) parts[arrK[i]] = 0;
				}
			}
		}
		for(int i = 1, length = parts.length; i < length; i++) {
			if(parts[i] == -1) parts[i] = 2;
		}
	}
}
