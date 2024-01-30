import java.util.*;

class PGS_소수찾기_공진호 {

    boolean isPrime(int num) {
        if (num == 0 || num == 1) {
            return false;
        }

        for (int i = 2; i <= (int) (Math.pow(num, 0.5)); i++) {
            if (num % i == 0) {
                return false;
            }

        }
        return true;

    }

    void dfs(int depth, int flag, int num) {
        if (depth == N) {
            return;
        }


        for (int i = 0; i < N; i++) {
            if ((flag & 1 << i) == 0) {
                int newNum = 10 * num + number[i];
                if (!hs.contains(newNum) && isPrime(newNum)) {
                    hs.add(newNum);
                }
                dfs(depth + 1, flag | 1 << i, newNum);
            }
        }

    }

    Set<Integer> hs = new HashSet<>();
    int[] number;
    int N;

    void init(String numbers) {
        N = numbers.length();
        number = new int[N];
        for (int i = 0; i < N; i++) {
            number[i] = numbers.charAt(i) - '0';
        }
    }

    public int solution(String numbers) {
        init(numbers);
        dfs(0, 0, 0);
        return hs.size();
    }
}