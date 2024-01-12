class Programmers_타겟넘버_이지영{
    int N, TARGET, answer = 0;
    public int solution(int[] numbers, int target) {
        N = numbers.length;
        TARGET = target;

        dfs(0, 0, numbers);

        return answer;
    }

    public void dfs(int idx, int sum, int[] numbers) {
        if(idx == N) {
            if(sum == TARGET) answer++;
            return;
        }

        dfs(idx+1, sum+numbers[idx], numbers);
        dfs(idx+1, sum-numbers[idx], numbers);
    }
}