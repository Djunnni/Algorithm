/**
 * @author djunnni
 * n의 다음 큰 숫자는 n보다 큰 자연수
 * n의 다음 큰 숫자와 n은 2진수로 변환했을 때, 1의 개수가 같다
 * n의 다음 큰 숫자는 조건 1,2를 만족하는 수중 가장 작은 수
 */
class Solution {
    public int solution(int n) {
        // 2진수로 표현함
        String str = Integer.toString(n, 2);
        // 01로 숫자가 뒤집어 지는 경우를 찾아라.
        int isFirstZero = -1;
        int countZero = 0;
        int countOne = 0;
        for(int i = str.length() - 2; i >= 0; i -= 1) {
            char ch = str.charAt(i);
            char ch2 = str.charAt(i + 1);
            
            if(ch == '0' && ch2 == '1') {
                isFirstZero = i;
                break;
            }
            if(ch2 == '1') {
                countOne++;
            } else {
                countZero++;
            }
        }
        
        StringBuilder strAnswer = new StringBuilder();
        if(isFirstZero == -1) { // 새롭게 1을 증가시키고 나머지는 모두 맨 뒤로 미루기
            strAnswer.append("10");
        } else { // 앞칸으로 하나 땡기고 나머지는 전부 맨 뒤로 미루기
            strAnswer.append(str.substring(0, isFirstZero)).append("10");
        }
        strAnswer.append("0".repeat(countZero));
        strAnswer.append("1".repeat(countOne));
        
        return Integer.valueOf(strAnswer.toString(), 2);
    }
}