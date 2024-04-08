import java.util.Arrays;

public class PGS_연속된부분수열의합_공진호 {
    public int[] solution(int[] sequence, int k) {
        // sequence 오름차순
        int left = 0;
        int right = 0;
        int N = sequence.length;

        int sum = 0;
        int[] answer = new int[2];
        answer[1] = N - 1;

        while (left <= right) {
            int len = right - left;
            int answerLen = answer[1] - answer[0] + 1;
            // 합이 같고 길이가 짧다면 업데이트
            if (sum == k && len < answerLen) {
                answer[0] = left;
                answer[1] = right - 1;
            }


            // 현재 합이 k보다 크다면 left +1
            if (sum > k) {
                sum -= sequence[left++];
                continue;
            } else if (right < N) {
                // 현재 합이 k보다 작거나 같으면 right +1
                sum += sequence[right++];
                continue;
            }

            // right가 N이면 break
            if (right == N) {
                break;
            }

        }


        return answer;
    }

    public static void main(String[] args) {
        PGS_연속된부분수열의합_공진호 solution = new PGS_연속된부분수열의합_공진호();


        int[] sequence = {1, 1, 1, 2, 3, 4, 5};
        int k = 5;

        System.out.println(Arrays.toString(solution.solution(sequence, k)));
    }
}
