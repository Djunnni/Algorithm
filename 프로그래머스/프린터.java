import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 1;
    
        ArrayList<String> queue = new ArrayList<>();
        ArrayList<Integer> priorityQueue = new ArrayList<>();
        
        for(int i = 0; i < priorities.length; i++) {
            queue.add(Character.toString(65+i));
            priorityQueue.add(priorities[i]);
        }
 
        while(!queue.isEmpty()) {
            String v = queue.remove(0);
            int priority = priorityQueue.remove(0);
            
            boolean canPrint = true;
            
            for(int i = 0; i < priorityQueue.size(); i++) {
                if(priority < priorityQueue.get(i)) {
                    canPrint = false;
                    break;
                }
            }
            if(canPrint) {
                if(v.equals(Character.toString(location + 65))) {
                    return answer;
                } else {
                    answer++;
                }
            } else {
                queue.add(v);
                priorityQueue.add(priority);
            }
        }
        
        
        return answer;
    }
}
