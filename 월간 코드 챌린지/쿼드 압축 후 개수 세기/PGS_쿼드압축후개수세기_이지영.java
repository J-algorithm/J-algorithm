public class PGS_쿼드압축후개수세기_이지영 {

    class Solution {
        int[] answer;

        public int[] solution(int[][] arr) {

            int N = arr.length;
            answer = new int[2];

            compress(0, 0, N, arr);

            return answer;
        }

        void compress(int startX, int startY, int length, int[][] arr) {
            if (length == 1) {
                answer[arr[startX][startY]]++;
                return;
            }

            int num = arr[startX][startY];
            boolean flag = true;

            for (int i=startX; i<startX+length; i++) {
                for (int j=startY; j<startY+length; j++) {
                    if(arr[i][j] != num) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                answer[num]++;
                return;
            }

            compress(startX, startY, length/2, arr);
            compress(startX + length/2, startY, length/2, arr);
            compress(startX, startY + length/2, length/2, arr);
            compress(startX + length/2, startY + length/2, length/2, arr);
        }
    }

}
