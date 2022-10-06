import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * acmicpc.BJ_9205_맥주마시면서_걸어가기
 * author djunnni
 * 
 * 풀이법 : DFS
 */
public class Main {
    static boolean visited[], success;
    static short beer, sangguen[], festival[], combini[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        short T = Short.parseShort(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            short combiniCount = Short.parseShort(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            // 상근이네 좌표
            sangguen = new short[] {
                    Short.parseShort(st.nextToken()),
                    Short.parseShort(st.nextToken()),
            };
            // 편의점 좌표
            combini = new short[combiniCount][2];
            visited = new boolean[combiniCount];

            for (int i = 0; i < combiniCount; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                combini[i] = new short[] {
                        Short.parseShort(st.nextToken()),
                        Short.parseShort(st.nextToken()),
                };
            }

            st = new StringTokenizer(br.readLine(), " ");
            // 페스티벌 좌표
            festival = new short[] {
                    Short.parseShort(st.nextToken()),
                    Short.parseShort(st.nextToken()),
            };

            beer = 1000;
            success = false;

            DFS(sangguen[1], sangguen[0]);

            if (success) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }
        }
    }

    public static void DFS(short r, short c) {
        if (r == festival[1] && c == festival[0]) {
            success = true;
            return;
        }

        int distance = getDistance(r, c, festival[1], festival[0]);
        if (distance <= beer) {
            DFS(festival[1], festival[0]);
            return;
        }

        for (int i = 0; i < combini.length && !success; i++) {
            distance = getDistance(r, c, combini[i][1], combini[i][0]);
            if (visited[i] || distance > beer)
                continue;

            // System.out.println( combini[i][1] + " " + combini[i][0]);
            visited[i] = true;
            DFS(combini[i][1], combini[i][0]);
        }
    }

    public static int getDistance(short r, short c, short r2, short c2) {
        return Math.abs(r2 - r) + Math.abs(c2 - c);
    }
}
