import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BJ_10775
 * @author djunnni
 * 여행
 * 
 * g번 게이트를 쓸 수 없다면 g-1번 게이트로 이동할 건데 1칸 씩 따지게 되면 10^5 x 10^5
 * unionFind를 통해 g번게이트를 사용할 수 없다면 인접한 다른 게이트를 빠르게 찾아올 수 있다. 10^5 * log(10^5)
 * 
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine()); // G 게이트
        int P = Integer.parseInt(br.readLine()); // P개 비행기

        int gate[] = new int[G + 1]; // gate 만들어두기
        for(int i = 1; i <= G; i++) {
            gate[i] = i;
        }
        int count = 0;
        for(int i = 1; i <= P; i++) {
            int g = Integer.parseInt(br.readLine()); // g번째 게이트
            int use = find(gate, g);
            // g번째 게이트를 쓸 수 있나?
            // no ->
            if(use == 0) break;
            // yes ->
            union(gate, gate[use], gate[use] - 1);
            count++;
        }

        System.out.println(count);
    }
    public static int find(int[] gate, int g) {
        if(gate[g] == g) return g;
        return gate[g] = find(gate, gate[g]);
    }
    public static void union(int[] gate, int a, int b) {
        a = find(gate, a); // 5
        b = find(gate, b); // 0

        if(a > b)
            gate[a] = b; // gate[5] = 0;

    }
}
