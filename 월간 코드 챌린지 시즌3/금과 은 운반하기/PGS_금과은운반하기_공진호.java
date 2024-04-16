public class PGS_금과은운반하기_공진호 {
    private boolean isValid(int a, int b, int[] g, int[] s, int[] w, int[] t, long time) {
        int N = g.length;

        long goldSum = 0;
        long silverSum = 0;
        long totalSum = 0;


        // 각각의 도시마다 금, 은, 총량을 계산
        for (int i = 0; i < N; i++) {
            long iter = 0;
            // 맨 처음은 편도로 이동
            if (time > t[i]) {
                iter++;
            }
            // 그 이후는 왕복으로 이동
            iter += (time - t[i]) / (2 * t[i]);

            goldSum += Math.min(g[i], w[i] * iter);
            silverSum += Math.min(s[i], w[i] * iter);
            totalSum += Math.min(g[i] + s[i], w[i] * iter);
        }
        // 금과 은 모두 만족하는지 체크
        if (goldSum < a || silverSum < b || totalSum < a + b) {
            return false;
        }
        return true;

    }

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int N = g.length;
        // 이분탐색 이용
        // 최대 2*10^5(최대 왕복시간) * 2*10^5(최대 크기)
        long lo = 0;
        long hi = 4 * (long) Math.pow(10, 5) * (long) Math.pow(10, 9);

        while (lo <= hi) {
            long m = (lo + hi) / 2;
            if (isValid(a, b, g, s, w, t, m)) {
                hi = m - 1;
            } else {
                lo = m + 1;
            }
        }
        return lo;
    }

    public static void main(String[] args) {

        PGS_금과은운반하기_공진호 solution = new PGS_금과은운반하기_공진호();

        int a = 10;
        int b = 10;
        int[] g = {100};
        int[] s = {100};
        int[] w = {7};
        int[] t = {10};

        System.out.println(solution.solution(a, b, g, s, w, t));

    }
}
