import java.util.*;

class Airplane implements Comparable<Airplane> {
    String name;
    LinkedList<Airplane> list;
    public Airplane(String name){
        this.name = name;
        list = new LinkedList<Airplane>();
    }
    @Override
    public int compareTo(Airplane a){
        return this.name.compareTo(a.name);
    }
    
}

class Solution {
    static int n;
    public boolean dfs(int cnt, Airplane ap, HashMap<String, Airplane> map, String[] result){
        if(cnt == n){
            return true;
        }
        
        int qSize = ap.list.size();
        while(qSize-- > 0){
            Airplane next = ap.list.get(0);
            ap.list.remove(0);
            result[cnt+1] = next.name;
            if(dfs(cnt + 1, next, map, result)) return true;
            result[cnt+1] = "";
            ap.list.add(next);
        }
        
        return false;
    }
    public String[] solution(String[][] tickets) {
        n = tickets.length;
        HashMap<String, Airplane> map = new HashMap<>();
        for(String[] ticket : tickets){
            Airplane from = map.get(ticket[0]) == null ? new Airplane(ticket[0]) : map.get(ticket[0]);
            Airplane to = map.get(ticket[1]) == null ? new Airplane(ticket[1]) : map.get(ticket[1]);
            from.list.add(to);
            map.put(ticket[0], from);
            map.put(ticket[1], to);

        }
        
        for(String key : map.keySet()){
            Collections.sort(map.get(key).list);
        }
        
        String start = "ICN";
        Airplane ap = map.get(start);
        String[] result = new  String[n];
        result[0] = start;
        dfs(0, ap, map, result);
        
        return result;
    }
}