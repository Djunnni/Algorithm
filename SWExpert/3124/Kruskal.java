import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * D4_3124_최소_스패닝_트리_kruskal
 * @author djunnni
 *
 */
public class Kruskal {
	public static class Edge implements Comparable<Edge> {
		int from;
		int to;
		long weight;
		
		public Edge(int from, int to, long weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Long.compare(weight, o.weight);
		}
		
		
	}
	public static int Parent[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder out = new StringBuilder();
		
		// 테스트케이스 수
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			out.append("#").append(tc).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
			// 정점의 개수
			int V = Integer.parseInt(st.nextToken());
			
			Parent = new int[V + 1];
			for(int i = 1; i <= V; i++) {
				Parent[i] = i;
			}
			
			// 간선의 개수
			int E = Integer.parseInt(st.nextToken()); 
			
			// 간선 리스트
			LinkedList<Edge> list = new LinkedList<>();
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				
				// 간선의 from
				int from = Integer.parseInt(st.nextToken());
				
				// 간선의 to
				int to = Integer.parseInt(st.nextToken());
				
				// 간선의 가중치, 음수일 수 있으며 절대값이 1,000,000을 넘지 않는다.
				long weight = Long.parseLong(st.nextToken());
				
				// 리스트에 간선 넣기
				list.add(new Edge(from, to, weight));
			}
			
			// 간선 정렬
			Collections.sort(list);
			
			long weight = 0;
			int line = 0;
			for(Edge e : list) {
				if(line == V - 1) break;
				if(union(e.from, e.to)) {
					weight += e.weight;
					line++;
				}
			}
			
			out.append(weight).append("\n");
		}
		System.out.println(out);
	}
	public static int find(int a) {
		if(a == Parent[a]) {
			return a;
		}
		return Parent[a] = find(Parent[a]);
	}
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot > bRoot) {
			int temp = aRoot;
			aRoot = bRoot;
			bRoot = temp;
		}
		if(aRoot == bRoot) {
			return false;
		}
		
		Parent[aRoot] = bRoot;
		return true;
	}
}
