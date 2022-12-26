import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 숫자 카드 게임
 * 1sec, 128MB
 * @author djunnni
 * [힌트]
 * 어떻게 행에서 뽑던지 간에 그 다음 작은 수를 선택하는 걸로 보아 행별로 최소값을 찾아 정렬하면 최종값을 알 수 있다.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 1 <= N <= 100, 행 
		int M = Integer.parseInt(st.nextToken()); // 1 <= M <= 100, 열
		
		int max = Integer.MIN_VALUE;
		
		for(int i = 0; i < N; i++) {
			int min = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int value = Integer.parseInt(st.nextToken());
				if(min > value) {
					min = value;
				}
			}
			if(max < min) {
                max = min;
            }
		}
		System.out.println(max);
		
	}
}
/*
3 3
3 1 2
4 1 4
2 2 2
=> 2

2 4
7 3 1 8
3 3 3 4
=> 3
 */
