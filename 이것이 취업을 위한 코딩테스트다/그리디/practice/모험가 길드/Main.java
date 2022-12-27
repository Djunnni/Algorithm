import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 모험가 길드
 * 1sec, 128MB, 30분
 * 
 * @author djunnni
 * [문제점]
 * 1. 원하는 결과값이 최대, 최소인지 확인하지 못함
 * 2. 뒤에서부터 접근을 해, 최대값이 나올 수 없는 구조 => 반대로 최소는 가능했다.
 */
public class Main {
	public static void main(String[] args) {
		int[] fears = input();
		int answer = greedy(fears);
		printAnswer(answer);
	}
	public static void printAnswer(int v) {
		System.out.println(v);
	}
	public static int[] input() {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine()); // N명의 모험가
		
		int fears[] = new int[N]; // 공포도 배열
		
		StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
		
		int i = 0;
		while(st.hasMoreTokens()) { // 인원 수만큼 공포도를 배열에 넣는다.
			fears[i++] = Integer.parseInt(st.nextToken());
		}
		
		return fears;
	}
	public static int greedy(int[] fears) {
		Arrays.sort(fears); // 오름차순으로 정렬한다.
		
		int groupCount = 0; // 그룹 개수
		int cnt = 0; // 누적 인원 수
		for(int fear : fears) {
			cnt++;
			if(fear <= cnt) {
				groupCount++;
				cnt = 0;
			}
		}
		return groupCount;
	}
}
