import java.util.Scanner;

/**
 * author djunnni
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt(); // 몬스터 방어율
        int b = sc.nextInt(); // 유저의 방무

        double ab = (a * b) / 100.0; // 무시하는 수
        if(a - ab >= 100) {
            System.out.println(0);
        } else {
            System.out.println(1);
        }
    }
}
