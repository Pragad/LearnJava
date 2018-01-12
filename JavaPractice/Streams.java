import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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

    public static List<String> getPersonNames(List<Person> persons) {
        return persons.stream().map(p -> p.name).collect(Collectors.toList());
    }

    public static void main(String[] args) 
    {
        {
            System.out.println("Hello Streams");
            String src = "/.komprise_move_reserved/being_moved/-1060194705";
            String tgt = "/Data/1_new/2/pdfs/z.pdf";
            System.out.println(skipNfsRequest(src,tgt));
        }

        {
            List<Person> persons = new ArrayList<>();
            /*
            persons.add(new Person("abc", 5));
            persons.add(new Person("def", 6));
            persons.add(new Person("qwerty", 7));
            persons.add(new Person("zx", 8));
            */
            List<String> personNames = getPersonNames(persons);
            System.out.println(getPersonNames(persons));
        }
    }
}

class Person {
    String name;
    int age;

    Person (String name, int age) {
        this.name = name;
        this.age= age;
    }
}
