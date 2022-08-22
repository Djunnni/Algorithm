import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BJ_1759_암호 만들기
 * author djunnni
 * 
 * 풀이
 * 받은 문자열을 정렬하고 L개를 뽑는 조합으로 문제를 풉니다. -> 사전식으로 출력
 * 그런다음 조건에 나온 한 개의 모음, 두 개의 자음으로 구성되어 있는지 확인하면 됩니다.
 */
public class Main {
    static char[] arr, data;
    static int L, C;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        sb = new StringBuilder();

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        data = new char[L];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < C; i++) {
           arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        cript(0, 0);

        System.out.println(sb);
    }
    public static void cript(int cnt, int start) {
        if(cnt == L) {
            if(isValid()) {
                sb.append(data).append("\n");
            }
            return;
        }
        for(int i = start; i < C; i++) {
            data[cnt] = arr[i];
            cript(cnt + 1, i + 1);
        }
    }
    public static boolean isValid() {
        int mo = 0;
        int ja = 0;

        for(int i = 0; i < data.length; i++) {
            if("aeiou".contains(data[i]+"")) {
                mo++;
            } else {
                ja++;
            }
        }
        if(mo >= 1 && ja >= 2) {
            return true;
        }
        return false;
    }
}
