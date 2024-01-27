#include <string>
#include <vector>

using namespace std;

//#define N_MAX 10000
//#define DUMMY 2134567890
#define FOR(i,s,e) for(int i=s; i<e; ++i)

//int path[2][N_MAX];
//vector<vector<int>> v;
//int min_num = DUMMY;
//
//void dfs(int lv, int l_max, int r_max) {
//    if (lv == v.size()) {
//        int num = l_max * r_max;
//        min_num = min(num, min_num);
//        return;
//    }
//
//    path[0][lv] = v[lv][0];
//    path[1][lv] = v[lv][1];
//    dfs(lv + 1, max(l_max, path[0][lv]), max(r_max, path[1][lv]));
//    path[0][lv] = v[lv][1];
//    path[1][lv] = v[lv][0];
//    dfs(lv + 1, max(l_max, path[0][lv]), max(r_max, path[1][lv]));
//}

int solution(vector<vector<int>> sizes) {
    int answer = 0;

    /*
    dfs를 활용해 문제를 해결 하려 했지만 일부 테스크케이스에서 시간초과가 나옴
    */
    //v = sizes;
    //dfs(0, 0, 0);
    //answer = min_num;


    /*
    모든 수에 대해 최댓값을 찾음.
    */
    int max_num1 = 0;
    FOR(y, 0, sizes.size()) {
        FOR(x, 0, 2) {
            max_num1 = max(max_num1, sizes[y][x]);
        }
    }
    /*
    한 개의 쌍에서 작은 수를 선택하고
    선택한 수들에서 최대값을 찾음
    */
    int max_num2 = 0;
    FOR(i, 0, sizes.size()) {
        int select_num = min(sizes[i][0], sizes[i][1]);
        max_num2 = max(select_num, max_num2);
    }

    answer = max_num1 * max_num2;

    return answer;
}