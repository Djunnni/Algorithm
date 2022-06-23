import java.util.ArrayList;

class Solution {
    public String solution(String s) {
        ArrayList<String> answer = new ArrayList<>();
        
        s = s + '$';
        for(String t : s.split(" ")) {
            char[] arr = t.toCharArray();
            for(int i = 0; i < arr.length; i++) {
                if(i==0 && !Character.isDigit(arr[0])) {
                    arr[0] = Character.toUpperCase(arr[0]);
                } else {
                    arr[i] = Character.toLowerCase(arr[i]);    
                }
            }
            answer.add(new String(arr));
        }
        return String.join(" ", answer).replace("$","");
    }
}
