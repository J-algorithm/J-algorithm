import java.util.*;
public class PGS_소수찾기_이지영 {

    class Solution {
        int N;
        int[] number;
        boolean[] used;
        Set<Integer> primes, answer;

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
            primes = new HashSet<>();
            answer = new HashSet<>();

            for (int i=0; i<N; i++) {
                number[i] = numbers.charAt(i) - '0';
            }
        }

        void getPrimeNumbers() {
            for (int num=2; num<10000000; num++) {
                boolean isPrime = true;

                for (int i=2; i<=Math.sqrt(num); i++) {
                    if(num%i == 0) {
                        isPrime = false;
                        break;
                    }
                }

                if(isPrime) primes.add(num);
            }
        }

        void pick(int cnt, StringBuilder sb) {
            if(cnt == N) {
                int num = Integer.parseInt(sb.toString());
                if (primes.contains(num)) {
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
