import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        
        Queue<int[]> road = new ArrayDeque<>(); // 다리를 최종적으로 건너는 시간, 해당 트럭의 무게
        
        int n = truck_weights.length;
        int restWeight = weight; // 현재 도로 위 남는 무게
        int time = 1; // 현재 시각
        int idx = 0; // 현재 건널 예정인 트럭의 인덱스
        
        while(idx < n) { // 1초씩 증가하면서 자동차 빼고 놓기 반복
            // 올려진 차 중 도착한 차 뺌
            if(!road.isEmpty() && time == road.peek()[0]) {
                restWeight += road.peek()[1];
                road.poll();
            }
            
            // 새로운 차 
            int cur = truck_weights[idx];
            if(cur <= restWeight) {
                restWeight -= cur;
                System.out.println("현재 추가된 차 무게 : " + cur +", 현재 시각 : "+ time);
                road.offer(new int[] {time + bridge_length, cur});
                idx++;
            }
            
            time++;
        }
        
        while(!road.isEmpty()) {
            time = Math.max(time, road.poll()[0]);
        }

        return time;
    }
}