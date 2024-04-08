import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        // 피로도 = (남은 작업량^2)
        long answer = 0;
        PriorityQueue<Integer> PQ = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int work : works) {
            PQ.add(work);
        }

        while (n > 0 && !PQ.isEmpty()) {
            int cur = PQ.poll();
            // 현재 가장 큰 값과 그 다음 값이랑 차이 비교
            // 만약 1개밖에 없다면 그냥 1개만 줄이고 끝
            int diff = PQ.isEmpty() ? cur : cur - PQ.peek();

            // 만약 차이가 0이라면 작업을 1만큼 줄이고 다시 PQ에 넣어줌
            if (diff == 0) {
                n -= 1;
                int remainWork = cur - 1;
                if (remainWork > 0) {
                    PQ.add(cur - 1);
                }
            } else {
                // 차이가 0이 아니라면 차이만큼 작업량을 줄여줌
                // 현재 할 수 있는 작업 시간과, 남은 작업량 중 작은 것을 선택
                int workTime = Math.min(n, diff);
                n -= workTime;
                int remainWork = cur - workTime;
                if (remainWork > 0) {
                    PQ.add(remainWork);
                }
            }
        }

        long result = 0;

        // PQ : 남은 작업량
        while (!PQ.isEmpty()) {
            int remainTime = PQ.poll();
            result += 1L * remainTime * remainTime;
        }
        return result;
    }


}