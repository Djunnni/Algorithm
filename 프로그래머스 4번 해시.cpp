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
    //----------- ���� ���� -------------------//
    Record record[genres.size()];
    map<string,int> rank;
    int i=0;
    vector<int> answer;
    //------------record Ŭ���� �迭�� �� �ֱ�------------------//
    for(string name : genres){
        record[i].genre = name;
        record[i].play = plays[i];
        record[i].desc = i;
        i++;
    }
    // ------------ ��ŷ���� ���� ������ ----------------------//
    i=0;
    for(string name : genres){
        rank[name]+=plays[i++];
    }
   // -------------------��ŷ �� ���� -------------------------//
    vector<std::pair<string, int>> elems(rank.begin(), rank.end());
    sort(elems.begin(), elems.end(), comp);
    
    //-----------------------��  ��   ------------------------//
    
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
    // //-----------���ڵ� �ȿ� ���� Ȯ���ϴ¹� -------------// 
    //  cout << "record Ȯ�� "<< endl;
    // for(auto & a : record){
    //     cout << a.genre << " " << a.play << " " << a.desc << endl;
    // }
    // ------------- ��ŷ�ȿ� Ȯ���ϱ� -------------------//
        // cout << "ranking Ȯ�� "<< endl;
        // for(auto & pair : rank){
        //     cout << pair.first << " " << pair.second << endl;
        // }
    
    return answer;
}
