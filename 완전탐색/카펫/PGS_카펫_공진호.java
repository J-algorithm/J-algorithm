class PGS_카펫_공진호 {
    public int[] solution(int brown, int yellow) {

        for(int i = 1; i<= yellow;i++){
            for(int j = 1;i*j<=yellow;j++){
                // 가능한 노란색 배열
                if(i*j==yellow){
                    if(2*(i+2)+ 2*j == brown){
                        // 가로길이 >= 세로 길이
                        return new int[]{j+2,i+2};
                    }
                }
            }
        }
        // default value
        return new int[]{-1, -1};
    }
}