import java.util.Scanner;

public class Main {
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertex = sc.nextInt();
        int edge = sc.nextInt();

        int arr[][] = new int[vertex + 1][vertex + 1];
        boolean visit[] = new boolean[vertex + 1];

        for(int i = 0; i < edge; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            arr[start][end] = arr[end][start] = 1;
        }

        System.out.println(dfs(arr, visit, 1));
    }
    static public int dfs(int[][]arr, boolean[] visit, int num) {
        visit[num] = true;
        int count = 0;
        for(int i = 1; i < arr[num].length; i++) {
            if(arr[num][i] == 1 && visit[i] == false) {
                count++;
                count += dfs(arr, visit, i);
            }
        }
        return count;
    }


}
