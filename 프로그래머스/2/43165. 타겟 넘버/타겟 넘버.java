import java.util.*;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0; // 만들 수 있는 가지수
        
        Queue<int[]> queue = new ArrayDeque<>(); // 현재까지의 합, 현재 숫자의 인덱스
        queue.offer(new int[] {numbers[0], 0});
        queue.offer(new int[] {-1*numbers[0], 0});
        
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int sum = cur[0];
            
            // 타겟 넘버와 같다면
            if(cur[1] == numbers.length-1 && sum == target) {
                answer++;
            }
            
            if(cur[1] + 1 < numbers.length) {         
                queue.offer(new int[] {sum + numbers[cur[1] + 1], cur[1] + 1});      
                queue.offer(new int[] {sum + -1*numbers[cur[1] + 1], cur[1] + 1});
            }
            
        }
        
        return answer;
    }
}