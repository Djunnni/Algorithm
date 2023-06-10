import java.util.*;

/**
 * @author djunnni
 * 고민사항
 * 1. 큐가 가질 수 있는 최대 길이는 얼마? [1번 케이스]
 * 2. 다른 큐가 먼저 비게 된다면?
 * 3. 두개 큐에 포함된 모든 값이 짝수인지?
 */
class Solution2 {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = convertQueue(queue1);
        Queue<Integer> q2 = convertQueue(queue2);
        
        long sumQ1 = sum(q1);
        long sumQ2 = sum(q2);
        
        int maximumCount = (queue1.length + queue2.length) * 2;
        long maximumValue = (sumQ1 + sumQ2) / 2;
        if((sumQ1 + sumQ2) % 2 != 0) return -1;
        
        int count = 0;
        
        while(
            count <= maximumCount &&
            sumQ1 != sumQ2 &&
            !q1.isEmpty() && !q2.isEmpty()
        ) {
            int q1First = q1.peek();
            int q2First = q2.peek();

            if(maximumValue <= sumQ2) {
                sumQ1 += q2First;
                sumQ2 -= q2First;
                q1.add(q2.poll());
            } else {
                sumQ2 += q1First;
                sumQ1 -= q1First;
                q2.add(q1.poll());
            }
            
            count++;
        }
        
        if(q1.isEmpty() || q2.isEmpty() || count > maximumCount) {
            return -1;
        }
        
        return count;
    }
    public long sum(Queue<Integer> queue) {
        long temp = 0;
        for(int data : queue) {
            temp += data;
        }
        return temp;
    }
    public Queue<Integer> convertQueue(int[] arr) {
        Queue<Integer> temp = new LinkedList<>();
        for(int data : arr) {
            temp.add(data);
        }
        return temp;
    }
}