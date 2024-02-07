#include <string>
#include <vector>
#include <algorithm>

using namespace std;

#define FOR(i,s,e) for(int i=s; i<e; ++i)

vector<int> path; // 조합 기록
vector<int> used; // 이미 사용한 던전 체크
int cnt; // 최대 던전 횟수 저장

void backTracking(int k, vector<vector<int>> dg, int lv) {
    if (lv == dg.size()) {
        int cur_k = k; // 현재 조합으로 남은 피로도 기록
        int cur_size = path.size();
        int cur_cnt = 0; // 현재 조합으로 진행할 수 있는 최대 던전 수
        FOR(i, 0, cur_size) {
            if (cur_k < dg[path[i]][0]) { // 최소 필요 피로도보다 작으면 끝
                break;
            }
            cur_k -= dg[path[i]][1]; // 소모 피로도 빼줌
            cur_cnt += 1; // 던전 수 증가
        }

        cnt = max(cnt, cur_cnt);
        return;
    }

    FOR(i, 0, dg.size()) {
        if (used[i] == 1) continue;
        path.push_back(i);
        used[i] = 1;
        backTracking(k, dg, lv + 1);
        used[i] = 0;
        path.pop_back();
    }
}

int solution(int k, vector<vector<int>> dungeons) {
    int answer = -1;

    used = vector<int>(dungeons.size(), 0);
    backTracking(k, dungeons, 0);

    answer = cnt;
    return answer;
}