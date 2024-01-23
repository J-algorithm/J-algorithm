import java.util.*;

public class Main {
    public static void main(String[] args) {

        int[][] game_board = {{1,1,0,0,1,0},{0,0,1,0,1,0},{0,1,1,0,0,1},{1,1,0,1,1,1},{1,0,0,0,1,0},{0,1,1,1,0,0}};
        int[][] table = {{1,0,0,1,1,0},{1,0,1,0,1,0},{0,1,1,0,1,1},{0,0,1,0,0,0},{1,1,0,1,1,0},{0,1,0,0,0,0}};

        Solution s = new Solution();
        s.solution(game_board, table);
    }
}

class Point {
    int i,j;
    public Point(int i, int j){
        this.i = i;
        this.j = j;
    }
}

class Puzzle {
    int size;
    List<Point> pointList;
    public Puzzle(int size, List<Point> pointList){
        this.size = size;
        this.pointList = pointList;
    }
}
class Solution {
    static int n, m;
    static int[][] delta = {{1, 0}, {-1, 0}, {0,1}, {0, -1}};
    static List<Puzzle> puzzleList;
    static Puzzle[][] puzzleArr;
    // 1. table위에 놓인 puzzle에 번호를 매긴다.
    public int setPuzzleNumber(int[][] table){
        boolean[][] visited = new boolean[n][n];

        int puzzleNumber = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j]) continue;
                if(table[i][j] == 0) {
                    visited[i][j] = true;
                    continue;
                }

                bfs(i, j, visited, puzzleNumber, table);
                puzzleNumber++;
            }
        }

        int puzzleCount = puzzleNumber-1;
        return puzzleCount;
    }

    // 1.1. 시작점 i,j에 연결된 puzzle의 영역에 번호(puzzleNumber)를 매긴다.
    private void bfs(int i, int j, boolean[][] visited, int puzzleNumber, int[][] table) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j));
        visited[i][j] = true;
        table[i][j] = puzzleNumber;
        while(!q.isEmpty()){
            Point point = q.poll();

            for(int[] d : delta){
                Point next = new Point(point.i + d[0], point.j+d[1]);
                // table위에 좌표가 아니거나, 방문한 좌표라면
                if(!inBoundary(next.i, next.j) || visited[next.i][next.j]) continue;
                // 빈칸이라면
                if(table[next.i][next.j] == 0) {
                    visited[next.i][next.j] = true;
                    continue;
                }
                visited[next.i][next.j] = true;
                table[next.i][next.j] = puzzleNumber;
                q.add(next);
            }
        }
    }


    public void initPuzzle(int[][] table) {

        puzzleList = new ArrayList<>();

        boolean[][] visited = new boolean[n][n];
        int puzzleNumber = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(visited[i][j]) continue;
                if(table[i][j] == 0) {
                    visited[i][j] = true;
                    continue;
                }

                addPuzzle(i, j, visited, table, puzzleNumber);
            }
        }
    }

    public LinkedList<Point> getPointList(int i, int j, boolean[][] visited, int[][] table, int puzzleNumber){
        // 하나의 puzzle을 의미하는 point들의 집합을 찾는다.
        LinkedList<Point> pointList = new LinkedList<>();

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i, j));
        visited[i][j] = true;
        while(!q.isEmpty()){
            Point point = q.poll();
            pointList.add(new Point(point.i - i, point.j - j));

            for(int[] d : delta){
                Point next = new Point(point.i + d[0], point.j+d[1]);
                // table위에 좌표가 아니거나, 방문한 좌표라면
                if(!inBoundary(next.i, next.j) || visited[next.i][next.j]) continue;
                // 빈칸이라면
                if(table[next.i][next.j] == 0) {
                    visited[next.i][next.j] = true;
                    continue;
                }
                visited[next.i][next.j] = true;
                q.add(next);
            }
        }

        return pointList;
    }
    public void addPuzzle(int i, int j, boolean[][] visited, int[][] table, int puzzleNumber){
        // 퍼즐을 의미하는 일련의 좌표들
        // 시작점은 항상 (0,0)
        // 하나의 퍼즐을 구성하는 일련의 좌표를 찾기위해(bfs) queue 사용
        LinkedList<Point> pointList = getPointList(i, j, visited, table, puzzleNumber);

        // 연결된 모든 좌표를 찾았다면 pointList에 저장한다.
        Puzzle puzzle = new Puzzle(pointList.size(), pointList);
        puzzleList.add(puzzle);
    }
    public boolean inBoundary(int i, int j){
        return 0 <= i && 0 <= j && i < n && j < n;
    }

    public void addPuzzle(int[][] rTable){
        boolean[][] visited = new boolean[n][n];


        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < n; j++){
                if(rTable[i][j] == 0) continue;

            }
        }
    }
    public void addAllPuzzle(int[][] table){
        // 보드를 네방향으로 회전했을 때 나오는 모양을 저장.
        int[][][] rotate = new int[4][n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                rotate[0][i][j] = table[i][j];
                rotate[1][n-i][n-j] = table[i][j];
                rotate[2][j][n-i] = table[i][j];
                rotate[3][n-j][i] = table[i][j];
            }
        }

        // 각 회전한 모양별로 puzzle을 찾아서 추가한다.
        for(int[][] rTable : rotate)
            addPuzzle(rTable);
    }

    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        n = game_board.length;
        m = setPuzzleNumber(table); // 퍼즐의 수
        puzzleArr = new Puzzle[m][4]; // 네방향으로 회전한 퍼즐
        addAllPuzzle(table);

//        initPuzzle(table);
//        m = puzzleList.size();
//
//
//        boolean[][] visited = new boolean[n][n];
//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < n; j++){
//                if(visited[i][j]) continue;
//                if(game_board[i][j] == 0) {
//                    visited[i][j] = true;
//                    continue;
//                }
//                // 빈칸이라면
//                // bfs로 빈 영역의 좌표와 크기를 찾는다.
//                // 같은 크기를 가진 퍼즐들 중 회전해서 모양이 맞는다면 다음 좌표로 탐색을 이어간다.
//                // 모양이 맞는다 =
//                boolean[] used = new boolean[m];
//                for(int k = 0; k < m; k++){
//
//                    checkPuzzle(game_board, i, j);
//
//                }
//            }
//        }
        // 최대한 많은 조각을 게임보드에 채워넣었을때 채운 칸의 수를 return
        return answer;
    }
}