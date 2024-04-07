import java.util.*;

public class PGS_요격시스템_이지영 {

    class Target implements Comparable<Target> {
        int start;
        int end;

        public Target(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Target o) {
            if (this.end == o.end) {
                if (this.start < o.start) return -1;
                else return 1;
            }
            if (this.end > o.end) return -1;
            else return 1;
        }
    }

    class Solution {

        PriorityQueue<Target> pq = new PriorityQueue<>();

        public int solution(int[][] targets) {
            int answer = 0;

            for (int[] target : targets) {
                pq.add(new Target(target[0], target[1]));
            }

            int s = pq.peek().start, e = pq.peek().end;

            while(!pq.isEmpty()) {
                Target target = pq.poll();

                if (s >= target.end) {
                    answer++;
                    s = target.start;
                }

                s = Math.max(s, target.start);
                e = Math.min(e, target.end);
            }

            return answer+1;
        }
    }

}
