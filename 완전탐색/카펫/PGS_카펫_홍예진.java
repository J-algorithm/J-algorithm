class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        // 세로(height) : n, 가로(width) : m (n <= m)
        // 테두리 1줄 => brown = 2n+2m - 4  => n+m = (brown+4)/2
        // yellow + brown = n*m
        
        int sum = (brown + 4)/2; // k = n+m;
        int height = 3;
        int width = sum - height;
        while(height < sum){
            if(yellow + brown == width*height) {
                break;
            }
            
            height++;
            width = sum - height;
        }
        
        answer[0] = width;
        answer[1] = height;
        
        return answer;
    }
}