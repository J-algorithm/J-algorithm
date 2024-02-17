public class PGS_도둑질_이지영 {

    class Solution {
        public int solution(int[] money) {
            int N = money.length;
            int[][] dp = new int[N+1][2];

            for (int i=2; i<=N; i++) {
                dp[i][0] = Math.max(dp[i-2][0]+money[i-2], dp[i-1][0]);
                dp[i][1] = Math.max(dp[i-2][1]+money[i-1], dp[i-1][1]);
            }

            return Math.max(dp[N][0], dp[N][1]);
        }
    }

}
