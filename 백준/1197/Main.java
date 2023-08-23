import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1197
 * union-find와 edge 거리가 가까운순으로 더해서 문제를 해결하는
 * 기본적인 MST 문제
 * => ElogE
 */
public class Main {
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int cost;

		public Edge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Edge{" +
				"start=" + start +
				", end=" + end +
				", cost=" + cost +
				'}';
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(cost, o.cost);
		}
	}
	public static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int V = Integer.parseInt(st.nextToken()); // 정점 개수
		int E = Integer.parseInt(st.nextToken()); // 간선 개수

		PriorityQueue<Edge> queue = new PriorityQueue<>();

		makeSet(V);

		for(int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			Edge edge = new Edge(A, B, C);
			queue.add(edge);
		}

		int answer = 0;
		int edgeCount = 0;
		while(!queue.isEmpty()) {
			Edge edge = queue.poll();
			if(find(edge.start) != find(edge.end)) {
				union(edge.start, edge.end);
				answer += edge.cost;
				edgeCount++;
				if(edgeCount == V - 1) {
					break;
				}
			}

		}

		System.out.println(answer);
	}

	private static void union(int start, int end) {
		start = find(start);
		end = find(end);

		if(start < end) {
			parents[end] = start;
		} else {
			parents[start] = end;
		}
	}

	private static void makeSet(int V) {
		parents = new int[V + 1]; // makeSet
		for(int i = 0; i <= V; i++) {
			parents[i] = i;
		}
	}

	private static int find(int node) {
		if(node == parents[node]) return node;

		return parents[node] = find(parents[node]);
	}
}
