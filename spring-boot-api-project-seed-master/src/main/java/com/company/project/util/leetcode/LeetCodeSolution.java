package com.company.project.util.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode-cn.com/problemset/all/
 * @Description: 力扣-算法练习
 * @author luxiang 
 * @date 2020年3月31日 下午3:17:04
 */
public class LeetCodeSolution {
	
	/**
	 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。</br>
		示例 1:
		输入: "abcabcbb"
		输出: 3 
		解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。</br>
		示例 2:
		输入: "bbbbb"
		输出: 1
		解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。</br>
		示例 3:
		输入: "pwwkew"
		输出: 3
		解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
		     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。</br>
		来源：力扣（LeetCode）
		链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
	   @param s
	 * @return
	 */
	 public int lengthOfLongestSubstring(String s) {
         if(s == null){
        	 return 0;
         }
         if(s.length() == 1){
        	 return 1;
         }
         String str = "";
         char[] arr = s.toCharArray();
         List<Integer> list = new ArrayList<Integer>();
         for(int i = 0; i < arr.length; i ++){
        	 char a = arr[i];
        	 str = a + "";
        	 for(int j = 1; j < arr.length; j ++){
        		 char b = arr[j];
        		 if(a == b){
        			 System.out.println(str + "=" + str.length());
        			 list.add(str.length());
        			 break;
        		 }
        		 str += b;
        	 }
         }
         if(list != null && list.size() > 0){
        	 return Collections.max(list);
         }
		 return 0;
	 }
	 
	 public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
	}
	 
	 public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
            System.out.println("ans=" + ans);
            System.out.println("map=" + map);
        }
        return ans;
    }


	public static void main(String[] args) {
		LeetCodeSolution lcs = new LeetCodeSolution();
		String testStr = "pwwkew";//abcabcbb  bbbbb  
		int length = lcs.lengthOfLongestSubstring3(testStr);
		System.out.println("length=" + length);

	}

}
