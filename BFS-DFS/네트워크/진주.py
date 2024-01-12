# 연결된 노드로 이동해서 네트워크 확인하는 함수
def dfs(node, visited):
    global graph
    visited[node] = True
    for i in graph[node]:
        if not visited[i]:
            dfs(i, visited)

graph = []
def solution(n, computers):
    global graph
    answer = 0
    # 각 노드와 연결된 노드 번호 정리
    graph = [[] for _ in range(n + 1)]
    for i in range(n):
        for j in range(n):
            if i == j:
                continue
            if computers[i][j] == 0:
                continue
            graph[i + 1].append(j + 1)

    visited = [False] * (n + 1)
    for i in range(1, n + 1):
        # 방문하지 않은 노드면, 네트워크 개수 추가
        if not visited[i]:
            answer += 1
            dfs(i, visited)
    return answer