import java.util.*;

public class PGS_튜플_공진호 {
    /**
     *  문자열을 받아서 set으로 만들어주는 함수
     *  ex) "{1,2,3}" -> {1,2,3}
     */
    private  HashSet<Integer> getSet(String s){
        StringTokenizer st = new StringTokenizer(s,",");
        int idx = 0;
        HashSet<Integer> hs = new HashSet<>();
        while(st.hasMoreTokens()){
            hs.add(Integer.parseInt(st.nextToken()));
        }
        return hs;
    }
    private int[] getContent(String s){
        StringTokenizer st = new StringTokenizer(s,"{}");
        // 나누어진 토큰 {} , {} , {} , {}
        // 토큰을 하나씩 꺼내서 set으로 만들어서 list에 넣어줌
        List<HashSet<Integer>> list = new ArrayList<>();

        while(st.hasMoreTokens()){
            String token = st.nextToken();
            if (token.equals(",")) {
                continue;
            }
            list.add(getSet(token));
        }
        // 사이즈 순으로 오름차순 정렬
        Collections.sort(list,( o1, o2) -> o1.size() - o2.size());

        int idx = 0;
        int[] result = new int[list.size()];
        HashSet<Integer> cur = new HashSet<>();

        // 현재 set에 포함되어 있는 원소를 제거하고 다음 set에 있는 원소를 찾아서 result에 넣어줌
        for(HashSet<Integer> hs : list){
            hs.removeAll(cur);
            int remain = hs.iterator().next();
            result[idx++] = remain;
            cur.add(remain);
        }
        return result;
    }


    public int[] solution(String s) {
        // 가장 바깥의 괄호 제거
        s = s.substring(1, s.length() - 1);
        return getContent(s);
    }

    public static void main(String[] args) {
        PGS_튜플_공진호 solution = new PGS_튜플_공진호();
        String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
        System.out.println(solution.solution(s));
    }
}
