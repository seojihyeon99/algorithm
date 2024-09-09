import java.util.*;

class Solution {
    public int solution(int[] citations) {
        // 정렬 : O(nlogn)
        Arrays.sort(citations);
        
        int result = 0;
        // 이진탐색 : O(10000log10000)
        int l = 0;
        int r = 10000;
        while(l <= r) {
            int m = (l+r)/2; // h-index 후보
            
            int idx = Arrays.binarySearch(citations, m);
            int under = idx + 1; // 이하
            int upper = citations.length - idx; // 이상
            if(idx < 0) {
                idx = -idx - 1; // 넣을 수 있는 인덱스 위치
                under = idx;
                upper = citations.length - under;
            }

            if(upper >= m && under <= m) {
                result = Math.max(result, m);
                l = m+1;
            }
            else if(upper < m || under > m) {
                r = m-1;
            }
        }
        
        return result;
    }
}