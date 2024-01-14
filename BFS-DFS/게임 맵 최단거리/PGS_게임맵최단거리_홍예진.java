import java.util.*;

class PGS_게임맵최단거리_홍예진 {
    // 동, 서, 남, 북
    int[] di = {0, 0, 1, -1};
    int[] dj = {1, -1, 0, 0};
    static class Point {
        int i, j;
        public Point(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
    public boolean inBoundary(int i, int j, int n, int m){
        return 0 <= i && 0 <= j && i < n && j < m;
    }
    public int solution(int[][] maps) {
        
        int n = maps.length;
        int m = maps[0].length;
        int[][] cost = new int[n][m]; // cost[i][j] : 0,0에서 i,j까지 도달하는 최단거리
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        maps[0][0] = 0; // 출발지 중복확인 방지
        
        int distance = 1;
        int qSize = q.size();
        while(!q.isEmpty()){
            distance++;
            while(qSize-- > 0){
                Point p = q.poll();
                for(int d =0; d < 4; d++){
                    int ni = p.i + di[d];
                    int nj = p.j + dj[d];
                    // 장외 or 벽
                    if(!inBoundary(ni, nj, n, m) || maps[ni][nj] == 0) continue;
                    
                    if(ni == n-1 && nj == m-1)
                        return distance;
                    
                    maps[ni][nj] = 0;
                    q.add(new Point(ni, nj));
                }
            }
            qSize = q.size();
        }
        
        return -1;
    }
}