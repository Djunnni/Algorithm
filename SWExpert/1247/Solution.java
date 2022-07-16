import java.util.*;

class Solution
{
    static int answer;
    static Spot Company, Home;
    static Spot[] customers;

    static class Spot {
        int x, y;
        boolean visited;
        Spot() {
            x = 0;
            y = 0;
            visited = false;
        }
        Spot(int x, int y) {
            this.x = x;
            this.y = y;
            visited = false;
        }
        public static int distance(Spot s, Spot e) {
            return Math.abs(s.x - e.x) + Math.abs(s.y - e.y);
        }

        @Override
        public String toString() {
            return "("+ x + ", " + y +")";
        }
    }

    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            Company = new Spot(sc.nextInt(), sc.nextInt());
            Home = new Spot(sc.nextInt(), sc.nextInt());

            customers = new Spot[N];
            for(int i = 0; i < N; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                customers[i] = new Spot(x, y);
            }


            answer = Integer.MAX_VALUE;

            permutation(Company, 0, 0);


            System.out.println("#" + test_case + " " + answer);
        }
    }
    public static void permutation(Spot spot, int count, int len) {
        if(count == customers.length) {
            len += Spot.distance(spot, Home);
            if(len < answer) answer = len;
            return;
        }

        for(int i = 0; i < customers.length; i++) {
            if(!customers[i].visited) {
                customers[i].visited = true;
                permutation(customers[i], count + 1, len + Spot.distance(spot, customers[i]));
                customers[i].visited = false;
            }
        }
    }
}