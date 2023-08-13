import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author djunnni
 * 한 줄로 그어진 직선의 길이를 구하는 문제
 * 
 * 1. 정렬을 하되 x축이 가장 작은 값이 만약 같은 값이 나오면 y가 큰 순서대로 정렬
 * ex) input: 0 2, 0 5, 0 7 -> output: 0 7, 0 5, 0 2
 * 2. 정렬된 데이터를 하나씩 읽어가면서 x, y축을 증가시킴 만약 y와 x를 모두 벗어나는 다음지점이라면 x,y 업데이트 그 외엔 각자 업데이트
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.add(new int[] { x, y });
        }

        Collections.sort(list, (a, b) -> {
           if(a[0] == b[0]) {
               return Integer.compare(b[1], a[1]);
           } else {
               return Integer.compare(a[0], b[0]);
           }
        });

        int answer = 0;
        int x = list.get(0)[0];
        int y = list.get(0)[1];
        answer += y - x; // 첫번째 거리는 반영해두기
        for(int i = 1, size = list.size(); i < size; i++) {
            int[] distance = list.get(i);
            if(distance[1] > y) {
                if(distance[0] >= x && distance[0] <= y) {
                    answer += distance[1] - y;
                    y = distance[1];
                } else if(distance[0] >= x && distance[0] > y) {
                  answer += distance[1] - distance[0];
                  x = distance[0];
                  y = distance[1];
                } else {
                    answer += distance[1] - y + x - distance[0];
                    y = distance[1];
                    x = distance[0];
                }
            }
        }

        System.out.println(answer);
    }
}