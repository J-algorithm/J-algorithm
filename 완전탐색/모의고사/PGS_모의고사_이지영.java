import java.util.*;

public class PGS_모의고사_이지영 {
    static class Solution {
        int N = 3;
        Queue<Integer>[] q;

        public int[] solution(int[] answers) {
            int[] score = new int[N];
            List<Integer> winner = new ArrayList<>();
            init();

            for (int answer : answers) {
                for (int k = 0; k < N; k++) {
                    if (q[k].peek() == answer) {
                        score[k]++;
                    }

                    q[k].add(q[k].poll());
                }
            }

            int maxScore = 0;
            for (int i=0; i<N; i++) {
                maxScore = Math.max(maxScore, score[i]);
            }

            for (int i=0; i<N; i++) {
                if(score[i] == maxScore) {
                    winner.add(i+1);
                }
            }

            int[] answer = new int[winner.size()];
            for (int i=0; i<winner.size(); i++) {
                answer[i] = winner.get(i);
            }

            return answer;
        }

        void init() {
            q = new Queue[N];
            for(int i=0; i<N; i++) {
                q[i] = new ArrayDeque<>();
            }

            Integer[] first = {1, 2, 3, 4, 5};
            Integer[] second = {2, 1, 2, 3, 2, 4, 2, 5};
            Integer[] third = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

            q[0].addAll(Arrays.asList(first));
            q[1].addAll(Arrays.asList(second));
            q[2].addAll(Arrays.asList(third));
        }
    }
}
