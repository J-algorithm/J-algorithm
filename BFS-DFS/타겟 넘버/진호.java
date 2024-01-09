class Solution {
    static int dfs(int depth, int sum, int[] numbers, int target) {
        // 마지막에 도달했다면
        if (depth == numbers.length) {
            // 연산의 결과가 target과 같다면
            if (sum == target) {
                return 1;
            }
            return 0;
        }

        int result = 0;
        int cur = numbers[depth];
        // 한 번은 빼고, 한 번은 더한 결과
        result += dfs(depth + 1, sum - cur, numbers, target);
        result += dfs(depth + 1, sum + cur, numbers, target);

        return result;
    }

    public int solution(int[] numbers, int target) {
        return dfs(0, 0, numbers, target);
    }
}