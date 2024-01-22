import java.util.*;
public class PGS_단어변환_이지영 {
    Map<String, Boolean> visited;

    class Word {
        String word;
        int dist;

        public Word(String word, int dist) {
            this.word = word;
            this.dist = dist;
        }
    }

    public int solution(String start, String target, String[] words) {
        int answer = 0;
        init(words);

        if(!visited.containsKey(target)) {
            return 0;
        }

        answer = bfs(start, target);

        return answer;
    }

    void init(String[] words) {
        visited = new HashMap<>();

        for (String word : words) {
            visited.put(word, false);
        }
    }

    int bfs(String start, String target) {
        Queue<Word> q = new ArrayDeque<>();
        q.add(new Word(start, 0));
        if(visited.containsKey(start)) {
            visited.put(start, true);
        }

        while(!q.isEmpty()) {
            Word now = q.poll();
            if (now.word.equals(target)) return now.dist;

            char[] word = now.word.toCharArray();

            for (int i=0; i<word.length; i++) {
                char origin = word[i];

                for (int j='a'; j<'z'+1; j++) {
                    char changed = (char) j;
                    if(origin == changed) continue;

                    word[i] = changed;
                    String nextWord = new String(word);
                    if(!visited.containsKey(nextWord) || visited.get(nextWord)) continue;

                    q.add(new Word(nextWord, now.dist+1));
                    visited.put(nextWord, true);
                }

                word[i] = origin;
            }
        }

        return 0;
    }
}