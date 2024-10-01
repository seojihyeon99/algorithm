import java.util.*;

class Solution {
    public int solution(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i<nums.length; i++) {
            int num = nums[i];
            
            if(map.containsKey(num)) {
                map.replace(num, map.get(num)+1);
            } else {
                map.put(num, 1);
            }
        }
        
        int n = nums.length/2; // 선택할 폰켓몬 수
        int m = map.size(); // 서로 다른 폰켓몬 크기
        
        int answer = n < m ? n : m;
        return answer;
    }
}