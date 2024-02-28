public class PGS_정수삼각형_이지영 {

    class Solution {
        int N, M;
        int[][] dp;
        public int solution(int[][] triangle) {
            N = triangle.length;
            M = triangle[N-1].length;
            dp = new int[N][M];

            for (int i=0; i<M; i++) {
                dp[N-1][i] = triangle[N-1][i];
            }

            for (int i=N-2; i>=0; i--) {
                for (int j=0; j<triangle[i].length; j++) {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i+1][j+1]);
                    dp[i][j] += triangle[i][j];
                }
            }

            return dp[0][0];
        }

    }

}
