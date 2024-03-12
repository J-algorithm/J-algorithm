import java.util.*;
class Solution {
    int n;
    Map<Integer, Node> map;

    public void init(int[][] edges){
        n = edges.length;
        map = new HashMap<>();
        // 각 정점은 서로 다른 번호가 매겨짐 (연속번호X)
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            Node nodeFrom = map.get(from);
            if(nodeFrom == null){
                nodeFrom = new Node(from, new ArrayList<>(), new ArrayList<>());
                map.put(from , nodeFrom);
            }
            Node nodeTo = map.get(to);
            if(nodeTo == null){
                nodeTo = new Node(to, new ArrayList<>(), new ArrayList<>());
                map.put(to , nodeTo);
            }
            nodeFrom.out.add(nodeTo);
            nodeTo.in.add(nodeFrom);
        }


    }
    public int bfs(Integer startNum){
        Map<Integer, Boolean> visited = new HashMap<>();

        // 1 : 도넛, 2: 막대, 3 : 8자
        // 인근 노드를 방문하여 어떤 그래프인지 판별한다.
        Queue<Integer> q = new LinkedList<>();
        q.add(startNum);
        visited.put(startNum, true);
        while(!q.isEmpty()){
            int now = q.poll();
            Node nowNode = map.get(now);
            // 나가고 들어오는 엣지가 4개 이상 있다면 8자형
            if(nowNode.in.size() + nowNode.out.size() >= 4) return 3;
            // 어디로 들어가는 엣지없이 끝난다면 막대형
            if(nowNode.out.isEmpty()) return 2;

            for(Node next : nowNode.out){
                if(visited.get(next.number) != null) continue;
                visited.put(next.number, true);
                q.add(next.number);
            }
        }

        // 나머지는 도넛형
        return 1;
    }

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        init(edges);
        // 그래프에 없던 노드
        // 1. 들어오는 edge가 없다.
        // 2. 그래프의 개수(2이상)만큼 나가는 edge를 가진다.

        // 그래프의 탐색 시작점
        int startNodeNumber = -1;
        for(int idx : map.keySet()){
            Node node = map.get(idx);
            if(node.in.isEmpty() && node.out.size() >= 2) {
                startNodeNumber = node.number;
                break;
            }
        }

        answer[0] = startNodeNumber;

        Queue<Integer> q = new LinkedList<>();
        for(Node node : map.get(startNodeNumber).out){
            q.add(node.number);
        }

        while(!q.isEmpty()){
            answer[bfs(q.poll())]++;
        }

        return answer;
    }
}
class Node {
    int number;
    List<Node> in;
    List<Node> out;
    public Node(int number, List<Node> in, List<Node> out){
        this.number = number;
        this.in = in;
        this.out = out;
    }
}