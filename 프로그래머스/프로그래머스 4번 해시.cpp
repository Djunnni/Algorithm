#include <string>
#include <vector>
#include <iostream>
#include <map>
#include <algorithm>
using namespace std;

bool comp(std::pair<string,int> a, pair<string,int> b) {
    return a.second > b.second;
}
class Record {
public: 
    int play=0, desc;
    string genre="";
    ~Record() {}
    Record(){
        play=0;
        genre="";
        desc=-1;
    }
    void clear() {
        play=0;
        genre="";
        desc=-1;
    }
};
void compare(Record &A,Record &B){
    if(A.play>B.play){
        swap(A,B);
    }
    if(A.play==B.play && A.play!=0){
        if(A.desc < B.desc && A.desc!=-1){
            swap(A,B);
        }
    }
}
void swap(Record &A,Record &B){
    Record temp;
    temp.play= A.play;
    temp.desc = A.desc;
    temp.genre = A.genre;
    A.play = B.play;
    A.desc = B.desc;
    A.genre = B.genre;
    B.play = temp.play;
    B.desc = temp.desc;
    B.genre = temp.genre;
}
vector<int> solution(vector<string> genres, vector<int> plays) {
    //----------- 변수 선언 -------------------//
    Record record[genres.size()];
    map<string,int> rank;
    int i=0;
    vector<int> answer;
    //------------record 클래스 배열에 다 넣기------------------//
    for(string name : genres){
        record[i].genre = name;
        record[i].play = plays[i];
        record[i].desc = i;
        i++;
    }
    // ------------ 랭킹으로 순위 나누기 ----------------------//
    i=0;
    for(string name : genres){
        rank[name]+=plays[i++];
    }
   // -------------------랭킹 값 정렬 -------------------------//
    vector<std::pair<string, int>> elems(rank.begin(), rank.end());
    sort(elems.begin(), elems.end(), comp);
    
    //-----------------------연  산   ------------------------//
    
    for(auto it=elems.begin();it!=elems.end();it++){
        Record temp[2];
        for(int i=0;i<genres.size();i++){
            if(it->first==record[i].genre) {
                    if(temp[0].genre==""){
                        temp[0].play=record[i].play;
                        temp[0].desc=record[i].desc;
                        temp[0].genre = record[i].genre;   
                    }
                    else{
                        compare(record[i],temp[0]);
                    }
                compare(temp[0],temp[1]);
                }
        }
        if(temp[0].genre!="" &&temp[1].genre!=""){
        answer.push_back(temp[1].desc);
        answer.push_back(temp[0].desc);
        }
        if(temp[0].genre=="" &&temp[1].genre!=""){
            answer.push_back(temp[1].desc);
        }
        temp[0].clear();
        temp[1].clear();
    }
    // //-----------레코드 안에 내용 확인하는법 -------------// 
    //  cout << "record 확인 "<< endl;
    // for(auto & a : record){
    //     cout << a.genre << " " << a.play << " " << a.desc << endl;
    // }
    // ------------- 랭킹안에 확인하기 -------------------//
        // cout << "ranking 확인 "<< endl;
        // for(auto & pair : rank){
        //     cout << pair.first << " " << pair.second << endl;
        // }
    
    return answer;
}
