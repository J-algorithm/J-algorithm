import java.util.*;

public class PGS_튜플_이지영 {

    class Solution {
        Map<Integer, Integer> map = new HashMap<>();

        public int[] solution(String s) {
            String[] tuples = s.substring(1, s.length()-1).split("},");

            for (int i=0; i<tuples.length; i++) {
                String tuple = tuples[i].substring(1);
                if (i == tuples.length-1) tuple = tuple.substring(0, tuple.length()-1);

                String[] nums = tuple.split(",");

                for (String num : nums) {
                    int n = Integer.parseInt(num);

                    if(!map.containsKey(n)) {
                        map.put(n, 0);
                    }
                    map.put(n, map.get(n)+1);
                }
            }

            List<Integer> keySet = new ArrayList<>(map.keySet());
            keySet.sort((key1, key2) -> map.get(key2) - map.get(key1));

            int[] answer = new int[keySet.size()];
            for (int i=0; i<keySet.size(); i++) {
                answer[i] = keySet.get(i);
            }

            return answer;
        }
    }

}
