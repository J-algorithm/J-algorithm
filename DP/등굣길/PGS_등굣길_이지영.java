public class PGS_등굣길_이지영 {

    class Solution {
        int K = 1_000_000_007;
        int[][] dp;
        boolean[][] isPaddle;

        public int solution(int m, int n, int[][] puddles) {

            init(n, m, puddles);

            for (int i=1; i<n; i++) {
                for (int j=1; j<m; j++) {
                    if(isPaddle[i][j]) continue;

                    if(!isPaddle[i-1][j]) dp[i][j] += dp[i-1][j];
                    if(!isPaddle[i][j-1]) dp[i][j] += dp[i][j-1];

                    dp[i][j]%=K;
                }
            }

            return dp[n-1][m-1];
        }

        void init(int n, int m, int[][] puddles) {
            dp = new int[n][m];
            isPaddle = new boolean[n][m];

            for (int[] puddle : puddles) {
                int x = puddle[1]-1;
                int y = puddle[0]-1;
                isPaddle[x][y] = true;
            }

            dp[0][0] = 1;

            for (int i=1; i<n; i++) {
                if(!isPaddle[i][0]) dp[i][0] = dp[i-1][0];
            }
            for (int j=1; j<m; j++) {
                if(!isPaddle[0][j]) dp[0][j] = dp[0][j-1];
            }

        }
    }

}
