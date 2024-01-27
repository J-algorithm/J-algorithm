class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        // 세로 : n, 가로 : m (n <= m)
        // 테두리 1줄 => brown = 2n+2m - 4  => n+m = (brown+4)/2
        // yellow + brown = n*m
        
        int k = (brown + 4)/2; // k = n+m;
        int n = 3;
        int m = k - n;
        while(n < k){
            if(yellow + brown == m*n) {
                break;
            }
            
            n++;
            m = k - n;
        }
        
        answer[0] = m;
        answer[1] = n;
        
        return answer;
    }
}