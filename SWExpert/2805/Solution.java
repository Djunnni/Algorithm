import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * D3_2805_농작물 수확하기
 * @author djunnni
 *
 * 제약조건
 * 1. 농장의 크기는 항상 홀수
 * 수확은 항상 농장의 크기에 딱 맞는 정사각형 마름모로만 가능
 */
public class Solution {
	public static int answer;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("src/com/ssafy/lab/SW_2805/input.txt"));
		BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(rd.readLine());
		for(int test = 1; test <= T; test++) {
			int SIZE = Integer.parseInt(rd.readLine());
			int[][] arr = new int[SIZE][SIZE];
			
			for(int i = 0; i < SIZE; i++) {
				String[] strs = rd.readLine().split("");
				for(int j = 0; j < strs.length; j++) {
					arr[i][j] = Integer.parseInt(strs[j]);
				}
			}
			
			/*
			 * 중간까지
			 * 0층 1개
			 * 1층 3개
			 * 2층 5개
			 * ... 
			 * n층 = 2 * n - 1
			 * 중간을 넘어서면
			 * 4층 5개 -2
			 * 5층 3개 -2
			 * 6층 1개 -2
			 * n층 = 2 * (높이 - n) + 1
			 */
			if(SIZE == 1) {
				answer = arr[0][0];
				
			} else {
				answer = 0;
				int MID = SIZE / 2;
				for(int i = 0; i < SIZE; i++) {
					if(i <= MID) {
						// 중간 높이보다 작다면
						int count = 2 * i + 1;
						int left = MID - i;
						for(int j = 0; j < count; j++) {
							answer += arr[i][left + j];
						}
					} else {
						// 중간 높이보다 크다면
						int count = 2 * (SIZE - i) - 1;
						int left = (i - MID);
						for(int j = 0; j < count; j++) {
							answer += arr[i][left + j];
						}
					}
				}
			}		
			
			System.out.println("#" + test + " " + answer);
		}
	}
}
