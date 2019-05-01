#include<stdio.h>
int find_prime(int );
int h_i(int ,int ,int );
int main() {

	// n의 개수 받기
	int num;
	scanf("%d",&num);
	
	//n개 만큼 배열에 저장하기
	int x[num],i=0;
	for(i=0;i<num;i++){
	scanf("%d",&x[i]);
	}

	//M구하기
	int M=2*num;
	while(++M) {
		if(find_prime(M)==1)
		break;
	}
	// 새로운 M의 해시테이블을 생성 및 0 초기화
	int hash[M];
	for(i=0;i<M;i++){
		hash[i]=0;
	}
	//break 수 세기
	int bk=0;

	for(i=0;i<num;i++){
		int j=0;
		if(hash[h_i(x[i],j,M)]==0) {
			hash[h_i(x[i],j,M)]=x[i];
		}else {
			while(1) {
				j++;
				bk++;
				if(hash[h_i(x[i],j,M)]==0){
					hash[h_i(x[i],j,M)]=x[i];
					break;
				}
			}
		}
	}
	printf("%d\n",bk);
	return 0;
}
// 해시의 위치 찾기 
int h_i(int x,int i,int M) {
	int v = (x+i) % M;
	return v;
}
// 공약수 찾기
int find_prime(int v) {
	int i=0;
	for(i=2;i<=(v/2);i++){
		if(v%i == 0)
		{
			return 0;
		}
	}
	return 1;	
}
