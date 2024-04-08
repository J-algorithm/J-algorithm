import java.util.*;

// 0점짜리~
class Solution {
    int[] info;
    int ans;

    boolean[] visited;
    Node[] nodes;
    List<Node> list;

    public void init(int[] info, int[][] edges){
        ans = 0;
        this.info = info;
        visited = new boolean[info.length];
        visited[0] = true;
        nodes = new Node[info.length];
        for(int i = 0; i < info.length; i++){
            nodes[i] = new Node(i);
        }
        for(int[] edge : edges){
            int parent = edge[0];
            int child = edge[1];

            Node pNode = nodes[parent];
            if(pNode.left == null){
                pNode.left = nodes[child];
            } else {
                pNode.right = nodes[child];
            }
        }
    }
    public List<Node> getList(){
        Queue<Node> q = new LinkedList<>();
        nodes[0].count = 1;
        q.add(nodes[0]);
        while(!q.isEmpty()){
            int qSize = q.size();
            while(qSize-- > 0){
                Node n = q.poll();
                if(n.left != null){
                    Node left = n.left;
                    left.count = info[n.num] == 1 ? n.count + 1: n.count - 1;
                    q.add(left);
                }
                if(n.right != null){
                    Node right = n.right;
                    right.count = info[n.num] == 1 ? n.count + 1 : n.count - 1;
                    q.add(right);
                }
            }
        }
        List<Node> list = new LinkedList<>();
        for(Node n : nodes){
            list.add(n);
        }
        Collections.sort(list, (n1, n2) -> n2.count - n1.count);
        return list;
    }
    public void getSheep(int num){
        ans += nodes[num].count;
        visited[num] = true;
    }
    public void recalculateCount(int num){
        nodes[num].count = 0;

        Queue<Node> q = new LinkedList<>();
        q.add(nodes[num]);
        while(!q.isEmpty()){
            int qSize = q.size();
            while(qSize-- > 0){
                Node n = q.poll();
                if(n.left != null){
                    Node left = n.left;
                    left.count = info[n.num] == 1 ? n.count + 1: n.count - 1;
                    q.add(left);
                }
                if(n.right != null){
                    Node right = n.right;
                    right.count = info[n.num] == 1 ? n.count + 1 : n.count - 1;
                    q.add(right);
                }
            }
        }
    }
    public int getNextNum(){
        Node n = list.get(0);
        if(n.count > 0) return n.num;
        return -1;
    }
    public int solution(int[] info, int[][] edges) {
        init(info, edges);

        list = getList();
        // 남은 양들 중에서 소모비용(경로상 늑대 수 - 양의 수)가 가장 적은 것을 선택한다.
        int num = 0;
        while(num != -1){
            getSheep(num);
            System.out.println(list);
            recalculateCount(num);


            Collections.sort(list, (n1, n2) -> n2.count - n1.count);

            System.out.println(list);
            num = getNextNum();
        }

        return ans;
    }
}

class Node {
    int num;
    int count;
    Node left;
    Node right;
    public Node(int num){
        this.num = num;
        this.left = null;
        this.right = null;
    }

    public String toString(){
        return "{"+this.num +" "+this.count+"}";
    }
}

public class Main{
    public static void main(String[] args){
        Solution s = new Solution();
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        int ans = s.solution(info, edges);
        System.out.println(ans);
    }
}