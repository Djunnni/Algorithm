package chapter6_1;

import java.util.*;

public class Solution6_4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        for(int i = 0; i < N; i++) a.add(sc.nextInt());
        for(int i = 0; i < N; i++) b.add(sc.nextInt());

        int MIN_VALUE = Integer.MAX_VALUE;
        b.sort(Comparator.naturalOrder());

        for(int i = 0; i < N; i++) {
            int iter = lower_bound(b, K - a.get(i));

            int v = a.get(i) + b.get(iter);
            if(v < MIN_VALUE && v >= K) {
                MIN_VALUE = v;
            }
        }

        System.out.println(MIN_VALUE);
    }
    public static int lower_bound(ArrayList<Integer> arr, int target) {
        int left = 0;
        int right = arr.size() - 1;

        while(left < right) {
            int mid = (left + right) / 2;
            if(arr.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
