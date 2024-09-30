import java.util.*;

class Solution {
    public int[] solution(int[] prices) {    
        int[] answer = new int[prices.length];
        
        Stack<int[]> stack = new Stack<>(); // 초, 가격      
        for(int i=0; i<prices.length; i++) {
            int time = i;
            int price = prices[time];
            
            while(!stack.isEmpty() && stack.peek()[1] > price) {
                int[] cur = stack.pop();
                answer[cur[0]] = time - cur[0];
            }
            
            stack.push(new int[] {time, price});
        }
        
        int finish = prices.length - 1; // 끝나는 시각 (초)
        while(!stack.isEmpty()) {
            int[] cur = stack.pop();
            answer[cur[0]] = finish - cur[0];            
        }
        
        return answer;
    }
}