import java.util.HashSet;
import java.util.Arrays;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] arr = new int[n];
        Arrays.fill(arr, 1);
        
        for(int los: lost) {
            arr[los - 1]--;
        }
        for(int res: reserve) {
            arr[res - 1]++;
        }
        
        for(int i=0; i < n; i++) {
            if(arr[i] == 0) {
                if(i >= 1 && i < n - 1) {
                    //왼쪽과 오른쪽이 둘 다 있을 때,
                    if(arr[i-1] == 2 && arr[i+1] == 2) {
                        if(i < n - 2 && arr[i+2] == 0) {
                            arr[i-1]--;
                            arr[i]++;
                        } else {
                            arr[i+1]--;
                            arr[i]++;
                        }
                    } else if(arr[i+1] == 2) {
                        arr[i+1]--;
                        arr[i]++;
                    } else if(arr[i-1] == 2){
                        arr[i-1]--;
                        arr[i]++;
                    }
                } else if(i == 0) {
                    // 왼쪽이 없을 때,
                    if(arr[i+1] == 2) {
                        arr[i+1]--;
                        arr[i]++;
                    }
                } else {
                    // 오른쪽이 없을 때,
                    if(arr[i-1] == 2) {
                        arr[i-1]--;
                        arr[i]++;
                    }
                }
            }
        }
        for(int x : arr) {
            System.out.print(x + " ");
            if(x > 0) {
                answer++;
            }
        }
        
        
        return answer;
    }
}
