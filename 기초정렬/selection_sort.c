#include<stdio.h>

// N^2 수행시간

int main() {
	int i,j, min, index, temp;
	int arr[10] = {1,10,5,8,7,6,4,3,2,9};
	for(int i= 0 ; i < 10; i++) {
		min = 9999;
		for(j = i ; j < 10; j++) {
			if(min > arr[j]) {
				min = arr[j];
				index = j;
			}
		}
		temp = arr[i];
		arr[i] = arr[index];
		arr[index] = temp;  
		
	}
	for(int i=0;i<10;i++){
		printf("%d ",arr[i]);
	}

}
