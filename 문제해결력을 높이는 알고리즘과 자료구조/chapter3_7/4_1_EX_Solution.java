public class Solution {
    int[] arr;
    public Solution() {
        arr = new int[50];
    }
    static int GCD(int m, int n) {
        if(n == 0) {
            return m;
        }

        return GCD(n, m % n);
    }
    int fibo(int n) {
        if(n == 0) {
            return 0;
        }
        if(n == 2 || n == 1) {
            return 1;
        }
        if(arr[n] != 0) {
            return arr[n];
        }
        arr[n] = fibo(n - 1) + fibo(n - 2);
        return arr[n];

    }
    static public void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.fibo(6));

    }
}
