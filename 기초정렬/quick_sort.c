#include<stdio.h>

int data[10] = {1,10,5,8,7,6,4,3,2,9};
int number = 10;

void swap(int *a, int *b) {
	int temp = *a;
	*a = *b;
	*b = temp;
}
void show(int *arr) {
	for(int i=0 ; i< number ; i++) {
		printf("%d ",arr[i]);
	}
}

void quickSort(int *arr,int start, int end) {
	if( start >= end ) {
		return;
	}
	int key = start;
	int i = start+1;
	int j = end;
	
	while(i <= j) {
		while(arr[i] >=  arr[key]) {
			i++;
		}
		while(arr[j] <= arr[key] && j > start) {
			j--;
		} 
		if( i > j ) {
			swap(&arr[j],&arr[key]);
		} else {
			swap(&arr[i],&arr[j]);
		}
	}
	quickSort(arr, start, j-1);
	quickSort(arr,j+1,end);
}


int main() {
	quickSort(data,0,number-1);
	show(data);
}
