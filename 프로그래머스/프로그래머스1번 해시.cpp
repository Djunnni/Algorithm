#include <string>
#include <vector>
#include <map>
#include <iostream>
using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
// -----------------------------------
    map<string,int> runner;
    for(string name: participant){
        ++runner[name];
    }
    for(string name: completion){
        --runner[name];
    }
    for(auto &pair : runner){
        if(pair.second>=1)
        answer=pair.first;
    }
    
    return answer;
}
