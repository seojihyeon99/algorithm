import java.util.*;

class Node {
    int priority;
    boolean target;
    
    public Node(int priority) {
        this.priority = priority;
        this.target = false;
    }
    
    public void setTarget() {
        this.target = true;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<Node> queue = new ArrayDeque<>();
        
        for(int i=0; i<priorities.length; i++) {
            Node cur = new Node(priorities[i]);   
            if(i == location) cur.setTarget();
            
            queue.offer(cur); 
        }
        
        int answer = 0;
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            
            boolean isMax = true;
            Iterator<Node> it = queue.iterator();
            while(it.hasNext()) {
                Node next = it.next();
                if(cur.priority < next.priority) {
                    isMax = false;
                    break;
                }
            }
            
            if(isMax) {
                ++answer;
                if(cur.target) break;
            } else {
                queue.offer(cur);
            }
            
        }
        
        return answer;
    }
}