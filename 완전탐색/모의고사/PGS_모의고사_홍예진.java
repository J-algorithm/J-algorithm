import java.util.*;

class Person {
    int num;
    int point;
    public Person(int num, int point){
        this.num = num;
        this.point = point;
    }
    public int getNum(){
        return this.num;
    }
    public String toString(){
        return num +", "+point;
    }
}
class Solution {
    public int getPoint(int[] p, int[] answers){
        int point = 0;
        int idx = 0;
        int size = p.length;
        for(int answer : answers){
            if(p[idx] == answer)
                point++;
            idx = (idx+1)%size;
        }
        return point;
    }
    public int[] solution(int[] answers) {
        int[][] pArr = 
        {{1,2,3,4,5},
        {2,1,2,3,2,4,2,5},
        {3,3,1,1,2,2,4,4,5,5}};
        int[] point = new int[3];
        
        ArrayList<Person> list = new ArrayList<>();
        int max = 0;
        for(int i = 0; i < 3 ; i++){
            point[i] = getPoint(pArr[i], answers);
            max = max < point[i] ? point[i] : max;
            list.add(new Person(i+1, point[i]));
        }
        for(int i = 2; i >= 0; i--){
            if(list.get(i).point == max) continue;
            list.remove(i);
        }
        
        int[] answer = list.stream().mapToInt(Person::getNum).toArray();
        return answer;
    }
}