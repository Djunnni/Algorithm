#include <string>
#include <vector>
#include <map>
#include <iostream>
using namespace std;

int solution(vector<vector<string>> clothes) {
    int answer = 0;
    int val=1;
    //---------------------------------------//
    map<string,int> match; // �ߺ��� ���� �Ÿ� �� �־!!
    for(auto& item : clothes){
        match[item[1]]++;
    }
    if(match.size()>30) return -1; // �ǻ� ���� 30�� �ʰ��̸� ����
    for(auto& pair : match){
        val *= (pair.second+1);
    }
    answer=val-1;
    return answer;
}
