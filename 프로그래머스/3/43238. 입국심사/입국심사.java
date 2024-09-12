import java.util.*;

class Solution { 
    public long solution(int n, int[] times) {
        long left = 1;
        long right = (long)Math.pow(10, 9)*(long)Math.pow(10, 9); // 기다리는 사람 * 걸리는 시간 최대
        
        long time = Long.MAX_VALUE;
        
        while(left <= right) {
            long mid = (left+right)/2; // 심사에 걸리는 시간
            
            long cnt = 0; // 심사할 수 있는 사람 수
            for(int i=0; i<times.length; i++) {
                cnt += mid/times[i];
            }
            
            // 모든 사람 심사 가능
            if(cnt >= n) {
                time = Math.min(mid, time);
                right = mid - 1; // 더 작은 시간 심사 가능성 있음!!
            }
            // 모든 사람 심사 불가능
            else {
                left = mid + 1; // 심사에 걸리는 시간 더 커야!!
            }
        }
        
        return time;
    }
}