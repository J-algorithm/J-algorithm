import java.util.HashSet;

public class PGS_N으로표현_공진호 {
    public static final int MAX_DEPTH = 9;
    HashSet<Integer>[] hs = new HashSet[MAX_DEPTH];


    void dfs(int depth, int N) {
        if (depth > 8) {
            return;
        }
        hs[depth] = new HashSet<>();

        int tmp = 0;
        for (int i = 0; i < depth; i++) {
            tmp = tmp * 10 + N;
        }
        hs[depth].add(tmp);
        for (int i = 1; i <= depth; i++) {
            for (int j = 1; i + j <= depth; j++) {
                // hs[i] : i번 연산으로 만들 수 있는 숫자 집합
                // hs[j] : j번 연산으로 만들 수 있는 숫자 집합
                for (int a : hs[i]) {
                    for (int b : hs[j]) {
                        // 두 숫자로 만들 수 있는 숫자 집합
                        // 더하기, 빼기, 곱하기, 나누기, 나머지
                        hs[depth].add(a + b);
                        hs[depth].add(a - b);
                        hs[depth].add(a * b);
                        if (b != 0) {
                            hs[depth].add(a / b);
                            hs[depth].add(a % b);
                        }

                    }
                }
            }
        }
        dfs(depth + 1, N);
    }

    public int solution(int N, int number) {
        dfs(1, N);
        for (int i = 1; i <= 8; i++) {
            // i번으로 만들 수 있는 숫자들 중 number가 있다면?
            if (hs[i].contains(number)) {
                return i;
            }
        }

        return -1;
    }
}
