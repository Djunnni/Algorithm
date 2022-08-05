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
 * 
 * @author djunnni
 *
 */
public class Main2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine()); // 1이상 500,000 이하
		
		// 탑들의 높이는 1이상 100,000,000(1억)이하의 정수다.

		// 값을 저장한 스택과 인덱스를 저장한 스택
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> indexStack = new Stack<>();
		
		StringTokenizer st  = new StringTokenizer(br.readLine(), " ");
		
		for(int i = 1; i <= N; i++) {
			int value = Integer.parseInt(st.nextToken());
			// 스택이 비어있다면 인덱스 1, 출력에 0을 미리 넣어둔다.
			if(stack.isEmpty()) {
				indexStack.add(1);
				sb.append("0 ");
			} else {
				while(!stack.isEmpty()) {
					if(value < stack.peek()) {
						sb.append(indexStack.peek() + " ");
						break;
					}
					stack.pop();
					indexStack.pop();
				}
				if(stack.isEmpty()) {
					sb.append("0 ");
				}
				indexStack.add(i);
			}
			// 스택에 값을 넣는다.
			stack.add(value);
		}
		System.out.print(sb);
	}

}
