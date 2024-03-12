import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class PGS_체육복_공진호 {



    public int solution(int n, int[] lost, int[] reserve) {

        HashSet<Integer> lostSet = new HashSet<>();
        HashSet<Integer> reserveSet = new HashSet<>();
        for (int l : lost) {
            lostSet.add(l);
        }
        for (int r : reserve) {
            reserveSet.add(r);
        }

        for(int l : lost){
            // 여분을 가져온 학생이 잃어버렸다면
            if(reserveSet.contains(l)){
                lostSet.remove(l);
                reserveSet.remove(l);
            }
        }


        // 오름차순으로 정렬
        List<Integer> lostList = new ArrayList<>(lostSet);
        Collections.sort(lostList);

        for(int l : lostList){
            // 왼쪽 학생 확인
            if (reserveSet.contains(l - 1)) {
                lostSet.remove(l);
                reserveSet.remove(l - 1);
            // 오른쪽 학생 확인
            } else if (reserveSet.contains(l + 1)) {
                lostSet.remove(l);
                reserveSet.remove(l + 1);
            }
        }
        return n- lostSet.size();
    }

    public static void main(String[] args) {

        int result = new PGS_체육복_공진호().solution(5, new int[]{2, 4}, new int[]{1, 3, 5});
        System.out.println(result);

    }
}
