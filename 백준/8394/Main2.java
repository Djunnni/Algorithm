import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// arr[i][0] = 악수하지 않을 때, arr[i][1] 악수할 때,
// 악수를 하고 있다면 그 옆은 악수 못함. 반대로 악수하지 않는다면 그 옆사람은 악수를 하거나 하지 않은 경우 모두 포함
public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int arr[][] = new int[N][2];

        arr[0][0] = 1;
        for(int i = 1; i < N; i++) {
            arr[i][1] = arr[i - 1][0];
            arr[i][0] = arr[i - 1][0] + arr[i - 1][1];
            arr[i][1] %= 10;
            arr[i][0] %= 10;
        }

        System.out.println((arr[N - 1][1] + arr[N - 1][0]) % 10);
    }
}
