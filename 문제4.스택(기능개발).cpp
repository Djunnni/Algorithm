#include <string>
#include <vector>
#include <iostream>
using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    //-----------------------------------------//
    // Temp �� False �� �ΰ� 100 ������ True�� �����ϱ�
    vector<pair<bool,int>> Temp(progresses.size(),make_pair(false,0));
    //-----------------------------------------//
    bool pass =false;
    // Temp �� ���� �� ������ �����Ѵ�.
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
    // Temp ������ Ȯ�ο�
    // for(auto & a : Temp){
    //     cout << a.first << " " << a.second << endl;
    // }
    //--------------------------------------------//
    return answer;
}
