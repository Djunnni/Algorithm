#include <string>
#include <vector>
#include <iostream>
using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 1;
    //----------------------------------------------//
    // 1번엔 priority 2번엔 locate 3번은 locate의 Index
    vector<pair<int,int>> locate; 
    //----------- locate에 push 하기 ---------------//
    for(int i=0; i< priorities.size();i++){
        locate.push_back(make_pair(priorities[i],i));
    }
    //------------locate 내부 확인 ------------------//
    // for(auto x : locate){
    //     cout << x.first << " " << x.second << endl;
    // }
    //-----------------로직 짜기 -------------------//
    while(locate.size()>1){
        // 첫번째 우선순의를 temp에 넣는다
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
    //----------------- 답 찾기 -------------------//
    return answer;
}
