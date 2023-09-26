import java.io.*;
import java.util.*;

/**
 * @author djunnni
 * BJ_20291
 */
public class Main {
	static Map<String, Integer> map;
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		initialize();
		answer();
	}
	public static void answer() {
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			sb.append(entry.getKey() + " " + entry.getValue()).append("\n");
		}
		System.out.println(sb);
	}
	public static void initialize() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 파일 수
		map = new TreeMap<String, Integer>((a, b) -> a.compareTo(b));
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), "."); // 파일명 .으로 자르기
			String fileName = st.nextToken();
			String format = st.nextToken();	
			map.put(format, map.getOrDefault(format, 0) + 1);
		}
	}

}
