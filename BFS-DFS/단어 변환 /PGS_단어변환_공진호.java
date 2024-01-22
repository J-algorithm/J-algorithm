import java.util.*;

class PGS_단어변환_공진호 {

    //두 단어 간 알파벳 차 개수
    int getDiffCnt(String a, String b) {
        int cnt = 0;
        int len = a.length();
        for (int i = 0; i < len; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                cnt++;
            }
        }
        return cnt;
    }


    public int solution(String begin, String target, String[] words) {

        Set<String> hs = new HashSet<>();
        Set<String> isUsed = new HashSet<>();
        for (String word : words) {
            hs.add(word);
        }

        Queue<String> Q = new ArrayDeque<>();
        Q.add(begin);
        isUsed.add(begin);

        int cnt = 0;
        while (!Q.isEmpty()) {
            int qSize = Q.size();
            // 큐사이즈 만큼 반복
            while (qSize-- > 0) {
                String cur = Q.poll();
                if (cur.equals(target)) {
                    return cnt;
                }
                for (String word : words) {
                    int diffCnt = getDiffCnt(cur, word);
                    // 알파벳 1개 차이나고, 사용한 적 없는 단어라면
                    if (diffCnt == 1 && !isUsed.contains(word)) {
                        Q.add(word);
                        isUsed.add(word);
                    }
                }
            }
            cnt++;
        }
        return 0;
    }
}