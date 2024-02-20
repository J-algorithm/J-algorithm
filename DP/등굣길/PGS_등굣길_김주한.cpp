#include <string>
#include <vector>

using namespace std;

#define FOR(i,s,e) for(int i=s; i<e; ++i)
#define MAP_MAX 101
#define MOD_NUM 1000000007

int dp[MAP_MAX][MAP_MAX];

int solution(int m, int n, vector<vector<int>> puddles) {
    int answer = 0;

    FOR(i, 0, puddles.size()) { // 우물 지정
        int y = puddles[i][0];
        int x = puddles[i][1];

        dp[y][x] = -1;
    }

    dp[1][1] = 1; // 시작위치 세팅
    FOR(y, 1, (m + 1)) {
        FOR(x, 1, (n + 1)) {
            int num1 = 0;
            int num2 = 0;
            if (dp[y][x] == -1) continue;//웅덩이 만남
            if (((y - 1) >= 1) && (dp[y - 1][x] != -1)) num1 = dp[y - 1][x];
            if (((x - 1) >= 1) && (dp[y][x - 1] != -1)) num2 = dp[y][x - 1];

            dp[y][x] += (num1 + num2) % MOD_NUM;
        }
    }

    answer = dp[m][n];

    return answer;
}