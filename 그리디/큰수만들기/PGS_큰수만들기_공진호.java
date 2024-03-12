public class PGS_큰수만들기_공진호 {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int N = number.length();

        int maxIdx = 0;
        int iter = N - k;

        for (int i = 0; i < iter; i++) {
            int max = 0; // 최댓값
            for (int j = maxIdx; j <= i + k; j++) {
                int n = number.charAt(j) - '0';
                if (max < n) {
                    max = n;
                    maxIdx = j + 1;
                }
            }
            sb.append(max);
        }


        return sb.toString();
    }

    public static void main(String[] args) {
        PGS_큰수만들기_공진호 solution = new PGS_큰수만들기_공진호();
        String number = "1924";
        int k = 2;
        System.out.println(solution.solution(number, k));
    }
}
