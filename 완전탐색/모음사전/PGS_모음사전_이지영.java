import java.util.*;

public class PGS_모음사전_이지영 {

    class Solution {
        int MAX_LENGTH = 5;
        char[] alphabet = {'A', 'E', 'I', 'O', 'U'};
        List<String> dictionary = new ArrayList<>();

        public int solution(String word) {
            int answer = 0;
            makeWord(new StringBuilder());

            Collections.sort(dictionary);

            for (int i=0; i<dictionary.size(); i++) {
                if(word.equals(dictionary.get(i))) {
                    answer = i;
                    break;
                }
            }
            return answer;
        }

        void makeWord(StringBuilder sb) {
            dictionary.add(sb.toString());
            if(sb.length()==MAX_LENGTH) return;

            for (char c : alphabet) {
                makeWord(sb.append(c));
                sb.setLength(sb.length()-1);
            }
        }
    }

}
