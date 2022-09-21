import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ_1049_기타줄
 * 
 * 푸는 방식
 * 
 * 6개로 구성된 세트, 낱개 가격을 구매해야할 N개와 비교를 한다.
 * 이 떄, 가격을 유심히 보는데
 * 
 * 1. 최소의 세트 가격에 6개입 구매 + 최소의 낱개 가격에 나머지 개수 구매
 * 2. 최소의 세트 가격 * 6개 구매 (낱개가 0 일 떄,)
 * 3. 최소의 낱개 가격 * N개 구매 (세트보다 가격이 쌀 때,)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int packagePrices[] = new int[M];
        int piecePrices[] = new int[M];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            packagePrices[m] = Integer.parseInt(st.nextToken());
            piecePrices[m] = Integer.parseInt(st.nextToken());
        }

        // 싼 가격순으로 패키지, 낱개 정렬
        Arrays.sort(packagePrices);
        Arrays.sort(piecePrices);

        int das = N / 6;
        int remain = N % 6;

        int answer = das * packagePrices[0] + remain * piecePrices[0];
        if (answer > N * piecePrices[0]) {
            answer = N * piecePrices[0];
            remain = 0;
        }
        if (remain > 0) {
            if (remain * piecePrices[0] >= packagePrices[0]) {
                answer = answer - remain * piecePrices[0] + packagePrices[0];
            }
        }

        System.out.println(answer);
    }
}
