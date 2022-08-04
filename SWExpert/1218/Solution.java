import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * D4_1218_괄호짝짓기
 * 
 * 4종류의 괄호문자들 '()','[]','{}','<>'으로 이루어진 문자열이 주어진다.
 * 괄호 짝이 모두 맞는지 판별하는 프로그램이다.
 * 
 * @author djunnni
 *
 */
public class Solution {

	public static boolean isValid;
	public static Stack<Character> stack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		for(int test = 1; test <= T; test++) {
			int length = Integer.parseInt(br.readLine());
			String input = br.readLine();
			// 처음엔 유효하다고 초기화 해두기
			isValid = true;
			stack = new Stack<>();
			
			check(input, 0, length);
			
			if(stack.size() > 0) {
				isValid = false;
			}
			
			System.out.println("#"+test+" " + (isValid ? 1 : 0));
		}
	}
	public static void check(String input, int position, int inputLength) {
		if(inputLength == position || !isValid) {
			return;
		}
		char c = input.charAt(position);
		switch(c) {
		case '{':
		case '(':
		case '[':
		case '<':
			stack.add(c);
			break;
		case '}':
			if(stack.peek() == '{') {
				stack.pop();
			} else {
				isValid = false;
			}
			break;
		case ')':
			if(stack.peek() == '(') {
				stack.pop();
			}else {
				isValid = false;
			}
			break;
		case ']':
			if(stack.peek() == '[') {
				stack.pop();
			}else {
				isValid = false;
			}
			break;
		case '>':
			if(stack.peek() == '<') {
				stack.pop();
			}else {
				isValid = false;
			}
			break;
		}
		check(input, position + 1, inputLength);
	}

}
