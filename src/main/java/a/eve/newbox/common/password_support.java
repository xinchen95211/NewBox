package a.eve.newbox.common;

import java.util.Random;

/**
 * 静态密码支持库
 */
public class password_support {
    public static String sym = "@#$%^&*()_+-=";
    public static String num = "0123456789";
    public static String big_zimu = "ASDFGHJKLQWERTYUIOPZXCVBNM";
    public static String little_zimu = "asdfghjklqwertyuiopzxcvbnm";
    /**
     * 随机密码生成器
     * @param str 生成密码的字符内容集;
     * @param length 生成密码的长度;
     * @return 生成的密码;
     */
    public static String password_generation(String str,Integer length){
        char[] chars = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int x = random.nextInt(chars.length);
            stringBuilder.append(chars[x]);
        }
        return stringBuilder.toString();

    }
}
