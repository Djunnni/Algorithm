import java.util.*;

/**
 * @author djunnni
 * 숫자를 하나의 문자열로 쭉 더해둔 다음 i를 0부터 돌아 m번 라운드 과정에서 p번째를 찾는다.
 */
class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        int i = 0;
        while(i <= m * t + p) {
            String value = parse(i++, n);
            answer += value;
        }
        
        i = 0;
        while(true) {
            sb.append(answer.charAt((m * i++) + p - 1));
            if(sb.length() == t) break;
        }
        
        
        return sb.toString();
    }
    /**
     *  i 숫자를 n 진법으로 파싱할 때,
     */
    public String parse(int share, int n) {
        StringBuilder data = new StringBuilder();
        
        do {
            int mock = share % n;
            share = share / n;
            
            data.append(changeChar(mock));
        } while(share >= n);
        if(share > 0) {
            data.append(changeChar(share));
        }
        
        return data.reverse().toString();
    }
    /**
     * 숫자를 문자로 변환
     */
    public char changeChar(int i) {
        if(i >= 10) {
            return (char) ('A' + (i - 10));
        } else if(i >= 0){
            return (char) ('0' + i);
        } else {
            return '\0';
        }
    }
}
/*
테스트 1 〉	통과 (1.25ms, 74.3MB)
테스트 2 〉	통과 (1.91ms, 80.3MB)
테스트 3 〉	통과 (3.65ms, 69.6MB)
테스트 4 〉	통과 (2.28ms, 82.6MB)
테스트 5 〉	통과 (3.40ms, 87.4MB)
테스트 6 〉	통과 (3.26ms, 74.6MB)
테스트 7 〉	통과 (3.81ms, 75.5MB)
테스트 8 〉	통과 (2.91ms, 85.2MB)
테스트 9 〉	통과 (2.71ms, 81.6MB)
테스트 10 〉	통과 (4.43ms, 90.6MB)
테스트 11 〉	통과 (2.50ms, 75.2MB)
테스트 12 〉	통과 (3.55ms, 80.5MB)
테스트 13 〉	통과 (3.51ms, 81.9MB)
테스트 14 〉	통과 (4698.17ms, 618MB)
테스트 15 〉	통과 (4640.69ms, 522MB)
테스트 16 〉	통과 (4817.62ms, 706MB)
테스트 17 〉	통과 (33.90ms, 95.5MB)
테스트 18 〉	통과 (30.37ms, 97.7MB)
테스트 19 〉	통과 (6.73ms, 84.9MB)
테스트 20 〉	통과 (101.65ms, 91.1MB)
테스트 21 〉	통과 (411.26ms, 371MB)
테스트 22 〉	통과 (259.41ms, 306MB)
테스트 23 〉	통과 (657.05ms, 379MB)
테스트 24 〉	통과 (3163.09ms, 456MB)
테스트 25 〉	통과 (3427.39ms, 518MB)
테스트 26 〉	통과 (199.42ms, 341MB)
*/