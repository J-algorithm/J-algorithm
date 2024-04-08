class PGS_이모티콘할인행사_공진호 {


    static int[] percent = {10,20,30,40};
    static int[] arr;
    static int N;
    static int maxPlus;
    static int maxPrice;

    // 플러스 서비스 가입자, 판매액 계산
    static int[] process(int[][] users,int[] emoticons){

        int totPrice = 0;
        int plus = 0;
        // 각각의 사용자에 대하여 판매액 계산
        for(int[] user : users){
            int tot = 0;
            for(int i = 0 ; i<N;i++){
                if(arr[i]>=user[0]){
                    int price = emoticons[i];
                    tot+= (int) (price*(100-arr[i])*0.01);
                }
            }
            // 판매액이 할인 가격보다 크다면 플러스 서비스 가입자 수 증가
            if(tot>=user[1]){
                plus++;
            }else{
                totPrice+=tot;
            }

        }

        return new int[]{plus,totPrice};

    }

    static void dfs(int idx,int[][] users,int[] emoticons ){
        if(idx == N){
            int[] result = process(users,emoticons);
            int curPlus = result[0];
            int curPrice = result[1];

            // 플러스 서비스 가입자 수가 더 많은 경우
            if(curPlus>maxPlus){
                maxPlus = curPlus;
                maxPrice = curPrice;
            }else if(curPlus == maxPlus && curPrice>maxPrice){
                // 플러스 서비스 가입자 수가 같지만 판매액이 더 큰 경우
                maxPrice = curPrice;
            }
            return;
            // 수행
        }

        // 할인율 적용
        for(int i= 0  ; i<4;i++){
            arr[idx] = percent[i];
            dfs(idx+1,users,emoticons);
        }
    }


    public int[] solution(int[][] users, int[] emoticons) {
        N = emoticons.length;
        arr = new int[N];

        dfs(0,users,emoticons);
        return new int[]{maxPlus,maxPrice};

    }
}