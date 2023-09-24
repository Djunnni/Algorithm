package _1141;

import java.io.*;
import java.util.*;

/**
 * BJ_1141
 * @author djunnni
 * 부분집합을 구하고 거기서 여부체크 -> 메모리초과
 * 반대로 정렬 후 => 다음게 속하는지 체크 ( 그리디)
 */
public class Main {
	public static int answer = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		String[] data = new String[N];
		for(int i = 0; i < N; i++) {
			data[i] = br.readLine();
		}
		
		// 부분집합 구하기
		Arrays.sort(data, (a, b) -> Integer.compare(b.length(), a.length())); 
		
		Set<String> list = new HashSet<>();
		for(String s1 : data) {
			if(list.size() == 0) {
				list.add(s1);
				continue;
			}
			
			boolean available = true;
			for(String s2 : list) {
				if(s2.indexOf(s1) == 0) {
					available = false;
					break;
				}
			}
			if(available) {
				list.add(s1);
			}
		}
		
		System.out.println(list.size());
	}

}
