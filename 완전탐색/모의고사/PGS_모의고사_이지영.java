import java.util.*;

public class PGS_모의고사_이지영 {

    class Solution {
        int N = 3;
        Queue<Integer>[] q;
        Integer[][] guessAnswer = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };

        public int[] solution(int[] answers) {
            int[] score = new int[N];
            List<Integer> winner = new ArrayList<>();
            init();

            int maxScore = 0;
            for (int i=0; i<answers.length; i++) {

                for (int k=0; k<N; k++) {
                    if(q[k].peek()==answers[i]) {
                        score[k]++;
                        maxScore = Math.max(maxScore, score[k]);
                    }

                    q[k].add(q[k].poll());
                }
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
                q[i] = new ArrayDeque<>(Arrays.asList(guessAnswer[i]));
            }
        }
    }
}
