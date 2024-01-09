import java.util.*;

class Solution {
    static class Point {
        int x;
        int y;
        int cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    public int solution(int[][] maps) {
        int N = maps.length; // 세로
        int M = maps[0].length; // 가로
        boolean[][] visited = new boolean[N][M]; //방문 배열
        visited[0][0] = true; // 시작위치

        Queue<Point> Q = new ArrayDeque<>(); // BFS을 위한 큐 자료구조

        Q.add(new Point(0, 0, 1));// 시작위치 추가
        int[] dx = {-1, 1, 0, 0}; // 이동 가능한 위치
        int[] dy = {0, 0, -1, 1};
        while (!Q.isEmpty()) {
            Point cur = Q.poll();
            // 현재 위치
            int x = cur.x;
            int y = cur.y;
            int cnt = cur.cnt;
            if (x == N - 1 && y == M - 1) { // 도착했다면
                return cnt; //cnt 리턴
            }

            for (int i = 0; i < 4; i++) { // 4방향에 대하여 반복 진행
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 이동할 수 없는 위치일 경우(격자 벗어나거나, 이미 방문했거나, 0이거나)
                if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || maps[nx][ny] == 0) {
                    continue; //다음 반복문 진행
                }
                visited[nx][ny] = true; //방문 표시
                Q.add(new Point(nx, ny, cnt + 1)); // 큐에 추가
            }
        }
        //찾을 수 없다면 -1 리턴
        return -1;
    }
}