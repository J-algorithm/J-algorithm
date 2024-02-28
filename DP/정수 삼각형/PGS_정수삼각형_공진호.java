import java.util.Arrays;

public class PGS_정수삼각형_공진호 {
    public int solution(int[][] triangle) {
        int N = triangle.length;

        // maxValue = 500 * 10_000 = 5_000_000
        int[] dp = new int[N];

        // (i,j)의 위치에 오는 경우는
        // (i-1,j-1)의 위치 or (i-1,j)의 위치이다.
        int maxLen = 0;
        for (int[] row : triangle) {
            for (int j = maxLen; j >= 0; j--) {
                dp[j + 1] = Math.max(dp[j + 1], dp[j]) + row[j];
            }
            maxLen++;
        }
        // 최댓값 출력
        return Arrays.stream(dp).max().getAsInt();
    }
}
