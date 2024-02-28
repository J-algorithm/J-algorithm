public class PGS_사칙연산_이지영 {

    class Solution {
        int N, M;
        int[][] min, max;
        public int solution(String arr[]) {
            init(arr);

            for (int i=0; i<M; i++) {
                int num = Integer.parseInt(arr[i*2]);
                min[i][i] = num;
                max[i][i] = num;
            }

            for (int k=1; k<M; k++) {
                for (int i=0; i<M-k; i++) {
                    int j = i+k;

                    int minResult = Integer.MAX_VALUE;
                    int maxResult = Integer.MIN_VALUE;
                    for (int op=i; op<j; op++) {
                        String operator = arr[op*2+1];
                        if(operator.equals("-")) {
                            minResult = Math.min(minResult, min[i][op]-max[op+1][j]);
                            maxResult = Math.max(maxResult, max[i][op]-min[op+1][j]);
                        } else {
                            minResult = Math.min(minResult, min[i][op]+min[op+1][j]);
                            maxResult = Math.max(maxResult, max[i][op]+max[op+1][j]);
                        }
                    }
                    min[i][j] = minResult;
                    max[i][j] = maxResult;
                }
            }

            return max[0][M-1];
        }

        void init(String arr[]) {
            N = arr.length;
            M = N/2 + 1;
            min = new int[M][M];
            max = new int[M][M];
        }

    }


}
