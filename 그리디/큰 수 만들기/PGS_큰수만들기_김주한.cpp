#include <string>
#include <vector>

using namespace std;

#define FOR(i,s,e) for(int i=s; i<e; ++i)

string solution(string number, int k) {
    string answer = "";

    pair<int, int> s = make_pair(-1, 0); // idx, num

    /*
      "4177252841"
      1. 앞에서부터 k자리만큼 버린다고 생각.
      2. [0] ~ [k=4] 각자리 수를 비교 후 가장 큰 수가 가장 앞자리가 됨. => [2]에 있는 7이 가장 큰 수
      3. [2] ~ [5] 0~k자리까지 수에서 가장 앞자리를 골랐으니, 가장 큰수가 있던 index부터 k+1자리까지 중 가장 큰수 찾기 => [3]에 있는 7이 가장 큰 수
      4. 마지막 자리까지 위와같이 반복하면
      5. "775841"이 나온다.
    */
    FOR(i, k, number.size()) {
        int tmp = -1;
        int start = s.first + 1;
        FOR(j, start, (i + 1)) {
            if (tmp < int(number[j] - '0')) {
                tmp = int(number[j] - '0');

                s.first = j;
                s.second = tmp;
            }
        }
        answer += to_string(s.second);
    }



    return answer;
}