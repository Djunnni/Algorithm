import java.util.Scanner;

/**
 * BJ_17478_S5
 * 재귀함수가 뭔가요?
 * 
 * 교수님이 재귀함수 물어보는 학생이 많아 챗봇을 준비했다.
 * 교수님이 원하는 출력수를 받아 그만큼 반복하자.
 * @author djunnni
 *
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		answer(0, N);
		
	}
	public static void answer(int count, int end) {
		String tab = "";
		for(int i = 0; i < count; i++) {
			tab += "____";
		}
		if(count == 0) {
			System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
			
		}
		System.out.printf("%s\"재귀함수가 뭔가요?\"\n", tab);
		if(count == end) {
			System.out.printf("%s\"재귀함수는 자기 자신을 호출하는 함수라네\"\n", tab);
		} else {
			System.out.printf("%s\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n", tab);
			System.out.printf("%s마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n", tab);
			System.out.printf("%s그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n", tab);	
			answer(count + 1, end);
		}
		System.out.printf("%s라고 답변하였지.\n", tab);
	}

}
