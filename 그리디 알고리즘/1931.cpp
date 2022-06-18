#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

bool compare(pair<int,int> a, pair<int,int> b) {
	if(a.second != b.second) { return a.second < b.second; }
	return a.first < b.first;
}

int main() {
	int N,S,E;
	int count = 0;	
	vector<pair<int,int> > T;
	pair<int,int> current;

	cin >> N;	
	for(int i=0; i<N;i++) {
		cin >> S >> E;
		T.push_back(make_pair(S,E));
	}
		
	sort(T.begin(),T.end(),compare);
	//for(pair<int,int> x: T) {
	//	cout << "PT "<< x.first << " " << x.second << endl;
	//}
	
	int _start = T[0].first;
	int _end = T[0].second;
	count++;
	for(int i=1;i<N;i++) {
		if(T[i].first >= _end) {
			count++;
			_end = T[i].second;
			_start = T[i].first;
		}
	}

	cout << count; 
	return 0;
}
