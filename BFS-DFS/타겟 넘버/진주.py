'''
주어진 모든 수를 - 붙였다가 떼었다가 반복하면서
그때마다 합을 구해서 타겟 넘버가 되는지 확인하기
'''
import sys
sys.setrecursionlimit(10**7)

def recur(numbers, target, start):
    global cnt
    if sum(numbers) == target:
        cnt += 1
        return
    for i in range(start, len(numbers)):
        numbers[i] = -numbers[i]
        recur(numbers, target, i + 1)
        numbers[i] = -numbers[i]
        
cnt = 0

def solution(numbers, target):
    global cnt
    recur(numbers, target, 0)
    answer = cnt
    return answer