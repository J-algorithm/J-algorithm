public class PGS_최소직사각형_이지영 {
    static class Solution {
        public int solution(int[][] sizes) {
            int maxWidth = Integer.MIN_VALUE;
            int maxHeight = Integer.MIN_VALUE;

            for (int[] card : sizes) {
                int width = Math.min(card[0], card[1]);
                int height = Math.max(card[0], card[1]);

                maxWidth = Math.max(maxWidth, width);
                maxHeight = Math.max(maxHeight, height);
            }

            return maxWidth * maxHeight;
        }
    }

}
