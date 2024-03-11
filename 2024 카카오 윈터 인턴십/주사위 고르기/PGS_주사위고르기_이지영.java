import java.util.*;

public class PGS_주사위고르기_이지영 {

    class Solution {

        int N;
        List<boolean[]> diceCombination;

        int maxResult = 0;
        List<Integer> answer;

        int[][] dice;

        public int[] solution(int[][] diceArr) {

            init(diceArr);

            pick(0, 0, new boolean[N]);

            for (int i=0; i<diceCombination.size()/2; i++) {
                calculateAllCase(diceCombination.get(i));
            }

            return answer.stream()
                    .mapToInt(i->(i+1))
                    .toArray();
        }

        void init(int[][] diceArr) {
            N = diceArr.length;
            diceCombination = new ArrayList<>();
            dice = diceArr;
        }

        void pick(int now, int cnt, boolean[] picked) {
            if(cnt==N/2) {
                diceCombination.add(picked.clone());
                return;
            }

            if(now==N) return;

            picked[now] = true;
            pick(now+1, cnt+1, picked);

            picked[now] = false;
            pick(now+1, cnt, picked);
        }

        void calculateAllCase(boolean[] picked) {
            List<Integer> diceA = new ArrayList<>();
            List<Integer> diceB = new ArrayList<>();

            for (int i=0; i<N; i++) {
                if (picked[i]) diceA.add(i);
                else diceB.add(i);
            }

            List<Integer> scoreA = new ArrayList<>();
            List<Integer> scoreB = new ArrayList<>();

            throwDices(0, 0, diceA, scoreA);
            throwDices(0, 0, diceB, scoreB);

            Collections.sort(scoreA);
            Collections.sort(scoreB);

            int winA = 0;
            for (int score : scoreA) {
                winA += countWin(score, scoreB);
            }
            if (maxResult < winA) {
                answer = diceA;
                maxResult = winA;
            }

            int winB = 0;
            for (int score : scoreB) {
                winB += countWin(score, scoreA);
            }
            if (maxResult < winB) {
                answer = diceB;
                maxResult = winB;
            }
        }

        void throwDices(int now, int sum, List<Integer> dices, List<Integer> scores) {

            if(now == N/2) {
                scores.add(sum);
                return;
            }

            int idx = dices.get(now);
            for (int num : dice[idx]) {
                throwDices(now+1, sum+num, dices, scores);
            }
        }

        int countWin(int score, List<Integer> rivalScore) {
            int start = 0;
            int end = rivalScore.size()-1;

            while (start <= end) {
                int mid = (start+end)/2;
                if (score > rivalScore.get(mid)) {
                    start = mid+1;
                } else {
                    end = mid-1;
                }
            }

            return end+1;
        }
    }

}
