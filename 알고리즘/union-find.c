#include<stdio.h>

int getParent(int parent[],int i) {
	if(parent[i] == i) return i;
	return parent[i] = getParent(parent, parent[i]);
}

void unionParent(int parent[],int i,int j) {
 i = getParent(parent,i);
 j = getParent(parent,j);
 if( i < j ) parent[j] = i;
 else parent[i] = j;
}

int findParent(int parent[],int i,int j) {
 i = getParent(parent,i);
 j = getParent(parent,j);
 if ( i == j ) return 1;
 else return 0;
}

int main() {
	int parent[10];
	for(int i=0; i<10;i++){
		parent[i] = i;
	}
	unionParent(parent,1,2);
	unionParent(parent,3,4);
	unionParent(parent,5,6);
	unionParent(parent,5,4);
	unionParent(parent,2,7);
	unionParent(parent,1,8);
	unionParent(parent,1,9);
	
	printf("1과 5는 연결되어 있나요? : %d",findParent(parent,1,5));
	return 0;
}
