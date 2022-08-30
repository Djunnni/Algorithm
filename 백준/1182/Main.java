import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_1182_부분수열의_합
 * author djunnni
 */
public class Main {
    public static int N, S, arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 확인
//        System.out.println(Arrays.toString(arr));


        int answer = select(arr, 0, 0, 0);

        System.out.println(answer);
    }
    public static int select(int[] arr, int cnt, int count, int sum) {
        if(cnt == arr.length) {
            if(count != 0 && sum == S) {
                return 1;
            }
            return 0;
        }

        int s = 0;
        s += select(arr, cnt + 1, count + 1, sum + arr[cnt]);
        s += select(arr, cnt + 1, count, sum);

        return s;
    }
}
