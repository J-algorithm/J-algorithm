class PGS_쿼드압축후개수세기_공진호 {
    int[] result;

    private void dfs(int startX, int startY, int size, int[][] arr) {

        int value = arr[startX][startY];
        if (size == 1) {
            result[value]++;
            return;
        }
        boolean isCompressPossible = true;

        endLoop:
        for (int i = startX; i < startX + size; i++) {
            for (int j = startY; j < startY + size; j++) {
                if (arr[i][j] != value) {
                    isCompressPossible = false;
                    break endLoop;
                }
            }
        }

        // 만약 압축이 가능하다면
        if (isCompressPossible) {
            result[value]++;
            return;
        }
        // 압축이 가능하지 않는 경우
        int halfSize = size / 2;
        dfs(startX, startY, halfSize, arr);
        dfs(startX + halfSize, startY, halfSize, arr);
        dfs(startX, startY + halfSize, halfSize, arr);
        dfs(startX + halfSize, startY + halfSize, halfSize, arr);

    }

    public int[] solution(int[][] arr) {
        int n = arr.length;
        result = new int[2];
        dfs(0, 0, n, arr);
        return result;
    }

    public static void main(String[] args) {
        PGS_쿼드압축후개수세기_공진호 solution = new PGS_쿼드압축후개수세기_공진호();
        int[][] arr = {{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}};
        int[] result = solution.solution(arr);
        for (int i : result) {
            System.out.println(i);
        }
    }
}