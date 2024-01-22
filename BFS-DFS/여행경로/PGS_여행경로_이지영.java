import java.util.*;

class PGS_여행경로_이지영 {
    int N;
    boolean[] used;
    Map<String, List<Integer>> graph;
    List<String> route;
    String[] answer;
    String answerStr, START = "ICN";

    public String[] solution(String[][] tickets) {
        init(tickets);

        route.add(START);
        visit(0, START, tickets);

        return answer;
    }

    void init(String[][] tickets) {
        N = tickets.length;
        used = new boolean[N];
        route = new ArrayList<>();
        answerStr = "Z";

        graph = new HashMap<>();
        for (int i=0; i<N; i++) {
            String start = tickets[i][0];
            String dest = tickets[i][1];

            if(!graph.containsKey(start)) {
                graph.put(start, new ArrayList<>());
            }
            if(!graph.containsKey(dest)) {
                graph.put(dest, new ArrayList<>());
            }

            graph.get(start).add(i);
        }
    }

    void visit(int count, String now, String[][] tickets) {
        if(count == N) {
            answer = getRoute();
            return;
        }

        List<Integer> idxs = graph.get(now);
        for (int i : idxs) {
            if(used[i]) continue;

            route.add(tickets[i][1]);
            used[i] = true;
            visit(count+1, tickets[i][1], tickets);
            route.remove(route.size()-1);
            used[i] = false;
        }
    }

    String[] getRoute() {
        StringBuilder sb = new StringBuilder();
        for (String city : route) {
            sb.append(city).append(" ");
        }

        if (answerStr.compareTo(sb.toString()) < 0) {
            return answer;
        }
        answerStr = sb.toString();
        return route.toArray(new String[0]);

    }
}
