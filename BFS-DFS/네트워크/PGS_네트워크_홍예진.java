import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = use_bfs(n, computers);
        return answer;
    }
    public int use_bfs(int n, int[][] computers){
        int network_cnt = 0;
        
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;
            computers[i][i] = 0;
            network_cnt++;
            bfs(i, visited, n, computers); 
        }
        
        return network_cnt;
    }
    public void bfs(int start, boolean[] visited, int n, int[][] computers){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()){
            int i = q.poll();
            for(int j = 0; j < n; j++){
                if(visited[j]) continue;
                if(computers[i][j] == 1){
                    visited[j] = true;
                    q.add(j);
                }
            }
        }
    }
}