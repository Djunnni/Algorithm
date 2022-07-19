#include<stdio.h>

int mock(int v,int what) {
	return v/what;
}

int devi(int v,int what) {
	return v%what;
}

int bucket(int q) {
	int kg = q;
	int count = 0;
	while(kg>0) {
		if(devi(kg,5) == 0) {
			count++;
			kg -= 5;
		} else if (devi(kg,3) == 0) {
			count++;
			kg -= 3;
		} else if(kg > 5) {
			count++;
			kg -= 5;
		} else {
			count = -1;
			break;
		}
	}
	return count;
}

int main() {
	int kg,how_to_many;
	scanf("%d",&kg);
	
	how_to_many = bucket(kg);
	printf("%d",how_to_many);

	return 0;
}	
