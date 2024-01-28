#include <string>
#include <vector>

using namespace std;

#define FOR(i,s,e) for(int i=s; i<e; ++i)

vector<int> p1 = { 1,2,3,4,5 }; // 1번 수포자
vector<int> p2 = { 2,1,2,3,2,4,2,5 }; // 2번 수포자
vector<int> p3 = { 3,3,1,1,2,2,4,4,5,5 }; // 3번 수포자
int p1_cnt; // 1번 수포자가 맞힌 문제 갯수
int p2_cnt; // 2번 수포자가 맞힌 문제 갯수
int p3_cnt; // 3번 수포자가 맞힌 문제 갯수
int max_num; // 최대로 많이 맞힌 문제 갯수 저장

vector<int> solution(vector<int> answers) {
    vector<int> answer;

    FOR(i, 0, answers.size()) {
        /*
        같은 번호로 무한 반복하며 찍는다고 가정
        p1은 5번에 1순환 == p1.size()
        p2는 8번에 1순환 == p2.size()
        p3는 10번에 1순환 == p3.size()

        answers배열을 순회하며 문제를 맞히면 수포자 각각의 cnt를 갱신해준다.

        또한 현재까지 가장 많이 맞힌 갯수를 max_num에 업데이트하면서 지나감.
        */
        if (answers[i] == p1[i % (p1.size())]) {
            ++p1_cnt;
        }
        if (answers[i] == p2[i % (p2.size())]) {
            ++p2_cnt;
        }
        if (answers[i] == p3[i % (p3.size())]) {
            ++p3_cnt;
        }
        max_num = max(max_num, max(p1_cnt, max(p2_cnt, p3_cnt)));
    }

    /*
    가장 많이 맞힌 갯수와 수포자 각각의 맞힌 갯수가 같다면,
    해당 수포자는 가장 많은 정답을 맞힌 수포자임
    */
    if (p1_cnt == max_num) answer.push_back(1);
    if (p2_cnt == max_num) answer.push_back(2);
    if (p3_cnt == max_num) answer.push_back(3);

    return answer;
}