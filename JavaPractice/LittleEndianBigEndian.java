import java.util.*;
import java.lang.*;
import java.io.*;
import java.nio.ByteBuffer;

/* Name of the class has to be "Main" only if the class is public. */
class LittleEndianBigEndian
{
    public static void main (String[] args) throws java.lang.Exception
    {
        int x = 1111;
        System.out.println(Integer.toHexString(x));
        System.out.println(Integer.toHexString(little2big(x)));
        System.out.println(Integer.toHexString(Integer.reverseBytes(x)));
    }
    
    public static int little2big(int i) {
        return (i&0xff)<<24 | (i&0xff00)<<8 | (i&0xff0000)>>8 | (i>>24)&0xff;
    }

    public static int big2little(int num) {
		return ((num>>24)&0xff) | // move byte 3 to byte 0
                ((num<<8)&0xff0000) | // move byte 1 to byte 2
                ((num>>8)&0xff00) | // move byte 2 to byte 1
                ((num<<24)&0xff000000); // byte 0 to byte 3
    }
}
