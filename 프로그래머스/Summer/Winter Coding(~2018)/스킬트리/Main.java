import java.util.*;

/**
 * @author djunnni
 * 스킬트리는 1번부터 순차적으로 나와야 한다.
 */
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int[] skillStudies = getSkillStudies(skill);
        int answer = 0;
        for(String skillTree: skill_trees) {
            if(can(skillStudies, skillTree)) answer++;
        }
        return answer;
    }
    // 스킬트리가 사용가능한지 boolean을 반환한다.
    public boolean can(int[] skillStudies, String skillTree) {
        char[] trees = skillTree.toCharArray();
        int treeSize = trees.length;
        
        int[] values = new int[treeSize];
        for(int i = 0; i < treeSize; i++) {
            values[i] = skillStudies[getIdx(trees[i])];
        }
        
        int max = 0;
        
        for(int i = 0; i < treeSize; i++) {
            if(values[i] == -1) continue;
            else if(max < values[i]) {
                if(values[i] - max > 1) return false;
                max = values[i];
            } else {
                return false;
            }
        }
        return true;
        
    }
    // 스킬 북의 idx는 A부터 시작하며 그 순위를 int[]로 가지고 있다.
    public int[] getSkillStudies(String skill) {
        int[] skillStudies = new int[32]; // 알파벳은 32개라서
        Arrays.fill(skillStudies, -1); // -1로 초기화
        
        char[] temp = skill.toCharArray();
        for(int i = 1; i <= temp.length; i++) {
            int idx = getIdx(temp[i - 1]);
            skillStudies[idx] = i;
        }
        return skillStudies;
    }
    // 모든 문자열은 대문자이다. A는 0, Z는 31
    public int getIdx(char ch) {
        return ch - 'A';
    }
}