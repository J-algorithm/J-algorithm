#include <cstring>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

#define N_MAX 10000000
#define FOR(i,s,e) for(int i=s; i<e; ++i)

vector<int> v;
bool arr[N_MAX];
int num;
vector<int> ans;
vector<int> used;

void dfs(int max_lv, int lv, int n) {
    if (max_lv == lv) {
        if (arr[n] == true)  ans.push_back(n);
        return;
    }
    FOR(i, 0, v.size()) {
        if (used[i] == 1) continue;
        used[i] = 1;
        n = (n * 10) + v[i];
        dfs(max_lv, lv + 1, n);
        n = (n / 10);
        used[i] = 0;
    }
}

int solution(string numbers) {
    int answer = 0;

    //-----------------------------------------------------
    //문자열에서 각 숫자 카드를 '한장 씩' 추출
    FOR(i, 0, numbers.length()) {
        v.push_back(int(numbers[i] - '0'));
    }
    //-----------------------------------------------------


    //-----------------------------------------------------
    //가장 큰 수를 확인하기 위해 내림차순으로 정렬
    sort(v.begin(), v.end(), greater<int>());

    // num변수에 가장 큰 수 저장
    FOR(i, 0, v.size()) {
        num = (num * 10) + v[i];
    }
    //-----------------------------------------------------

    
    //-----------------------------------------------------
    // 소수확인 후 판별하기 위한 arr배열 초기화
    // 소수라면 true, 아니라면 false
    memset(arr, true, sizeof(arr));
    
    // 0과 1은 소수가 아니다.
    arr[0] = false;
    arr[1] = false;

    // 가장 큰 수 9999999이므로 배열의 크기는 10000000
    // 카드 조합 중 나올 수 있는 가장 큰 수(num)까지 소수 판별을 한다.
    FOR(i, 1, 10000000) {
        if (arr[i] == false) continue;
        int s = i;
        for (int j = i * 2; j <= num; j += i) {
            arr[j] = false;
        }
    }
    //-----------------------------------------------------

    //-----------------------------------------------------
    // 카드를 조합해 나올 수 있는 모든 수를 구한다.
    FOR(i, 1, v.size() + 1) {
        used = vector<int>(v.size(), 0);
        dfs(i, 0, 0);
    }
    //-----------------------------------------------------

    
    //-----------------------------------------------------
    // 중복 제거 처리
    
    // 중복을 제거하기 위한 정렬을 한다.
    sort(ans.begin(), ans.end());
    // unique()는 중복된 값을 vector의 가장 뒤로 보내고
    // 이러한 값을 보낸 시작 위치를 반환한다.
    // erase()를 사용해 중복값이 존재하는 위치부터 끝까지 지워준다.
    ans.erase(unique(ans.begin(), ans.end()),ans.end());
    //-----------------------------------------------------

    answer = ans.size();
    return answer;
}