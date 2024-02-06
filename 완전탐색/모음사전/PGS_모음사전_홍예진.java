
class Solution {
    int count;
    String[] alphabets = {"A", "E", "I", "O", "U"};

    public boolean solve(int length, StringBuilder sb, String word){
        count++;
        if(word.equals(sb.toString())) {
            return true;
        }
        if(length == 5){
            return false;
        }

        for(String alphabet : alphabets){
            sb.append(alphabet);

            if(solve(length + 1, sb, word)){
                return true;
            }

            sb.setLength(sb.length() -1);
        }

        return false;
    }

    public int solution(String word) {
        count = 0;
        for(String alphabet : alphabets){
            if(solve(1, new StringBuilder(alphabet), word)){
                break;
            }
        }
        return count;
    }
}