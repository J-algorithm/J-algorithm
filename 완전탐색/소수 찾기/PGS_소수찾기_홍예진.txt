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
    public void solve(int idx, String num, String[] number){
        String temp = number[idx];
        num += number[idx];
        number[idx] = "";
        if(isPrime(Integer.parseInt(num))) set.add(Integer.parseInt(num));

        for(int i = 0; i < number.length; i++){
            if(number[i] == "") continue;
            solve(i, num, number);
        }

        number[idx] = temp;
    }
    public int solution(String numbers) {
        String[] number = numbers.split("");
        set = new HashSet<>();
        for(int i = 0; i < number.length; i++)
            solve(i, "", number);

        return set.size();
    }
}