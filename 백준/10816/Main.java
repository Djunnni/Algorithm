import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Solution_10816 {
    static public void mainByBinary(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] cards = new int[N];
        for(int i = 0; i < N; i++) {
            cards[i] = sc.nextInt();
        }

        Arrays.sort(cards);

        int M = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++) {
            int key = sc.nextInt();
            sb.append(upperBound(cards, key) - lowerBound(cards, key)).append(" ");
        }
        System.out.println(sb);
    }
    static public int upperBound(int arr[], int key) {
        int lo = 0;
        int hi = arr.length;

        while(lo < hi) {
            int mid = lo + ((hi - lo) / 2);
            if(key < arr[mid]) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
    static public int lowerBound(int arr[], int key) {
        int lo = 0;
        int hi = arr.length;

        while(lo < hi) {
            int mid = lo + ((hi - lo) / 2);
            if(arr[mid] >= key) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
   static public void mainByHashMap(String[] args) {
       Scanner sc = new Scanner(System.in);
       int N = sc.nextInt();

       int[] cards = new int[N];
       for(int i = 0; i < N; i++) {
           cards[i] = sc.nextInt();
       }
       int M = sc.nextInt();
       int[] cardCounts = new int[M];
       for(int i = 0; i < M; i++) {
           cardCounts[i] = sc.nextInt();
       }

       HashMap<Integer, Integer> map = new HashMap<>();
       for(int i = 0; i < N; i++) {
           int count = map.getOrDefault(cards[i], 0);
           map.put(cards[i], count + 1);
       }

       String countAnswer[] = new String[M];
       for(int i = 0; i < M; i++) {
           countAnswer[i] = "" + map.getOrDefault(cardCounts[i], 0);
       }
       System.out.println(String.join(" ", countAnswer));

   }
}
