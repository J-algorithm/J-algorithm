class Solution {
    int N;
    int[][] arr;
    int[] count;
    public boolean isSameNumbers(int si, int sj, int ei, int ej){
        int num = arr[si][sj];
        for(int i = si; i < ei; i++){
            for(int j = sj; j < ej; j++){
                if(arr[i][j] != num)
                    return false;
            }
        }
        return true;
    }
    public void solve(int si, int sj, int ei, int ej){
        int num = arr[si][sj];
        if(isSameNumbers(si, sj, ei, ej)) {
            count[num]++;
            return;
        }
        int mi = (ei + si)/2;
        int mj = (ej + sj)/2;
        solve(si, sj, mi, mj);
        solve(mi, sj, ei, mj);
        solve(si, mj, mi, ej);
        solve(mi, mj, ei, ej);
    }
    public int[] solution(int[][] arr) {
        count = new int[2];
        N = arr.length;
        this.arr = arr;
        solve(0, 0, N, N);
        return count;
    }
}
