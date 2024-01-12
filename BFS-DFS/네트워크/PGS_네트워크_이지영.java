import java.util.*;

public class PGS_네트워크_이지영 {
    int N;
    boolean[] visited;
    boolean[][] isLinked;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        init(n, computers);

        for (int i=0; i<N; i++) {
            if(!visited[i]) {
                bfs(i);
                answer++;
            }
        }

        return answer;
    }

    void init(int n, int[][] computers) {
        N = n;
        visited = new boolean[N];
        isLinked = new boolean[N][N];

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if(computers[i][j]>0) isLinked[i][j] = true;
            }
        }
    }

    void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();

        visited[start] = true;
        q.add(start);

        while(!q.isEmpty()) {
            int now = q.poll();

            for (int next=0; next<N; next++) {
                if(!isLinked[now][next] || visited[next]) continue;
                visited[next] = true;
                q.add(next);
            }
        }
    }
}
