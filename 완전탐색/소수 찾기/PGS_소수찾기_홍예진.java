import java.util.*;

class Solution {
    Set<Integer> set;
    public boolean isPrime(int value){
        if(value == 2) return true;
        if(value == 1 || value%2 == 0) return false;
        for(int i = 3; i <= Math.sqrt(value); i+=2){
            if(value%i == 0) {
                return false;
            }
        }
        return true;
    }
    public void solve(int idx, StringBuilder sb, String[] number){
        String temp = number[idx];
        sb.append(number[idx]);
        number[idx] = "";
        int value = Integer.parseInt(sb.toString());
        if(isPrime(value)) set.add(value);

        for(int i = 0; i < number.length; i++){
            if(Objects.equals(number[i], "")) continue;
            solve(i, sb, number);
        }

        sb.setLength(sb.length() - 1);
        number[idx] = temp;
    }
    public int solution(String numbers) {
        String[] number = numbers.split("");
        set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < number.length; i++) {
            sb.setLength(0);
            solve(i, sb, number);
        }
        return set.size();
    }
}