import java.util.*;
import java.io.*;

/**
 * @author djunnni
 * 
 */
public class Main {
    static class Knight {
        int no, r, c, h, w, k, attacked;

        Knight(int no, int r, int c, int h, int w, int k) {
            this.no = no;
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
            this.attacked = 0;
        }

        @Override
        public String toString() {
            return "Knight{" +
                    "no=" + no +
                    ", r=" + r +
                    ", c=" + c +
                    ", h=" + h +
                    ", w=" + w +
                    ", k=" + k +
                    ", attacked=" + attacked +
                    '}';
        }
    }
    static Map<Integer, Knight> knights;
    static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 상우하좌
    static int L, N, Q, map[][], areaMap[][]; // L 체스판, N 기사 수, Q: 명령어
    static List<int[]> commands;
    public static void main(String[] args) throws IOException {
        initialize();
        for(int[] command : commands) {
            // printMap(areaMap);
            play(command);
        }
        answer();
    }
    public static void answer() {
        int ans = 0;
        for(Knight k : knights.values()) {
            ans += k.attacked;
        }
        System.out.println(ans);
    }
    public static void play(int[] command) {
        Knight knight = knights.getOrDefault(command[0], null);
        if(knight == null) {
            return;
        }
        List<Integer> stack = findNextknight(knight, command[1]);
        // System.out.println(knight);
        // System.out.println(Arrays.toString(command));
        // System.out.println(stack);
        if(stack == null) { // 옆으로 진행을 할 수 없는 경우에 건너 뜁니다.
            return;
        }
        for(int knightNo : stack) {
            Knight nextKnight = knights.get(knightNo);
            nextKnight.r += d[command[1]][0];
            nextKnight.c += d[command[1]][1];
            knights.put(nextKnight.no, nextKnight);
        }
        int[][] nextMap = new int[L + 1][L + 1];

        Set<Integer> keys = new HashSet<>(knights.keySet());
        int current = stack.remove(0); // 시작지점이 자기자신이므로
        keys.remove(current);
        drawKnightToArea(nextMap, knights.get(current));
        for(int knightNo : stack) {
            keys.remove(knightNo);
            Knight nextKnight = knights.get(knightNo);
            int cnt = meetBomb(nextKnight);
            nextKnight.attacked += cnt;
            if(nextKnight.attacked >= nextKnight.k) {
                knights.remove(knightNo);

                continue;
            }
            drawKnightToArea(nextMap, nextKnight);
            knights.put(knightNo, nextKnight);
        }
        for(int knightNo : keys) {
            drawKnightToArea(nextMap, knights.get(knightNo));
        }
        copyOfMap(areaMap, nextMap);
    }
    public static void copyOfMap(int[][] to, int[][] from) {
        for(int i = 1; i <= L; i++) {
            for(int j = 1; j <= L; j++) {
                to[i][j] = from[i][j];
            }
        }
    }
    public static int meetBomb(Knight knight) {
        int fromY = knight.r;
        int fromX = knight.c;

        int cnt = 0;
        for(int i = fromY; i < fromY + knight.h; i++) {
            for(int j = fromX; j < fromX + knight.w; j++) {
                if(map[i][j] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public static boolean isOver(int r, int c) {
        return r <= 0 || c <= 0 || r > L || c > L;
    }
    public static boolean isWall(int r, int c) {
        return map[r][c] == 2;
    }
    public static List<Integer> findNextknight(Knight knight, int direction) {
        // 나이트의 방향에 맞게 인접한 부분에 이동이 가능한지 여부를 반환합니다.
        List<Integer> temp = new LinkedList<>();
        temp.add(knight.no); // 시작점

        // 근처 지점 찾기
        Set<Integer> nk = new HashSet<>();
        // 찾다가 끝지점이거나 벽이 있으면 return null;
        int fromY = knight.r + d[direction][0];
        int fromX = knight.c + d[direction][1];

        if(direction == 0) {
            for(int x = fromX; x < fromX + knight.w; x++) {
                if(isOver(fromY, x) || isWall(fromY, x)) {
                    return null;
                }
                if(areaMap[fromY][x] != 0) {
                    nk.add(areaMap[fromY][x]);
                }
            }
        } else if(direction == 1) {
            for(int y = fromY; y < fromY + knight.h; y++) {
                if(isOver(y, fromX + knight.w - 1) || isWall(y, fromX + knight.w - 1)) {
                    return null;
                }
                if(areaMap[y][fromX + knight.w - 1] != 0) {
                    nk.add(areaMap[y][fromX + knight.w - 1]);
                }
            }
        } else if(direction == 2) {
            for(int x = fromX; x < fromX + knight.w; x++) {
                if(isOver(fromY + knight.h - 1, x) || isWall(fromY + knight.h - 1, x)) {
                    return null;
                }
                if(areaMap[fromY + knight.h - 1][x] != 0) {
                    nk.add(areaMap[fromY + knight.h - 1][x]);
                }
            }
        } else if(direction == 3) {
            for(int y = fromY; y < fromY + knight.h; y++) {
                if(isOver(y, fromX) || isWall(y, fromX)) {
                    return null;
                }
                if(areaMap[y][fromX] != 0) {
                    nk.add(areaMap[y][fromX]);
                }
            }
        }
        for(int no : nk) {
            List<Integer> nextList = findNextknight(knights.get(no), direction);
            if(nextList == null) {
                return null;
            }
            temp.addAll(nextList);
        }
        return temp;
    }
    public static void printMap(int[][] map) {
        for(int[] row : map) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("===================");
    }
    public static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        commands = new ArrayList<>();

        areaMap = new int[L + 1][L + 1];
        map = new int[L + 1][L + 1];
        knights = new HashMap<>();

        for(int i = 1; i <= L; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= L; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            Knight knight = new Knight(i, r, c, h, w, k);
            knights.put(i, knight);
            drawKnightToArea(areaMap, knight);
        }

        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int knightNo = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            commands.add(new int[] { knightNo, direction });
        }
    }
    public static void drawKnightToArea(int[][] areaMap, Knight knight) {
        int fromY = knight.r;
        int fromX = knight.c;

        for(int i = fromY; i < fromY + knight.h; i++) {
            for(int j = fromX; j < fromX + knight.w; j++) {
                areaMap[i][j] = knight.no;
            }
        }
    }
}