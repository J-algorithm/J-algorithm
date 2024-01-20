import java.util.*;

public class Main {
    public static void main(String[] args) {
//        int[][] rectangle = {{2, 2, 5, 5}, {1, 3, 6, 4}, {3, 1, 4, 6}};
//        int characterX = 1;
//        int characterY = 4;
//        int itemX = 6;
//        int itemY = 3;

        int[][] rectangle = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
        int characterX = 1;
        int characterY = 3;
        int itemX = 7;
        int itemY = 8;
        Solution s = new Solution();

        int ans = s.solution(rectangle, characterX, characterY, itemX, itemY);
    }
}

class Point {
    int x, y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return x+", "+y;
    }
}
class Solution {
    boolean[][] visited;
    int[][][] recBoundary;
    int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void setRecNum(int x, int y, int recNum){
        if(recBoundary[x][y][0] == 0){
            recBoundary[x][y][0] = recNum;
        } else {
            recBoundary[x][y][1] = recNum;
        }
    }
    // 사각형 모서리 설정 + 사각형 내부 좌표 접근 불가 처리
    public void setInRecInfo(int recNum, int[] rec){

        for(int x = rec[0]; x <= rec[2]; x++){
            for(int y = rec[1]; y <= rec[3]; y++){
                if(x == rec[0] || x == rec[2] || y == rec[1] || y == rec[3]){
                    setRecNum(x, y, recNum);
                } else {
                    visited[x][y] = true;
                }
            }
        }
    }
    public void init(int[][] rectangle){
        // 범위 밖의 좌표 접근 불가 처리
        Arrays.fill(visited[0], true);
        Arrays.fill(visited[51], true);
        for(int i =1; i < 51; i++){
            visited[i][0] = true;
            visited[i][51] = true;
        }

        // 사각형 인근 정보 설정
        for(int i = 0; i < rectangle.length; i++){
            setInRecInfo(i+1, rectangle[i]);
        }
    }


    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        visited = new boolean[52][52];
        recBoundary = new int[52][52][2];
        init(rectangle);
        // 사각형의 둘레 위 -> 경로가 정해져있다. 문제는 어느 방향으로 갈 것인가.
        // 인접한 좌표가 방문하지 않았으며, 어느 사각형의 내부에 있지 않으며, 같은 사각형의 번호를 가지고 있다면 이동한다.
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(characterX, characterY));
        visited[characterX][characterY] = true;
        int distance = 1, qSize = q.size();
        while(!q.isEmpty()){

            while(qSize-- > 0){
                Point now = q.poll();
                for(int[] d : delta){
                    Point next = new Point(now.x + d[0], now.y + d[1]);
                    // 범위내 좌표가 아니거나 사각형 내부좌표거나 사각형 바깥 좌표라면 넘어간다.
                    if(visited[next.x][next.y] || recBoundary[next.x][next.y][0] == 0) continue;
                    // now에서 next로 가는 루트가 사각형 내에 있다면 넘어간다.
                    if(isRouteInRec(now, next, rectangle)) continue;
                    // now에서 next로 가는 루트가 같은 사각형 위가 아니라면 넘어간다.
                    if(!isOnSameRec(now, next)) continue;

                    if(next.x == itemX && next.y == itemY)
                        return distance;
                    visited[next.x][next.y] = true;
                    q.add(next);
                }
            }
            qSize = q.size();
            distance++;
        }

        return answer;
    }

    private boolean isOnSameRec(Point now, Point next) {
        int recNum = recBoundary[now.x][now.y][0];
        if(recBoundary[next.x][next.y][0] == recNum || recBoundary[next.x][next.y][1] == recNum)
            return true;
        recNum = recBoundary[now.x][now.y][1];
        if(recNum == 0)
            return false;
        if(recBoundary[next.x][next.y][0] == recNum || recBoundary[next.x][next.y][1] == recNum)
            return true;
        return false;
    }

    private boolean isRouteInRec(Point now, Point next, int[][] rectangle) {
        float x = (now.x + next.x)/2.0f;
        float y = (now.y + next.y)/2.0f;

        for(int[] rec : rectangle){
            if(rec[0] < x && x < rec[2] && y > rec[1] && y < rec[3]){
                return true;
            }
        }

        return false;
    }
}