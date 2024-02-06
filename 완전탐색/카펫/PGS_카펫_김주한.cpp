#include <string>
#include <vector>

using namespace std;

#define FOR(i,s,e) for(int i=s; i<e; ++i)

vector<int> length_v; // 약수 저장
vector<int> width_v; // 약수 저장

void get_divisor(int num) {
    /*
    num에 대한 약수를 찾는 함수
    */
    FOR(i, 1, num + 1) {
        if ((num % i) != 0) continue; // 나누어 떨어지지 않으면 약수가 아님
        if (i > (num / i)) break; // 세로 길이가 가로 길이보다 길면 안됨
        length_v.push_back(i);
        width_v.push_back(num / i);
    }
}

bool isSame(int n1, int n2, int brown) {
    /*
    yellow를 감싸고 있는 brown의 크기는 항상
    yellow의 가로길이의 2배, 세로길이의 2배에 모퉁이 4개를 더한 값이다.
    */
    int width = n2 * 2;
    int length = n1 * 2;
    int corner = 4;

    /*
    num은 yellow의 크기에 대해 테두리를 감싼 크기
    */
    int num = width + length + corner;

    if (num == brown) return true; // brown과 같다면 찾고자 하는 가로 세로 길이임
    else return false;
}

vector<int> solution(int brown, int yellow) {
    vector<int> answer;

    get_divisor(yellow);

    FOR(i, 0, length_v.size()) {
        if (isSame(length_v[i], width_v[i], brown) == true) {
            answer.push_back(width_v[i] + 2);
            answer.push_back(length_v[i] + 2);
        }
    }

    return answer;
}