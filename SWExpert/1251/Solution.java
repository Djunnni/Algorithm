import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SW_1251_하나로
 * 20개 - 20초 256MB
 * 환경 부담금을 최소로 지불하며 모든 섬을 연결할 수 있도록 만들기.
 * 
 * MST_kruscal, unionFind
 * @author djunnni
 *
 */
public class Solution {
	public static class Edge implements Comparable<Edge> {
		int from;
		int to;
		double weight;
		
		public Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(weight, o.weight);
		}
	}
	public static int[] x, y;
	public static int Parents[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 수
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 섬들의 개수
			int N = Integer.parseInt(br.readLine());
			
			// 섬 x축 정보
			x = new int[N];
			// 섬 y축 정보
			y = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			// 세율 E
			double E = Double.parseDouble(br.readLine());
			
			List<Edge> list = new LinkedList<>();
			
			for(int from = 0; from < N - 1; from++) {
				for(int to = from + 1; to < N; to++) {
					list.add(new Edge(from, to, getDistance(from, to)));
				}
			}
			
			Collections.sort(list);
			
			make(N); 
			
			double dis = 0;
			int line = 0;
			for(int i = 0; i < list.size(); i++) {
				if(line == N - 1) break;
				Edge e = list.get(i);
				
				if(union(e.from, e.to)) {
					line++;
					dis += e.weight;
				}
			}
			
			sb.append(Math.round(dis * E)).append("\n");
			
		}
		
		out.println(sb);
		
		out.flush();
		out.close();
	}
	public static void make(int n) {
		Parents = new int[n + 1];
		
		for(int i = 0; i <= n; i++) {
			Parents[i] = i;
		}
	}
	public static int find(int a) {
		if(Parents[a] == a) {
			return a;
		}
		return Parents[a] = find(Parents[a]);
	}
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot > bRoot) {
			Parents[aRoot] = bRoot; 
		} else if(aRoot < bRoot) {
			Parents[bRoot] = aRoot;
		} else {
			return false;
		}
		return true;
	}
	public static Double getDistance(int from, int to) {
		return Math.pow(Math.abs(x[from] - x[to]), 2) + Math.pow(Math.abs(y[from]- y[to]), 2); 
	}
}
