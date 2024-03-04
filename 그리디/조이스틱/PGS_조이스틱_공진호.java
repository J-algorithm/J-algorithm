public class PGS_조이스틱_공진호 {


    public int solution(String name) {
        //answer = upDown + leftRight

        int N = name.length();

        // leftRight
        // ZINOAAAAAAAZ
        // 오른쪽 갔다가 돌아가기 vs 왼쪽으로 갔다가 돌아가기
        // Z (-> I -> N -> O) 반복 + Z  =  7
        // Z (-> Z) 반복 -> I -> N -> O  = 5

        int leftRight = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int next = i + 1;
            while (next < N && name.charAt(next) == 'A') {
                next++;
            }
            // next : A가 아닌 문자열의 위치
            // i : 현재 위치
            // (i)*2 + N - next : 오른쪽 갔다가 돌아가
            // i + (N- next)*2 : 왼쪽으로 갔다가 돌아가기
            int minValue = Math.min((i) * 2 + N - next, i + (N - next) * 2);
            leftRight = Math.min(leftRight, minValue);
        }

        // upDown
        int upDown = 0;
        for (int i = 0; i < N; i++) {
            upDown += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
        }
        return upDown + leftRight;
    }

    public static void main(String[] args) {
        PGS_조이스틱_공진호 solution = new PGS_조이스틱_공진호();
        String name = "AZAAA";
        System.out.println(solution.solution(name));
    }
}
