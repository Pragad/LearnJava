import java.util.List;
import java.util.Arrays;

public class Streams 
{
    private static final List<String> KOMPRISE_EXCLUDE_DIRS = Arrays.asList(".komprise_move_reserve", ".komprise_move_test_reserved");
    private static final List<String> KOMPRISE_EXCLUDE_FILES = Arrays.asList("_komprise_migrate_test", ".kompriseWriteTes");
    private static final List<String> KOMPRISE_EXCLUDE_FILETYPES = Arrays.asList(".bcup");

    public static boolean skipNfsRequest(String source)
	{
        String s = source.startsWith("/") ? source.substring(1) : source;
        //source = Util.removePrefixIfExists(source, "/");
        System.out.println(s);
        return KOMPRISE_EXCLUDE_FILETYPES.stream().anyMatch(entry -> s.endsWith(entry))
            || KOMPRISE_EXCLUDE_FILES.stream().anyMatch(entry -> s.startsWith(entry))
            || KOMPRISE_EXCLUDE_DIRS.stream().anyMatch(entry -> s.startsWith(entry));
    }

    public static boolean skipNfsRequest(String source, String target)
	{
        return skipNfsRequest(source) || skipNfsRequest(target);
    }

    public static void main(String[] args) 
    {
        System.out.println("Hello Streams");
        String src = "/.komprise_move_reserved/being_moved/-1060194705";
        String tgt = "/Data/1_new/2/pdfs/z.pdf";
		System.out.println(skipNfsRequest(src,tgt));
    }
}

