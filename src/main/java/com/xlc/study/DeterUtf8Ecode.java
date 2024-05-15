package com.xlc.study;

import java.io.*;

public class DeterUtf8Ecode {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:/软件/安装包/kafka学习/text.txt");
        boolean  isUtf8 = isUTF8_zc(fileInputStream);
        System.out.println(isUtf8);
        FileInputStream fileInputStream1 = new FileInputStream("D:/软件/安装包/kafka学习/text.txt");
        boolean  isUtf8Zc = isUTF8_zc(fileInputStream1);
        System.out.println(isUtf8Zc);
        if (!isUtf8) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("D:/软件/安装包/kafka学习/text.txt"), "GB2312"));

            String index;
            while ((index = reader.readLine()) != null) {
                System.out.println(index);
            }
        }
    }
/**    UTF-8是一种多字节编码的字符集，表示一个Unicode字符时，它可以是1个至多个字节，在表示上有规律：
      1字节：0xxxxxxx （10000000到11000000无效）
      2字节：110xxxxx 10xxxxxx ()
                     11100000
                     11000000
                     10000000
      3字节：1110xxxx 10xxxxxx 10xxxxxx
      4字节：11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 */
    public static boolean isUtf8(FileInputStream fileInputStream){
        // fileInputStream是流不能复用的
        try {
            while (true) {
                int cur = fileInputStream.read();
                System.out.println(cur);
                if (cur == -1) {
                    return true;
                } else if (cur < 0x80) { // 10000000 小于0x80为ASCII
                    continue;
                } else if (cur < (0xC0)) { // 11000000 0X80与0xC0之间
                    return false;
                } else if (cur < (0xE0)) { // 11100000 次范围内2个字节
                    if ((fileInputStream.read() & (0xC0)) != (0X80)) {
                        return false;
                    }
                } else if (cur < (0xF0)) { // 11110000 此范围3个字节
                    if ((fileInputStream.read() & (0xC0)) != (0X80) ||
                            (fileInputStream.read() & (0xC0)) != (0X80)) {
                        return false;
                    }
                } else {
                    return false;
                }

            }
        } catch (IOException e){
            return false;
        }

    }

    /**
     * 判断文件内容是否为 UTF-8 编码
     * 翻译自C++版本 @see <a href="https://blog.csdn.net/jiangqin115/article/details/42684017">cpp原文</a>
     * @author Piao
     */
    public static boolean isUTF8_zc(FileInputStream fis) {
        //请注意FileInputStream是流，是不能复用的！
        try {
            while (true) {
                int curr = fis.read();
                System.out.println(curr);
                if (curr == -1) {
                    return true;
                }
                if (curr < 0x80) {// (10000000): 值小于0x80的为ASCII字符
                } else if (curr < (0xC0)) { // (11000000): 值介于0x80与0xC0之间的为无效UTF-8字符
                    return false;
                } else if (curr < (0xE0)) { // (11100000): 此范围内为2字节UTF-8字符
                    if ((fis.read() & (0xC0)) != 0x80) {
                        return false;
                    }
                } else if (curr < (0xF0)) { // (11110000): 此范围内为3字节UTF-8字符
                    if ((fis.read() & (0xC0)) != 0x80 || (fis.read() & (0xC0)) != 0x80) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

}


