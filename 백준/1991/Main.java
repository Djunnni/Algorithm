import java.util.Scanner;

/**
 * BJ_1991_트리순회
 * 
 * 전위, 중위, 후위 순회를 연습합니다.
 * 
 * 풀이 방법
 * 트리를 만들어 연결시켜 검색해 찾아가는 방법을 이용했습니다.
 * 
 * author djunnni
 */
public class Main {
	static class Node {
		char data;
		Node left;
		Node right;
		
		public Node(char data) {
			this.data = data;
		}
		public static Node findNode(Node p, char id) {
			if(p.data == id) {
				return p;
			}
			Node n = null;
			if(p.left != null) {
				n = findNode(p.left, id);
			}
			if(n != null) {
				return n;
			}
			if(p.right != null) {
				n = findNode(p.right, id);
			}
			if(n != null) {
				return n;
			}
			
			return null;
		}
		public String toString() {
			return "id: " + data;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		Node tree =new Node('A');
		
		for(int i = 0; i < N; i++) {
			char data = sc.next().charAt(0);
			char left = sc.next().charAt(0);
			char right = sc.next().charAt(0);
			
			Node t = Node.findNode(tree, data);
			if(t == null) {
				t = new Node(data);
			}
			if(left != '.') { 
				Node l = Node.findNode(t, left);
				if(l == null) {
					l = new Node(left);
				}
				t.left = l;
			}
			if(right != '.') { 
				Node r = Node.findNode(t, right);
				if(r == null) {
					r = new Node(right);
				}
				t.right = r;
			}
		}
		
		tracePre(tree);
		System.out.println();
		traceIn(tree);
		System.out.println();
		tracePost(tree);
	}
	public static void tracePre(Node n) {
		System.out.print(n.data);
		if(n.left != null) {
			tracePre(n.left);
		}
		if(n.right != null) {
			tracePre(n.right);
		}
	}
	public static void traceIn(Node n) {
		if(n.left != null) {
			traceIn(n.left);
		}
		System.out.print(n.data);
		if(n.right != null) {
			traceIn(n.right);
		}
	}
	public static void tracePost(Node n) {
		if(n.left != null) {
			tracePost(n.left);
		}
		if(n.right != null) {
			tracePost(n.right);
		}
		System.out.print(n.data);
	}
}
