import java.util.Scanner;

class Solution
{
	static int[] sides = { -2, -1, 1, 2 };
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T = 10;
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int width = sc.nextInt(); // 1000 이하로 주어진다.
			int[] arr = new int[width];
			
			for(int i = 0; i < width; i++) {
				arr[i] = sc.nextInt();
			}
			int answer = 0;
			
			for(int i = 0; i < width; i++) {
				int height = arr[i];
				int max = 0;
				for(int j = 0; j < sides.length; j++) {
					if(i + sides[j] < 0 || i + sides[j] >= width) {
						continue;
					}
					if(max <= arr[i + sides[j]]) {
						max = arr[i + sides[j]];
					}
				}
				if(height - max >= 0) 
					answer += height - max;
			}
			System.out.println("#" + test_case + " " + answer);
		}
	}	
}