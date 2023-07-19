import java.util.*;

/**
 * @author djunnni
 * [1차] 캐시
 * 
 */
class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        // 캐시를 구성한다.
        // 캐시는 나온 순서를 기억해야한다. cache miss가 발생하면 제일 나중에 발견한 아이템을 지운다
        List<String> map = new LinkedList<>();
        
        for(String c: cities) {
            String city = c.toLowerCase(); // 영문자 대소문자 구분을 없애기
            int index = map.indexOf(city);
            
            if(index >= 0) { // 가지고 있는 도시라면
                answer += 1;
                map.remove(index);
                map.add(city);
            } else { // 없는 도시라면
                answer += 5;
                if(map.size() == cacheSize && cacheSize > 0) { // 캐시 사이즈와 같아진다면
                    map.remove(0);
                }
                // 새로 반영하기
                if(cacheSize > 0) {
                    map.add(city); // 최근 접근한 초를 저장하기
                }
            }
        }



        return answer;
    }
}