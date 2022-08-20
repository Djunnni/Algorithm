import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ_2457_공주님의_정원
 * 1초, 192MB
 * author djunnni
 */
public class Main {
    static class Flower implements Comparable<Flower> {
        int from;
        int to;
        public Flower(int from, int to) {
            this.from = from;
            this.to = to;
        }
        @Override
        public int compareTo(Flower f) {
            if(this.from == f.from) {
                return Integer.compare(f.to, this.to);
            }
            return Integer.compare(this.from, f.from);
        }
        @Override
        public String toString() {
            return from + ", " + to;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());
        Flower flowers[] = new Flower[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            // 꽃은 from <= date < to의 기간을 가지고 있다.
            flowers[i] = new Flower(parseDate(
                    Integer.parseInt(st.nextToken()), // start
                    Integer.parseInt(st.nextToken()) // start
            ), parseDate(
                    Integer.parseInt(st.nextToken()), // end
                    Integer.parseInt(st.nextToken()) // end
            ));
        }
        Arrays.sort(flowers);

//        for(Flower f : flowers) {
//            System.out.println(f);
//        }
//        System.out.println("----------------------");

        int count = 0;
        int start = parseDate(3, 1);
        int end = parseDate(12, 1);
        int index = 0;
        int max = 0;

        while(start < end) {
            boolean isFinish = false;
            for(int i = index; i < N; i++) {
                if(flowers[i].from > start) {
                    break;
                }
                if(max < flowers[i].to) {
                    isFinish = true;
                    max = flowers[i].to;
                    index += 1;
                }
            }

            if(isFinish) {
                start = max;
                count++;
            } else {
                break;
            }
        }
        if(max < end) {
            System.out.println(0);
        } else {
            System.out.println(count);
        }
    }
    public static int parseDate(int month, int day) {
      int sum = 0;
      for(int m = 1; m < month; m++) {
          sum += getMonthDay(m);
      }
      return sum + day;
    };
    public static int getMonthDay(int month) {
        int day = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = 30;
                break;
            case 2:
                day = 28;
                break;
        }
        return day;
    }
}
