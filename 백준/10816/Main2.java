import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 숫자카드 2
 * 상근이는 N개의 카드를 가지고 있다.
 * 정수 M개가 주어졌을 때, 숫자 카드를 상근이가 몇개를 가지고 있는지 구하라
 * O(logN)으로 끝난다.
 * @author djunnni
 *
 */
public class Main2 {
	static int i;
	static StringTokenizer st;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); // 상근이가 가지고 있는 숫자카드 N
		
		int[] cards = new int[N]; // N개의 카드배열 생성
		
		st = new StringTokenizer(br.readLine(), " "); // N개의 숫자들 읽기
		
		i = 0;
		while(st.hasMoreTokens()) {
			cards[i++] = Integer.parseInt(st.nextToken()); // 배열에 넣기
		}
		
		Arrays.sort(cards); // 오름차순 정렬
		
		int M = Integer.parseInt(br.readLine()); // M개의 숫자 카드
		
		int[] cardNums = new int[M]; // 조회해야할 M개의 카드 배열 생성
		
		st = new StringTokenizer(br.readLine(), " "); // M개의 숫자들 읽기
		
		i = 0;
		while(st.hasMoreTokens()) {
			cardNums[i++] = Integer.parseInt(st.nextToken()); // 배열에 넣기
		}
		
		i = 0;
		for(int num : cardNums) {
			sb.append(upperBound(cards, num) - lowerBound(cards, num)).append(" "); 
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	public static int lowerBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		int min_idx = arr.length;
		while(left <= right) {
			int mid = (left + right) / 2;
			if(arr[mid] >= target) {
				right = mid - 1;
				min_idx = Math.min(min_idx, mid);
			} else {
				left = mid + 1;
			}
		}
		return min_idx;
	}
	public static int upperBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		int min_idx = arr.length;
		while(left <= right) {
			int mid = (left + right) / 2;
			if(arr[mid] > target) {
				right = mid - 1;
				min_idx = Math.min(min_idx, mid);
			} else {
				left = mid + 1;
			}
		}
		return min_idx;
	}
}
