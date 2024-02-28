public class PGS_등굣길_공진호 {
    public final int MOD = 1_000_000_007;

    public int solution(int m, int n, int[][] puddles) {
        // (1,1) -> (m,n)
        int[][] dp = new int[m + 1][n + 1];

        // 웅덩이의 dp 값은 -1로 한다.
        for (int[] puddle : puddles) {
            int x = puddle[0];
            int y = puddle[1];
            dp[x][y] = -1;
        }

        dp[1][0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] += (dp[i - 1][j] + dp[i][j - 1]) % MOD;
            }
        }
        return dp[m][n];
    }
}
