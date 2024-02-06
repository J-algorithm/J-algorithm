#include <iostream>
#include <string>
#include <vector>

using namespace std;

#define FOR(i,s,e) for(int i=s; i<e; ++i)

string path[51]; // 사용한 문자를 기록해둘 배열
vector<bool> used; // 사용한 문자를 기록할 배열
int min_num = 2134567890; 

int mstrcmp(string str1, string str2) {
    int diff = 0; // 두 문자열에서 "같은 자리"에 "동일 문자"라면 카운팅해주는 변수
    
    FOR(i, 0, str1.size()) {
        if (str1[i] == str2[i]) ++diff;
    }

    return diff;
}

void dfs(string begin, string target, vector<string> words, int lv) {
    // ---------------------------------------------------
    // 타겟 문자와 같으면 
    // 최소 변경 횟수를 업데이트 후 return
    if (path[lv - 1] == target) {
        if (min_num > (lv - 1)) {
            min_num = lv - 1;
        }
        return;
    }
    // ---------------------------------------------------

    FOR(i, 0, words.size()) {
        // 이미 변환했던 단어는 건너 뜀
        if (used[i] == true) continue;

        // ---------------------------------------------------
        // mstrcmp() 함수는 두 문자열을 비교 후
        // 같은 자리에 같은 문자의 수를 return 해줌
        // ex) aba, aaa => return 2
        //     abc, aaa => return 1
        // 
        // 단어의 길이는 같기 때문에 begin 문자열을 기준으로 삼음.
        // return한 수가 문자열 길이에서 1개 적다면
        // 다음 단계로 변환이 가능한 단어로 판단
        // ex) aba, aaa => return 2 | 변환 할수 있는 문자
        //     abc, aaa => return 1 | 변환할 수 없는 문자
        if (mstrcmp(path[lv - 1], words[i]) == begin.size() - 1) {
            path[lv] = words[i]; // 사용한 단어의 로그 기록
            used[i] = true; // 사용했던 단어 처리
            dfs(begin, target, words, lv + 1);
            used[i] = false;
            path[lv] = "";
        }
        // ---------------------------------------------------

    }
}

int solution(string begin, string target, vector<string> words) {
    int answer = 0;
    used = vector<bool>(words.size(), false);

    // 백트래킹 방법으로 문제 해결 ----------------------
    path[0] = begin;
    dfs(begin, target, words, 1);
    // ---------------------------------------------------


    // ---------------------------------------------------
    // 최솟값이 갱신 되지 않았다면 answer은 0
    // 최솟값이 갱신 되었다면 answer은 최솟값
    if (min_num == 2134567890)
        answer = 0;
    else
        answer = min_num;
    // ---------------------------------------------------

    return answer;
}

int main() {
    //freopen("input.txt", "r", stdin);
    string begin = "aoa";
    string target = "aof";
    vector<string> words = { "aob", "aoc", "aod", "aof", "aoe" };

    solution(begin, target, words);
    return 0;
}