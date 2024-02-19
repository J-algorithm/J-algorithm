public class PGS_도둑질_공진호 {

    public int solution(int[] money) {
        int N = money.length;
        int lastIdx = N-1;

        // firstX : 첫 번째 집을 털지 않는 경우
        // firstO : 첫 번째 집을 터는 경우
        int[] firstO = new int[N];
        int[] firstX = new int[N];

        firstO[0] =  firstO[1] = money[0];
        firstX[1] = money[1];


        // N => 1_000_000
        for(int i = 2; i<N-1; i++){
            // i 번쨰 집을 털려면 i-1 번째 집을 털면 안됨
            firstO[i] = Math.max(firstO[i - 1], firstO[i - 2] + money[i]);
            firstX[i] = Math.max(firstX[i - 1], firstX[i - 2] + money[i]);
        }
        // 마지막 집은 0번 쨰 집과 연결되어 있음
        firstX[lastIdx] = Math.max(firstX[lastIdx - 1], firstX[lastIdx - 2] + money[lastIdx]);
        firstO[lastIdx] = firstO[lastIdx - 1];

        int maxMoney = Math.max(firstX[lastIdx], firstO[lastIdx]);
        return maxMoney;
    }


    public int solution2(int[] money) {
        int N = money.length;
        int lastIdx = N-1;

        int[][] dp = new int[N][2];
        // dp[?][0] : 첫 번째 집을 털지 않는 경우
        // dp[?][1] : 첫 번째 집을 터는 경우
        dp[0][1] = money[0];
        dp[1][0] = money[1];
        dp[1][1] = money[0];

        // N => 1_000_000
        // 시간초과 발생
        for(int i = 2; i<N-1; i++){
            // i 번쨰 집을 털려면 i-1 번째 집을 털면 안됨
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][0] + money[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][1] + money[i]);
        }
        // 마지막 집은 0번 쨰 집과 연결되어 있음
        dp[lastIdx][0] = Math.max(dp[lastIdx - 1][0] ,dp[lastIdx - 2][0] + money[lastIdx]);
        dp[lastIdx][1] = dp[lastIdx-1][1];

        int maxMoney = Math.max(dp[lastIdx][0], dp[lastIdx][1]);
        return maxMoney;
    }


    public static void main(String[] args) {
        PGS_도둑질_공진호 solution = new PGS_도둑질_공진호();
        int money[] = {1, 2, 3};
        System.out.println(solution.solution(money));
    }



}
