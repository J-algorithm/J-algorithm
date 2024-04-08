public class PGS_양과늑대_공진호 {


    int answer = 0;
    boolean[] visited;

    private void dfs(int sheep, int wolf, int[] info, int[][] edges) {
        // 늑대가 같거나 더 많다면 return
        if (sheep <= wolf) {
            return;
        }

        // 최대 양 갱신
        answer = Math.max(answer, sheep);

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            // 새로운 노드에 방문
            if (visited[from] && !visited[to]) {
                visited[to] = true;
                if (info[to] == 0) {
                    dfs(sheep + 1, wolf, info, edges);
                } else {
                    dfs(sheep, wolf + 1, info, edges);
                }
                visited[to] = false;
            }
        }
    }

    public int solution(int[] info, int[][] edges) {
        int N = info.length;

        visited = new boolean[N];
        visited[0] = true;
        dfs(1, 0, info, edges);
        return answer;
    }
}
