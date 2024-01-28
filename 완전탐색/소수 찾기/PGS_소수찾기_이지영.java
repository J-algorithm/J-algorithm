import java.util.*;
public class PGS_소수찾기_이지영 {

    class Solution {
        int N, MAX = 10_000_000;
        int[] number;
        boolean[] used, isPrime;
        Set<Integer> answer;

        public int solution(String numbers) {

            init(numbers);
            getPrimeNumbers();
            pick(0, new StringBuilder("0"));

            return answer.size();
        }

        void init(String numbers) {
            N = numbers.length();
            number = new int[N];
            used = new boolean[N];
            isPrime = new boolean[MAX];
            answer = new HashSet<>();

            for (int i=0; i<N; i++) {
                number[i] = numbers.charAt(i) - '0';
            }
        }

        void getPrimeNumbers() {
            for (int num=2; num<MAX; num++) {
                boolean flag = true;

                for (int i=2; i<=Math.sqrt(num); i++) {
                    if(num%i == 0) {
                        flag = false;
                        break;
                    }
                }

                if(flag) {
                    isPrime[num] = true;
                }
            }
        }

        void pick(int cnt, StringBuilder sb) {
            if(cnt == N) {
                int num = Integer.parseInt(sb.toString());
                if (isPrime[num]) {
                    answer.add(num);
                }

                return;
            }

            for (int i=0; i<N; i++) {
                if(used[i]) continue;

                used[i] = true;
                pick(cnt+1, sb.append(number[i]));

                used[i] = false;
                sb.setLength(sb.length()-1);
                pick(cnt+1, sb);
            }
        }
    }
}
