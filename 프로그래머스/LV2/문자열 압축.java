/**
 * 문자열 압축
 * 2시간 
 * 
 * 소요가 많이 걸린 이유, 문제를 잘못 이해해 앞에서부터 1,2,3,4,5,6 ... 길이로 잘라서 압축하는 줄 알았다.
 * 사실은 앞에서부터 n개 자리만큼 잘라서 압축하는 문제
 * 
 * 2,20번 케이스에서 오래걸린 이유는 9 ~> 10의 카운팅을 잘 못하던 문제 count를 애초에 1을 전제로 가니 해결
 */
class Solution {
    public int solution(String s) {
        int answer = s.length();
        for(int len = 1; len <= s.length() / 2; len++) { // 반 정도의 길이어야 잘랐을 때 줄지 않을까? [가정 1]
            String splitStr = split(s, 0, len);
            int summary = 0;
            int count = 1;
            
            int spot = len;
            while(true) {
                int nextSpot = spot + len > s.length() ? s.length() : spot + len;
                String next = split(s, spot, nextSpot);
               // System.out.println(splitStr + " @ " + next);
                if(splitStr.equals(next)) { // 같은 놈이면 count 증가
                    count++;
                } else { // 다른 경우면
                    if(count > 1) { // 이미 횟수가 있다면
                        summary += (String.valueOf(count).length() + len); // 결과에 숫자의 길이 + 문자의 길이 더해주기
                        count = 1; // 초기화
                    } else {
                        summary += len; // 결과에 문자의 길이 더해주기
                        count = 1; // 초기화
                    }
                }    
                if(nextSpot == s.length()) {
                    if(count > 1) {
                        summary += (String.valueOf(count).length() + next.length());
                        count = 1;
                    } else {
                        summary += next.length();
                    }
                    break;
                }
                
                spot += len;
                splitStr = next;
            }
            answer = Math.min(summary, answer);
        }
        
        return answer;
    }
    public String split(String s, int from, int to) {
        return s.substring(from, to);
    }
}