public class PGS_최소직사각형_공진호 {
    public int solution(int[][] sizes) {
        int longLen = 0;
        int shortLen = 0;
        for (int[] card : sizes) {
            // 가로 세로 중 긴 길이를 maxOne으로 설정
            int maxOne = Math.max(card[0], card[1]);
            int minOne = Math.min(card[0], card[1]);

            // 가능한 큰 지갑의 길이로 설정
            longLen = Math.max(longLen, maxOne);
            shortLen = Math.max(shortLen, minOne);

        }

        return longLen * shortLen;
    }


}
