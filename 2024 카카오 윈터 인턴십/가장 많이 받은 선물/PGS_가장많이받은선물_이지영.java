import java.util.*;

public class PGS_가장많이받은선물_이지영 {

    class Solution {

        int N;
        int[] in, out, giftCount;
        int[][] giftRecord;
        Map<String, Integer> friendsInfo;

        public int solution(String[] friends, String[] gifts) {
            int answer = 0;

            init(friends);
            initGiftRecord(gifts);

            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if(isReciever(i, j)) giftCount[i]++;
                }
            }

            for (int i=0; i<N; i++) {
                answer = Math.max(answer, giftCount[i]);
            }

            return answer;
        }

        void init(String[] friends) {
            N = friends.length;

            in = new int[N];
            out = new int[N];
            giftCount = new int[N];
            giftRecord = new int[N][N];

            friendsInfo = new HashMap<>();
            for (int i=0; i<N; i++) {
                friendsInfo.put(friends[i], i);
            }
        }

        void initGiftRecord(String[] gifts) {
            for (String gift : gifts) {
                String[] str = gift.split(" ");

                int a = friendsInfo.get(str[0]);
                int b = friendsInfo.get(str[1]);

                giftRecord[a][b]++;
                out[a]++;
                in[b]++;
            }
        }

        boolean isReciever(int a, int b) {
            if (giftRecord[a][b] > giftRecord[b][a]) {
                return true;
            } else if (giftRecord[a][b] < giftRecord[b][a]) {
                return false;
            } else if ((out[a] - in[a]) > (out[b] - in[b])) {
                return true;
            } else return false;
        }
    }

}
