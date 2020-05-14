package com.company.project.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: java basic test
 * @author luxiang 
 * @date 2020年3月9日 上午10:43:54
 */
public class TestClass {
	
	public static void regExpTest(){
		String str = "成都市(成华区)(武侯区)(高新区)";
        Pattern p = Pattern.compile(".*?(?=\\()");
        Matcher m = p.matcher(str);
        if(m.find()) {
            System.out.println(m.group());
        }
	}
	
	public static void bigDecimalTest(double a, double b){
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);
		System.out.println("计算：");
		//加
        System.out.println(a + " + " + b + " = " + b1.add(b2).doubleValue());
        //减
        System.out.println(a + " - " + b + " = " + b1.subtract(b2).doubleValue());
        //乘
        System.out.println(a + " × " + b + " = " + b1.multiply(b2).doubleValue());
        //除 4.0 ÷ 3.6
        //b1.divide(b2).doubleValue()抛出异常：Exception in thread "main" java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        //JAVA中如果用BigDecimal做除法的时候一定要在divide方法中传递第二个参数，定义精确到小数点后几位，否则在不整除的情况下，结果是无限循环小数时，就会抛出以上异常。
        //修改为 b1.divide(b2,2);
        //divide方法有两个重载的方法，一个是传两个参数的，一个是传三个参数的：
        //两个参数的方法：
        //@param divisor value by which this {@code BigDecimal} is to be divided. 传入除数
        //@param roundingMode rounding mode to apply. 传入round的模式
        //三个参数的方法：
        //@param divisor value by which this {@code BigDecimal} is to be divided. 传入除数
        //@param scale scale of the {@code BigDecimal} quotient to be returned. 传入精度
        //@param roundingMode rounding mode to apply. 传入round的模式
        System.out.println(a + " ÷ " + b + " = " + b1.divide(b2,4,BigDecimal.ROUND_HALF_UP).doubleValue());
	}
	
	/**
	 * Java 为每个原始类型提供了包装类型：
		- 原始类型: boolean，char，byte，short，int，long，float，double
		- 包装类型：Boolean，Character，Byte，Short，Integer，Long，Float，Double
	 */
	public static void autoUnboxingTest(){
		Integer a = new Integer(3);
        Integer b = 3;                  // 将3自动装箱成Integer类型
        int c = 3;
        System.out.println("a.hashCode:" + a.hashCode());
        System.out.println("b.hashCode:" + b.hashCode());
        System.out.println("a.equals(b):" + a.equals(b));
        System.out.println("a == b:" + (a == b));     // false 两个引用没有引用同一对象
        System.out.println("a == c:" + (a == c));     // true a自动拆箱成int类型再和c比较
        System.out.println("b == c:" + (b == c));     // true b自动拆箱成int类型再和c比较
	}
	
	public static String translate (String str) {
		 String tempStr = "";
		 try {
			 tempStr = new String(str.getBytes("ISO-8859-1"), "GBK");
			 tempStr = tempStr.trim();
		 }
		 catch (Exception e) {
		     System.err.println(e.getMessage());
		 }
		 return tempStr;
	 }
	
	/**
	 * 请你解释为什么会出现4.0-3.6=0.40000001 或者 0.3999999999999999 这种现象？
		考察点：计算机基础
		参考回答：
		原因简单来说是这样：2进制的小数无法精确的表达10进制小数，计算机在计算10进制小数的过程中要先转换为2进制进行计算，这个过程中出现了误差。
	 */
	public static void doubleTest(){
		double a = 4.0;
		double b = 3.6;
		double c = a - b;
		//所以关带小数计算 推荐用 BigDecimal 
		System.out.println(c);
		bigDecimalTest(a, b);
	}
	
	public static void java8Test(){
		// 参数不能是null  
		Optional<Object> optional1 = Optional.of("1");
		// 参数可以是null  
		Optional<Object> optional2 = Optional.ofNullable(null);
		// 参数可以是非null  
		Optional<Object> optional3 = Optional.ofNullable(2);
		String a = "a";
		String b = new String("a");
		Object o = new Object();
		System.out.println(a == b);
		System.out.println(a.equals(b));
		System.out.println(optional1.toString());
		System.out.println(optional2.toString());
		System.out.println(optional3.toString());
	}
	
	/**
	 * java为数据结构中的映射定义了一个接口java.util.Map;它有四个实现类,分别是HashMap Hashtable LinkedHashMap 和TreeMap.
	 */
	public static void mapTest(){
		Collections.synchronizedMap(null);
	}
	
	/**
	 * 
	 */
	public static void methodInvokeTest(){
		String str = "hello";
	    Method m;
		try {
			m = str.getClass().getMethod("toUpperCase");
			System.out.println(m.invoke(str));  // HELLO
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//bigDecimalTest();
		//regExpTest();
		autoUnboxingTest();
		//System.out.println(translate("苹果&iPhone"));
		//doubleTest();
		//java8Test();
		//methodInvokeTest();
	}

}
