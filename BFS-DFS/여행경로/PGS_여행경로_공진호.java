import java.util.*;
class PGS_여행경로_공진호 {

    boolean[] used;
    String[] routes;
    int N;

    boolean find(String cur, int depth,String[][] tickets){
        // 끝까지 도달했다면 참
        if(depth==N+1){
            return true;
        }

        for(int i = 0; i< N; i++){
            String start = tickets[i][0];
            String end = tickets[i][1];

            // 현재 티켓을 사용하지 않았고 현재지점에서 출발 가능한 티켓인 경우
            if(!used[i] && cur.equals(start)){
                used[i] = true;
                // 경로 추가
                routes[depth] = end;
                //재귀 호출
                if(find(end,depth+1,tickets)){
                    return true;
                }
                used[i] = false;
            }
        }
        return false;

    }

    public String[] solution(String[][] tickets) {
        // 모든 공항 3글자
        // 항공권 모두 사용
        // 경로가 여러개일 시 알파벳 순서
        // 경로는 무조건 존재

        // 도착 경로를 사전순으로 정렬
        Arrays.sort(tickets, (o1,o2) -> o1[1].compareTo(o2[1]));

        N = tickets.length;
        used = new boolean[N];
        routes = new String[N+1];
        //시작은 무조건 ICN
        routes[0] = "ICN";

        find("ICN",1,tickets);
        return routes;
    }
}