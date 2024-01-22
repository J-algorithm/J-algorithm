import java.util.*;

class PGS_아이템줍기_공진호 {
    // 다각형의 테두리만 이동경로임

    static final int MAX_SIZE = 50 + 1;

    class Point {
        int x;
        int y;
        int dist;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        // 아 모르겠다!
        // 직사각형 부분 일단 다 채우기
        boolean[][] G = new boolean[MAX_SIZE][MAX_SIZE];

        for (int[] r : rectangle) {
            int leftX = r[0];
            int leftY = r[1];
            int rightX = r[2];
            int rightY = r[3];

            for (int i = leftX; i <= rightX; i++) {
                for (int j = leftY; j <= rightY; j++) {
                    G[i][j] = true;
                }
            }
        }

        boolean[][] visited = new boolean[MAX_SIZE][MAX_SIZE];
        boolean[][] route = new boolean[MAX_SIZE][MAX_SIZE];
        visited[0][0] = true;
        Queue<Point> Q = new ArrayDeque<>();

        int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
        Q.add(new Point(0, 0));

        // 테두리를 채우자
        while (!Q.isEmpty()) {
            Point cur = Q.poll();

            int x = cur.x;
            int y = cur.y;

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= MAX_SIZE || ny >= MAX_SIZE || visited[nx][ny]) {
                    continue;
                }
                if (G[nx][ny]) {
                    route[nx][ny] = true;
                    continue;
                }
                visited[nx][ny] = true;
                Q.add(new Point(nx, ny));
            }
        }

        // 이제 캐릭터 이동하자
        visited = new boolean[MAX_SIZE][MAX_SIZE];
        visited[characterX][characterY] = true;
        System.out.println(route[characterX][characterY]);
        Q = new ArrayDeque<>();
        Q.add(new Point(characterX, characterY, 0));


        while (!Q.isEmpty()) {
            Point cur = Q.poll();
            int x = cur.x;
            int y = cur.y;
            int dist = cur.dist;

            if (x == itemX && y == itemY) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= MAX_SIZE || ny >= MAX_SIZE || visited[nx][ny] || !route[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                Q.add(new Point(nx, ny, dist));
            }
        }

        return 0;
    }
}