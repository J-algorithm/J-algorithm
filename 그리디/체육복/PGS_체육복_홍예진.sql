import java.util.*;
class Solution {

    public int[] countClothes(int n, int[] lost, int[] reserve){
        int[] clothes = new int[n+2];
        
        Arrays.fill(clothes, 1);
        clothes[0] = 0;
        clothes[n+1] = 0;
        
        for(int l : lost){
            clothes[l]--;
        }

        for(int r : reserve){
            clothes[r]++;
        }

        return clothes;
    }
    public int solution(int n, int[] lost, int[] reserve){
        int answer = 0;
        Arrays.sort(lost);
        int[] clothes = countClothes(n, lost, reserve);

        for(int i = 1; i <= n; i++){
            if(clothes[i] > 0){
                clothes[i]--;
                answer++;
                continue;
            }

            // 체육복이 없을 때
            if(clothes[i-1] > 0) {
                clothes[i-1]--;
                answer++;
            } else if(clothes[i+1] > 1){
                clothes[i+1]--;
                answer++;
            }
        }

        return answer;
    }
}
