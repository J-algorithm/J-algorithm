import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PGS_섬연결하기_공진호 {
    class Bridge implements Comparable<Bridge> {
        int v1;
        int v2;
        int cost;

        public Bridge(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bridge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }


    int[] parent;
    List<Bridge> bridges = new ArrayList<>();

    void init(int n, int[][] costs) {
        parent = new int[n];
        // 초기값으로 부모는 자기자신
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int[] cost : costs) {
            bridges.add(new Bridge(cost[0], cost[1], cost[2]));
        }
    }

    // 부모 찾기
    int getParent(int v) {
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = getParent(parent[v]);
    }


    public int solution(int n, int[][] costs) {
        init(n, costs);

        int answer = 0;
        int edgeCnt = 0;
        // 비용 오름차순 정렬
        Collections.sort(bridges);
        // 모든 섬이 연결될 때까지
        for (Bridge bridge : bridges) {
            if (edgeCnt == n - 1) {
                break;
            }

            int v1 = bridge.v1;
            int v2 = bridge.v2;
            int cost = bridge.cost;

            int v1Parent = getParent(v1);
            int v2Parent = getParent(v2);

            // 부모가 다르다면 연결
            if (v1Parent != v2Parent) {
                parent[v1Parent] = v2Parent;

                answer += cost;
                edgeCnt++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        PGS_섬연결하기_공진호 solution = new PGS_섬연결하기_공진호();
        int n = 4;
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
        System.out.println(solution.solution(n, costs));
    }

}

