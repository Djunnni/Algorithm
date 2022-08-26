import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BJ_3055_탈출
 * author djunnni
 */
public class Main {
    public static int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    public static int[] dc = {0, 0, -1, 1};
    public static int R, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 행 // 50보다 작거나 같음
        R = Integer.parseInt(st.nextToken());
        // 열 // 50보다 작거나 같음
        C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];

        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> floods = new LinkedList<>();

        for(int i = 0; i < R; i++) {
            String input = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                // 고슴도치의 위치라면 dochi에 저장한다.
                if(map[i][j] == 'S') {  queue.add(new int[] { i, j, 0 }); }
                if(map[i][j] == '*') { floods.add(new int[] { i, j }); }
            }
        }

//        for(char[] row : map) {
//            System.out.println(Arrays.toString(row));
//        }
//        System.out.println("=====================");

        int answer = BFS(map, queue, floods);

        if(answer == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(answer);
        }
    }
    public static int BFS(char[][] map, Queue<int[]> queue, Queue<int[]> floods) {
        /**
         * 매 분마다 고슴토치는 현재 칸과 인접한 4칸으로 이동이 가능하다.
         * 물도 매분마다 비어있는 칸으로 확장한다.
         *
         * 물이 있는 칸과 인접해 있으면 물이찬다.
         *
         * 고슴도치는 물이 찰 예정인 곳으로 이동할 수 없다. 즉, 다음시간이 물에 찰 예정인 칸으로 고슴도치는 이동 X
         */
        while(true) {
            // 물 확장
            int size = floods.size();
            while(size-- > 0) {
                int[] flood = floods.poll();

                for(int i = 0; i < dr.length; i++) {
                    int nr = flood[0] + dr[i];
                    int nc = flood[1] + dc[i];

                    if(!isOver(nr, nc) && map[nr][nc] == '.') {
                        map[nr][nc] = '*';
                        floods.add(new int[] { nr, nc });
                    }
                }
            }


            // 고슴도치의 이동
            size = queue.size();
            while(size-- > 0) {
                int[] dochi = queue.poll();

                for(int i = 0; i < dr.length; i++) {
                    int nr = dochi[0] + dr[i];
                    int nc = dochi[1] + dc[i];
                    // 칸을 벗어났는지, 돌을 만났는지, 물로 차있는지, 같은 지점을 한 번 더 방문하는 지
                    if(isOver(nr, nc) || map[nr][nc] == '*' ||  map[nr][nc] == 'X' || map[nr][nc] == 'S') {
                        continue;
                    }

                    if (map[nr][nc] == 'D') {
                        return dochi[2] + 1;
                    }
                    map[nr][nc] = 'S';
                    queue.add(new int[]{nr, nc, dochi[2] + 1});
                }
            }

            if(queue.isEmpty()) {
                return -1;
            }
        }
    }
    public static boolean isOver(int r, int c) {
        if(r < 0 || r >= R || c < 0 || c >= C) {
            return true;
        }
        return false;
    }
}
