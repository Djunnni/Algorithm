import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int[][] matrix = new int[1001][1001];
    static int N;
    static boolean[] visit = new boolean[1001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 점 개수
        int M = sc.nextInt(); // 간선 개수
        int V = sc.nextInt(); // 시작점

        for(int i = 0; i < M; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            matrix[src][dest] = matrix[dest][src] = 1;
        }
        dfs(V);
        System.out.println();
        visit = new boolean[1001];
        bfs(V);
    }
    public static void dfs(int start) {
       visit[start] = true;
       System.out.print(start + " ");
       for(int i = 0; i < N + 1; i++) {
           if(matrix[start][i] == 1 && !visit[i]) {
               dfs(i);
           }
       }
    }
    public static void bfs(int start) {
        ArrayList<Integer> queue = new ArrayList<>();
        queue.add(start);
        visit[start] = true;
        System.out.print(start);
        while(!queue.isEmpty()) {
            int v = queue.remove(0);
            for(int i = 0; i < N + 1; i++) {
                if(matrix[v][i] == 1 && !visit[i]) {
                    queue.add(i);
                    visit[i] = true;
                    System.out.print(" " + i);
                }
            }
        }
    }
}
