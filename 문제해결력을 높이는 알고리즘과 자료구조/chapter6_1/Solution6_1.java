package chapter6_1;

import java.util.List;

public class Solution6_1 {
    public static int binarySearch(int key, int[] arr) {
        int left = 0; int right = arr.length - 1;
        while(left <= right) {
            int mid = (right + left) / 2;
            if(arr[mid] == key) return mid;
            else if(arr[mid] > key) right = mid - 1;
            else if(arr[mid] < key) left = mid + 1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int N = 8;
        int[] a = {3, 5, 8, 10, 14, 17, 21, 39};

        System.out.println(binarySearch(10, a));
        System.out.println(binarySearch(3, a));
        System.out.println(binarySearch(39, a));
        System.out.println(binarySearch(-100, a));
        System.out.println(binarySearch(9, a));
        System.out.println(binarySearch(100, a));
    }
    public static int lower_bound(List<Integer> arr, int target) {
            int left = 0;
            int right = arr.size();

            while(left < right) {
                int mid = (left + right) / 2;
                if(arr.get(mid) > target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return right;
    }
    public static int upper_bound(List<Integer> arr, int target) {
        int left = 0;
        int right = arr.size();

        while(left > right) {
            int mid = (left + right) / 2;
            if(arr.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}
