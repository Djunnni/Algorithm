import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BJ_15686_치킨_배달
 * 각 칸은 빈 칸, 치킨집, 집 중 하나이며 NxN이다.
 * 
 * 치킨거리는 집과 가장 가까운 치킨집 사이의 거리다.
 * 치킨 거리는 집을 기준으로 정해지며, 각각의 집은 치킨거리를 가지고 있다.
 * 도시의 치킨 거리는 모든 집의 치킨 거리의 합이다.
 * 
 * 두 칸 사이의 거리는 |r1 - r2| + |c1 - c2|
 * 
 * 소요시간 1시간 15분
 * 
 * @author djunnni
 *
 */
public class Main {
	public static class Spot {
		int r;
		int c;
		public Spot(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
        // initialCapacity는 주어진 MAX에 한해서 넣음.
		List<Spot> chickens = new ArrayList<>(13);
		List<Spot> homes = new ArrayList<>(100);
		// 치킨집 배열에 넣기
		for(int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int c = 1; c <= N; c++) {
				int v = Integer.parseInt(st.nextToken());
				if(v == 2) {
					chickens.add(new Spot(r, c));
				} else if(v == 1) {
					homes.add(new Spot(r, c));
				}
			}
		}
		int answer = Integer.MAX_VALUE;
		// 폐업 시키지 않을 치킨집을 N개 골랐을 때, 도시 치킨 거리의 최솟값을 구하라.
		// 치킨집 3개 => bit -> 111, 110, 101, 001 등
		for(int bit = 0; bit < (1 << chickens.size()); bit++) {
			int cnt = 0;
            // 치킨집을 선택하게 될 때, answer에 들어가기 전, 해당 거리가 최소인지를 판별
			int h[] = new int[homes.size()];
			for(int i = 0; i < chickens.size() && cnt <= M; i++) {
				// 치킨집 선택하기
				if((bit & (1 << i)) != 0) {
					cnt++;
					Spot chicken = chickens.get(i);
                    // 선택된 치킨 집으로 집들을 돌면서 거리를 h[j]에 저장.
					for(int j = 0; j < homes.size(); j++) {
						Spot home = homes.get(j);
						int distance = Math.abs(chicken.c - home.c) + Math.abs(chicken.r - home.r);
						if(h[j] == 0) {
							h[j] = distance;
						} else {
							h[j] = Math.min(h[j], distance);
						}
					}
				}
			}
            // 선택된 치킨집의 수가 0 < cnt <= M 일때, 정답에 반영.
			if(cnt > 0 && cnt <= M) {
				answer = Math.min(answer, Arrays.stream(h).sum());
			}
		}
		
		System.out.println(answer);
	}
}
