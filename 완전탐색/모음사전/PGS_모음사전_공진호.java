public class PGS_모음사전_공진호 {
    char[] vowels = {'A', 'E', 'I', 'O', 'U'};
    int cnt = 0;

    boolean dfs(int depth, String target, String str) {

        // 일치한 경우
        if (target.equals(str)) {
            return true;
        }
        // 5글자가 된 경우
        if (depth == vowels.length) {
            return false;
        }

        for (int i = 0; i < vowels.length; i++) {
            cnt++;
            if (dfs(depth + 1, target, str + vowels[i])) {
                return true;
            }
        }
        return false;
    }

    public int solution(String word) {
        // word의 길이는 5이하
        // word는 'A', 'E', 'I', 'O', 'U'
        dfs(0, word, "");
        return cnt;
    }
}
