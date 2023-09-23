package _12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BJ_12100
 * @author djunnni
 * 2048 (Easy)
 */
public class Main {
	public static int N, ANSWER = Integer.MIN_VALUE;
	public static BufferedReader br;
	public static void main(String[] args) throws IOException {
		int[][] MATRIX = initialize();
		play(1, MATRIX);
		
		System.out.println(ANSWER);
	}
	public static int[][] initialize() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 보드의크기 
		
		int[][] MATRIX = new int[N][N]; // NxN으로 초기화
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				// MATRIX[i][j] = 0 이면 빈칸이다.
				MATRIX[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return MATRIX;
	}
	public static int getScore(int[][] matrix) {
		int answer = Integer.MIN_VALUE; // 최고는 2^14까지이므로 int 가능
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				answer = Math.max(answer, matrix[i][j]);
			}
		}
		return answer;
	}
	public static int[][] copyOf(int[][] map) {
		int[][] temp = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				temp[i][j] = map[i][j];
			}
		}
		return temp;
	}
	public static void play(int count, int[][] matrix) {
		if(count > 5) {
			ANSWER = Math.max(ANSWER, getScore(matrix));
			return;
		}
		// 어느방향으로 몰아볼건지 ->
		for(int i = 0; i < 4; i++) {
			int[][] temp = copyOf(matrix);
			if(i == 1) {
				turnUp(temp);
			} else if(i == 2) {
				turnDown(temp);
			} else if(i == 3) {
				turnLeft(temp);
			} else {
				turnRight(temp);
			}
			play(count + 1, temp);
		}
	}
	public static void turnRight(int[][] map) {
		for(int y = 0; y < N; y++) {
			List<Integer> list = new ArrayList<>();
			for(int x = N - 1; x >= 0; x--) {
				if(map[y][x] != 0) {
					list.add(map[y][x]);
				}
			}
			List<Integer> newList = summary(list, true);
			int max = newList.size() - 1; // N = 4, max = 3 -> 0, 1, 2, 3
			for(int x = N - 1; x >= 0; x--) { // x -> 3 2 1 0
				map[y][x] = 0;
				if(N - 1 - x <= max) { // 3 - 3 => 0, 1, 2, 3
					map[y][x] = newList.get(N - 1 - x); // 0, 1 ,2 3 
				}
			}
		}
	}
	public static void turnLeft(int[][] map) {
		for(int y = 0; y < N; y++) {
			List<Integer> list = new ArrayList<>();
			for(int x = 0; x < N; x++) {
				if(map[y][x] != 0) {
					list.add(map[y][x]);
				}
			}
			List<Integer> newList = summary(list, true);
			int max = newList.size() - 1;
			for(int x = 0; x < N; x++) {
				map[y][x] = 0;
				if(x <= max) {
					map[y][x] = newList.get(x);
				}
			}
		}
	}
	public static void turnDown(int[][] map) {
		for(int x = 0; x < N; x++) {
			List<Integer> list = new ArrayList<>();
			for(int y = N - 1; y >= 0; y--) {
				if(map[y][x] != 0) {
					list.add(map[y][x]);
				}
			}
			List<Integer> newList = summary(list, true);
			int max = newList.size() - 1;
			for(int y = N - 1; y >= 0; y--) {
				map[y][x] = 0;
				if(N - 1 - y <= max) {
					map[y][x] = newList.get(N - 1 - y); 
				}
			}
		}
	}
	public static void turnUp(int[][] map) {
		for(int x = 0; x < N; x++) { // 행마다
			List<Integer> list = new ArrayList<>();
			for(int y = 0; y < N; y++) { //열 
				if(map[y][x] != 0) {
					list.add(map[y][x]);
				}
			}
			List<Integer> newList = summary(list, true);
			int max = newList.size() - 1;
			for(int y = 0; y < N; y++) {
				map[y][x] = 0;
				if(y <= max) {
					map[y][x] = newList.get(y);
				}
			}
		}
	}
	public static List<Integer> summary(List<Integer> list, boolean c) {
		if(!c) return list;
		List<Integer> temp = new ArrayList<>();
		boolean check = false;
		
		for(int i = 0; i < list.size(); i++) {
			int v = list.get(i); 
			if(!check && temp.size() > 0 && temp.get(temp.size() - 1) == v) {
				temp.remove(temp.size() - 1);
				temp.add(v * 2);
				check = true;
				continue;
			}
			temp.add(v);
			check = false;
		}
		return summary(temp, false);
	}
	// 2 2 2 2 <-
	// 4 4
	// 2 4 4 4 <-
	// 2 4 8
	// 2 8 4 4 <-
	// 2 8 8
}
