import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 싸움땅_2022년_삼성_하반기_1번
 * @author 이동준
 */
public class Main {
    public static class Player {
        public static int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        static int fieldSize = 2; // 2 <= fieldSize <= 20로 20까지 이동이 가능하다.
        int number; // 플레이어 넘버
        int x, y;
        int energy;
        int gun;
        int direction;

        public Player(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
        }

        public int getEnergy() {
            return energy;
        }

        public int getGun() {
            return gun;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public void setEnergy(int energy) {
            this.energy = energy;
        }

        public void setGun(int gun) {
            this.gun = gun;
        }

        public void move() {
            int nx = x + d[direction][0];
            int ny = y + d[direction][1];
            if(Player.isOverField(nx, ny)) {  // 필드 범위를 나가면 방향을 정반대로
                direction = (direction + 2) % d.length;
                nx = x + d[direction][0];
                ny = y + d[direction][1];
            }
            this.x = nx;
            this.y = ny;
        }
        public void changeGun() {
            if(!guns[x][y].isEmpty()) { // [x][y]위치에 총이 있다면
                int dropGun = guns[x][y].peek();
                if(dropGun > gun) { // 새롭게 얻은 총이 지금 총보다 더 강하면
                    dropGun = guns[x][y].poll();
                    if(gun > 0) { // 이전총을 반납
                        guns[x][y].add(gun);
                    }
                    gun = dropGun; // 총기 변경
                }
            }
        }
        public void dropGun() {
            guns[x][y].add(gun);
            gun = 0; // 총 초기화
        }
        public void run() {
            int nx, ny;
            int nextDirection = direction;
            while(true) {
                nx = x + d[nextDirection][0];
                ny = y + d[nextDirection][1];
                if (Player.isOverField(nx, ny) || Player.isThereEnermy(nx, ny)) {  // 필드 범위를 나가면 방향을 정반대로
                    nextDirection = (nextDirection + 1) % d.length;
                    continue;
                }
                break;
            }
            this.direction = nextDirection;
            this.x = nx;
            this.y = ny;
            changeGun();
        }

        @Override
        public String toString() {
            return "Player{" +
                    "number=" + number +
                    ", x=" + x +
                    ", y=" + y +
                    ", energy=" + energy +
                    ", gun=" + gun +
                    ", direction=" + direction +
                    '}';
        }

        public static boolean isOverField(int x, int y) {
            if(x < 1 || y < 1 || x > fieldSize || y > fieldSize) { // 범위를 나가면 true
                return true;
            }
            return false; // 나가지 않으면 false
        }
        public static boolean isThereEnermy(int x, int y) {
            return curPlayer[x][y] >= 0;
        }
        public static void writeCurPlayer(Player player, boolean clear) {
            if(clear) {
                curPlayer[player.x][player.y] = -1;
                return;
            }
            curPlayer[player.x][player.y] = player.number; // x,y에 number 선수가 있다.
        }
        public static int checkEnermyNextSpot(Player player) {
            int nx = player.x + d[player.direction][0];
            int ny = player.y + d[player.direction][1];
            int nd = player.direction;
            if(Player.isOverField(nx, ny)) {  // 필드 범위를 나가면 방향을 정반대로
                nd = (nd + 2) % d.length;
                nx = player.x + d[nd][0];
                ny = player.y + d[nd][1];
            }
            return curPlayer[nx][ny];
        }
        public static int[] fight(Player p1, Player p2) {
            int p1Power = p1.energy + p1.gun;
            int p2Power = p2.energy + p2.gun;
            if(p1Power > p2Power) {
                return new int[] { p1.number, p1Power - p2Power };
            } else if(p1Power == p2Power) {
                if(p1.energy > p2.energy) { // 플레이어의 에너지가 같은 경우는 없을까? [Q]
                    return new int[] { p1.number, p1Power - p2Power };
                } else {
                    return new int[] { p2.number, p2Power - p1Power };
                }
            } else {
                return new int[] { p2.number, p2Power - p1Power };
            }
        }
    }
    public static PriorityQueue<Integer>[][] guns;
    public static int[][] curPlayer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // br = new BufferedReader(new FileReader("./src/input.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        /** 변수 세팅하기 */
        int n = Integer.parseInt(st.nextToken()); // 격자 크기
        int m = Integer.parseInt(st.nextToken()); // 플레이어 수
        int k = Integer.parseInt(st.nextToken()); // 라운드 수

        Player.fieldSize = n;
        guns = new PriorityQueue[n + 1][n + 1]; // 총에 대한 필드 정보
        curPlayer = new int[n + 1][n + 1]; // 현재 플레이어의 위치

        for(int i = 0; i < n + 1; i++) {
            for(int j = 0; j < n + 1; j++) {
                guns[j][i] = new PriorityQueue<>(Comparator.reverseOrder()); // 내림차순 정렬로 세팅
                curPlayer[i][j] = -1;
            }
        }

        /** 필드 세팅하기 */
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= n; j++) {
                int spot = Integer.parseInt(st.nextToken()); // 해당 위치에서의 총의 위력
                if(spot > 0) {
                    guns[i][j].add(spot); // 0 이면 총이 없다. 0보다 크면 총의 공격력
                }
            }
        }
        List<Player> playerList = new ArrayList<>(31); // 최대 크기 30이므로

        /** 플레이어 세팅하기 */
        for(int p = 0; p < m; p++) {
            st = new StringTokenizer(br.readLine(), " ");
            Player player = new Player(
                    p,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
            player.setDirection(Integer.parseInt(st.nextToken()));
            player.setEnergy(Integer.parseInt(st.nextToken()));
            playerList.add(player); // 플레이어 리스트에 추가

            Player.writeCurPlayer(player, false); // 플레이어의 위치를 기록
        }

        int[] scoreBoard = new int[31];

        for(int round = 1; round <= k; round++) { // 라운드 스타트!
            playerList.forEach((player) -> {
                int otherPlayerNumber = Player.checkEnermyNextSpot(player);
                if(otherPlayerNumber >= 0) { // 다음위치에 적이 있다면

                    Player.writeCurPlayer(player, true);
                    player.move();
                    Player other = playerList.get(otherPlayerNumber);
                    Player.writeCurPlayer(other, true);
                    Player.writeCurPlayer(player, true);
                    int winInfo[] = Player.fight(player, other);
                    scoreBoard[winInfo[0]] += winInfo[1]; // 점수반영

                    if(other.number == winInfo[0]) { // 적이 이겼다면
                        player.dropGun();
                        player.run();
                        other.changeGun();
                    } else { // 내가 이겼다면
                        other.dropGun();
                        other.run();
                        player.changeGun();
                    }
                    Player.writeCurPlayer(other, false);
                    Player.writeCurPlayer(player, false);
                } else { // 다음위치에 적이 없다면
                    Player.writeCurPlayer(player, true);
                    player.move();
                    player.changeGun();
                    Player.writeCurPlayer(player, false);
                }
            });
        }
        System.out.print(scoreBoard[0]);
        for(int i = 1; i < m; i++) {
            System.out.print(" " + scoreBoard[i]);
        }
    }
}