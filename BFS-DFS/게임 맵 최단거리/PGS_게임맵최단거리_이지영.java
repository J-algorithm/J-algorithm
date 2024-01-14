import java.util.*;

class Point {
    int x;
    int y;
    int dist;

    public Point(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class PGS_게임맵최단거리_이지영 {
    int N, M;
    int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    boolean[][] visited;

    public int solution(int[][] maps) {
        init(maps);

        int answer = bfs(new Point(0, 0, 1), new Point(N-1, M-1, 0), maps);

        return answer;
    }

    void init(int[][] maps) {
        N = maps.length;
        M = maps[0].length;

        visited = new boolean[N][M];
    }

    int bfs(Point start, Point goal, int[][] maps) {
        Queue<Point> q = new ArrayDeque<>();
        visited[start.x][start.y] = true;
        q.add(start);

        while(!q.isEmpty()) {
            Point now = q.poll();
            if(now.x == goal.x && now.y == goal.y) {
                return now.dist;
            }

            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
                if(maps[nx][ny]==0 || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new Point(nx, ny, now.dist+1));
            }
        }

        return -1;
    }
}
