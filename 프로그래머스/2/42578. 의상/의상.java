import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>(); // type, count
        
        for(int i=0; i<clothes.length; i++) {
            String type = clothes[i][1];

            if(map.containsKey(type)) {
                map.put(type, map.get(type) + 1);
            }
            else {
                map.put(type, 1);
            }
        }    
    
        int answer = 1;
        Set<String> keys = map.keySet();
        Iterator<String> it = keys.iterator();
        while(it.hasNext()) {
            String t = it.next();
            answer *= (map.get(t) + 1); // 해당 키들 중 선택 / 아무 키도 선택 x
        }
        answer = answer - 1; // 아무 키도 선택 안하는 경우
        
        return answer;
    }
}