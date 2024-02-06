import java.util.*;

class Solution {
    int k, n, answer;
    int[][] dungeons;
    boolean[] visited;
    public void solve(int idx, int count){
        visited[idx] = true;
        if(dungeons[idx][0] > k){
            visited[idx] = false;
            return;
        }
        
        count++;
        k -= dungeons[idx][1];
        
        answer = answer < count ? count : answer;
        for(int i = 0; i < n; i++){
            if(visited[i]) continue;
            solve(i, count);
        }
        
        k += dungeons[idx][1];
        count--;
        visited[idx] = false;
        
    }
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        this.k = k;
        this.dungeons = dungeons;
        this.n = dungeons.length;
        this.visited = new boolean[n];
        
        for(int i = 0; i < n; i++)
            solve(i, 0);
        

        return answer;
    }
}