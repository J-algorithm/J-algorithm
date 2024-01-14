import java.util.*;

class Programmers_타겟넘버_홍예진 {
    public int solution(int[] numbers, int target) {
        // 주어지는 숫자의 개수 최대 20개
        // 경우의 수 2^20 = 1024*1024 => O(100만)
        // 총합의 최대값 50*20 = 1000 최소값 50*-20 = -1000
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        for(int number : numbers){
            int qSize = q.size();
            while(qSize-- > 0){
                int sum = q.poll();
                q.add(sum + number);
                q.add(sum - number);
            }
        }
        int answer = 0; 
        while(!q.isEmpty()){
            int sum = p.poll();
            if(sum == target)
                answer++;
        }
        return answer;
    }
}