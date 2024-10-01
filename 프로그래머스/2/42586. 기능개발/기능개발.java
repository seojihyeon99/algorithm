import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length; // 총 기능의 개수
        
        int[] days = new int[n]; // 배포 가능까지 걸리는 날짜
        for(int i=0; i<n; i++) {
            days[i] = (int)Math.ceil((100 - progresses[i]) / (double)speeds[i]);
        }
        
        ArrayList<Integer> list = new ArrayList<>(); // 한번에 배포 가능한 개수
        for(int i=0; i<n; i++) {
            int start = days[i];
            int cnt = 1; // 하루에 함께 배포 가능한 작업의 개수
            while(i+1 < n && start >= days[i+1]) {
                i++;
                cnt++;
            }
            list.add(cnt);
        }
        
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}