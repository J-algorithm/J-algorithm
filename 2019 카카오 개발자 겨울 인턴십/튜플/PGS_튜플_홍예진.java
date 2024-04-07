import java.util.*;

class Solution {
    public int[] solution(String s) {
        s = s.replaceAll("\\}", "");
        s = s.replaceAll("\\{", "");
        StringTokenizer st = new StringTokenizer(s, ",");
        Map<Integer, Integer> map = new HashMap<>();
        while(st.hasMoreTokens()){
            int key = Integer.parseInt(st.nextToken());
            if(map.get(key) == null)
                map.put(key, 1);
            else 
                map.put(key, map.get(key)+1);
        }
        
        List<Integer> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList, (k1, k2) -> map.get(k2) - map.get(k1));
        
        int[] answer = new int[keyList.size()];
        for(int i = 0; i < keyList.size(); i++){
            answer[i] = keyList.get(i);
        }
        
        return answer;
    }
}
