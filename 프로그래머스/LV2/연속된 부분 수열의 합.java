/**
 * @author djunnni
 * 연속된 부분 수열의 합
 * 슬라이딩 기법을 통해 문제를 풀어야 될 것 같다.
 */
import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[] {-1, -1};
        int[] sumArr = new int[sequence.length + 1];
        
        sumArr[1] = sequence[0]; // 누적 합 위치 구하기
        for(int i = 2; i <= sequence.length; i++) {
            sumArr[i] += sumArr[i - 1] + sequence[i - 1];
        }
        // System.out.println(Arrays.toString(sumArr));
        
        int left = 0; // 왼쪽
        int right = 0; // 오른쪽
        while(right <= sequence.length) {
            if(sumArr[right] - sumArr[left] < k) { // k보다 작을 때, right 증가
                right++;
            } else if(sumArr[right] - sumArr[left] > k) { // k보다 크다면 left 증가
                left++;
            } else { // 값이 k와 같다면
                if(answer[0] == answer[1] && answer[0] == -1) { // 기본값이면 교체
                    answer[0] = left + 1;
                    answer[1] = right;
                }
                if(answer[1] - answer[0] > right - (left + 1)) {
                    answer[0] = left + 1;
                    answer[1] = right;
                } else if(answer[1] - answer[0] == right - (left + 1)) {
                    if(answer[0] > left + 1) {
                        answer[0] = left + 1;
                        answer[1] = right;
                    }
                }
                left++;
            }
        }
        
        answer[0] -= 1;
        answer[1] -= 1;
        
        return answer;
    }
}