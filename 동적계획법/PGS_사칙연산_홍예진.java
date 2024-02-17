
class Solution {
    public int getMax(int idx, String arr[]){
        int num = Integer.parseInt(arr[idx+1]);

        if(idx == arr.length - 2){
            if(arr[idx].equals("+"))
                return num;
            else
                return -num;
        }

        if(arr[idx].equals("+")){
            return getMax(idx+2, arr) + num;
        } else {
            // idx+1번째 수를 괄호로 감싸지 않을 때
            int max1 = getMax(idx+2, arr) - num;
            // idx+1번째 수를 괄호로 감쌀 때
            int max2 = -(getMin(idx+2, arr) + num); // getMin(idx+1, arr) = num + getMin(idx+2, arr)

            return Math.max(max1, max2);
        }
    }
    public int getMin(int idx, String arr[]){
        int num = Integer.parseInt(arr[idx+1]);

        if(idx == arr.length - 2){
            if(arr[idx].equals("+"))
                return num;
            else
                return -num;
        }

        if(arr[idx].equals("+")){
            return getMin(idx+2, arr) + num;
        } else {
            // idx+1번째 수를 괄호로 감싸지 않을 때
            int max1 = getMin(idx+2, arr) - num;
            // idx+1번째 수를 괄호로 감쌀 때
            int max2 =  -(getMax(idx+2, arr) + num);
            return Math.min(max1, max2);
        }
    }
    public int solution(String arr[]) {
        return getMax(1, arr) + Integer.parseInt(arr[0]);
    }
}
