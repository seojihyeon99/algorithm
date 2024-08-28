import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 최소힙
        for(int i=0; i<scoville.length; i++) {
            pq.offer(scoville[i]);
        }
        
        int answer = -1;
        int cnt = 0; // 반복횟수
        while(!pq.isEmpty()) {
            // 모든 음식의 스코빌지수 K이상인지 확인 => 가장 낮은게 K이상이면, 모두 K이상임!
            if(!pq.isEmpty()) {
                int peek = pq.peek();
                if(peek >= K) {
                    answer = cnt;
                    break;
                }
            }
            
            int first = -1;
            int second = -1;
            
            if(!pq.isEmpty()) first = pq.poll(); 
            if(!pq.isEmpty()) second = pq.poll();
            
            // 스코빌 지수 섞음
            if(first!= -1 && second != -1) {
                cnt++;
                int cur = first + (second*2);           
                pq.offer(cur);
            }
        }
        
        return answer;
    }
}