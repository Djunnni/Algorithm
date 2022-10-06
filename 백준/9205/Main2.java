import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * acmicpc.BJ_9205_맥주 마시면서 걸어가기
 * 1sec, 128MB
 * author djunnni
 * 
 * 풀이법 : BFS
 */
public class Main2 {
    static boolean success;
    static short home[], festival[], PER_BEER_IN_BOX = 20, n;
    static short[][] combinis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 테스트케이스
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            // 편의점의 개수 n (0<= n <= 100)
            n = Short.parseShort(br.readLine());
            StringTokenizer st = null;

            // 편의점 리스트
            combinis = new short[n][];
            // 상근이네
            st = new StringTokenizer(br.readLine(), " ");
            home = new short[] {
                    Short.parseShort(st.nextToken()),
                    Short.parseShort(st.nextToken())
            };
            // 편의점
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                combinis[i] = (new short[] {
                        Short.parseShort(st.nextToken()),
                        Short.parseShort(st.nextToken())
                });
            }

            st = new StringTokenizer(br.readLine(), " ");
            festival = new short[] {
                    Short.parseShort(st.nextToken()),
                    Short.parseShort(st.nextToken())
            };

            success = false; // 현재: 도착 못한 상태

            /**
             * 문제 풀이방식
             * 중간에 어디를 들리든지간 락페스티벌에 도착할 수 있는지 <= 목표
             * 상근이집 거리 ~ (편의점: 맥주리필) ~ 페스티벌
             */
            goToFestival();

            System.out.println(success ? "happy" : "sad");
        }
    }

    static void goToFestival() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { home[0], home[1] });
        boolean visited[] = new boolean[n];

        while (!queue.isEmpty()) {
            int[] point = queue.poll();

            int festivalDistance = getDistance(point[0], point[1], festival[0], festival[1]);
            // 현재갈수있는 거리로 fesitval까지 충분히 도달할 수 있다면 break;
            if (1000 >= festivalDistance) {
                success = true;
                break;
            } else {
                for (int i = 0; i < n; i++) {
                    if (visited[i])
                        continue;
                    short[] combini = combinis[i];
                    int meToFestivalDis = getDistance(point[0], point[1], combini[0], combini[1]);
                    if (1000 >= meToFestivalDis) {
                        visited[i] = true;
                        queue.add(new int[] { combini[0], combini[1] });
                    }
                }
            }
        }
    }

    static int getDistance(int r, int c, int r2, int c2) {
        return Math.abs(r2 - r) + Math.abs(c2 - c);
    }
}
