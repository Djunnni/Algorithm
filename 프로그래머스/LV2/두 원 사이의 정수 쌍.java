/**
 * x: 0 ~ r2까지 
 * 문제에 있어 이해하고 있어야 하는 부분
 * 1. 숫자 100만의 인풋이 들어올 수 있다.
 * 2. 숫자 비교를 할 줄 아는 지?
 * 3. 사분면을 계산할 줄 아는 지?
 * 
 * 오래 걸린 이유: 숫자 범위에 따른 대소 비교에서 문제가 있었음
 */
class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        if(r1 == r2) {
            return answer;
        }
        
        long maxHeight = r2; // r2가 최대의 높이다.
        long minHeight = r1;
        for(long x = 1; x < r2; x++) {
            long t_maxHeight = maxHeight;
            long t_minHeight = minHeight;
            while(t_maxHeight > 0) {
                int compared = Double.compare(distance(x, t_maxHeight), Math.pow(r2, 2));
                // spot에서 거리가 r2보다 크다면 높이를 줄여서 r2 범위에 들어오도록 조치
                if(compared > 0) {
                    t_maxHeight--;
                } else {
                    break;
                }
            }
            while(t_minHeight > 0) {
                int compared = Double.compare(Math.pow(r1, 2), distance(x, t_minHeight));
                // spot에서의 거리가 r1보다 크다면 높이를 낮춰서 r1에 들어오도록 조치
                if(compared > 0) {
                    t_minHeight++;
                    break;
                } else {
                    t_minHeight--;
                    
                }
            }
            // System.out.println(x + " : "+ t_maxHeight + " | " + t_minHeight);
            if(t_minHeight == 0) {
                answer += t_maxHeight * 4 + 2;
            } else {
                answer += (t_maxHeight - t_minHeight + 1) * 4;
            }
            maxHeight = t_maxHeight;
            minHeight = t_minHeight;
        }
        
        answer += (r2 - r1 + 1) * 2; // 축의 개수
        answer += 2;
        
        
        return answer;
    }
    public double distance(long x, long y) {
        return Math.pow(x,2) + Math.pow(y, 2);
    }
}