#include <math.h>
#include <string>
#include <vector>
#include <queue>

using namespace std;

#define FOR(i,s,e) for(int i=s; i<e; ++i)
#define N_MAX 101

vector<vector<int>> graph;
vector<vector<int>> map;
vector<int> used;

void bfs(int node, int num) {
    queue<int> q;

    q.push(node);
    used[node] = num;

    while (!q.empty()) {
        int now = q.front();
        q.pop();

        FOR(i, 0, map[now].size()) {
            if (map[now][i] == 0) continue;
            if (used[i] != 0) continue;
            used[i] = num;
            q.push(i);
        }
    }
}

int divide_net() {
    int my_abs = -1;
    int num = 1;

    used = vector<int>(N_MAX, 0);
    FOR(y, 0, map.size()) {
        FOR(x, 0, map[y].size()) {
            if (map[y][x] == 0) continue;
            if (used[x] != 0) continue;
            bfs(x, num++); // 트리로 연결된 노드들 찾기
        }
    }
    
    if (num == 2) { // 트리와 노드1개로 이루어진 분리, (num이 1인 트리 와 num번호가 없는 단일 노드)
        int fir = 0, sec = 0;
        FOR(i, 1, N_MAX) {
            if (used[i] == 1) fir += 1;
            if (used[i] == 0) sec += 1;
        }
        my_abs = abs(fir - sec);
    }
    else if (num == 3) { // 두개의 트리로 이루어진 분리, (num이 1인 트리 와 num이 2인 트리)
        int fir = 0, sec = 0;
        FOR(i, 1, N_MAX) {
            if (used[i] == 1) fir += 1;
            if (used[i] == 2) sec += 1;
        }
        my_abs = abs(fir - sec);
    }
    // 나머지의 경우는 여러개의 트리가 나오기때문에 처리 필요없다.

    return my_abs;
}

int solution(int n, vector<vector<int>> wires) {
    int answer = -1;
    int min_abs = 2134567890; // 절댓값 차이의 최솟값 저장

    // wires에 대한 정보로 graph행렬 형태를 그림
    graph = vector<vector<int>>(N_MAX, vector<int>(N_MAX, 0));
    FOR(i, 0, wires.size()) {
        graph[wires[i][0]][wires[i][1]] = 1;
        graph[wires[i][1]][wires[i][0]] = 1;
    }

    // graph 행렬을 map에 복사하고
    // 연결된 선을 하나씩 끊어가며 분리
    FOR(y, 0, graph.size()) {
        FOR(x, 0, graph[y].size()) {
            if (graph[y][x] == 0) continue;
            map = graph;
            map[y][x] = 0;
            map[x][y] = 0;
            int my_abs = divide_net();
            if(my_abs != -1) min_abs = min(my_abs, min_abs);
        }
    }

    answer = min_abs;
    return answer;
}