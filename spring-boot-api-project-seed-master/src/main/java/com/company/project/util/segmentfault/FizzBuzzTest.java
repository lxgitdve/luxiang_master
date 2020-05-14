package com.company.project.util.segmentfault;

import java.util.Scanner;

/**
 * FizzBuzz游戏
 * @author luxiang 
 * @date 2020年3月19日 下午4:00:38
 */
public class FizzBuzzTest {
	
	public static String fizzAndBuzz(Integer num) throws Exception{
		if(num <= 0){
			throw new IllegalArgumentException("必须为大于零的正整数!");//抛出不合理参数异常
		}
		String res = "";
		if((num % 3 == 0) && (num % 5 == 0)){//3与5的倍数
			res = "fizzguzz";
		}else if((num % 3) == 0){//3的倍数
			res = "fizz";
		}else if((num % 5) == 0){//5的倍数
			res = "guzz";
		}else{
			res = String.valueOf(num);
		}
		return res;
	}

	public static void main(String[] args) {
    	try {
    		Scanner scanner = new Scanner(System.in);
    		while(scanner.hasNextLine()){
    			String str = scanner.nextLine();
			    String res = fizzAndBuzz(Integer.valueOf(str));
			    System.out.println(res);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
