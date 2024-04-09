public class PGS_연속된부분수열의합_이지영 {

    class Solution {
        public int[] solution(int[] sequence, int k) {
            int[] answer = new int[2];
            answer[1] = Integer.MAX_VALUE;

            int left = 0, right = 0, sum = 0;

            while (right < sequence.length) {
                if (sum<=k) {
                    sum += sequence[right];
                    right++;
                } else {
                    sum -= sequence[left];
                    left++;
                }

                if (sum==k && answer[1] - answer[0] > right - 1 - left) {
                    answer[0] = left;
                    answer[1] = right-1;
                }
            }

            while(left < right) {
                sum -= sequence[left];
                left++;

                if (sum==k && answer[1] - answer[0] > right - 1 - left) {
                    answer[0] = left;
                    answer[1] = right-1;
                }
            }

            return answer;
        }
    }

}
