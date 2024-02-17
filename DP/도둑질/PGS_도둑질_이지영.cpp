#include <string>
#include <vector>

using namespace std;

int solution(vector<int> money) {
    int answer = 0;
    int N = money.size();
    int dp[1000001][2] = {0, };

    for (int i=2; i<=N; i++) {
        dp[i][0] = max(dp[i-2][0]+money[i-2], dp[i-1][0]);
        dp[i][1] = max(dp[i-2][1]+money[i-1], dp[i-1][1]);
    }

    return max(dp[N][0], dp[N][1]);
}