import java.util.*;

// 2024-02-20 답봄
class Sol-ution {

    int numbers[];
    String operations[];
    int[][][] dp;

    public int solution(String arr[]){
        int n = arr.length/2;

        dp = new int[2][200][200];
        for(int i = 0; i < n+1; i++){
            for(int j = 0; j < n+1; j++){
                // dp[0] : 최댓값
                // dp[1] : 최소값
                dp[0][i][j] = Integer.MIN_VALUE;
                dp[1][i][j] = Integer.MAX_VALUE;
            }
        }

        numbers = new int[n+1];
        operations = new String[n];

        for(int i = 0; i < arr.length; i++){
            if(i%2 == 0){
                numbers[i/2] = Integer.parseInt(arr[i]);
                continue;
            }
            operations[i / 2] = arr[i];
        }

        return calculate(0, 0, n);
    }

    public int calculate(int flag, int start, int end){
        // start == end인 경우는 숫자 하나만 선택된 경우
        // 자기 자신을 리턴
        if (start == end) {
            dp[flag][start][end] = numbers[start];
            return dp[flag][start][end];
        }

        // 이미 계산했었던 경우, 기존에 계산된 값 사용
        if (visited(flag, start, end)) {
            return dp[flag][start][end];
        }

        //방문 체크
        dp[flag][start][end] = 0;

        int result = flag == 0? Integer.MIN_VALUE : Integer.MAX_VALUE;

        // 최대값을 구해야할 때 flag = 0
        if(flag == 0){
            for(int mid = start; mid < end; mid++){
                if(operations[mid].equals("-")){
                    // Max(a - b) = Max(a) - Min(b)
                    result = Math.max(result, calculate(0, start, mid) - calculate(1, mid+1, end));
                    continue;
                }

                // Max(a+b) -> Max(a) + Max(b)
                result = Math.max(result, calculate(0, start, mid) + calculate(0, mid+1, end));
            }
        }

        // 최소값을 구해야할 때 flag = 1
        if(flag == 1) {
            for(int mid = start; mid < end; mid++){
                if(operations[mid].equals("-")){
                    // Max(-(a-b)) = Max(a), Min(b)
                    result = Math.min(result, calculate(1, start, mid) - calculate(0, mid+1, end));
                    continue;
                }
                // -(a+b)가 최댓값이 되는 조건 -> a, b 둘 다 최소값
                result = Math.min(result, calculate(1, start, mid) + calculate(1, mid+1, end));
            }
        }

        dp[flag][start][end] = result;
        return dp[flag][start][end];
    }

    public boolean visited(int flag, int start, int end){
        return dp[flag][start][end] != Integer.MIN_VALUE && dp[flag][start][end] != Integer.MAX_VALUE;
    }
}
