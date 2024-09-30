import java.util.*;

class Genre implements Comparable<Genre> {
    int total;
    // 가장 먼저 수록 됨
    int tmax1;
    int imax1;
    // 두번째로 수록됨
    int tmax2;
    int imax2;
    
    public Genre() {
        this.total = 0; this.tmax1 = -1; this.imax1 = -1; this.tmax2 = -1; this.imax2 = -1;
    }
    
    public void addTime(int time) {
        this.total += time;
    }
    
    // 많이 재생된 노래 먼저 수록 => 재생횟수 같다면 고유번호 낮은 노래 먼저 수록
    public void updateMax(int time, int idx) {
        int uidx = -1;
        
        if(time > tmax1) uidx = 1;
        else if(time == tmax1 && imax1 > idx) uidx = 1;
        else if(time == tmax1 && imax1 < idx) uidx = 2;
        else if(time > tmax2) uidx = 2;
        else if(time == tmax2 && imax2 > idx) uidx = 2;
        
        if(uidx == 1) {
            tmax2 = tmax1; imax2 = imax1;
            tmax1 = time; imax1 = idx;
        }
        else if(uidx == 2) {
            tmax2 = time; imax2 = idx;
        }
    }
    
    @Override
    public int compareTo(Genre o) {
        return o.total - this.total;
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int n = genres.length;
        HashMap<String, Genre> map = new HashMap<>();
        
        for(int i=0; i<n; i++) {
            String genre = genres[i];
            int time = plays[i];
            
            Genre cur;
            if(map.containsKey(genre)) cur = map.get(genre);
            else cur = new Genre();
            
            cur.addTime(time);
            cur.updateMax(time, i);
            map.put(genre, cur);
        }
        
        List<String> keySet = new ArrayList<>(map.keySet());

        // Value 값으로 오름차순 정렬
        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return map.get(o1).compareTo(map.get(o2));
            }
        });
        
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<keySet.size(); i++) {
            Genre cur = map.get(keySet.get(i));
            
            queue.offer(cur.imax1);
            if(cur.tmax2 != -1) queue.offer(cur.imax2);
        }
        
        int size = queue.size();
        int[] result = new int[size];
        for(int i=0; i<size; i++) {
            result[i] = queue.poll();
        }
        
        return result;
    }
}