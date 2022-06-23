import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static public int solution(int[] priorities, int location) {
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
    static public void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();


        for(int i = 0; i < caseNum; i++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[] arr = new int[N];

            for(int j = 0; j < N; j++) {
                int priority = sc.nextInt();
                arr[j] = priority;
            }

            System.out.println(solution(arr, M));
        }
    }
}