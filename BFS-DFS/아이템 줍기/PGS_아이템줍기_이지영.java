import java.util.*;

public class PGS_아이템줍기_이지영 {

    int N = 102;
    int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    int[][] map;

    static class Point {
        int x;
        int y;
        int dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        map = new int[N][N];

        for (int i=0; i<rectangle.length; i++) {
            int startX = rectangle[i][0]*2;
            int startY = rectangle[i][1]*2;
            int endX = rectangle[i][2]*2;
            int endY = rectangle[i][3]*2;

            checkEdge(startX, startY, endX, endY);
            checkInside(startX, startY, endX, endY);
        }

        answer = bfs(characterX*2, characterY*2, itemX*2, itemY*2);

        return answer;

    }

    void checkEdge(int startX, int startY, int endX, int endY) {
        for (int i=startX; i<=endX; i++) {
            if(map[i][startY]==0) map[i][startY] = 2;
            if(map[i][endY]==0) map[i][endY] = 2;
        }

        for (int i=startY; i<=endY; i++) {
            if(map[startX][i]==0) map[startX][i] = 2;
            if(map[endX][i]==0) map[endX][i] = 2;
        }
    }

    void checkInside(int startX, int startY, int endX, int endY) {
        for (int i=startX+1; i<endX; i++) {
            for (int j=startY+1; j<endY; j++) {
                map[i][j] = 1;
            }
        }
    }

    int bfs(int startX, int startY, int endX, int endY) {
        boolean[][] visited = new boolean[N][N];
        Queue<Point> q = new ArrayDeque<>();

        q.add(new Point(startX, startY, 0));
        visited[startX][startY] = true;

        while(!q.isEmpty()) {
            Point now = q.poll();

            if(now.x==endX && now.y==endY) {
                return now.dist/2;
            }

            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
                if(map[nx][ny]<2 || visited[nx][ny]) continue;

                q.add(new Point(nx, ny, now.dist + 1));
                visited[nx][ny] = true;
            }
        }

        return -1;
    }
}
