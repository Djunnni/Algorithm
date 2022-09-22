package com.ssafy.lab.BJ_1026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * BJ_1026_보물
 * @author djunnni
 */
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 길이 N
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int A[] = new int[N];
		int B[] = new int[N];
		
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");		
		for(int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		// B는 재배열하지 않고 a의 수를 재배열 하자. 그 결과는 s 값을 가장작게 만드는 것!
		
		// 한쪽은 큰 순으로 다른한쪽은 작은순으로 가면 되지 않을까?
		
		Arrays.sort(A);
		Arrays.sort(B);
		
		int S = 0;
		for(int i = 0; i < N; i++) {
			S += A[i] * B[N - i - 1];
		}
		
		System.out.println(S);
	}
}
