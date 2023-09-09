package solved._11779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

/**
 * 최소비용구하기_2
 * @author djunnni
 *
 */
public class Main {
    public static int[][] initialize(int size) {
        int temp[][] = new int[size + 1][size + 1];
        for(int[] row : temp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        return temp;
    }
    public static void drawMatrix(int M, int[][] matrix, BufferedReader br) throws IOException {
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            matrix[start][end] = Integer.min(matrix[start][end], cost);
        }
    }
    public static int[] getStartWithEnd(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int START = Integer.parseInt(st.nextToken());
        int END = Integer.parseInt(st.nextToken());

        return new int[] { START, END };
    }
    public static int[] intializeDistance(int N) {
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        return distance;
    }
    public static void printAnswer(int first, List<Integer> answer, StringBuilder sb) {
        String list = answer.toString();
        StringBuilder sbTemp = new StringBuilder(list.substring(1, list.length() - 1).replace(",", ""));
        sb.append(first)
                .append("\n")
                .append(answer.size())
                .append("\n")
                .append(sbTemp);

        System.out.println(sb);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine()); // 도시의 개수
        int M = Integer.parseInt(br.readLine()); // 버스의 개수

        int matrix[][] = initialize(N); // 배열 초기화

        drawMatrix(M, matrix, br); // matrix 경로 그리기

        int spot[] = getStartWithEnd(br); // 시작점 끝점 얻기

        // TODO -> A->B의 최소비용을 구하고 경로를 추출해라.

        int[] distance = intializeDistance(N);
        Queue<Integer> queue = new LinkedList<>();
        distance[spot[0]] = 0; // 시작점은 0으로 변경
        queue.add(spot[0]); // 시작점 추가하기

        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            map.put(i, new LinkedList<>());
        }

        while(!queue.isEmpty()) {
            int current = queue.poll(); // 현재 점 하나 빼내기
            for(int i = 1; i <= N; i++) {
                if(matrix[current][i] != Integer.MAX_VALUE && distance[current] + matrix[current][i] < distance[i]) {
                    distance[i] = distance[current] + matrix[current][i];
                    queue.add(i);
                    List<Integer> temp = new LinkedList<>(map.get(current));
                    temp.add(current);
                    map.put(i, temp);
                }
            }
        }
        List<Integer> answer = getAnswer(map, spot[1]);

        printAnswer(distance[spot[1]], answer, sb);
    }
    public static List<Integer> getAnswer(Map<Integer, List<Integer>> map, int end) {
        List<Integer> answer= new LinkedList<>();
        answer.addAll(map.get(end));
        answer.add(end);
        return answer;
    }
}
