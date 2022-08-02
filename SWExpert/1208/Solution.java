import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA_1208_Flatten
 * 한쪽 벽면에 노란색 상자들이 쌓여있다.
 * 높은 곳의 상자를 낮은 곳에 옮기는 방식으로 최고점과 최저점의 간격을 줄이는 작업이 Flatten이다.
 * 
 * 평탄화를 모두 수행하고 나면, 가장 높은 곳과 낮은 곳의 차이는 최대 1이내이다.
 * 평탄화 작업을 위해서 상자를 옮기는 작업횟수에 제한이 있을 때, 제한된 횟수만큼 옮기는 작업을 한 후
 * 최고점과 최저점의 차이를 반환하는 프로그램을 작성해라.
 * @author djunnni
 *
 */
public class Solution {

	/*
	 * 제약 사항
	 * 가로길이는 항상 100
	 * 상자의 높이는 1이상 100이하
	 * 1<= 덤프 횟수 <= 1000
	 */
	public static final int SIZE = 100;
	public static int dump, dumpCount;
	public static int[] arr;
	public static int left, right;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
			//System.setIn(new FileInputStream("src/com/ssafy/lab/SW_1208/input.txt"));
			BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
			int T = 10;
			
			for(int test = 1; test <= T; test++) {
				dumpCount = Integer.parseInt(rd.readLine());
				dump = 0;
				
				arr = new int[SIZE];
				StringTokenizer st = new StringTokenizer(rd.readLine(), " ");
				for(int i = 0; i < SIZE; i++) {
					arr[i] = Integer.parseInt(st.nextToken());
				}
				
				/**
				 * 박스가 위치할 곳은 자유로우니까 차라리 배열을 정렬시킨 뒤 끝점간 이동을 시키는 건 어떨까?
				 * left, right를 두고 양 끝단의 개수를 한개씩 옮깁니다. 
				 * 한개를 옮긴 뒤에도 arr[left] < arr[left+1] 이면 left -> left + 1옮기기 아니면 left를 그대로
				 * 한개를 옮긴 뒤 arr[right] > arr[right - 1] 이면 right -> right - 1 옮기기 right를 그대로
				 */
				Arrays.sort(arr);
				left = 0; right = SIZE - 1;
				
				flatten();
				//System.out.println(Arrays.toString(arr));
				
				System.out.println("#"+ test + " " + (arr[SIZE - 1] - arr[0]));
			}
	}
	public static void flatten() {
		dump();
		Arrays.sort(arr);
		// left가 right와 같은 걸 바라보면 끝.
		if(dump >= dumpCount) {
			return;
		}
		flatten();
		
	}
	public static void dump() {
		// 오른쪽 하나 빼기
		arr[right]--;
		// 왼쪽 하나 더하기
		arr[left]++;
		// 덤프하나 더하기
		dump++;
	}
}
