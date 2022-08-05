package com.ssafy.lab.BJ_2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * BJ_2493_탑
 * 
 * 제한 1.5초, 메모리 128MB
 * N개의 높이가 서로 다른 탑을 수평 직선의 왼쪽부터 오른쪽 방향으로 차례로 세우고 
 * 그 위에 레이저 송신기를 설치했다.
 * 
 * 모든 탑의 레이저 송신기는 레이저 신호를 지표면과 평행하게 수평 직선의 왼쪽 방향으로 발사하고
 * 탑의 기둥 모두에는 레이저를 수신하는 장치가 설치되어있다.
 * 하나의 탑에서 발사된 레이저 신호는 가장 먼저 만나는 단 하나의 탑에서만 수신 가능하다.
 * 
 * 시도 실패 / [시간초과]
 * 
 * @author djunnni
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine()); // 1이상 500,000 이하
		
		// 탑들의 높이는 1이상 100,000,000(1억)이하의 정수다.

		Stack<Integer> stack = new Stack<>();
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++) {
			int v = Integer.parseInt(st.nextToken());
			if(stack.isEmpty()) {
				sb.append(0).append(" ");
			} else {
				int idx = stack.size() - 1;
				while(v >= stack.get(idx)) {
					idx--;
					if(idx == -1 || v < stack.get(idx)) {
						break;
					}
				}
				sb.append(++idx).append(" ");
			}
			stack.add(v);
		}
		System.out.print(sb);
	}
}
