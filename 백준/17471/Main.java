import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BJ_17471_게리맨더링
 * @author djunnni
 * 소요시간 1h 10m
 */
public class BJ_17471_게리맨더링_이동준 {
	static int N, minimum, peoples[];
	static List<List<Integer>> map;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N : 구역의 개수 (10이하)
		N = Integer.parseInt(br.readLine());
		
		// peoples: 구역별 인구 수 (100 이하)
		peoples = new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++) {
			peoples[i] = Integer.parseInt(st.nextToken());
		}
		
		// 구역간 관계
		map = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			map.add(new ArrayList<Integer>());
		}
		
		// 각 구역에 인접한 구역의 정보
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			// 구역에 인접한 구역 수
			int length = Integer.parseInt(st.nextToken());
			// length만큼 인접한 구역의 번호가 주어진다.
			for(int j = 1; j <= length; j++) {
					map.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 구별 연결정보 확인
//		for(List<Integer> gu: map) {
//			System.out.println(gu);
//		}
		minimum = -1; // 선거구를 나눌 수 없는 경우로 초기화한다. 
		
		boolean guA[] = new boolean[N + 1];
		splitGu(guA, 1);
		
		// 결과출력
		System.out.println(minimum);
	}
	static void splitGu(boolean[] guA, int count) {
		if(count > N) {
			// A,B 선거구끼리 연결되어있는지 체크
			List<Integer> A = new ArrayList<>();
			List<Integer> B = new ArrayList<>();
			
			for(int i = 1; i <= N; i++) {
				if(guA[i]) A.add(i);
				else B.add(i);
			}
			// A와 B에 적어도 하나의 선거구가 없을 경우
			if(A.size() == 0 || B.size() == 0) {
				return;
			}
			
			// A의 지역구가 서로 연결되어있는 지 확인하기 (최적화 필요)
			boolean isRelA = isRelation(A); 
			boolean isRelB = isRelation(B);
			
			// 서로의 지역구가 연결되어있다면 진행
			if(isRelA && isRelB) {
				int peoplesA = getPeoples(A);
				int peoplesB = getPeoples(B);
				
				// 미니멈이 한번도 안바뀌었다면 변경
				if(minimum == -1) {
					minimum = Math.abs(peoplesA - peoplesB);
				} else if(minimum > 0) {
					// 0보다 크다면 더 작은값으로 변경
					minimum = Math.min(minimum, Math.abs(peoplesA - peoplesB));
				}
			}
			return;
		}	
		guA[count] = true;
		splitGu(guA, count + 1);
		guA[count] = false;
		splitGu(guA, count + 1);
		
	}
	static int getPeoples(List<Integer> list) {
		int sum = 0;
		
		for(int gu : list) {
			sum += peoples[gu];
		}
		
		return sum;
	}
	static boolean isRelation(List<Integer> list) {
		// 사이즈가 1일경우는 참.
		if(list.size() == 1) {
			return true;
		}
		boolean[] mustVisit = new boolean[N + 1];
		for(int v : list) {
			mustVisit[v] = true;
		}
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(list.get(0));
		
		while(!queue.isEmpty()) {
			int spot = queue.poll();
			
			for(Integer ng : map.get(spot)) {
				if(mustVisit[ng]) {
					mustVisit[ng] = false;
					queue.add(ng);
				}
			}
		}
		for(int v : list) {
			if(mustVisit[v]) return false;
		}
		
		return true;
	}
}