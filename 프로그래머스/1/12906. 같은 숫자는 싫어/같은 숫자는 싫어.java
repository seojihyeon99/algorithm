import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int n = arr.length;
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        
        queue.offer(arr[0]);
        for(int i=1; i<n; i++) {
            if(queue.peekLast() == arr[i]) continue;
            
            queue.offer(arr[i]);
        }
        
        n = queue.size();
        int[] result = new int[n];
        for(int i=0; i<n; i++) {
            result[i] = queue.poll();
        }
        
        return result;
    }
}