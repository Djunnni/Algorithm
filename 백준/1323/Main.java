import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int ans = 1;

        int len = String.valueOf(n).length(); // 숫자 길이
        long multi = 1;
        for(int i = 0; i < len; i++) multi *= 10;
        multi %= k; // 어짜피 10 ^ (n의 자리수) % k
        // 어마무시하게 커지면 multi가 int를 벗어나거나 long을 벗어날 수 있다.
        
        Set<Long> set = new HashSet<>();
        long proc = (n %= k); // 첫 나머지 저장해두기
        set.add(proc);

        while(true) {
            if(proc == 0) break;
            ans++;
            proc = (proc * multi + n) % k; // n' = (n * (10 ^ (n자리수)) + n) % k
            if(set.contains(proc)) {
                System.out.println(-1);
                return;
            }
            set.add(proc);
        }

        System.out.println(ans);
    }
}
