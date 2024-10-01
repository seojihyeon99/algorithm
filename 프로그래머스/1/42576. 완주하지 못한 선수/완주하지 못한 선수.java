import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        String s = participant[participant.length - 1]; // 완주 못한 선수 이름
        int n = completion.length;
        for(int i=0; i<n; i++) {
            if(!participant[i].equals(completion[i])) {
                s = participant[i];
                break;
            }
        }
        
        return s;
    }
}