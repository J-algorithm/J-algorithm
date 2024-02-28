#include <string>
#include <vector>

using namespace std;

#define FOR(i,s,e) for(int i=s; i<e; ++i)

string start_name;
int joy_cnt;
int isNot_A_cnt;

int solution(string name) {
    int answer = 0;

    // 목표 문자열 길이만큼 AA...A 문자열 생성
    FOR(i, 0, name.size()) {
        start_name += "A";
        if (name[i] != 'A') ++isNot_A_cnt;
    }

    /*
      A = 65
      Z = 90
      26개
    
      각 자리마다 목표 문자열을 만들기 위해 조이스틱을 조작하는 횟수 (위아래 조작)    
    */
    FOR(i, 0, name.size()) {
        if (start_name[i] == name[i]) continue;
        char ch1 = start_name[i];
        char ch2 = name[i];

        joy_cnt += min(ch2 - ch1, (ch1 + 26) - ch2);
    }

    /*
      문자를 바꿔야하는 자리로 이동하기 위해 조이스티을 조작하는 횟수 (좌우 조작)
    */
    
    /* 단방향으로만 이동할 경우 (일반 통행) */
    int seq_cnt = isNot_A_cnt; // 정방향 이동할 수 있는 횟수
    int rev_cnt = isNot_A_cnt; // 역방향  이동할 수 있는 횟수
    int half_move_cnt = 0; // 단방향 이동 횟수
    FOR(i, 0, name.size()) {
        // 출발 위치는 name[0]을 시작으로 정방향, 역방향 이동
        if (name[i] != 'A') { // [0] [1] [2] ...
            --seq_cnt; // 찾아야할 A 1개 감소
        }
        if (name[(name.size() - i) % name.size()] != 'A') { // [0] [name.size()-1] [name.size()-2] [name.size()-3] ...
            --rev_cnt; // 찾아야할 A 1개 감소
        }

        if (seq_cnt == 0 || rev_cnt == 0) break; // 정방향 역방향 중 한방향으로 이동해 A를 먼저 모두 찾는 방향이 최소 이동거리
        ++half_move_cnt;
    }

    /* 한 방향으로 가다가 꺽을 수 있음 (U턴 가능) */
    int move_cnt = isNot_A_cnt;
    seq_cnt = 0;
    rev_cnt = 0;
    int full_move_cnt = 0; // 양방향 이동
    // B B B B A A A A B A
    // 0 1 2 3 4 5 6 7 8   => 단방향으로 이동할 경우
    // 0 1 2 3         2 1
    // 4 5 6 7           3 => U턴을 할경우
     FOR(i, 0, name.size()) {
        if (name[i] != 'A') {
            seq_cnt = i; // 마지막으로 찾은 A의 index가 이동거리와 같다.
            --move_cnt; // A를 찾을때마다 찾을 A갯수 감소
        }
        if ((name[(name.size() - i) % name.size()] != 'A')) {
            rev_cnt = name.size() - ((name.size() - i) % name.size()); // 마지막으로 찾은 A의 index가 이동거리와 같다.
            if (i != 0)--move_cnt; // [0] == 'A'라면 앞에 조건에서 한번 찾았기에 중복으로 지워질거 방지
        }
        if (move_cnt == 0) break;
    }

    full_move_cnt = min((2 * seq_cnt) + rev_cnt, seq_cnt + (2 * rev_cnt)); // 정방향 u턴 역방향 vs 역방향 u턴 정방향 => 중 최소거리


    joy_cnt += min(half_move_cnt, full_move_cnt); // 단방향 vs 양방향 => 중 최소 거리

    answer = joy_cnt;

    return answer;
}