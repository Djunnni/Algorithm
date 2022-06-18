#include<stdio.h>

int number = 9;

int a[9][9] = {
      // A B C D E F G H I
	{0,1,0,0,0,0,1,0,0}, //A
	{1,0,1,1,1,0,0,0,0}, //B
	{0,1,0,1,0,0,0,0,0}, //C
	{0,1,1,0,0,0,0,0,0}, //D
	{0,1,0,0,0,1,1,0,0}, //E	
	{0,0,0,0,1,0,0,0,0}, //F
	{1,0,0,0,1,0,0,1,0}, //G
	{0,0,0,0,0,0,1,0,1}, //H
	{0,0,0,0,0,0,0,1,0}, //I
};
int visit[9]= {0}; // 방문 여부

void DFS(int start) {
	visit[start] = 1;
	printf("%d ", start);
	for(int i=0; i<number; i++) {
		if(visit[i] == 0 && a[start][i] == 1) {
			DFS(i);
		} 
	}
}

int main() {
	DFS(2); // start C 
}
