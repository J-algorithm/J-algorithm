import java.util.ArrayList;
import java.util.List;

public class PGS_도넛과막대그래프_공진호{
    int[] in;
    int[] out;
    List<Integer>[] adjList;

    boolean isEight;
    boolean isDoughnut;

    static final int DOUGHNUT_IDX = 1;
    static final int STICK_IDX = 2;

    static final int EIGHT_IDX = 3;


    void init(int[][] edges){

        int N = -1;
        for(int[] edge : edges){
            N = Math.max(N,edge[0]);
            N = Math.max(N,edge[1]);
        }
        in = new int[N+1];
        out = new int[N+1];
        adjList = new ArrayList[N+1];
        for(int i = 0; i<=N;i++){
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            // start -> end
            in[end]++;
            out[start]++;

            adjList[start].add(end);
        }

    }


    void dfs(int v,int start){
        if(adjList[v].size()>=2){
            isEight = true;
            return;
        }

        for(int next : adjList[v]){
            // 그래프 탐색시 시작 지점으로 돌아 온다면 도넛모양
            if(next == start){
                isDoughnut = true;
                return;
            }
            dfs(next,start);
        }

    }



    public int[] solution(int[][] edges) {
        int N = -1;
        for(int[] edge : edges){
            N = Math.max(N,edge[0]);
            N = Math.max(N,edge[1]);
        }

        init(edges);


        int start = -1;

        for(int i = 0; i<N;i++){
            if(in[i] == 0 && out[i]>=2){
                start = i;
            }
        }


        int[] answer = new int[4];

        answer[0] = start;

        for(int next: adjList[start]){
            // 그래프 탐색
            dfs(next,next);
            if(isEight){
                answer[EIGHT_IDX] += 1;
            }else if(isDoughnut){
                answer[DOUGHNUT_IDX] += 1;
            }else{
                answer[STICK_IDX] += 1;
            }
        }


        return answer;
    }


    public static void main(String[] args) {
        PGS_도넛과막대그래프_공진호 sol = new PGS_도넛과막대그래프_공진호();
        int[][] edges = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3, 8}};
        int[] ret = sol.solution(edges);
        for (int i = 0; i < ret.length; i++) {
            System.out.print(ret[i] + " ");
        }
    }
}