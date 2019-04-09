#include <string>
#include <vector>
#include <iostream>
using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 1;
    //----------------------------------------------//
    // 1���� priority 2���� locate 3���� locate�� Index
    vector<pair<int,int>> locate; 
    //----------- locate�� push �ϱ� ---------------//
    for(int i=0; i< priorities.size();i++){
        locate.push_back(make_pair(priorities[i],i));
    }
    //------------locate ���� Ȯ�� ------------------//
    // for(auto x : locate){
    //     cout << x.first << " " << x.second << endl;
    // }
    //-----------------���� ¥�� -------------------//
    while(locate.size()>1){
        // ù��° �켱���Ǹ� temp�� �ִ´�
        int temp = locate[0].first;
        for(int i=1;i<locate.size();i++){
            if(temp < locate[i].first) {
                locate.push_back(locate[0]);
                locate.erase(locate.begin());
                break;
            }
            if(i==locate.size()-1){
                if(locate[0].second==location) return answer;
                locate.erase(locate.begin());
                answer++;
            }
        }
    }
    //----------------- �� ã�� -------------------//
    return answer;
}
