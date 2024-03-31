import java.util.*;

public class PGS_야근지수_이지영 {

    class Solution {

        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        public long solution(int n, int[] works) {
            long answer = 0;

            for (int work : works) {
                q.add(work);
            }

            while(q.peek()>0 && n>0) {
                int work = q.poll();
                q.add(--work);
                n--;
            }

            while(!q.isEmpty()) {
                long work = q.poll();
                answer += (work*work);
            }

            return answer;
        }
    }

}
