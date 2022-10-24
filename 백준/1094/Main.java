import java.util.Scanner;
import java.util.Stack;

/**
 * @author djunnni
 * 풀이방법: Stack을 활용함.
 */
public class Main {
	static int answer;
	static final int POLL = 64;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int wantPoll = Integer.parseInt(sc.nextLine()); // 만들고 싶은 길이 받기
		int currentPoll = POLL; // 현재 길이 64cm
		answer = 1; // 정답 0으로 초기화
		
		Stack<Integer> stack = new Stack<>(); // 스택 자료구조 사용
		stack.push(currentPoll); // 현재 길이 넣기
		
		int sum = currentPoll; // 현재까지 가지고 있는 길이 합
		
		if(sum != wantPoll) { // 64일경우 케이스를 제외하면 안으로 진입
			while(!stack.isEmpty()) { // 스택이 비기 전까지
				int poll = stack.pop(); // 스택에서 하나 꺼내기
				int dividedPoll = poll / 2; // 반으로 나누기
				sum -= dividedPoll; // 길이 누적에서 반 길이 뺴보기

				stack.add(dividedPoll); // 하나는 일단 넣어두기
				if(wantPoll == sum) { // 값이 원하던 길이와 같으면 루프 빠지기
					break;
				} else if(wantPoll > sum) { // 원하던 길이가 길이 누적보다 길면
					stack.push(dividedPoll); // 나머지 길이 추가
					sum += dividedPoll; // 길이 누적에 추가해주기
				}
			}
		}
		System.out.println(stack.size()); // 결과 출력
	}

}
