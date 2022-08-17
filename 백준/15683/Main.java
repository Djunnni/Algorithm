package com.ssafy.lab.BJ_15683;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BJ_15683_감시
 * 
 * 1초, 512MB
 * 
 * @author djunnni
 *
 */
public class Main {
	public static class Spot {
		int x;
		int y;
		char action;
		public Spot(int x, int y, char action) {
			this.x = x;
			this.y = y;
			this.action = action;
		}
	}
	static public char [][] matrix;
	static public int answer, N, M;
	static public int[] dx = { 1, 0, -1, 0 }; // 우, 상, 좌, 하
	static public int[] dy = { 0, -1, 0, 1 }; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		
		/* 배열을 초기화 합니다. */
		List<Spot> cctvs = new ArrayList<>();
		matrix = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				matrix[i][j] = st.nextToken().charAt(0);
				if(matrix[i][j] == '0' || matrix[i][j] == '6') {
					continue;
				}
				cctvs.add(new Spot(j, i, matrix[i][j]));
			}
		}
		answer = Integer.MAX_VALUE;
		
		trace(matrix, cctvs, 0);
		
		/* 
		 * cctv 8개, 크기도 작다?
		 * 사각지대의 최소 크기를 구합니다.
		 * 1 ~ 5의 CCTV 지점에 대해 상, 하, 좌, 우를 모두 확인한다.
		 * 첫번째 루프일 때
		 * 1의 경우, 1방향으로 [4가지]
		 * 2의 경우, 상, 하 or 좌, 우 [2가지]
		 * 3의 경우, [4가지]
		 * 4의 경우, [4가지]
		 * 5의 경우, [1가지]
		 * 128가지의 경우를 모두 들어야 한다.
		 * 선택은 1번째 해도, 마지막 애까지 선택이 되어야 사각지대 최소를 구할 수 있다.
		 */
		System.out.println(answer);
	}
	public static int getSize(char[][] matrix) {
		int sum = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(matrix[i][j] == '0') sum++;
			}
		}
		return sum;
	}
	public static void move(char[][] matrix, int x, int y, int d) {
		d %= dx.length;
		
		while(true) {
			int nextX = x + dx[d];
			int nextY = y + dy[d];
			if(isOver(nextX, nextY) || isWall(matrix, nextX, nextY)) {
				break;
			}
			if(!isCamera(matrix, nextX, nextY)) {
				matrix[nextY][nextX] = '#';
			}
			x = nextX;
			y = nextY;
		}
	}
	public static void print(char[][] matrix) {
		for(char[] x : matrix) {
			System.out.println(x);
		}
	}
	public static void trace(char[][] matrix, List<Spot> cctvs, int cnt) {
		if(cnt == cctvs.size()) {			
			answer = Math.min(answer, getSize(matrix));
			return;
		}
		char[][] temp = copy(matrix);
		Spot cctv = cctvs.get(cnt);
		switch(cctv.action) {
			case '1':
				// 방향 // 우, 상, 좌, 하
				for(int i = 1; i <= 4; i++) {
					move(temp, cctv.x, cctv.y, i);
					trace(temp, cctvs, cnt + 1);
					temp = copy(matrix);
				}
				break;
			case '2':
				for(int i = 1; i <= 2; i++) {
					move(temp, cctv.x, cctv.y, i);
					move(temp, cctv.x, cctv.y, i + 2);
					trace(temp, cctvs, cnt + 1);
					temp = copy(matrix);
				}
				break;
			case '3':
				for(int i = 1; i <= 4; i++) {
					move(temp, cctv.x, cctv.y, i);
					move(temp, cctv.x, cctv.y, i + 1);
					trace(temp, cctvs, cnt + 1);
					temp = copy(matrix);
				}
				break;
			case '4':
				for(int i = 1; i <= 4; i++) {
					move(temp, cctv.x, cctv.y, i);
					move(temp, cctv.x, cctv.y, i + 1);
					move(temp, cctv.x, cctv.y, i + 2);
					trace(temp, cctvs, cnt + 1);
					temp = copy(matrix);
				}
				break;
			case '5':
				// 1방향
				move(temp, cctv.x, cctv.y, 1);
				move(temp, cctv.x, cctv.y, 2);
				move(temp, cctv.x, cctv.y, 3);
				move(temp, cctv.x, cctv.y, 4);
				trace(temp, cctvs, cnt + 1);
				temp = copy(matrix);
				break;
		}
		
	}
	public static boolean isWall(char[][] matrix, int x, int y) {
		return matrix[y][x] == '6';
	}
	public static boolean isCamera(char[][] matrix, int x, int y) {
		return matrix[y][x] > '0' && matrix[y][x] < '6';
	}
	public static boolean isOver(int x, int y) {
		if(x < 0 || x >= M || y < 0 || y >= N) {
			return true;
		}
		return false;
	}
	public static char[][] copy(char[][] matrix) {
		char[][] temp = new char[N][M];
		for(int i = 0; i < N; i++) {
			temp[i] = Arrays.copyOf(matrix[i], M);
		}
		return temp;
	}

}
