import java.util.*;
import java.io.*;

/**
 * BJ_4386
 * 별자리
 * @author djunnni
 * 
 */
public class Main {
    public static class Star {
        double x, y;

        public Star(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public static double getDistance(Star a, Star b) {
            return Math.sqrt(
                Math.pow(Math.abs(a.x - b.x), 2) +
                Math.pow(Math.abs(a.y - b.y), 2)  
            );
        } 
    }
    public static class Edge implements Comparable<Edge> {
        int start, end;
        double distance;

        public Edge(int start, int end, double distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        public int compareTo(Edge e) {
            return Double.compare(this.distance, e.distance);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 별의 개수
        
        Star[] stars = new Star[N]; // 별의 배열
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars[i] = new Star(x, y);
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        // 별들간의 거리 구해두기
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                double distance = Star.getDistance(stars[i], stars[j]);

                queue.add(new Edge(i, j, distance));
                queue.add(new Edge(j, i, distance));
            }
        }

        int parents[] = new int[N];
        for(int i = 0; i < N; i++) {
            parents[i] = i;
        }

        int count = 0;
        double answer = 0;
        while(!queue.isEmpty()) {
            Edge e = queue.poll();
            
            if(find(parents, e.start) != find(parents, e.end)) {
                answer += e.distance;
                count++;        
                union(parents, e.start, e.end);
            }

            if(count == N - 1) break;
        }

        System.out.println(String.format("%.2f", answer));
    }
    public static int find(int[] parents, int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents, parents[a]);
    }
    public static void union(int[] parents, int a, int b) {
        a = find(parents, a);
        b = find(parents, b);

        if(a < b) { // 작은값으로 연결고리 맺기
            parents[b] = a; 
        } else {
            parents[a] = b;
        }
    }
}
