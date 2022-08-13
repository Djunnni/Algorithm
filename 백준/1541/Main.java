import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


/**
 * BJ_1541_잃어버린괄호
 * 
 * 2초, 128MB
 * 
 * 양수, +, - 그리고 괄호를 가지고 식을 만들었다.
 * 그리고 나서 세준이는 모든 괄호를 지웠다.
 * 
 * 세준이는 괄호를 적절히 쳐서 식의 값을 최소로 만들려고 한다.
 * 
 * 입력
 * 식은 0~9, +, - 만으로 이뤄져있다.
 * 처음과 마지막 문자는 숫자다.
 * 
 * 연속해서 2개 이상의 연산자가 나타나지 않고, 5자리 보다 연속되는 숫자는 없다.
 * 
 * @author djunnni
 *
 *         +
 *    -       40        => + - 55 50 40
 * 55   50              => 55 - 50 + 40                   
 *						=> 55 50 - 40 +
 * 
 *  55 - 50 + 40
 *  1. 55 - 50 => 5 + 45 => 50, 2. 55 - 90 => -35
 *  55 - 50 + 40 - 55 - 50
 *  1. 55 - 50 => 5 + 40 => 45 - 105 => - 60
 *  2. 55 - 90 - 55 - 50 => -140
 *  
 *  더한다 => 앞의 연산이 끝나고 더한다, 그냥 더한다.
 *  뺀다 => 뒤의 연산이 끝나고 뺀다. 그냥 뺀다.
 *  
 *  => 연산이 끝나고는 스택? (누적을 해야하니까)
 * 
 * 최소값 => - 로 자르고 다음 - 까지 사이 값을 더한다.
 * 최대값 -> + 로 자르고 다음 + 까지 사이 값을 더한다.
 *  
 */                     
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), "-");
		StringTokenizer st2 = null;
		Stack<Integer> minusStack = new Stack<>();
		while(st.hasMoreTokens()) {
			Stack<Integer> plusStack = new Stack<>();
			String input = st.nextToken().trim();
			st2 = new StringTokenizer(input, "+");
			while(st2.hasMoreTokens()) {
				int num = Integer.parseInt(st2.nextToken().trim());
				plusStack.push(num);
			}
			int add = 0;
			while(!plusStack.isEmpty()) {
				add += plusStack.pop();
			}
			minusStack.push(add);
		}
		int answer = 0;
		while(!minusStack.isEmpty()) {
			int v = minusStack.pop();
			if(minusStack.isEmpty()) {
				answer += v;
			} else {
				answer -= v;
			}
		}
		
		System.out.println(answer);
	}
}
