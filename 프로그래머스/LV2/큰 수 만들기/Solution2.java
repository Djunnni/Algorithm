/**
 * 
 * 큰 수 만들기
 * 
 * 
 */
import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        String[] numbers = number.split(""); // 숫자 배열화 적용
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0, size = numbers.length; i < size; i++) {
            int n = Integer.parseInt(numbers[i]);
            
            while(!stack.empty() && stack.peek() < n && k-- > 0) {
                stack.pop();
            }
            
            stack.push(n);
        }
        
        while(k > 0) {
            stack.pop();
            k--;
        }
        
        // 정답 리스트 반영하기
        while(!stack.empty()) {
            answer.append(stack.pop());
        }
        
        return answer.reverse().toString();
    }
}
/**
테스트 1 〉	통과 (0.26ms, 75.8MB)
테스트 2 〉	통과 (0.46ms, 73.9MB)
테스트 3 〉	통과 (0.84ms, 77.9MB)
테스트 4 〉	통과 (3.52ms, 74.6MB)
테스트 5 〉	통과 (4.49ms, 69.3MB)
테스트 6 〉	통과 (23.86ms, 70.7MB)
테스트 7 〉	통과 (42.66ms, 86.6MB)
테스트 8 〉	통과 (69.60ms, 97.4MB)
테스트 9 〉	통과 (157.99ms, 125MB)
테스트 10 〉	통과 (218.35ms, 128MB)
테스트 11 〉	통과 (0.24ms, 69.4MB)
테스트 12 〉	통과 (0.22ms, 85.5MB)
 */