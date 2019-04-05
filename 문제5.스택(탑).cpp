#include <string>
#include <vector>
#include <iostream>
using namespace std;

vector<int> solution(vector<int> heights) {
    vector<int> answer(heights.size(),0);
    //------------------------------------//
    for(int i=heights.size()-1;i>=0;i--){
        bool trans = false;
        for(int j=i;j>=0;j--){
            if(heights[i]<heights[j]) {
                answer[i]=j+1;
                trans =true;
            }
            if(trans==true) break;
        }
    }
    return answer;
}
