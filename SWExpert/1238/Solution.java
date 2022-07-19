import java.util.Scanner;
import java.util.LinkedList;

class Solution
{
	static int MAX_USER = 100;
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int length = sc.nextInt();
			int start = sc.nextInt();
			
			LinkedList<Integer>[] list = new LinkedList[MAX_USER + 1];
			int[] visited = new int[MAX_USER + 1];
			for(int i = 1; i <= MAX_USER; i++) {
				list[i] = new LinkedList<Integer>();
			}
			
			length /= 2;
			for(int i = 0; i < length; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				
				if(!list[from].contains(to)) {
					list[from].add(to);
				}
			}
			
			int th = 1;
			LinkedList<Integer> tmp = new LinkedList<Integer>();
			tmp.add(start);
			while(tmp.size() > 0) {
				LinkedList<Integer> join = new LinkedList<Integer>();
				for(int next : tmp) {
					if(visited[next] == 0) {
						visited[next] = th;
						if(list[next].size() > 0)
							join.addAll(list[next]);
					}
				}
				tmp = join;
				th++;
			}	
			
			int visitCount = visited[start];
			int idx = 1;
			for(int i = 1; i <= MAX_USER; i++) {
				if(visited[i] >= visitCount) {
					visitCount = visited[i];
					idx = i;
				}
			}
			
			System.out.println("#" + test_case + " " + idx);
		}
	}
}