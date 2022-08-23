import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BJ_13023_ABCDE
 * 2초 512mb
 * @author djunnni
 *
 */
public class Main {
	public static int answer;
	public static boolean isFinish;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 사람 수
		int N = Integer.parseInt(st.nextToken());
		// 친구 관계 수
		int M = Integer.parseInt(st.nextToken());
		
		// 친구관계 지도
		List<Integer>[] list = new LinkedList[N];
		
		for(int i = 0; i < N; i++) {
			list[i] = new LinkedList<Integer>();
		}
		
		// 친구관계 잇기
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			list[from].add(to);
			list[to].add(from);
		}
		
		// 친구관계 간선 확인하기
//		for(List<Integer> friends : list) {
//			System.out.println(friends);
//		}
//		System.out.println("-----------------------");
		
		// 친구 관계를 오름차순으로 정렬하기
		for(List<Integer> friends : list) {
			Collections.sort(friends);
		}
		
		answer = 0;
		
        // dfs 시작
		for(int i = 0; i < N && !isFinish; i++) {
			boolean isFriends[] = new boolean[N];
			dfs(list, isFriends, i, 0);
		}
		
        // 출력
		System.out.println(answer);
	}
	public static void dfs(List<Integer>[] list, boolean[] isFriends, int me, int count) {
        // 이미 끝났었다면 전부 return
		if(isFinish) return;
        // 최고점에 도달하면 answer = 1;
		if(count == 5) {
			answer = 1;
			isFinish = true;
			return;
		}
        // 내 리스트에서 탐색하기
		for(int friend : list[me]) {
			if(isFriends[friend]) continue; // 방문했었다면 다음껄로 이동
			isFriends[friend] = true; // 방문체크
			dfs(list, isFriends, friend, count + 1); // 진입
			isFriends[friend] = false; // 방문해제
		}
	}
}