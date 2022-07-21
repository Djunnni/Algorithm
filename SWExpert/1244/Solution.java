import java.util.Scanner;
class Solution
{
    static int answer;
    static int TURN;
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		

		for(int test_case = 1; test_case <= T; test_case++)
		{            
            String number = sc.next();
            TURN = sc.nextInt();

            char[] arr = number.toCharArray();

            answer = 0;
            func(arr, 0);

            System.out.println("#" + test_case + " " + answer);
		}
	}
    static public void func(char[] arr, int turn) {
        if(turn == TURN) {
            int v = Integer.parseInt(new String(arr));
            if(v > answer) {
                answer = v;
            }
            return;
        }

        int minIdx = -1;
        int maxIdx = -1;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i = 0; i < arr.lnegth; i++) {
            int v = arr[i] - '0';
            if(v >= max) {
                max = v;
                maxIdx = i;
            }
            if(v <= min) {
                min = v;
                minIdx = i;
            }
        }
        if(minIdx >= maxIdx) {
            swap(arr, i, j);
            func(arr, turn + 1);
        } else {
            
        }
    }
    static public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}