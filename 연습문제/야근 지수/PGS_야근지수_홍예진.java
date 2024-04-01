import java.util.Arrays;

class Solution {
    public long getSum(int[] works){
        long sum = 0;
        for(int work : works){
            sum += work;
        }
        return sum;
    }
    public long solution(int n, int[] works) {
        long answer = 0;
        long sum = getSum(works);
        sum -= n; // 야근을 해야하는 시간 총합
        if(sum <= 0)
            return 0;

        int length = works.length;
        Arrays.sort(works);
        for(int i = 0; i < works.length; i++){
            int work = works[i];
            long average = sum/length;
            if(work <= average){
                answer += work*work;
                sum -= work;
                length--;
            } else {
                // 남은 야근 시간이 전부 평균 이상일 때
                // 편차를 최대한 나눈다면,
                // 일부(over)는 average+1 만큼의 야근시간이 필요하고,
                long over = sum%length;
                answer += (over)*(average+1)*(average+1);

                // 일부(other, 나머지)는 average만큼의 야근시간이 필요하다.
                long other = length - over;
                answer += (other)*(average)*(average);

                break;
            }
        }

        return answer;
    }
}