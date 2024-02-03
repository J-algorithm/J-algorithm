public class PGS_카펫_이지영 {

    class Solution {
        public int[] solution(int brown, int yellow) {

            int perimeter = getYellowPerimeter(brown);
            int[] answer = getCarpetSize(perimeter, yellow);

            return answer;
        }

        int getYellowPerimeter(int brown) {
            return (brown - 4) / 2;
        }

        int[] getCarpetSize(int perimeter, int yellow) {
            int[] result = new int[2];

            for (int height = 1; height <= perimeter/2; height++) {
                int width = perimeter - height;

                if (width*height == yellow) {
                    result[0] = width+2;
                    result[1] = height+2;

                    break;
                }
            }

            return result;
        }
    }

}
