import java.util.*;

public class PGS_N으로표현_이지영 {

    class Solution {
        int MAX = 9;
        Set<Integer>[] set;
        public int solution(int N, int number) {

            init(N);

            if(N==number) return 1;

            for (int i=2; i<MAX; i++) {
                for (int j=1; j<i; j++) {

                    int k = i-j;

                    for (int num1 : set[j]) {
                        for (int num2 : set[k]) {
                            set[i].add(num1+num2);
                            set[i].add(num1-num2);
                            set[i].add(num1*num2);
                            if(num2!=0) set[i].add(num1/num2);
                        }
                    }
                }
                if(set[i].contains(number)) return i;
            }

            return -1;
        }

        void init(int N) {
            set = new HashSet[MAX];
            for (int i=1; i<MAX; i++) {
                set[i] = new HashSet<>();
            }

            int tmp = N;
            for (int i=1; i<MAX; i++) {
                set[i].add(tmp);
                tmp = tmp*10+N;
            }
        }
    }
}
