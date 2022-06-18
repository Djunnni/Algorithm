#include <string>
#include <vector>
#include <map>
#include <iostream>
using namespace std;

int solution(vector<vector<string>> clothes) {
    int answer = 0;
    int val=1;
    //---------------------------------------//
    map<string,int> match; // 중복을 쉽게 거를 수 있어서!!
    for(auto& item : clothes){
        match[item[1]]++;
    }
    if(match.size()>30) return -1; // 의상 수가 30개 초과이면 종료
    for(auto& pair : match){
        val *= (pair.second+1);
    }
    answer=val-1;
    return answer;
}
