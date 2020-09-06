#include<stdio.h>
#define INF 1000000000

int number = 6;

// 방향성 있는 매트릭스
int a[6][6] = {
	{0,1,5,INF,INF,2},
	{INF,0,2,INF,INF,INF},
	{INF,INF,0,INF,1,INF},
	{INF,1,INF,0,INF,INF},
	{INF,INF,INF,1,0,INF},
	{INF,INF,INF,INF,INF,0},
};
int d[6][6];

void floyd_warshell() {
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
				if(d[i][k] + d[k][j] < d[i][j]) {
					d[i][j] = d[i][k] + d[k][j];
				}					
			}	
		}
	}

	// d 보기
	for(int i=0;i<number;i++) {
		for(int j=0;j<number;j++) {
			if(d[i][j] == INF) printf("INF ");
			else printf("%3d ",d[i][j]);
		}	
		printf("\n");
	}
}

int main() {
	floyd_warshell();
}
