import java.util.*;

public class PGS_구명보트_이지영 {

    class Solution {
        public int solution(int[] people, int limit) {
            int answer = 0;
            boolean[] isRescued = new boolean[people.length];
            Arrays.sort(people);

            int start = 0, end = people.length - 1;

            while (start < end) {
                if (people[start] + people[end] <= limit) {
                    isRescued[start++] = true;
                    isRescued[end--] = true;
                } else {
                    isRescued[end--] = true;
                }

                answer++;
            }

            while(!isRescued[start]) {
                answer++;
                isRescued[start] = true;
            }

            return answer;
        }
    }

}
