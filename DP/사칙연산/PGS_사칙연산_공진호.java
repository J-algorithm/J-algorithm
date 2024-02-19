public class PGS_사칙연산_공진호 {

    // dp[i][j][0] : [i,j]의 최소 값
    // dp[i][j][1] : [i,j]의 최대 값
    int[][][] dp;
    int[] num; // 피연산자
    boolean[] isMinus; // 해당 연산자가 - 인지 여부
    boolean[][] visited; // [i,j] 연산한 적이 있는지 체크 배열

    public int solution(String arr[]) {
        int arrN = arr.length;
        int opNum = arr.length / 2;
        init(arr, opNum, arrN);

        getCalc(0, arrN);
        return dp[0][arrN][1];
    }

    private void init(String[] arr, int opNum, int arrN) {
        dp = new int[opNum + 1][opNum + 1][2];
        isMinus = new boolean[opNum];
        visited = new boolean[opNum + 1][opNum + 1];
        num = new int[opNum + 1];
        for (int i = 0; i < arrN; i++) {
            if (i % 2 == 0) {
                int n = Integer.parseInt(arr[i]);
                num[i / 2] = n;
            } else {
                if (arr[i].equals("-")) {
                    isMinus[i / 2] = true;
                }
            }
        }
    }

    /**
     * s부터 e까지의 연산 수행
     * @param s : 시작 인덱스
     * @param e : 끝 인덱스
     * @return (min, max) 값
     */
    int[] getCalc(int s, int e) {
        // s - e 까지 계산한 적이 있다면 해당 값 리턴
        if (visited[s][e]) {
            return dp[s][e];
        }
        //  이제 s-e 계산 방문 표시
        visited[s][e] = true;
        if (s == e) {
            int n = num[s];
            return dp[s][e] = new int[]{n, n};
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = s; i < e; i++) {
            int[] left = getCalc(s, i);
            int[] right = getCalc(i + 1, e);

            if (isMinus[i]) {
                // left - right
                int minValue = left[0] - right[1];
                int maxValue = left[1] - right[0];
                min = Math.min(min, minValue);
                max = Math.max(max, maxValue);
            } else {
                // left + right
                int minValue = left[0] + right[0];
                int maxValue = left[1] + right[1];
                min = Math.min(min, minValue);
                max = Math.max(max, maxValue);
            }
        }
        return dp[s][e] = new int[]{min, max};
    }

    public static void main(String[] args) {
        PGS_사칙연산_공진호 solution = new PGS_사칙연산_공진호();
        String arr[] = {"1", "-", "3", "+", "5", "-", "8"};
        System.out.println(solution.solution(arr));
    }


}
