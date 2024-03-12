import java.util.Arrays;

public class PGS_구명보트_공진호 {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        int p = 0;
        int q = people.length - 1;

        int cnt = 0;
        while(p<=q){
            int lightOne = people[p];
            int heavyOne = people[q];
            if(lightOne + heavyOne <= limit){
                p++;
                q--;
            }else{
                q--;
            }
            cnt++;

        }
        return cnt;
    }

    public static void main(String[] args) {
        PGS_구명보트_공진호 solution = new PGS_구명보트_공진호();
        int[] people = {70, 80, 50};
        int limit = 100;
        System.out.println(solution.solution(people, limit));
    }

}
