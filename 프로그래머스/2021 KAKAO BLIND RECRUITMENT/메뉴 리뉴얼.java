import java.util.*;

/**
 * @author djunnni
 * 문제풀이: 각 order별로 조합을 수행할 예정 조합개수는 course에 적힌 숫자만큼 함.
 * 만들어진 항목은 Map에서 관리를 하고 2이상인 경우에 한해서만 오름차순으로 리턴
 * 13:42 - 14:42
 */
class Solution {
    Map<String, Integer> map;
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>(); // 정답을 받을 배열 리스트
        
        map = new HashMap<String, Integer>();
        for(int co : course) { // 코스요리 수로 조합 진행
            for(String order : orders) {
                combination(order.toCharArray(), new char[co], 0, 0, co);
           }
        }
        // 결과 맵 출력
        // System.out.println(map);
        
        Map<Integer, List<String>> _temp = new HashMap<>();
        for(int co : course) {
            _temp.put(co, new ArrayList<String>());
        }
        Iterator iter = map.keySet().iterator();
        
        while(iter.hasNext()) {
            String key = (String) iter.next();
            int count = map.get(key);
            
            if(count >= 2) {
                List<String> list = _temp.get(key.length());
                if(list.isEmpty()) {
                    list.add(key);
                } else {
                    int cnt = map.get(list.get(0));
                    if(cnt > count) continue;
                    else if(cnt < count) {
                        list.clear();
                    }
                    list.add(key);
                }
            }
        }
        for(int co : course) {
            answer.addAll(_temp.get(co));
        }
        
        Collections.sort(answer);
        
        return answer.toArray(new String[answer.size()]);
    }
    public void combination(char[] data, char[] arr, int start, int count, int MAX) {
        if(count == MAX) {
            char[] x = arr.clone();
            Arrays.sort(x); // 오름차순으로 저장하기
            String v = new String(x);
            map.put(v, map.getOrDefault(v, 0) + 1);
            return;
        }
        
        for(int i = start; i < data.length; i++) {
            arr[count] = data[i];
            combination(data, arr, i + 1, count + 1, MAX);
        }
    }
}