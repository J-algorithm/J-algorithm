import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
       int ans = 0;

        Arrays.sort(people);

        int minIndex = 0;
        int maxIndex = people.length-1;

        while(minIndex < maxIndex){
            int max = people[maxIndex];
            int min = people[minIndex];
            // 초과. max만 태운다.
            if(max + min > limit){
                maxIndex--;
                ans++;
                continue;
            }
            
            // 이하. min과 max모두 태운다.
            maxIndex--;
            minIndex++;
            ans++;
        }
        
        if(minIndex == maxIndex)
            ans++;
        
        return ans;
    }
}