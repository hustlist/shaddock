package com.list.shaddock.ruleengine;

public class Utils {

    public static String[] string2unicode(String words) {
        String[] s2u = new String[words.length()];
        char[] c = words.toCharArray();
        for (int i = 0; i < c.length; i++) {
            s2u[i] = "\\u" + Integer.toHexString(c[i]);
        }
        return s2u;
    }

    public static String unicodeToString(String unicode) {
        if (unicode == null) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 0; i < hex.length; i++) {
            int index = Integer.parseInt(hex[i],16);
            sb.append((char)index);
        }
        return sb.toString();
    }


    public static boolean equals(String str1,String str2){
        if(str1 == null){
            return str2==null;
        }
        return str1.equals(str2);
    }

    public static boolean isEmpty(String str){
        if(str == null){
            return true;
        }
        return str.isEmpty();
    }
}
