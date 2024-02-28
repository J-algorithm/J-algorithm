#include <string>
#include <vector>
#include <cstring>

using namespace std;

#define FOR(i,s,e) for(int i=s; i<e; ++i)
#define N_MAX (30 + 1)

bool lost_flag[N_MAX];
bool reserve_flag[N_MAX];
bool isUniform[N_MAX];

int solution(int n, vector<int> lost, vector<int> reserve) {
    int answer = 0;

    FOR(i, 0, lost.size()) lost_flag[lost[i]] = true; // 잃어버린 사람 확인
    FOR(i, 0, reserve.size()) {
        reserve_flag[reserve[i]] = true; // 여유분 있는 사람 확인
        isUniform[reserve[i]] = true; // 여유분 있는 사람은 체육복이 있다.
    }
    FOR(i, 1, (n + 1)) {
        if (!lost_flag[i]) isUniform[i] = true; // 잃어버린 사람을 제외한 나머지 사람은 체육복이 있다.
    }

    FOR(i, 1, (n + 1)) {
        if ((i - 1) < 0) continue;
        if (!reserve_flag[i]) continue; // 체육복에 여유분이 없는 학생은 건너뜀
        if (lost_flag[i] && reserve_flag[i]) continue; // 체육복을 도난당했지만 여유분이 있는 사람은 빌려줄 수 없다.

        /*
            본인이 도난당하지 않고 체육복 여유분이 있는 사람들 중에서
        */

        if (lost_flag[i - 1] && (i != 1)) { // 앞사람이 체육복을 도난당했다면
            if (lost_flag[i - 1] && reserve_flag[i - 1]) {} // 체육복을 도난당했지만 여유분이 있다면 빌려줄 필요가 없다.
            else {
                isUniform[i - 1] = true; // 체육복을 빌려주고
                lost_flag[i - 1] = false; // 도난 확인을 지워줌
                continue;
            }
        }
        if (lost_flag[i + 1] && (i != n)) { // 뒷사람이 체육복을 도난당했다면
            if (lost_flag[i + 1] && reserve_flag[i + 1]) {} // 체육복을 도난당했지만 여유분이 있다면 빌려줄 필요가 없다.
            else {
                isUniform[i + 1] = true; // 체육복을 빌려주고
                lost_flag[i + 1] = false; // 도난 확인을 지워줌
                continue;
            }
        }
    }

    FOR(i, 1, (n + 1)) {
        if (isUniform[i]) answer += 1;
    }

    return answer;
}