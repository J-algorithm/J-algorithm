import java.util.*;

class Solution {
    int N;
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        N = sequence.length;
        // 1000000 * 1000000 = 10000 0000 0000
        int[] sum = new int[N]; // sum[i] : 0 ~ i까지의 합
        sum[0] = sequence[0];
        Queue<Integer> q = new LinkedList<>();
        for(int idx = 0; idx < N; idx++){
            int num = sequence[idx];
            if(num == k) return new int[]{idx, idx};
            if(num > k) continue;
            
            q.add(idx);
            if(idx > 0)
               sum[idx] = sum[idx-1] + sequence[idx];
        }
        
        
        int length = 2;
        while(length <= N){
            
            int qSize = q.size();
            while(qSize-- > 0){
                int start = q.poll();
                int end = start + length - 1;
                if(end >= N) continue;

                while(end < N){
                    
                    int partSum = start == 0? sum[end] : sum[end] - sum[start-1];
                    
                    
                    if(partSum == k){
                        return new int[]{start, end};
                    } else if(partSum < k){
                        q.add(start);
                    } else {
                        break;
                    }

                    start++;
                    end++;
                }
                
            }
            
            length++;
        }
        
        return answer;
        
    }
}