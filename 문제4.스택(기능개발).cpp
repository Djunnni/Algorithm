#include <string>
#include <vector>
#include <iostream>
using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    //-----------------------------------------//
    // Temp 를 False 로 두고 100 넘을시 True로 변경하기
    vector<pair<bool,int>> Temp(progresses.size(),make_pair(false,0));
    //-----------------------------------------//
    bool pass =false;
    // Temp 에 전부 들어간 값들을 전달한다.
    while(Temp.size()>0){
        if(pass==true){
            int Largest=0;
            for(int i=0;i<Temp.size();i++){
                if(Temp[0].second>=Temp[i].second) {Largest=i+1;}
                else break;
            }
            answer.push_back(Largest);
            Temp.erase(Temp.begin(),Temp.begin()+Largest);
            continue;
        }
        int count=0;
        for(int i=0;i<progresses.size();i++){
            if(progresses[i]<100){
                progresses[i]+=speeds[i];
                Temp[i].second++;
            }
            else {
                count++;
                Temp[i].first=true;
            }
        }

        if(count==progresses.size()) {
            pass=true;
        }
    }
    //---------------------------------------------//
    // Temp 데이터 확인용
    // for(auto & a : Temp){
    //     cout << a.first << " " << a.second << endl;
    // }
    //--------------------------------------------//
    return answer;
}
