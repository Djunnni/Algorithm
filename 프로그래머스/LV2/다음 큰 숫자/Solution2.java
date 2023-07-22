public int solution(int n) {
    int a = Integer.bitCount(n);
    int compare = n + 1;
    while(true) {
        if(Integer.bitCount(compare) == a) break;
        compare++;
    }
    
    return compare;
}
// 테스트 1 〉	통과 (0.02ms, 75.2MB)
// 테스트 2 〉	통과 (0.02ms, 90.1MB)
// 테스트 3 〉	통과 (0.02ms, 76.1MB)
// 테스트 4 〉	통과 (0.02ms, 74.6MB)
// 테스트 5 〉	통과 (0.01ms, 70.9MB)
// 테스트 6 〉	통과 (0.02ms, 72.8MB)
// 테스트 7 〉	통과 (0.02ms, 75.1MB)
// 테스트 8 〉	통과 (0.03ms, 72.6MB)
// 테스트 9 〉	통과 (0.02ms, 70.6MB)
// 테스트 10 〉	통과 (0.04ms, 78.6MB)
// 테스트 11 〉	통과 (0.01ms, 75.1MB)
// 테스트 12 〉	통과 (0.02ms, 76.9MB)
// 테스트 13 〉	통과 (0.02ms, 68.6MB)
// 테스트 14 〉	통과 (0.02ms, 73.4MB)
// 효율성  테스트
// 테스트 1 〉	통과 (0.02ms, 52.1MB)
// 테스트 2 〉	통과 (0.02ms, 52.1MB)
// 테스트 3 〉	통과 (0.02ms, 52.7MB)
// 테스트 4 〉	통과 (0.02ms, 52.2MB)
// 테스트 5 〉	통과 (0.02ms, 52.4MB)
// 테스트 6 〉	통과 (0.04ms, 53MB)