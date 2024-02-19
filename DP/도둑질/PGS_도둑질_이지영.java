public class PGS_도둑질_이지영 {

    class Solution {
        public int solution(int[] money) {
            int N = money.length;
            int[] first = new int[N+1];
            int[] last = new int[N+1];

            for (int i=2; i<=N; i++) {
                first[i] = Math.max(first[i-2]+money[i-2], first[i-1]);
                last[i] = Math.max(last[i-2]+money[i-1], last[i-1]);
            }

            return Math.max(first[N], last[N]);
        }
    }

}
