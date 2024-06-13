public class PGS_금과은운반하기_이지영 {

    class Solution {
        int N;
        public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
            N = w.length;

            long start = 0, end = 400_000_000_000_000L;
            while (start <= end) {
                long mid = (start + end) / 2;

                if(canCarry(mid, a, b, g, s, w, t)) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            return start;
        }

        // time 내에 운반할 수 없는 경우
        // 1. time 내내 금을 운반해도 금이 모자를때
        // 2. time 내내 은을 운반해도 은이 모자를때
        // 3. time 내내 금+은을 운반해도 금+은을 채울 수 없을때

        boolean canCarry(long time, int a, int b, int[] g, int[] s, int[] w, int[] t) {
            long goldSum = 0, silverSum = 0, weightSum = 0;

            for (int i=0; i<N; i++) {
                int roundTripTime = t[i] * 2;
                long cnt = time / roundTripTime;
                if (time % roundTripTime >= t[i]) cnt++;

                long weight = Math.min(cnt * w[i], g[i]+s[i]);

                goldSum += Math.min(weight, g[i]);
                silverSum += Math.min(weight, s[i]);
                weightSum += weight;
            }

            if (goldSum < a || silverSum < b || weightSum < a+b) return false;
            return true;
        }
    }

}
