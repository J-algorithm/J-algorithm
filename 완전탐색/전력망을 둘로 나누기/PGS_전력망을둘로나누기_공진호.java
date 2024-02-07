import java.util.ArrayList;
import java.util.List;

public class PGS_전력망을둘로나누기_공진호 {

    static int dfs(int cur, boolean[] visited, boolean[] isCut, List<Integer>[] adjList) {
        visited[cur] = true;
        int cnt = 1;
        for (int next : adjList[cur]) {
            if (visited[next]) {
                continue;
            }
            // 자른 전선은 패스
            if (isCut[next] && isCut[cur]) {
                continue;
            }
            cnt += dfs(next, visited, isCut, adjList);
        }
        return cnt;
    }


    public int solution(int n, int[][] wires) {

        // 어떤 전선을 나눠도 2개의 트리로 나누어짐
        // 나눈 트리 하나에 대하여 DFS 돌리기
        List<Integer>[] adjList = getAdjList(n, wires);
        int minDiff = n;

        for (int i = 0; i < n - 1; i++) {
            // 나누는 전선
            boolean[] visited = new boolean[n + 1];
            boolean[] isCut = new boolean[n + 1];
            isCut[wires[i][0]] = true;
            isCut[wires[i][1]] = true;
            int result = dfs(wires[i][0], visited, isCut, adjList);
            // A번 트리의 노드 개수 : result
            // B번 트리의 노드 개수 : n - result
            minDiff = Math.min(minDiff, Math.abs(n - result - result));
        }


        return minDiff;
    }

    // 인접리스트 생성
    private static List<Integer>[] getAdjList(int n, int[][] wires) {
        List<Integer>[] adjList = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            adjList[i] = new ArrayList<>();
        }
        // 인접리스트 세팅
        for (int i = 0; i < n - 1; i++) {
            int a = wires[i][0];
            int b = wires[i][1];
            adjList[a].add(b);
            adjList[b].add(a);
        }
        return adjList;
    }
}
