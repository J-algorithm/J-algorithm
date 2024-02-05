
public class PGS_피로도_공진호 {


    int dfs(int depth, int flag, int fatigue, int[][] dungeons) {
        if (depth == dungeons.length) {
            // 모든 던전을 다 돌았을 때
            return 0;
        }
        // 던전 횟수
        int cnt = 0;
        for (int i = 0; i < dungeons.length; i++) {
            int minFatigue = dungeons[i][0];
            int consumeFatigue = dungeons[i][1];
            // 최소 필요 피로도를 만족하고, 방문하지 않은 던전일 때
            if (minFatigue <= fatigue && (flag & 1 << i) == 0) {
                cnt = Math.max(cnt, 1 + dfs(depth + 1, flag | 1 << i, fatigue - consumeFatigue, dungeons));
            }
        }
        return cnt;
    }

    public int solution(int k, int[][] dungeons) {
        // 던전의 개수는 최대 8개
        // 순열 => 8! = 40320
        return dfs(0, 0,k, dungeons);
    }

}
