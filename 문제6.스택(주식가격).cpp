#include <string>
#include <vector>
#include <iostream>
using namespace std;

vector<int> solution(vector<int> prices) {
    vector<int> answer;

    //--------원래 풀이 ----------------//
    for(int i=0;i<prices.size();i++){
        int num=0;
        for(int j=i+1;j<prices.size();j++){
            num++;
            if(prices[i]>prices[j]) break;
        }
        answer.push_back(num);
    }
    return answer;
}
