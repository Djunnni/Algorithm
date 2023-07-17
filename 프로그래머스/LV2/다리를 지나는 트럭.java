import java.util.*;

/**
 * 큐를 2개 이용해, 지나갈 트럭, 현재 대기중인 트럭을 하나씩 push한다.
 * 다 빠져나가면 그것이 answer
 */
class Solution {
    public class Truck { // current: 0일 경우 대기트럭이다.
        int weight;
        int current;
        
        public Truck(int w, int current) {
            this.weight = w;
            this.current = current;
        }
        
        public void goStep() {
            this.current += 1;
        }
        
        public boolean isFinish(int length) {
            return this.current > length;
        }
        
        public String toString() {
            return "Truck { weight: " + weight + ", current: " + current;
        }
    }
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0; // 초
        
        Queue<Truck> waitingQueue = new LinkedList<>();
        Queue<Truck> goingQueue = new LinkedList<>();
        
        for(int w : truck_weights) {
            waitingQueue.add(new Truck(w, 0));
        }
        
        int bridgeWeight = 0;
        // waitingQueue와 goingQueue가 모두 비어있지 않을 경우!
        while(!(waitingQueue.isEmpty() && goingQueue.isEmpty())) {
            int pollCount = 0;
            if(!goingQueue.isEmpty()) {
                for(Truck t : goingQueue) {
                    t.goStep();
                    if(t.isFinish(bridge_length)) {
                        pollCount++;
                    }
                }
                for(int i = 0; i < pollCount; i++) {
                    Truck t = goingQueue.poll();
                    bridgeWeight -= t.weight;
                }
            }
            answer++;
            if(!waitingQueue.isEmpty() && bridgeWeight + waitingQueue.peek().weight <= weight) {
                Truck t = waitingQueue.poll(); // 대기 큐에서 하나 꺼내기
                t.goStep(); // 한칸 전진
                goingQueue.add(t); 
                bridgeWeight += t.weight;
            }
            
        }
        return answer;
    }
}