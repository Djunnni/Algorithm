import java.util.*;
import java.io.*;

/**
 * Iterator의 실습문제로 iterator를 통해 조회시 O(N)이 아니라 O(1)인 상태로 추가제거를 연습합니다.
 * iterator.remove()는 iter의 다음 요소를 지웁니다.
 * @Djunnni
 */
public class Main {
    static List<Character> breads = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken()); // 식빵의 개수
        int M = Integer.parseInt(st.nextToken()); // 암호문의 개수

        char[] inputBreads = br.readLine().toCharArray();
        for(char input : inputBreads) { // 배열에 처음 식빵 넣기
            breads.add(input);
        }

        ListIterator<Character> iter = breads.listIterator(breads.size());

        for(int command = 1; command <= M; command++) { // 명령어 수행하기
            st = new StringTokenizer(br.readLine(), " ");
            switch(st.nextToken()) {
                case "L":
                    if(iter.hasPrevious()) iter.previous();
                    break;
                case "P":
                    char newChar = st.nextToken().charAt(0);
                    iter.add(newChar);
                    break;
                case "R":
                    if(iter.hasNext()) iter.next();
                    break;
                case "D":
                    if (iter.hasNext()) {
                        iter.next();
                        iter.remove();
                    }
                    break;
            }
        }
        for(char item : breads) {
            sb.append(item);
        }
        System.out.print(sb);
    }
}