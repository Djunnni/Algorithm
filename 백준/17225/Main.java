import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac
 * 17225 세훈이의 선물가게
 *
 * 손님이 구매할 물건 선택 X, 세훈이의 취향으로 랜덤하게 준비된 선물중 몇 개 구매할 지, 파란색/빨간색 중 포장할 색만 결정가능(2)
 * 파란색 포장 -> 상민(a초), 빨간색 포장 -> 지수(b초)
 * 미리 선물을 가져오거나 포장하는 일은 X, 동시에 선물을 가져올 때는 상민이가 먼저 가져간다.
 *
 * 누가 선물을 포장했는지 파악하려고 함.
 * 방문한 손님 수, 손님이 주문한 시각, 선택한 포장지, 포장받을 선물의 개수가 주어짐.
 *
 * @author djunnni
 */
public class Main {
    // 데이터용 클래스 따로 두기
    public static class Data {
        int t;
        char color;
        int count;

        public Data(int t, char color, int count) {
            this.t = t;
            this.color = color;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine(), " ");

        int A = Integer.parseInt(token.nextToken());
        int B = Integer.parseInt(token.nextToken());
        int N = Integer.parseInt(token.nextToken()); // 어제 방문한 손님 수


        // 상민과 지수의 업무 처리한 리스트를 저장한다.
        ArrayList<Integer> sangmin = new ArrayList<>();
        ArrayList<Integer> jisu = new ArrayList<>();

        Queue<Data> aQueue = new LinkedList<>(); // 상민
        Queue<Data> bQueue = new LinkedList<>(); // 자수

        // 큐에 미리 업무를 넣어둔다.
        for(int i = 0; i < N; i++) {
            token = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(token.nextToken()); // 가게 오픈한지 t초 후에 손님이 주문
            char color = token.nextToken().charAt(0); // B | R
            int boxCount = Integer.parseInt(token.nextToken()); // 주문한 선물의 개수
            Data d = new Data(t, color, boxCount);
            if(color == 'B') {
                aQueue.add(d);
            } else {
                bQueue.add(d);
            }
        }

        int no = 0;
        Data sangminJob = aQueue.poll();
        Data jisuJob = bQueue.poll();

        // 현재 시각
        int time = 0;
        do {
            // 상민의 작업 시간이 time과 같다면
            if(sangminJob != null && sangminJob.t == time) {
                // 카운트가 1보다 크다면
                if(sangminJob.count > 0) {
                    // 작업하는데 걸리는 시간이 0이면 한번에 처리하며 아닐 경우엔 1개씩 차감한다.
                    if(A == 0) {
                        for(int i = sangminJob.count; i > 0; i--) {
                            sangminJob.count--;
                            sangminJob.t += A;
                            sangmin.add(++no);
                        }
                    } else {
                        sangminJob.count--;
                        sangminJob.t += A;
                        sangmin.add(++no);
                    }
                }
                // 빼고난 뒤 남은 개수가 0개면 다음 작업을 가져오는데
                // 이전 시간을 가지고 온다. 만약 이전 마무리 될 시간보다 작업 시작시간이 전이면 해당시간으로 변경해준다.
                if(sangminJob.count == 0) {
                    int t = sangminJob.t;
                    sangminJob = aQueue.poll();
                    if(sangminJob != null && sangminJob.t < t) {
                        sangminJob.t = t;
                    }
                }
            }

            // 지수는 상민과 같은 방법으로 반복한다.
            if(jisuJob != null && jisuJob.t == time) {
                if(jisuJob.count > 0) {
                    if(B == 0) {
                        for(int i = jisuJob.count; i > 0; i--) {
                            jisuJob.count--;
                            jisuJob.t += B;
                            jisu.add(++no);
                        }
                    } else {
                        jisuJob.count--;
                        jisuJob.t += B;
                        jisu.add(++no);
                    }
                }
                if(jisuJob.count == 0) {
                    int t = jisuJob.t;
                    jisuJob = bQueue.poll();
                    if(jisuJob != null && jisuJob.t < t) {
                        jisuJob.t = t;
                    }
                }
            }
            time++;
        } while(!(aQueue.isEmpty() && bQueue.isEmpty() && jisuJob == null && sangminJob == null));


        System.out.println(sangmin.size());
        for(int s : sangmin) System.out.print(s + " ");
        System.out.println();
        System.out.println(jisu.size());
        for(int s : jisu) System.out.print(s + " ");
    }
}
