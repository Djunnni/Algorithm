import java.util.Scanner;

/**
 * BJ_3040_백설_공주와_일곱_난쟁이
 * 
 * bitmask로 변경
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
public class Main2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int arr[] = new int[9];
		int select[] = new int[7];
		for(int i = 0; i < 9; i++) {
			arr[i] = sc.nextInt();
		}
		
		for(int bit = (1 << 7); bit < (1 << 9); bit++) {
			int sum = 0;
			int user = 0;
			for(int i = 0; i < 9 && user < 7; i++) {
				if((bit & (1 << i)) != 0) {
					select[user++] = arr[i];
					sum += arr[i];
				}
			}
			if(user == 7 && sum == 100) {
				for(int x : select) {
					System.out.println(x);
				}
				return;
			}
		}
	}
}
