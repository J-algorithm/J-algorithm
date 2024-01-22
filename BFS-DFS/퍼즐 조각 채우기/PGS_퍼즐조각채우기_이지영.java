import java.util.*;

public class PGS_퍼즐조각채우기_이지영 {
    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Block {
        Point start;
        List<Point> list;

        public Block(int x, int y) {
            this.start = new Point(x, y);
            this.list = new ArrayList<>();
        }
    }

    class Solution {
        final int HOLE = 0, PIECE = 1;

        int N;
        int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
        List<Block> holes, pieces;
        boolean[][][] visited;

        public int solution(int[][] game_board, int[][] table) {
            int answer = 0;

            init(game_board);

            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if(table[i][j]==PIECE && !visited[PIECE][i][j]) {
                        checkPiece(i, j, table);
                    }
                }
            }

            for (int k=0; k<4; k++) {
                game_board = rotateArray(game_board);

                for (int i=0; i<N; i++) {
                    for (int j=0; j<N; j++) {
                        if(game_board[i][j]==HOLE && !visited[HOLE][i][j]) {
                            checkHole(i, j, game_board);
                        }
                    }
                }

                List<Block> removeHoles = new ArrayList<>();

                for (int i=0; i<holes.size(); i++) {
                    for (int j=0; j<pieces.size(); j++) {
                        Block hole = holes.get(i);
                        Block piece = pieces.get(j);

                        if(isMatch(hole, piece)) {
                            answer += hole.list.size();
                            removeHoles.add(hole);

                            holes.remove(i);
                            pieces.remove(j);
                            i--;

                            break;
                        }
                    }
                }

                for (Block hole : removeHoles) {
                    removeHole(game_board, hole);
                }

            }


            return answer;
        }

        void init(int[][] arr) {
            N = arr.length;
            holes = new ArrayList<>();
            pieces = new ArrayList<>();
            visited = new boolean[2][N][N];
        }

        boolean isMatch(Block hole, Block piece) {
            if(hole.list.size() != piece.list.size()) {
                return false;
            }

            for (int i=0; i<hole.list.size(); i++) {
                Point p1 = hole.list.get(i);
                Point p2 = piece.list.get(i);

                if(p1.x!=p2.x || p1.y!=p2.y) return false;
            }

            return true;
        }

        void checkHole(int x, int y, int[][] game_board) {
            Block hole = new Block(x, y);
            hole.list = bfs(x, y, HOLE, game_board);

            holes.add(hole);
        }

        void checkPiece(int x, int y, int[][] table) {
            Block piece = new Block(x, y);
            piece.list = bfs(x, y, PIECE, table);

            pieces.add(piece);
        }

        List<Point> bfs(int x, int y, int type, int[][] arr) {
            Queue<Point> q = new ArrayDeque<>();
            List<Point> result = new ArrayList<>();

            q.add(new Point(x, y));
            result.add(new Point(0, 0));
            visited[type][x][y] = true;

            while(!q.isEmpty()) {
                Point now = q.poll();

                for (int i=0; i<4; i++) {
                    int nx = now.x+dx[i];
                    int ny = now.y+dy[i];

                    if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
                    if(arr[nx][ny]!=type || visited[type][nx][ny]) continue;

                    q.add(new Point(nx, ny));
                    result.add(new Point(nx-x, ny-y));
                    visited[type][nx][ny] = true;
                }
            }

            return result;
        }

        int[][] rotateArray(int[][] arr) {
            int[][] result = new int[N][N];

            holes.clear();
            for (int i=0; i<N; i++) {
                Arrays.fill(visited[HOLE][i], false);
            }

            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    result[j][N-1-i] = arr[i][j];
                }
            }

            return result;
        }

        void removeHole(int[][] arr, Block hole) {
            Point start = hole.start;
            List<Point> list = hole.list;

            for (int i=0; i<list.size(); i++) {
                int nx = start.x + list.get(i).x;
                int ny = start.y + list.get(i).y;

                arr[nx][ny] = 1;
            }
        }
    }
}
