/**
 * @author djunnni
 * 큰 수 만들기
 * 2자리 이상, 1_000_000 자리 이하인 숫자 => 엄청 큰 수라는 의미
 * 맨앞의 숫자는 최대한 클수록 좋다.
 * 단 뒤에 숫자의 개수가 전체 길이 - (k + 1) 개임을 보장해야한다.
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
            int remainCount = size - (i + 1);
            
            // 리스트가 비어있다면 추가하고 계속
            if(stack.empty()) {
                stack.push(n);
                continue;
            }
            
            // 스택에 있는 최고 값이 n보다 크다면
            if(stack.peek() < n) {
                // TODO: 뺴내기
                while(!stack.empty()) {
                    if(stack.peek() >= n || stack.size() + remainCount > size) {
                        break;
                    }
                    if(k == 0) break;
                    stack.pop();
                    k--;
                }
                // System.out.println(n + " | " + remainCount);
            } else if(stack.peek() >= n && remainCount < k) { // 스택 최대보다 작다면 건너뛰기
                // System.out.println(n + " @ " + remainCount + " ! " + k);
                k--;
                continue;
            }
            stack.push(n);
            // System.out.println(stack);
        }
        
        // 정답 리스트 반영하기
        while(!stack.empty()) {
            answer.append(stack.pop());
        }
        
        return answer.reverse().toString();
    }
}