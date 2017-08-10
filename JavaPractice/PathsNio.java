import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsNio {

    public static void main(String[] args) {

        Path fullTgtPath = Paths.get("/kt/1056/1063/Data/1_new/2/FPolicy SDK Cluster Mode/FPolicy API xml v1.2 schema.pdf");
        Path baseTgtPath = Paths.get("/kt/1056/1063");

        Path path01_to_path02 = baseTgtPath.relativize(fullTgtPath);
        System.out.println(path01_to_path02);
    }

}
