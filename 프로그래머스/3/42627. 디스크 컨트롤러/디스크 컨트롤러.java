import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) { // 요청시각 1차 정렬, 소요시간 2차 정렬
                if(o1[0] == o2[0]) return o1[1] - o2[1]; 
                return o1[0] - o2[0];   
            }
        });
        
        Queue<int[]> job = new ArrayDeque<>(); // 실행 중인 요청 (요청시각, 처리시작시간, 소요시간)
        PriorityQueue<int[]> wait = new PriorityQueue<>(new Comparator<int[]>() { // 대기 중인 요청 (요청시각, 소요시간)
            @Override
            public int compare(int[] o1, int[] o2) { // 소요시간 1차 정렬, 요청시각 2차 정렬
                if(o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });
        
        int sum = 0;
        int n = jobs.length; // 총 요청의 개수
        int idx = 1; // 현재 wait 들어간 요청의 수
        job.offer(new int[] {jobs[0][0], jobs[0][0], jobs[0][1]}); // 먼저 들어온 요청부터 처리
        
        while(true) {
            // 모든 작업 다 끝냄
            if(job.isEmpty()) break;
            
            // job큐의 작업 끝냄
            int[] finish = job.poll();
            int cur = finish[1] + finish[2]; // 현재시각 (작업 끝난 시각)
            sum += (cur - finish[0]);
            
            // wait큐에 넣을 수 있는 작업 있는지 봄
            for(int i=idx; i<n; i++) {
                if(jobs[i][0] <= cur) {
                    wait.offer(new int[] {jobs[i][0], jobs[i][1]});
                    idx++;
                } else {
                    break;
                }
            }
            
            // wait큐에 있는 작업 하나 시작 => job큐로
            // 1) wait큐 비어있는 경우
            if(wait.isEmpty() && idx < n) {
                cur = jobs[idx][0];
                wait.offer(new int[] {jobs[idx][0], jobs[idx][1]});
                idx++;
            }
            
            // 2) wait큐 비어있지 않은 경우
            if(!wait.isEmpty()) {
                int[] next = wait.poll();
                job.offer(new int[] {next[0], cur, next[1]});       
            }
        }
        
        int answer = sum / n;
        return answer;
    }
}