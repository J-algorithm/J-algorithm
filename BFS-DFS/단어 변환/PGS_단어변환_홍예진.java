import java.util.*;
class PGS_단어변환_홍예진 {
    public boolean isChangeable(String word, String target) {
        int count = 0;
        for(int i = 0; i < word.length(); i++){
            char chr_word = word.charAt(i);
            char chr_target = target.charAt(i);
            if(chr_word != chr_target) count++;
        }
        
        if(count == 1)
            return true;
        return false;
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean[] visited = new boolean[words.length];
        Queue<String> q = new LinkedList<>();
        q.add(begin);
        int count = 1;
        int qSize = q.size();
        while(!q.isEmpty()){
            while(qSize-- > 0){
                String now = q.poll();
                for(int i = 0; i < words.length; i++){
                    if(visited[i]) continue;
                    if(!isChangeable(now, words[i])) continue;
                    
                    
                    if(words[i].contentEquals(target)) return count;
                    
                    q.add(words[i]);
                    visited[i] = true;
                }
            }
            count++;
            qSize = q.size();
        }
        return answer;
    }
}