#include <string>
#include <vector>
#include <algorithm>

using namespace std;

#define FOR(i,s,e) for(int i=s; i<e; ++i)

/*
  시도했던 방법들
  1. 모든 조합을 찾아보니 시간 초과 발생
  
  2. 정렬 후 앞에서부터 탐색하면서 하나를 선택하고 뒤에서부터 탐색하면서 한 명을 더 태울 수 있는지 확인해 태울 수 있다면 선택
    뽑은 사람은 used처리
    2중 for문 구조여서 시간초과
  
  3. 아래 방법
    각 index를 갱신하는 방법
*/
int solution(vector<int> people, int limit) {
    int answer = 0;

    sort(people.begin(), people.end(),greater<>()); // 내림차순 정렬

    int s_idx = 0; // start idx (정방향으로 이동하는 index)
    int e_idx = people.size() - 1; // end idx (역방향으로 이동하는 index)
    int cnt = 0;
    while(1) {
        int weight = limit - people[s_idx]; // 더 태울수 있는 남은 무게
        if (weight >= people[e_idx]) --e_idx;  // 더 태울수 있다면 e_idx를 역방향으로 한칸 이동시켜 태움 처리
        ++s_idx; // 태울 수 있든 없든 1싸이클에 1명은 탐
        ++cnt;
        if (s_idx > e_idx) break; // s_idx와 e_idx가 교차되면 전부 확인한셈.
    }

    answer = cnt;
    return answer;
}