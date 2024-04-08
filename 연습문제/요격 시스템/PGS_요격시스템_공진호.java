import java.util.*;
class PGS_요격시스템_공진호 {
    public int solution(int[][] targets) {
        // 미사일의 위치를 정렬하는 Comparator
        Comparator<int[]> TargetComparator = new Comparator<int[]>(){
            @Override
            public int compare(int[] o1,int[] o2){
                return o1[1]-o2[1];
            }
        };

        // 미사일을 e위치를 기준으로 오름차순 정렬
        Arrays.sort(targets,TargetComparator);

        // 요격 위치
        int loc = -1;
        int cnt = 0;
        for(int[] target: targets){
            int targetStart = target[0];
            int targetEnd = target[1];
            // 현재 위치로 미사일을 요격하치 못한다면 새로운 요격 미사일을 사용해야 함
            if(targetStart>=loc){
                loc = targetEnd;
                cnt++;
            }

        }

        return cnt;
    }
}