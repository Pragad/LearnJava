import java.io.*;
import java.util.*;

public class MD5OfFile {
    public static String getMD5OfFile(String fileName) {
        FileInputStream fis = new FileInputStream(new File(fileName));
        String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);
        fis.close();
        return md5;
    }

    public static void main(String[] args) {
        System.out.println();
        String file = "/Users/pragad/ItsMine/Dev_Git/LearnJava/JavaPractice/a.txt";
        String md5Value = getMD5OfFile(file);
    }
}
