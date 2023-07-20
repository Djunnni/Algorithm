import java.util.*;

/**
 * @author djunnni
 * Integer.toString으로 진법 교환이 가능하다.
 * StringBuilder를 사용하는게 성능상에 엄청나다.
 */
class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        
        int i = 0;
        while(sb.length() <= m * t) {
            sb.append(Integer.toString(i++, n).toUpperCase());
        }
        
        String value = sb.toString();
        i = 0;
        while(answer.length() < t) {
            answer.append(value.charAt((m * i++) + p - 1));
        }
        
        return answer.toString();
    }
}
/**
 * 
테스트 1 〉	통과 (0.06ms, 75.6MB)
테스트 2 〉	통과 (0.08ms, 76.5MB)
테스트 3 〉	통과 (0.11ms, 80MB)
테스트 4 〉	통과 (0.08ms, 70.1MB)
테스트 5 〉	통과 (0.18ms, 79.1MB)
테스트 6 〉	통과 (0.28ms, 66.6MB)
테스트 7 〉	통과 (0.18ms, 75.6MB)
테스트 8 〉	통과 (0.36ms, 74.7MB)
테스트 9 〉	통과 (0.31ms, 85.7MB)
테스트 10 〉	통과 (0.28ms, 73.2MB)
테스트 11 〉	통과 (0.64ms, 75MB)
테스트 12 〉	통과 (0.63ms, 78.1MB)
테스트 13 〉	통과 (0.42ms, 72.7MB)
테스트 14 〉	통과 (12.58ms, 89.7MB)
테스트 15 〉	통과 (10.84ms, 81.1MB)
테스트 16 〉	통과 (8.91ms, 82MB)
테스트 17 〉	통과 (1.41ms, 76MB)
테스트 18 〉	통과 (1.60ms, 73.1MB)
테스트 19 〉	통과 (0.85ms, 78.6MB)
테스트 20 〉	통과 (1.54ms, 77MB)
테스트 21 〉	통과 (4.17ms, 78.5MB)
테스트 22 〉	통과 (2.03ms, 76.7MB)
테스트 23 〉	통과 (3.67ms, 81MB)
테스트 24 〉	통과 (3.19ms, 76.7MB)
테스트 25 〉	통과 (2.91ms, 74.7MB)
테스트 26 〉	통과 (2.63ms, 74MB)
 */