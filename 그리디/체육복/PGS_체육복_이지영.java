import java.util.*;

public class PGS_체육복_이지영 {

    class Solution {
        Set<Integer> lostStudents, reserveStudents;
        public int solution(int n, int[] lost, int[] reserve) {

            init(n, lost, reserve);

            for (int idx : reserveStudents) {
                if (lostStudents.contains(idx-1)) {
                    lostStudents.remove(idx-1);
                } else if (lostStudents.contains(idx+1)) {
                    lostStudents.remove(idx+1);
                }
            }

            return n - lostStudents.size();
        }

        void init(int n, int[] lost, int[] reserve) {
            lostStudents = new HashSet<>();
            reserveStudents = new HashSet<>();

            for (int i=0; i<lost.length; i++) {
                lostStudents.add(lost[i]);
            }

            for (int i=0; i<reserve.length; i++) {
                if (lostStudents.contains(reserve[i])) {
                    lostStudents.remove(reserve[i]);
                    continue;
                }

                reserveStudents.add(reserve[i]);
            }
        }
    }

}
