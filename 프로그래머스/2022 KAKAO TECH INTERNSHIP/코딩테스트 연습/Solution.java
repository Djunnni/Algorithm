import java.util.*;

/**
 * dp를 통해 문제를 접근한다.
 * i,j 번째는 도달하는데 걸리는 최소의 시간
 * 
 * 풀이에는 알고력을 1 증가하기, 코딩력을 1증가하기, 문제를 하나 풀기 3개의 방법으로 나아갈 수 있다.
 * 
 */
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        // 최대의 알고력과 코딩력을 구한다.
        int maxAlp = -1;
        int maxCop = -1;
        for(int[] problem : problems) {
            if(maxAlp < problem[0]) {
                maxAlp = problem[0];
            }
            if(maxCop < problem[1]) {
                maxCop = problem[1];
            }
        }
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        if(maxAlp <= alp && maxCop <= cop) return 0;
        // [i][j]에까지 도달하는데 걸리는 최소 시간 
        int[][] dp = new int[maxAlp + 3][maxCop + 3];
        for(int [] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[alp][cop] = 0; // 시간을 0으로 초기화
        
        for(int _alp = alp; _alp <= maxAlp; _alp++) {
            for(int _cop = cop; _cop <= maxCop; _cop++) {
                dp[_alp][_cop + 1] = Math.min(dp[_alp][_cop + 1], dp[_alp][_cop] + 1);
                dp[_alp + 1][_cop] = Math.min(dp[_alp + 1][_cop], dp[_alp][_cop] + 1);
                
                for(int[] problem : problems) {
                    if(_alp < problem[0] || _cop < problem[1]) continue;
                    int nextAlp = _alp + problem[2];
                    int nextCop = _cop + problem[3];
                    int time = problem[4];
                    
                    nextAlp = Math.min(maxAlp, nextAlp);
                    nextCop = Math.min(maxCop, nextCop);
                    dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[_alp][_cop] + time);
                }
            }
        }
        
//         for(int[] row : dp) {
//             System.out.println(Arrays.toString(row));
//         }
        
        return dp[maxAlp][maxCop];
    }
}