public class PGS_이모티콘할인행사_이지영 {

    class Solution {

        final int PLUS_CNT = 0, SALES = 1;
        int N, M;
        int[] answer;
        int[] rate = {0, 10, 20, 30, 40};

        public int[] solution(int[][] users, int[] emoticons) {
            N = emoticons.length;
            M = users.length;
            answer = new int[2];

            discount(0, new int[N], users, emoticons);

            return answer;
        }

        void discount(int now, int[] discountRate, int[][] users, int[] emoticons) {
            if(now==N) {
                int[] price = calculateEmoticonPrice(discountRate, emoticons);
                int[] result = calculateResult(discountRate, users, price);

                if(isOptimal(result)) {
                    answer[0] = result[0];
                    answer[1] = result[1];
                }

                return;
            }

            for (int i=0; i<rate.length; i++) {
                discountRate[now] = rate[i];
                discount(now+1, discountRate, users, emoticons);
            }
        }

        int[] calculateEmoticonPrice(int[] discountRate, int[] emoticons) {
            int[] result = new int[N];

            for (int i=0; i<N; i++) {
                result[i] = (int) (emoticons[i] * (double)(100 - discountRate[i])/100.0);
            }

            return result;
        }

        int[] calculateResult(int[] discountRate, int[][] users, int[] price) {
            int[] result = new int[2];

            for (int i=0; i<M; i++) {
                int sum = 0;
                for (int j=0; j<N; j++) {
                    if(users[i][0] > discountRate[j]) continue;
                    sum += price[j];
                }

                if (sum >= users[i][1]) result[PLUS_CNT]++;
                else result[SALES] += sum;
            }

            return result;
        }

        boolean isOptimal(int[] result) {
            if(result[0] > answer[0]) return true;
            if(result[0] == answer[0] && result[1] > answer[1]) return true;

            return false;
        }
    }

}
