class Solution {
	public int getMinIdx(int[] a){
		int min = Integer.MAX_VALUE;
		int idx = -1;
		for(int i = 0; i < a.length; i++){
			if(a[i] < min){
				idx = i;
				min = a[i];
			}
		}
		return idx;
	}
	public int solution(int[] a){
		int answer = 1; // minIdx
		
		int minIdx = getMinIdx(a);
		// 가장 작은 숫자는 여태까지의 숫자들 중에 가장 작거나, 두번째로 작아야한다.
		int minLeftFirst = 0;
		int minLeftSecond = 0;
		for(int i = 0; i < minIdx; i++){
			if(a[i] <= a[minLeftFirst]){
				answer++;
				minLeftFirst = i;
				minLeftSecond = i;
			} else if(a[minLeftFirst] < a[i] && a[i] < a[minLeftSecond]){
				answer++;
				minLeftSecond = i;
			}
		}
		
		int minRightFirst = a.length - 1;
		int minRightSecond = a.length - 1;
		for(int i = a.length - 1; i > minIdx; i--){
			if(a[i] <= a[minRightFirst]){
				answer++;
				minRightFirst = i;
				minRightSecond = i;
			} else if(a[minRightFirst] < a[i] && a[i] < a[minRightSecond]){
				answer++;
				minRightSecond = i;
			}
		}
		
		return answer;
	}
}
