
class Solution {

    // 지우면 값이 커지는 자리수를 찾는다.
    public int getRemoveIndex(StringBuilder sb){
        for(int i = 0; i < sb.length() -1; i++){
            if(sb.charAt(i) < sb.charAt(i+1)) return i;
        }
        return -1;
    }
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder(number);

        while(k > 0){
            int index = getRemoveIndex(sb);
            if(index != -1) {
                sb.deleteCharAt(index);
                k--;
            } else {
                break;
            }
        }

        //  전체 수가 내림차순으로 지울 인덱스가 없었다면 뒤에서부터 지운다.
        sb.setLength(sb.length() - k);

        return sb.toString();
    }
}