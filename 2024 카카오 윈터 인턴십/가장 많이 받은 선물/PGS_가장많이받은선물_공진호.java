import java.util.*;

public class PGS_가장많이받은선물_공진호 {
    public int solution(String[] friends, String[] gifts) {
        int N = friends.length;
        HashMap<String, Integer> hm = new HashMap<>();
        int[][] giftInfo = new int[N][N]; // 주고 받은 선물 정보
        int[] giftIndex = new int[N]; // 선물 지수
        for (int i = 0; i < N; i++) {
            String name = friends[i];
            hm.put(name, i);
        }

        for (String gift : gifts) {
            StringTokenizer st = new StringTokenizer(gift);
            String from = st.nextToken();
            String to = st.nextToken();

            int fromIdx = hm.get(from);
            int toIdx = hm.get(to);

            giftIndex[fromIdx]++;
            giftIndex[toIdx]--;

            giftInfo[fromIdx][toIdx]++;
        }


        int maxCnt = 0;
        //가장 선물 많이 받은 사람의 선물 수 찾기
        for (int i = 0; i < N; i++) {
            int cnt = 0;

            for (int j = 0; j < N; j++) {
                if (i == j) {
                    continue;
                }

                if (giftInfo[i][j] > giftInfo[j][i]) {
                    cnt++;
                }
                if (giftInfo[i][j] == giftInfo[j][i]) {
                    if (giftIndex[i] > giftIndex[j]) {
                        cnt++;
                    }
                }
            }
            maxCnt = Math.max(maxCnt, cnt);

        }
        return maxCnt;
    }

}