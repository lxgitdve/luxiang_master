package com.company.project.util;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.nowcoder.com/ta/cracking-the-coding-interview
 * @author luxiang 
 * @date 2020年3月16日 上午11:14:15
 * 程序员面试金典
      介绍
     本套编程题为CC150（《Cracking the Coding Interview程序员面试金典》）一书配套
     在线练习，共84题，未包含原书150题中8.1-8.10、10.1-10.7、12.1-12.6、13.1-13.10、14.1-14.6、15.1-15.7、16.1-16.5
     等题目（因用在线编程方式不易判定）。
     官方参考代码链接：https://github.com/gaylemcd/ctci。
 */
public class NiuKeExample {
	
	public static int strlength(String str){
	    String arr[] = str.split(" ");
	    String lastWord = "";
	    if(arr != null && arr.length > 0){
	        lastWord = arr[arr.length - 1];
	    }
	    if(lastWord != null){
	    	 return lastWord.length();
	    }else{
	    	 return 0;
	    }
	}
	
	/**
	 * 判断字符串是否有相同字符，如果存在相同字符则返回true，否则返回false
	 * @param iniString
	 * @return
	 * 请实现一个算法，确定一个字符串的所有字符是否全都不同。这里我们要求不允许使用额外的存储结构。
	        给定一个string iniString，请返回一个bool值,True代表所有字符全都不同，False代表存在相同的字符。保证字符串中的字符为ASCII字符。字符串的长度小于等于3000。
		测试样例：
		"aeiou"
		返回：True
		"BarackObama"
		返回：False
	 */
	public static boolean checkDifferent(String iniString){
		char[] cs = iniString.toCharArray();
		if(cs.length > 0){
			for(char c : cs){
				int val = 0;
				for(char c2 : cs){
					if(c == c2){
						val ++;
					}
					if(val > 1){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 请实现一个算法，在不使用额外数据结构和储存空间的情况下，翻转一个给定的字符串(可以使用单个过程变量)。
		给定一个string iniString，请返回一个string，为翻转后的字符串。保证字符串的长度小于等于5000。
		测试样例：
		"This is nowcoder"
		返回："redocwon si sihT"
	 * @param iniString
	 * @return
	 */
	public static String reverseString(String iniString) {
	     char arr[] = iniString.toCharArray();
	     String newStr = "";
	     for(int i = arr.length - 1; i >= 0; i--){
	    	 newStr += arr[i];
	     }
		 return newStr;
    }
	
	/**
	 * 给定两个字符串，请编写程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。这里规定大小写为不同字符，且考虑字符串中的空格。
		给定一个string stringA和一个string stringB，请返回一个bool，代表两串是否重新排列后可相同。保证两串的长度都小于等于5000。
		测试样例：
		"This is nowcoder","is This nowcoder"
		返回：true
		"Here you are","Are you here"
		返回：false
	 * @param stringA
	 * @param stringB
	 * @return
	 */
	public static boolean checkSam(String stringA, String stringB) {
        System.out.println(stringA);
        System.out.println(stringB);
        String arr[] = stringA.split(" ");
        String arr2[] = stringB.split(" ");
        if(stringA == null || stringB == null || stringA.length() != stringB.length()){
        	return false;
        }
        if(arr.length == arr2.length){
        	List<String> list = new ArrayList<String>();
        	for(String s : arr){
        		list.add(s);
        	}
        	for(int i = 0; i < arr2.length; i ++){
        		String str = arr2[i];
        		if(!list.contains(str)){
        			return false; 
        		}
        	}
        	return true;
        }else{
        	return false; 
        }
    }
	
	public static boolean checkSam2(String a, String b) {
        // write code here
        if (a == null || b == null || a.length() != b.length())
            return false;
          
        int[] charSet = new int[256];
        for (int i = 0; i < a.length(); i++) {
            charSet[a.charAt(i)]++;
        }
        System.out.println(charSet.toString());
        for (int i = 0; i < b.length(); i++) {
            if (charSet[b.charAt(i)] <= 0)
                return false;
            charSet[b.charAt(i)]--;
        }
          
        return true;
    }

	public static void main(String[] args) {
//		Scanner scan = new Scanner(System.in);
//		while(scan.hasNextLine()){
//			String str = scan.nextLine();
//			int wordLength = strlength(str);
//			System.out.println(wordLength);
//			boolean flag = checkDifferent(str);
//			System.out.println(flag);
//		}
		
//		String newStr = reverseString("This is nowcoder");
//		System.out.println(newStr);
		
		boolean bool = checkSam("This is nowcoder", "is This nowcoder");
//		boolean bool = checkSam("Here you are", "Are you here");
		System.out.println(bool);
		
	}

}
