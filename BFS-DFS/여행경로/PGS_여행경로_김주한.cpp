#include <string>
#include <vector>

using namespace std;

#define FOR(i,s,e) for(int i=s; i<e; ++i)

vector<int> used;
vector<string> path;
vector<string> tmp;

bool isAllVisit() {
    /*
    모든 티켓을 사용했는지 검증하는 함수
    모두 사용했다면 true
    아니라면 false
    */
    FOR(i, 0, used.size()) {
        if (used[i] != 1) return false;
    }
    return true;
}

void dic_sort() {
    /* 
    사전 순으로 앞선 티켓 조합으로 갱신하는 함수
    모든 티켓 조합을 path에 저장한 후 tmp에 저장된 티켓조합을 비교 후
    사전 순으로 앞선 조합을 tmp배열에 갱신
    */
    if (tmp.empty() == true) {
        tmp = path;
    }
    else {
        FOR(i, 0, tmp.size()) {
            if (tmp[i] == path[i]) continue;
            else if (tmp[i] < path[i]) {
                return;
            }
            else if (tmp[i] > path[i]) {
                tmp = path;
                return;
            }
        }
    }
}

void find_path(string start, vector<vector<string>> tickets, int lv) {
    //--------------------------------------
    /*
    티켓을 모두 사용한 조합이라면 사전 순으로 앞선 조합 확인
    */
    if (isAllVisit() == true) {
        dic_sort();
        return;
    }
    //--------------------------------------

    FOR(i, 0, tickets.size()) {
        if (used[i] == 1) continue;
        if (tickets[i][0] == start) {
            used[i] = 1;
            path.push_back(tickets[i][1]);
            find_path(tickets[i][1], tickets, lv + 1);
            path.pop_back();
            used[i] = 0;
        }
    }
}

vector<string> solution(vector<vector<string>> tickets) {
    vector<string> answer;

    used = vector<int>(tickets.size(), 0); // 티켓 사용여부 확인

    path.push_back("ICN");
    find_path("ICN", tickets, 1); // DFS로 모든 티켓 조합확인

    answer = tmp;

    return answer;
}