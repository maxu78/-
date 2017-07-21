package com.zhongying.slserv.busi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitWord {
	
	/**
	 * 
	 * @param str  待分割的字符串
	 * @param regex  正则表达式
	 * @param type  类型     
	 * 			e.g. str=&&&&a&&b&&c&&&&d&&&&  regex=&& 
	 * 				   如果type=0 结果为:[, , a, b, c, , d, ]
	 * 				   如果type=1 结果为:[, , a, b, c, , d, , ]
	 * 				   如果type=2 结果为:[, , a, b, c, , d] 普通的split 
	 * 				   如果type=3 结果为:[a, b, c, , d]
	 * 				   如果type=4 结果为:[a, b, c, d]
	 * @return  分割后的字符串集合
	 */
	public static List<String> splitWord(String str, String regex, int type){
		
		List<String> result = new ArrayList<String>();
		//重写split
		if(type == 0 || type == 1){
			int index = 0;
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(str);
			while(m.find()){
				String match = str.substring(index, m.start());
				result.add(match);
	            index = m.end();
			}
			if(type == 1){
				if(str.length() == index){
					result.add("");
				}
			}
		} else if(type == 2 || type == 3 || type == 4){
			//在split的基础上修改
			String[] arr = str.split(regex);
			if(arr != null){
				if(type == 2){
					result = Arrays.asList(arr);
				} else if(type == 3){
					boolean flag = false;
					for(int i=0; i<arr.length; i++){
						if(arr[i] != null && !"".equals(arr[i])){
							result.add(arr[i]);
							flag = true;
						}
						if(arr[i] != null && "".equals(arr[i])){
							if(flag){
								result.add(arr[i]);
							}
						} else if(arr[i] == null){
							if(flag){
								result.add(arr[i]);
							}
						}
					}
				} else if(type == 4){
					for(String s : arr){
						if(s != null && !"".equals(s)){
							result.add(s);
						}
					}
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args){
		String s = "123456";
		System.out.println(SplitWord.splitWord(s, "\\d", 2));
//		String[] arr = s.split("&&");
//		List<String> l = new ArrayList<String>();
//		l = Arrays.asList(arr);
//		System.out.println(l);
//		Pattern p = Pattern.compile("&&");
//		Matcher m = p.matcher(s);
//		for(String b:p.split("a&&b&&c&&&&d&&")){
//			System.out.println(b);
//		}
		
//		while(m.find()){
//			System.out.println(m.start()+"---");
//			System.out.println(m.end());
//		}
//		System.out.println(s.substring(3, 4));
	}
}
