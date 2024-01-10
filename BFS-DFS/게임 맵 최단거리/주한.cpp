#include <iostream>
#include<vector>
#include <queue>
using namespace std;

#define DUMMY 2134567890

vector<vector<int>> used;

int dy[4] = { -1,0,1,0 };
int dx[4] = { 0,1,0,-1 };

void bfs(int sy, int sx, vector<vector<int>> maps) {
    queue<pair<int, int>> q;
    used = vector<vector<int>>(maps.size(), vector<int>(maps[0].size(), DUMMY));

    q.push(make_pair(sy, sx));
    used[sy][sx] = 1;

    while (!q.empty()) {
        pair<int, int> now = q.front();
        q.pop();


        for (int i = 0; i < 4; ++i) {
            int ny = now.first + dy[i];
            int nx = now.second + dx[i];

            if (ny < 0 || ny >= maps.size() || nx < 0 || nx >= maps[0].size()) continue;
            if (used[ny][nx] != DUMMY) continue;
            if (maps[ny][nx] == 0) continue;

            used[ny][nx] = used[now.first][now.second] + 1;
            if ((now.first == maps.size()) && (now.second == maps[0].size())) return;

            q.push(make_pair(ny, nx));
        }
    }
}

int solution(vector<vector<int> > maps)
{
    int answer = 0;

    bfs(0, 0, maps);

    if (used[maps.size() - 1][maps[0].size() - 1] == DUMMY) answer = -1;
    else {
        answer = used[maps.size() - 1][maps[0].size() - 1];
    }

    return answer;
}