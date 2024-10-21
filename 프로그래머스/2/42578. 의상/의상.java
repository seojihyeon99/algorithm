import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int n = clothes.length; // 의상수
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i=0; i<n; i++) {
            String type = clothes[i][1]; // 의상종류
            
            if(map.containsKey(type)) {
                map.put(type, map.get(type) + 1);
            }
            else {
                map.put(type, 1);
            }
        }
        
        int answer = 1;
        Iterator<String> keys = map.keySet().iterator();
        while(keys.hasNext()) {
            String key = keys.next();
            answer = answer * (map.get(key)+1);
        }
        answer = answer - 1; // 아무것도 입지 않는 경우는 없다
        
        return answer;
    }
}