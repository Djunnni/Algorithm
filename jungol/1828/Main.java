import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 정올_1828_냉장고
 * 1s, 32MB
 *
 * author djunnni
 */
public class Main {
    public static class Rifrigator {
        int low;
        int high;

        public Rifrigator(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }
    public static class Chemical implements Comparable<Chemical> {
        int low;
        int high;

        public Chemical(int low, int high) {
            this.low = low;
            this.high = high;
        }

        @Override
        public int compareTo(Chemical c) {
            if(this.low == c.low) {
                return Integer.compare(this.high, c.high);
            }
            return Integer.compare(this.low, c.low);
        }

        @Override
        public String toString() {
            return "[" + low + ", " + high + "]";
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 화학물질 N개
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        // 인덱스 i는 C[i] 화학물질이다.
        // x[i] : 최저 보관온도, y[i] : 최고 보관온도
        // - 270 ~ 10000 온도범위
        Chemical c[] = new Chemical[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            c[i] = new Chemical(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(c);

        // 최소한 1대는 기록하자.
        Rifrigator rifrigator = null;
        int answer = 0;

        for(int i = 0; i < N; i++) {
            // 냉장고가 현재 설정이 0이면 새롭게 만들어준다.
            if(rifrigator == null) {
                rifrigator = new Rifrigator(c[i].low, c[i].high);
                answer++;
            } else {
                // 냉장고의 최고온도 보다 화학물질의 최저온도가 낮다면! 냉장고를 새롭게 바꿔야해.
                if(rifrigator.high < c[i].low) {
                    rifrigator.low = c[i].low;
                    rifrigator.high = c[i].high;
                    answer++;
                } else {
                    // 그렇지 않다면 냉장고의 최고온도는 항상 다음 화학물징의 높이에 따라 최솟값으로 넣어줘야 한다.
                    rifrigator.high = Math.min(rifrigator.high, c[i].high);
                }
            }
        }
        
        System.out.println(answer);
    }
}
