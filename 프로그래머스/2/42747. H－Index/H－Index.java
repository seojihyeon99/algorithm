import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int n = citations.length; // 논문 수
        Arrays.sort(citations);
        
        int answer = 0; // H-Index
        for(int i=10000; i>=0; i--) {
            int idx = Arrays.binarySearch(citations, i);
            
            // 해당 숫자가 있는 경우
            int right = n-idx; // 이상 개수
            int left = idx+1; // 이하 개수
            
            // 해당 숫자가 없는 경우
            if(idx < 0) {
                idx = -idx-1;
                left = idx; // 이하 개수
                right = n-idx; // 이상 개수
            }
            
            if(left <= i && i <= right) answer = Math.max(answer, i);
        }
        
        return answer;
    }
}