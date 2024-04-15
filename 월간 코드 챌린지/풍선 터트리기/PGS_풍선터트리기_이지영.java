public class PGS_풍선터트리기_이지영 {

    class Solution {
        int N;
        int[] leftMin, rightMin;

        // 나보다 작은 수들은 최솟값 풍선을 이용하여 다 제거하고, 맨 마지막에 찬스를 써서 최솟값 풍선을 터뜨리면 살아남을 수 있음
        // 하지만 찬스는 1번 -> 좌우를 살폈을 때, 한쪽에는 나보다 큰 수밖에 없어야 함
        public int solution(int[] a) {
            int answer = 0;

            N = a.length;
            leftMin = new int[N];
            rightMin = new int[N];

            leftMin[0] = a[0];
            for (int i=1; i<N; i++) {
                leftMin[i] = Math.min(leftMin[i-1], a[i]);
            }

            rightMin[N-1] = a[N-1];
            for (int i=N-2; i>=0; i--) {
                rightMin[i] = Math.min(rightMin[i+1], a[i]);
            }

            for (int i=0; i<N; i++) {
                if(leftMin[i] < a[i] && a[i] > rightMin[i]) continue;
                answer++;
            }

            return answer;
        }
    }

}
