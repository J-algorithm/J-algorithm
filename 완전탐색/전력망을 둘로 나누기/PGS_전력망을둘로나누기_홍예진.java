import java.util.*;

class Solution {
    int[] disable;
    ArrayList<Integer>[] list;
    public boolean isDisable(int now, int next){
        if(disable[0] == now && disable[1] == next)
            return true;
        if(disable[1] == now && disable[0] == next)
            return true;
        return false;
    }
    public int bfs(int n, int[][] wires){
        int treeSize = 0;

        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;
        while(!q.isEmpty()){
            int now = q.poll();
            for(int next : list[now]){
                if(isDisable(now, next)) continue;
                if(visited[next]) continue;

                visited[next] = true;
                q.add(next);
            }

            treeSize++;
        }

        return treeSize;
    }

    public int solve(int n, int[][] wires) {
        int treeSize = bfs(n, wires);
        int anotherSize = n - treeSize;
        return Math.abs(anotherSize - treeSize);
    }
    public int solution(int n, int[][] wires) {
        int answer = n;
        list = new ArrayList[n+1];
        for(int i = 1; i <= n ; i++){
            list[i] = new ArrayList<>();
        }
        for(int[] wire : wires){
            int v1 = wire[0];
            int v2 = wire[1];
            list[v1].add(v2);
            list[v2].add(v1);
        }
        for(int i = 0; i < n-1; i++){
            disable = wires[i];
            int temp = solve(n, wires);
            answer = answer > temp? temp : answer;
        }

        return answer;
    }
}