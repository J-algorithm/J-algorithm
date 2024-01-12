import sys
input = sys.stdin.readline
from collections import deque

# 범위 체크 함수
def possible(a, b):
    global n, m
    return 0 <= a < n and 0 <= b < m

# 출발 지점부터 다른 지점까지의 거리 측정하는 함수
def bfs(x, y, maps, visited):
    q = deque()
    q.append((x, y))
    visited[x][y] = 1
    while q:
        cur_x, cur_y = q.popleft()
        # 도착 지점에 도달하면 끝내기
        if cur_x == n - 1 and cur_y == m - 1:
            return
        for dx, dy in dxy:
            nx, ny = cur_x + dx, cur_y + dy
            if not possible(nx, ny):
                continue
            if visited[nx][ny] != -1:
                continue
            if maps[nx][ny] == 0:
                continue
            # 이동할 때마다 한 칸씩 더해주기
            visited[nx][ny] = visited[cur_x][cur_y] + 1
            q.append((nx, ny))

dxy = [(0, 1), (1, 0), (0, -1), (-1, 0)]
n = 0
m = 0

def solution(maps):
    global n, m
    answer = -1
    n = len(maps)
    m = len(maps[0])
    visited = [[-1] * m for _ in range(n)]
    bfs(0, 0, maps, visited)
    # 출발 지점부터 도착 지점까지의 거리
    answer = visited[n - 1][m - 1]
    return answer