package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BJ_1290
 * lowerBound와 upperBound의 차를 통해서 index가 0보다 크면 해당 값이 존재, 0이거나 작으면 존재하지 않음
 * lowerBound, upperBound는 log(N)의 연산으로 해당 알고리즘에선 2log(N) + Nlog(N) => (N + 2)log(N)의 연산이 보임.
 * 
 * @author djunnni
 */
public class Main2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int A[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		int finds[] = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			finds[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(A); // lowerBound 적용을 위해 정렬한다.
		for(int findNum : finds) {
			// findNum을 A에서 찾아라
			int lower_index = lowerBound(A, findNum);
			int upper_index = upperBound(A, findNum);
			System.out.println(upper_index - lower_index == 0 ? 0 : 1);
		}
	}

	private static int upperBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		int mid_index = arr.length;

		while(left <= right) {
			int mid = (left + right) / 2;
			if(arr[mid] > target) {
				right = mid - 1;
				mid_index = Math.min(mid, mid_index);
			} else {
				left = mid + 1;
			}
		}

		return mid_index;
	}

	private static int lowerBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		int mid_index = arr.length;

		while(left <= right) {
			int mid = (left + right) / 2;
			if(arr[mid] >= target) {
				right = mid - 1;
				mid_index = Math.min(mid, mid_index);
			} else {
				left = mid + 1;
			}
		}

		return mid_index;
	}
}
