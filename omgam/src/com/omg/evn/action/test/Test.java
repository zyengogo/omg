package com.omg.evn.action.test;

import java.util.*;

public class Test {

	/** 
	 * @Description: TODO
	 * @author zyen
	 * @date 2015-5-2 上午11:02:26 
	 * @param @param args     
	 * @return void    
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//generateWord();
		//System.out.println(generateWord());
		
		Math.random();
		String _pwd="p"+Math.random()*900000+100000;
		System.out.println(_pwd.subSequence(0, _pwd.indexOf(".")));

	}
	
	private static String generateWord() {  
        String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",  
                "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",  
                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
                "W", "X", "Y", "Z" };  
        List list = Arrays.asList(beforeShuffle);  
        Collections.shuffle(list);  
        StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < list.size(); i++) {  
            sb.append(list.get(i));  
        }  
        String afterShuffle = sb.toString();  
        String result = afterShuffle.substring(0, 8);  
        return result;  
    }  
}
