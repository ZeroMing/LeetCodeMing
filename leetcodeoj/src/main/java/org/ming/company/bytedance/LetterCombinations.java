package org.ming.company.bytedance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 马士兵 · 项目架构部
 * @version V1.0
 * @contact zeroming@163.com
 * @company 马士兵（北京）教育科技有限公司 (http://www.mashibing.com/)
 * @copyright 马士兵（北京）教育科技有限公司 · 项目架构部
 */
public class LetterCombinations {

    public static void main(String[] args) {
        List<String> list = letterCombinations("23");
        System.out.println(list);
    }

    /**
     * 循环的个数不固定，因此这种情况得用递归，使用DFS+hash table完成
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        List<String>list=new ArrayList<>();
        String [] strings =new String[digits.length()];
        if(strings.length==0){
            return list;
        }
        for(int i=0;i<digits.length();i++){
            switch (digits.charAt(i)){
                case '2':strings[i]="abc";break;
                case '3':strings[i]="def";break;
                case '4':strings[i]="ghi";break;
                case '5':strings[i]="jkl";break;
                case '6':strings[i]="mno";break;
                case '7':strings[i]="pqrs";break;
                case '8':strings[i]="tuv";break;
                case '9':strings[i]="wxyz";break;
                default: break;
            }
        }
        getStringWithFor(strings,0,list,"");
        return list;
    }

    private static List<String> getStringWithFor(String [] strings,int i,List<String> list,String stemp) {

        if(i<strings.length-1){
            for(int j=0;j<strings[i].length();j++){
                list=getStringWithFor(strings,i+1,list,stemp+strings[i].charAt(j));
            }
        } else {
            for(int j=0;j<strings[i].length();j++){
                list.add(stemp+strings[i].charAt(j));
            }
        }

        return list;
    }

}
