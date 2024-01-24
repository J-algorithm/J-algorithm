class Solution {
    public void swap(int[] size){
        int temp = size[0];
        size[0] = size[1];
        size[1] = temp;
    }
    public int solution(int[][] sizes) {
        int answer = 0;
        int[] max = new int[2];
        for(int i = 0; i < sizes.length; i++){
            if(sizes[i][0] < sizes[i][1]) swap(sizes[i]);
            max[0] = Math.max(max[0], sizes[i][0]);
            max[1] = Math.max(max[1], sizes[i][1]);
        }
        answer = max[0]*max[1];
        return answer;
    }
}