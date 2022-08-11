import java.util.Arrays;
import java.util.Scanner;

/**
 * BJ_3040_백설_공주와_일곱_난쟁이
 * 
 * 1초, 128MB
 * 
 * 어느날 광산에서 아홉난쟁이가 돌아왔다.
 * 자기네들끼리 백설공주의 일곱 난쟁이라고 우기고 있다.
 * 
 * 백설공주는 이를 대비해, 난쟁이가 쓰는 모자에 100보다 작은 양수를 적어놨다.
 * 
 * 일곱 난쟁이의 모자에 쓰여진 숫자 합은 100이 된다.
 * 아홉 난쟁이 모자에 쓰여있는 수가 주어졌을 때, 일곱 난쟁이를 찾으시오.
 * 
 * 입력
 * 1 <= x <= 99
 * 
 * 출력
 * 난쟁이가 쓴 모자에 쓰여진 수를 한줄에 하나씩 출력한다.
 * 
 * @author djunnni
 *
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int arr[] = new int[9];
		int select[] = new int[7];
		
		for(int i = 0; i < 9; i++) {
			arr[i] = sc.nextInt();
		}
		combi(arr, select, 0, 0, 0);
	}
	public static void combi(int[] arr, int[] select, int cnt, int start, int sum) {
		if(select.length == cnt) {
			if(sum == 100) {
				for(int x : select) {
					System.out.println(x);
				}
			}
			return;
		}
		
		for(int i = start; i < 9; i++) {
			select[cnt] = arr[i];
			combi(arr, select, cnt + 1, i + 1, sum + arr[i]);
		}
	}
}
