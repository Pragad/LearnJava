import java.io.*;
import java.time.*;

public class EpochTime {
    public static void main(String[] args) {
        long curtime = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        System.out.println(curtime);
    }
}
