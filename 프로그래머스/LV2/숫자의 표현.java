class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int sum[] = new int[10001]; // 10001개의 공간
        for(int i = 1; i <= 10000; i++) { // 10000개의 공간에 대한 sum 구해두기
            sum[i] = i + sum[i - 1];
        }
        
        for(int i = 0; i < 10001; i++) {
            for(int j = i; j < 10001; j++) {
                if((sum[j] - sum[i]) > n) break;
                if((sum[j] - sum[i]) == n) {
                    answer++;
                }
            }
        }
        return answer;
    }
}