import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main3 {
    public static class Top {
        int spot;
        int height;

        Top(int spot, int height) {
            this.spot = spot;
            this.height = height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        Stack<Top> stack = new Stack<>();
        Top[] arr = new Top[N];
        int[] answer = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = new Top(i + 1, Integer.parseInt(st.nextToken()));
        }


        for(int i = N - 1; i >= 1; i--) {
            // i번째 높이가 i - 1 보다 크다면 해당 높이를 i로 지정한다.
            if(arr[i - 1].height >= arr[i].height) {
                answer[i] = arr[i - 1].spot;
                while(!stack.isEmpty() && arr[i - 1].height >= stack.peek().height) {
                    Top tmp = stack.pop();
                    answer[tmp.spot - 1] = arr[i - 1].spot;
                }
            } else {
                // i 번째 높이가 i - 1보다 크다면 높이를 하위에서 판단할거야.
                // 이경우 스택에 미리 넣어두기
                stack.add(arr[i]);
            }
        }

        // 스택이 비어있지 않다면 통과할 수 있는 높이가 없으니까 0으로 변경
        while(!stack.isEmpty()) {
            Top x = stack.pop();
            answer[x.spot - 1] = 0;
        }

        StringBuilder sb = new StringBuilder();
        for(int i : answer) {
            sb.append(i + " ");
        }
        System.out.println(sb);
    }
}
