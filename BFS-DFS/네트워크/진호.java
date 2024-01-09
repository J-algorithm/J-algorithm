import java.util.*;

class Solution {

    static void bfs(int start, int[][] computers, boolean[] visited) {

        visited[start] = true;  //시작 지점 방문 표시
        int N = computers.length;
        Queue<Integer> Q = new ArrayDeque<>();
        Q.add(start);
        // 큐가 비어 있을 때 까지 반복
        while (!Q.isEmpty()) {
            int cur = Q.poll();
            for (int next = 0; next < N; next++) {
                // 인접한 컴퓨터에 대하여 방문하지 않았다면
                if (computers[cur][next] == 1 && !visited[next]) {
                    // 방문 표시 후 큐에 추가
                    visited[next] = true;
                    Q.add(next);
                }
            }
        }
    }

    public int solution(int n, int[][] computers) {

        boolean[] visited = new boolean[n];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            //방문하지 않은 컴퓨터라면
            if (!visited[i]) {
                //bfs 진행
                bfs(i, computers, visited);
                //네트워크 수 1 추가
                cnt++;
            }
        }
        return cnt;
    }
}