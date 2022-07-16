import java.util.HashSet;
import java.util.Scanner;

class Solution
{
    static class Node {
        int data;
        Node left, right, parent;

        Node() {}
        Node(int v) {
            data = v;
            left = null; right = null; parent= null;
        }
        public void connect(Node v) throws Exception {
            // 둘 다 차있으면 에러발생 ( 이진트리라고 나와있어서 )
            if(left != null && right != null) {
                throw new Exception("Node " + data + "is Full");
            }

            // v 부모에 this Node 넣기
            v.parent = this;
            if(left == null) {
                // 왼쪽이 비면
                left = v;
                return;
            }
            // 오른쪽이 비면
            right = v;
        }
        public static int findParent(Node n1, Node n2) {
            // n1의 부모들 Set, n2 부모들 Set
            HashSet<Integer> n1Set = new HashSet<>();
            HashSet<Integer> n2Set = new HashSet<>();

            int d = 1;
            Node n1p = n1.parent;
            // n1 부모들을 n1Set에 while 돌면서 저장
            while(n1p != null) {
                n1Set.add(n1p.data);
                n1p = n1p.parent;
            }
            // n2 역시 반복
            Node n2p = n2.parent;
            while(n2p != null) {
                // 만약 n1Set에 있는 부모라면 d의 값을 업데이트하고 리턴
                if(n1Set.contains(n2p.data)) {
                    d = n2p.data;
                    break;
                }
                n2p = n2p.parent;
            }

            return d;
        }
        // 자식들 count 하기
        public int countSibling() {
            int count = 1;
            int c = 0;
            if(this.left != null) {
                c += this.left.countSibling();
            }
            if(this.right != null) {
                c += this.right.countSibling();
            }

            return count + c;
        }
    }
    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int V = sc.nextInt();
            int E = sc.nextInt();
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();

            Node[] Nodes = new Node[V + 1];
            for(int i = 1; i < V + 1; i++) {
                Nodes[i] = new Node(i);
            }

            for(int i = 0; i < E; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                // a와 b 크키비교를 할까 했는데 간선은 간선을 이루는 두 정점으로, 항상 “부모 자식” 순서로 표기된다.
                Nodes[a].connect(Nodes[b]);
            }

            // 부모찾기
            Node parent = Nodes[Node.findParent(Nodes[n1], Nodes[n2])];

            // 답 출력
            System.out.println("#" + test_case + " " + parent.data + " " + parent.countSibling());
        }
    }
}