import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

// 뒤에서 부터 자전거를 건너는 방식을 창안. N으로 계산 가능
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine()); // 도로의 길이 N

        int arr[] = new int[N];
        int answer[] = new int[N];

        StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");

        int i = 0;
        while(st.hasMoreTokens()) {
            arr[i++] = Integer.parseInt(st.nextToken());
        }

        i = N - 1; // 마지막 계단의 위치로 이동하기
        for(; i >= 0; i--) {
            if(i == N - 1) {
                answer[i] = 1;
                continue;
            }

            if(arr[i] == 0) { // 높이가 없었을 때,
                answer[i] = answer[i + 1] + 1;
            } else { // 높이가 있었을 때,
                int nextStep = i + arr[i] + 1;
                if(nextStep < N) {
                    answer[i] = answer[nextStep] + 1;
                } else {
                    answer[i] = 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answer[0]);

        for(i = 1; i < N; i++) {
            sb.append(" ").append(answer[i]);
        }

        System.out.println(sb);
    }
}
