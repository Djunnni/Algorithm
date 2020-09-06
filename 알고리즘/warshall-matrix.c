#include<stdio.h>

int number = 8;

// 방향성 있는 매트릭스
int a[8][8] = {
	{0,0,1,0,0,1,0,0},
	{1,0,0,0,0,0,0,0},
	{0,1,0,0,0,0,0,0},
	{0,0,0,0,0,0,1,0},
	{0,0,1,0,0,0,0,1},
	{0,0,0,1,0,0,0,0},
	{0,0,0,0,0,1,0,0},
	{0,0,0,0,1,0,0,0},
};
int d[8][8];

void washell() {
	// d 초기화
	for(int i=0;i<number;i++) {
		for(int j=0;j<number;j++) {
			d[i][j] = a[i][j];
		}	
	}
	// 출발 노드 
	for(int i=0;i<number; i++) {
		// 도착 노드
		for(int j=0;j<number; j++) {
			// 거쳐가는 노드
			for(int k=0;k<number;k++) {
				// 출발 노드에서 거쳐가는 노드의 도착노드가 접근 가능한지 확인 출발노드와 도착노드는 같지 않아야 함
				if(d[i][k] && d[k][j] && i != j) {
					d[i][j] = 1;
				}					
			}	
		}
	}

	// d 보기
	for(int i=0;i<number;i++) {
		for(int j=0;j<number;j++) {
			printf("%d ",d[i][j]);
		}	
		printf("\n");
	}
}

int isConnect(int i, int j) {
	if(d[i][j]) return 1;
	return 0;
}

int main() {
	// washell 동작하기
	washell();
	
	// 5 , 1은 연결되어 있나?
	printf("5와 1은 연결되어 있나? : %d\n",isConnect(5-1,1-1));
	// 1 , 5는 연결되어 있나?
	printf("1와 5은 연결되어 있나? : %d",isConnect(1-1,5-1));

}
