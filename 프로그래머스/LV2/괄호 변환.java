/**
 * @author djunnni
 * 괄호 변환
 * 균형잡힌 괄호 문자열 '(', ')'의 개수가 같다.
 * 올바른 문자열 '(', ')' 짝이 맞다.
 */
import java.util.*;

class Solution {
    public String solution(String p) {
        return make(p);
    }
    public String make(String p) {
        String answer = "";
        char[] pArr = p.toCharArray();
        
        int leftCount = 0;
        int rightCount = 0;
        
        int startPoint = 0;
        for(int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == ')') {
                rightCount++;
            } else {
                leftCount++;
            }
            
            if(rightCount == leftCount) { // 두개가 같으면 균형잡힌 문자열로 분리가 일어남
                String u = p.substring(startPoint, i + 1);
                String v = p.substring(i + 1, p.length());
                
                if(isCorrect(u)) {
                    answer += u;
                    leftCount = 0;
                    rightCount = 0;
                    startPoint = i + 1;
                } else {
                    answer += "(" + make(v) + ")";
                    answer += makeCorrect(u);
                    break;
                }
            }
        }
        return answer;
    }
    public boolean isCorrect(String s) {
        boolean v = true;
        Stack<Character> stack = new Stack<>();
        for(char c : s.toCharArray()) {
            if(c == '(') {
                stack.push(c);   
            } else {
                if(!stack.isEmpty()) {
                    stack.pop();   
                } else {
                    v = false; // 스택 붕괴 올바르지 않다..!
                    break;
                }
            }
        }
        return v;
    }
    public String makeCorrect(String s) {
        // 문자열 V에 대해서 1단계부터 재귀
        String temp = "";
        String sliceStr = s.substring(1, s.length() - 1);
        if(sliceStr.length() > 0) {
            for(int i = 0; i < sliceStr.length(); i++) {
                if(sliceStr.charAt(i) == '(') {
                    temp += ")";
                } else {
                    temp += "(";
                }
                
            }    
        }
        return temp;
    }
}