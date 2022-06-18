#include<iostream>
#include<stack>
#include<vector>
using namespace std;
int num = 7;
int c[7];
vector<int> a[8];

void dfs(int start) {
	if(c[start]) return;
	c[start] = true;
	cout << start << ' ';
	for(int x=0; x < a[start].size();x++){
		int y=  a[start][x];
		dfs(y);
	}

}

int main() {
	a[1].push_back(2);
        a[1].push_back(3);
        
	a[2].push_back(1);
        a[3].push_back(1);

        a[2].push_back(3);
        a[3].push_back(2);

        a[2].push_back(4);
        a[4].push_back(2);

        a[2].push_back(5);
        a[5].push_back(2);

        a[4].push_back(5);
        a[5].push_back(4);

        a[3].push_back(6);
        a[6].push_back(3);

        a[3].push_back(7);
        a[7].push_back(3);

        a[6].push_back(7);
        a[7].push_back(6);

        dfs(1);

}
