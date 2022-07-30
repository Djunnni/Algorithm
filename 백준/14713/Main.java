import java.util.ArrayList;
import java.util.Scanner;

/**
 * 백준 14713
 * 앵무새는 한 문장을 기억, 문장은 여러 단어로 이루어져 있으나 앵무새는 순서대로 말함.
 * 한 앵무새가 단어를 말하고 그 다음 단어를 말하기 전에 다른 앵무새가 가로채고 자신의 문장을 말할 수 있다.
 * 한 앵무새가 말하는 중에는 다른 앵무새가 말을 가로채지 않는다.
 * 어떤 단어도 앵무새가 말하는 모든 문장을 통틀어 2번 이상 등장하지 않는다.
 *
 * 1) cseteram이 말한 단어의 수가 맞을 경우
 * 2) cseteram이 말한 단어를 지운 뒤에도 큐가 남아있을 경우, 
 * => 앵무새는 끝까지 말한 다음 돌아가며 cseteram은 돌아갈때까지 단어를 받아적는다.
 * => 이 부분에서 방황함.
 * 
 * @author djunnni
 *
 */
public class Main {

	static int N, cnt;
	static ArrayList<String> queues[];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		// 앵무새 수
		N = Integer.parseInt(sc.nextLine().trim());
		queues = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			queues[i] = new ArrayList<String>();
			String talk = sc.nextLine().trim();
			for(String t: talk.split(" ")) {
				queues[i].add(t);
			}
		}
		
		String words[] = sc.nextLine().trim().split(" ");
		boolean answer = true;
		// cseteram이 말한 단어가 앵무새 N 안에 있을 수 있다. 중복 가능성이 있음.
		for(String w : words) {
			for(int i = 0; i < N; i++) {
				answer = false;
				if(queues[i].isEmpty()) {
					continue;
				} else if(queues[i].get(0).equals(w)) {
					queues[i].remove(0);
					answer = true;
					cnt++;
					break;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(!queues[i].isEmpty()) {
				answer = false;
			}
		}
		
		System.out.println(answer && cnt == words.length ? "Possible":"Impossible");
	}

}
