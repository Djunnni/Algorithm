package solved._1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author djunnni
 * PQ를 활용해 계산한다.
 * 자료구조를 이해하고 사용한다.
 * 	17140KB	168ms
 */
public class Main3 {
    public static class Route implements Comparable<Route> {
        int spot, cost;

        public Route(int end, int cost) {
            this.spot = end;
            this.cost = cost;
        }

        public int compareTo(Route r) {
            return Integer.compare(this.cost, r.cost);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // n명
        int M = Integer.parseInt(st.nextToken()); // m개의 단방향
        int X = Integer.parseInt(st.nextToken()); // 도착점

        Map<Integer, List<Route>> map = new HashMap<>();
        Map<Integer, List<Route>> reMap = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
            reMap.put(i, new ArrayList<>());
        }
        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.get(start).add(new Route(end, cost));
            reMap.get(end).add(new Route(start, cost));
        }
        
        int[] distance = new int[N + 1];
        dijkstra(map, distance, X, N);
        
        int[] reverse = new int[N + 1];
        dijkstra(reMap, reverse, X, N);
        

        int answer = Integer.MIN_VALUE;
        for(int i = 1; i <= N; i++) {
            answer = Math.max(answer, distance[i] + reverse[i]);
        }

        System.out.println(answer);
    }

    private static void dijkstra(Map<Integer, List<Route>> map, int[] distance, int start, int People) {
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0; // 출발지점

        boolean visited[] = new boolean[People + 1];
        Queue<Route> queue = new PriorityQueue<Route>();
        queue.add(new Route(start , 0));

        while(!queue.isEmpty()) {
            Route route = queue.poll();
            if(visited[route.spot]) continue;
            visited[route.spot] = true;

            for(Route r : map.get(route.spot)) {
                if(!visited[r.spot] && distance[route.spot] + r.cost < distance[r.spot]) {
                    distance[r.spot] = distance[route.spot] + r.cost;
                    queue.add(new Route(r.spot, distance[r.spot]));
                }
            }
        }
    }
}
