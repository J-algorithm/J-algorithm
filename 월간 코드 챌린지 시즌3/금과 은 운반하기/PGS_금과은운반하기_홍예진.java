import java.util.*;
class City {
    int gold, silver, moveTime, maxWeight;
    long totalMoveTime; // 해당 도시만을 이용해서 금, 은을 운반할 때 드는 시간. 최대 10^14
    public City(int gold, int silver, int maxWeight, int moveTime, long totalMoveTime){
        this.gold = gold;
        this.silver = silver;
        this.moveTime = moveTime;
        this.maxWeight = maxWeight;
        this.totalMoveTime = totalMoveTime;
    }
}
class Solution {
    long a, b;
    long minTime, maxTime;
    int sum;
    int N;
    City[] cities;
    public void setMinMaxTime(){
        minTime = Long.MAX_VALUE;
        maxTime = Long.MIN_VALUE;
        for(City city : cities){    
            minTime = Math.min(minTime, city.totalMoveTime);
            maxTime = Math.max(maxTime, city.totalMoveTime);
        }
    }
    // 옮겨야할 무게 w와 한 회당 옮길 수 있는 무게 weightAtOnce
    // 무게를 전부 옮기기 위해 필요한 이동 횟수 moveCount반환
    public long getMoveCount(int w, int weightAtOnce){
        int moveCount = w/weightAtOnce;
        if(w%weightAtOnce > 0) moveCount++;
        // 가는거 + 오는거
        return moveCount + (moveCount -1);
    }
    public void init(int a, int b, int[] g, int[] s, int[] w, int[] t){
        // 모든 도시의 광물(금, 은)의 합이, 요구되는 광물의 양(a, b)보다 같거나 크기 때문에
        // 한 도시에서 모든 광물을 전달하는 시간이 시간의 최대값과 최소값을 결정함.
        this.a = a;
        this.b = b;
        N = g.length;
        sum = a+b;
        cities = new City[N];
        for(int i = 0; i < N; i++){
            long moveCount = getMoveCount(sum, w[i]);
            long totalMoveTime = (long)t[i]*moveCount; // 한 도시에서 모든 광물을 옮길 때 필요한 총 시간           
            cities[i] = new City(g[i], s[i], w[i], t[i], totalMoveTime);
        }
        setMinMaxTime();
    }
    public boolean isEnoughTime(long time){
        long totalWeight = 0;
        long totalGold = 0;
        long totalSilver = 0;
        for(City city : cities){
            long moveCount = (time/city.moveTime)/2 + (time/city.moveTime)%2; // 주어진 시간동안 이동할 수 있는 횟수
            totalWeight += Math.min(city.gold+city.silver, city.maxWeight*moveCount);
            totalGold += Math.min(city.gold, city.maxWeight*moveCount);
            totalSilver += Math.min(city.silver, city.maxWeight*moveCount);
        }
        // 모든 도시에서 옮길 수 있는 광물의 총합(w)이 구해야하는 광물의 총합(a+b)보다 작다면 false
        if(totalWeight < sum) return false;
        // 모든 도시에서 옮길 수 있는 금(g)의 합이 구해야하는 금의 총합(a)보다 적다면 false
        if(totalGold < a) return false;
        // 모든 도시에서 옮길 수 있는 은(s)의 합이 구해야하는 은의 총합(b)보다 적다면 false
        if(totalSilver < b) return false;
        
        return true;
    }
    public long binarySearch(long left, long right){
        if(left == right) return left;
        
        long mid = (left + right)/2;
        if(isEnoughTime(mid))
            return binarySearch(left, mid);
        else
            return binarySearch(mid+1, right);
    }
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        init(a, b, g, s, w, t);
        return binarySearch(minTime, maxTime);        
    }
}
