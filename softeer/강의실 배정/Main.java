import java.util.*;
import java.io.*;

/**
 * @author djunnni
 * 강의실 배정
 * 
 * 해당 문제는 강의실이 무엇이 나오든 항상 종료 시간이 작은 순서대로가 보장이 되면 해결할 수 있다.
 * 
 */
public class Main
{
    public static class Subject {
        int s, f;

        public Subject(int s, int f) {
            this.s = s;
            this.f = f;
        }

        public String toString() {
            return "Subject { s : " + s + ", f : " + f + " } ";
        }
    }
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // N개의 강의 개수

        List<Subject> subjects = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            subjects.add(new Subject(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(subjects, (a, b) -> {
            return Integer.compare(a.f, b.f);
        });
        int count = 0;
        int start = -1;
        int end = -1;
        
        // System.out.println(subjects);
        for(Subject s : subjects) {
            if(start == -1 || end <= s.s) {// 처음일 경우
                start = s.s;
                end = s.f;
                count++;
            }
        }

        System.out.println(count);
    }
}