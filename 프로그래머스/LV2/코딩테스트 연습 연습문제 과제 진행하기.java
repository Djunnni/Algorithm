/**
 * @author djunnni
 * 이름, 과제 시작시간, 마치는데 걸리는시간
 * 시작시간, 이름, 걸리는 시간 순으로 저장이 필요한 상황
 * 
 */
import java.util.*;

class Subject implements Comparable<Subject> {
    String name;
    int start;
    int playTime;
    boolean delayed;

    public Subject(String name, String start, String playTime) {
        this.name = name;
        this.start = Subject.parseStartTime(start);
        this.playTime = Integer.parseInt(playTime);

    }

    public int compareTo(Subject o) {
        if(o.start == this.start) {
            return Integer.compare(this.playTime, o.playTime);
        } else {
            return Integer.compare(this.start, o.start);
        }
    }

    public String toString() {
        return "subject { name:" + this.name + ", start:" + this.start + ", playTime:" + this.playTime + " }";
    }

    private static int parseStartTime(String start) {
        StringTokenizer st = new StringTokenizer(start, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        return hour * 60 + minute;
    }

}
class Solution {
    public String[] solution(String[][] plans) {
        List<String> finishList = new ArrayList<>();
        LinkedList<Subject> subjects = new LinkedList<>();
        LinkedList<Subject> pendingSubjects = new LinkedList<>();

        for(String[] plan : plans) { // 플랜 추가하기
            Subject sub = new Subject(plan[0], plan[1], plan[2]);
            subjects.add(sub);
        }

        Collections.sort(subjects);
        // System.out.println(subjects);
        while(!subjects.isEmpty()) {
            Subject sub = subjects.poll(); // 맨앞요소 빼기

            int endTime = sub.start + sub.playTime;
            Subject nextSub = subjects.peek();

            if(nextSub != null) {
                if(nextSub.start > endTime) { // 다음거가 시작시간이 크면
                    finishList.add(sub.name);
                    int dim = nextSub.start - endTime;
                    while(true) {
                        Subject ps = pendingSubjects.peekLast();
                        if(ps != null) {
                            ps = pendingSubjects.removeLast();
                            if(ps.playTime <= dim) {
                                finishList.add(ps.name);
                                dim -= ps.playTime;
                            } else {
                                ps.playTime -= dim;
                                pendingSubjects.add(ps);
                                break;
                            }

                        } else {
                            break;
                        }
                    }
                } else if(nextSub.start < endTime) { // 작다면! 여기서 멈추기
                    int realPlayTime = endTime - nextSub.start; // 실제 돌아간 시간
                    // sub.start = endTime - realPlayTime;
                    sub.playTime = realPlayTime; // 시작한 만큼은 빼주기
                    sub.delayed = true;
                    pendingSubjects.add(sub);
                } else { // 같은 경우
                    finishList.add(sub.name);
                }
            } else {
                finishList.add(sub.name);
            }
            // System.out.println(subjects);
            // System.out.println("----------------");
            // System.out.println(pendingSubjects);
        }

        while(!pendingSubjects.isEmpty()) {
            Subject ps = pendingSubjects.removeLast();
            finishList.add(ps.name);
        }

        String answer[] = new String[plans.length];
        return (String[]) finishList.toArray(answer);
    }
}