package chapter4_8;

import java.util.Scanner;

public class Solution4_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        long ans = dfs(0,n,false,false,false);
        System.out.println(ans);
    }
    static int dfs(long cur, int n, boolean cnt7, boolean cnt5, boolean cnt3){
        int count = 0;
        if(cur<=n && cur>0 && cnt7 && cnt5 && cnt3) count++;

        if(cur*10 + 3 <= n) count += dfs((cur*10 + 3), n, cnt7, cnt5, true);

        if(cur*10 + 5 <= n) count += dfs((cur*10 + 5), n, cnt7, true, cnt3);

        if(cur*10 + 7 <= n) count += dfs((cur*10 + 7), n, true, cnt5, cnt3);

        return count;
  }
}
