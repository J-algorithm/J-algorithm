import java.util.*;

public class PGS_단속카메라_이지영 {

    class Section implements Comparable<Section> {
        int start;
        int end;

        public Section(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Section o) {
            if (this.end == o.end) {
                return this.start - o.start;
            }
            return this.end - o.end;
        }
    }

    class Solution {
        PriorityQueue<Section> pq;

        public int solution(int[][] routes) {

            init(routes);

            int answer = 0;
            int minEnd = pq.peek().end;

            while(!pq.isEmpty()) {
                Section s = pq.poll();

                if (s.start > minEnd) {
                    answer++;
                    minEnd = s.end;
                }
            }
            return ++answer;
        }

        void init(int[][] routes) {
            pq = new PriorityQueue<>();

            for (int[] route : routes) {
                pq.add(new Section(route[0], route[1]));
            }
        }
    }

}
