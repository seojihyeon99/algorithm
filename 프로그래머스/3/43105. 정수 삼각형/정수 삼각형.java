class Solution {
    public int solution(int[][] triangle) {
        
        int n = triangle.length;
        int m = triangle[n-1].length;
        int[][] dp = new int[n][m];
        
        dp[0][0] = triangle[0][0];
        for(int r=1; r<n; r++) {
            for(int i=0; i<r+1; i++) {
                if(i-1 < 0) {
                    dp[r][i] = dp[r-1][i] + triangle[r][i];
                } else {
                    dp[r][i] = Math.max(dp[r-1][i-1], dp[r-1][i]) + triangle[r][i];
                }
            }
        }
        
        int answer = 0;
        for(int i=0; i<m; i++) {
            answer = Math.max(answer, dp[n-1][i]);
        }
        
        return answer;
    }
}