import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HackerRankProblems {

	// -------------------------------------------------------------------------
	// https://www.hackerrank.com/challenges/sparse-arrays/problem
	// -------------------------------------------------------------------------
    public static void findStringCount() {
        Scanner s = new Scanner(System.in);
        int numStrings = s.nextInt();
        s.nextLine();
        List<String> strList = new ArrayList<>();
        List<String> queries = new ArrayList<>();
        Map<String, Integer> strCount = new HashMap<>();
        for (int i = 0; i < numStrings; i++) {
            String tmp = s.nextLine();
            strList.add(tmp);
            if (strCount.containsKey(tmp)) {
                strCount.put(tmp, strCount.get(tmp) + 1);
            } else {
                strCount.put(tmp, 1);
            }
        }
        int numQueries = s.nextInt();
        s.nextLine();
        for (int i = 0; i < numQueries; i++) {
            String tmp = s.nextLine();
            queries.add(tmp);
            if (strCount.containsKey(tmp)) {
                System.out.println(strCount.get(tmp));
            } else {
                System.out.println(0);
            }
        }
        // System.out.println(strList);
        // System.out.println(queries);
    }

	// -------------------------------------------------------------------------
	// https://www.hackerrank.com/challenges/arrays-ds/problem
	// -------------------------------------------------------------------------
    public static void printInReverseOrder() {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        // Corner cases
        if (count < 1) {
            return;
        }
        int[] nums = new int[count];
        scanner.nextLine(); // To consume the new line character
        for (int i = 0; i < count; i++) {
            if (scanner.hasNextInt()) {
                nums[i] = scanner.nextInt();
            } else {
                System.out.println("Did not receive a number");
                break;
            }
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

	// -------------------------------------------------------------------------
	// https://www.hackerrank.com/challenges/2d-array/problem
	// -------------------------------------------------------------------------
    public static void maxSandClock() {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int ROW = 6;
        int COL = 6;
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int[][] data = new int[ROW][COL]; 
        Scanner in = new Scanner(System.in);
        for(int row = 0; row < ROW; row++) { 
            for(int col = 0; col < COL; col++) { 
                data[row][col] = in.nextInt(); 
            }
        } 

        for(int row = 0; row < ROW; row++) {
            for(int col = 0 ;col < COL; col++) {
                if (row > 1 && col > 1) {
                    curSum = data[row - 2][col - 2] 
                        + data[row - 2][col - 1]
                        + data[row - 2][col]
                        + data[row - 1][col - 1]
                        + data[row][col - 2] 
                        + data[row][col - 1]
                        + data[row][col];
                    maxSum = Math.max(maxSum, curSum);
                }
            } 
        }
        System.out.println(maxSum);
    }

	// -------------------------------------------------------------------------
	// Read text from STDIN. This text is the source code of C/C++/Java.
    // Extract and print all the comments present in the code.
	// -------------------------------------------------------------------------
    public static void printOnlyComments() {
        List<String> lines = readMultipleLinesFromConsole();
        List<String> comments = new ArrayList<>();

        boolean multiLineComment = false;
        for (String line : lines) {
            if (multiLineComment) {
                if (line.contains("*/")) {
                    comments.add(line.substring(0, line.indexOf("*/")));
                    multiLineComment = false;
                } else {
                    comments.add(line);
                }
            }
            else if (line.contains("//")) {
                comments.add(line.substring(line.indexOf("//") + 2));
            } else if (line.contains("/*")) {
                multiLineComment = true;
                int startIndex = line.indexOf("/*");
                comments.add(line.substring(line.indexOf("/*") + 2));
            } else {
                continue;
            }
        }

        System.out.println(comments);
    }

    public static List<String> readMultipleLinesFromConsole() {
        List<String> lines = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line.isEmpty()) {
                break;
            }
            lines.add(line);
        }
        return lines;
    }

	// -------------------------------------------------------------------------
	// Read text from STDIN. This text is the source code of Html code fragment
    // Extract and print all the domain names present in the code.
	// -------------------------------------------------------------------------
    public static boolean isDomainNameChar(char c) {
        return (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z') ||
               (c >= '0' && c <= '9') ||
               (c == '.') ||
               (c == '-');
    }

    public static void getDomainNames() {
        String HTTP = "http://";
        List<String> lines = readNlinesFromConsole();
        List<String> domainNames = new ArrayList<>();
        /*
        //String s = "abcdefà";
        String s = "abc,d.f-";
        char a = 'a';
        Pattern p = Pattern.compile("[^a-zA-Z0-9.-]");
        boolean hasSpecialChar = p.matcher(s).find();
        System.out.println(hasSpecialChar);
        */
        for (String line : lines) {
            if (line.contains(HTTP)) {
                int startIndex = line.indexOf(HTTP);
                // There can be more than one occurance of "HTTP" in the same line
                while (startIndex  != -1 && startIndex < line.length()) {
                    // VERY IMP: This should be done here else startIndex will never be -1
                    startIndex += HTTP.length();
                    StringBuilder sb = new StringBuilder();
                    for (int i = startIndex; i < line.length(); i++) {
                        if (isDomainNameChar(line.charAt(i))) {
                            sb.append(line.charAt(i));
                        } else {
                            domainNames.add(sb.toString());
                            sb.setLength(0);
                            break;
                        }
                    }
                    // If we have a non-empty string, add it to domain names
                    if (sb.length() != 0) {
                        domainNames.add(sb.toString());
                    }
                    startIndex = line.indexOf(HTTP, startIndex + HTTP.length());
                }
            }
        }
        System.out.println(domainNames);
    }

    public static List<String> readNlinesFromConsole() {
        Scanner s = new Scanner(System.in);
        int numLines = s.nextInt();
        s.nextLine();
        String line = "";
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < numLines; i++) {
            lines.add(s.nextLine());
        }
        return lines;
    }

	// -------------------------------------------------------------------------
    // Main Function
	// -------------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("Hacker Rank problems");
        // This prints only the comments for a given set of source code
        {
            //printOnlyComments();
        }

        // Get set of lines and get domain names from it
        {
            getDomainNames();
        }
    }
}

