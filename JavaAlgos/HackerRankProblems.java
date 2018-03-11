/* 
 * Problem 1. Count occurence of a string in list of strings
 * 
 * Problem 2. Print array in reverse order
 *
 * Problem 3. Count sand clocks or hour glasses in 2D array
 *
 * Problem 4. Extract and print all the comments present in the code
 *
 * Problem 5. Extract and print all the domain names present in the code
 *
 * Problem 6. Find max you can get from the array after Q operations
 *
 * Problem 7. Reverse an array in subset of N
 */

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class HackerRankProblems {

	// -------------------------------------------------------------------------
	// https://www.hackerrank.com/challenges/sparse-arrays/problem
    // Problem 1. Count occurence of a string in list of strings
    //
    // There is a collection of N strings ( There can be multiple occurences of a particular string ).
    // Each string's length is no more than 20 characters. There are also Q queries.
    // For each query, you are given a string, and you need to find out how many times this string occurs in the given collection of N strings.
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
    // Problem 2. Print array in reverse order
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
    // Problem 3. Count sand clocks or hour glasses in 2D array
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
	// Read text from STDIN. This text is the source code of C/C++/Java
    // Problem 4. Extract and print all the comments present in the code
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
    // Problem 5. Extract and print all the domain names present in the code
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
    //  Problem 6. Find max you can get from the array after Q operations
	// -------------------------------------------------------------------------
    static void computeMaxValue(int a, int b, int k, List<Integer> result) {
        for (int i = a; i <= b; i++) {
            result.set(i, result.get(i) + k);
        }
    }

    static int findMax(List<Integer> result) {
        int maxNum = Integer.MIN_VALUE;
        for (int num : result) {
            maxNum = Math.max(num, maxNum);
        }
        return maxNum;
    }

    static void findMaxAfterQOperations() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        // Fill the arrayList with 0s
        List<Integer> diffList = new ArrayList<Integer>(Collections.nCopies(n, 0));
        /*
        List<Integer> diffList = new ArrayList<>(n + 2);
        for (int i = 0; i <= n + 1; i++) {
            diffList.add(0);
        }
        */
        
        for(int a0 = 0; a0 < m; a0++){
            int a = in.nextInt();
            int b = in.nextInt();
            int k = in.nextInt();
            // Comment older approach
            // computeMaxValue(a - 1, b - 1, k, diffList);
            diffList.set(a, diffList.get(a) + k);
            diffList.set(b + 1, diffList.get(b + 1) - k);   
        }
        in.close();
        
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;

        for (int num : diffList) {
            curSum = curSum + num;
            maxSum = Math.max(maxSum, curSum);
        }
        System.out.println(maxSum);
        // Comment older approach
        // System.out.println(findMax(diffList));
    }

	// -------------------------------------------------------------------------
    // Problem 7. Reverse an array in subset of N
	// -------------------------------------------------------------------------
    static void reverseList(List<Integer> nums, int stIdx, int endIdx) {
        if (endIdx >= nums.size()) {
            endIdx = nums.size() - 1;
        }

        for (int i = stIdx, j = endIdx; i < j; i++, j--) {
            int tmp = nums.get(i);
            nums.set(i, nums.get(j));
            nums.set(j, tmp);
        }
    }

    static void reverseSubsetsOfArray(List<Integer> nums, int subsetSize) {
        System.out.println(nums);
        int stIdx = 0;
        for (int i = 0; i < nums.size();) {
            reverseList(nums, i, i + subsetSize - 1);
            i = i + subsetSize;
        }
        System.out.println(nums);
    }

	// -------------------------------------------------------------------------
    // Problem 8. Convert byte size to human readable format
    // https://stackoverflow.com/questions/3758606/how-to-convert-byte-size-into-human-readable-format-in-java
    // https://stackoverflow.com/questions/11701399/round-up-to-2-decimal-places-in-java
	// -------------------------------------------------------------------------
    static String convertToHumanReadableForm(long byteValue) {
        String strByte = String.valueOf(byteValue);
        String CONVERSION_CHARS = "BKMGT";
        if (byteValue < 1000) {
            return strByte + CONVERSION_CHARS.charAt(0);
        }
        int quotient = (int) Math.ceil(strByte.length() / 3.0) - 1;
        int endChar = (strByte.length() % 3 == 0 ? 3 : strByte.length() % 3);
        String resultStr = strByte.substring(0, endChar);
        // System.out.println("Tmp: " + strByte.substring(endChar, endChar + 1));
        //double remainingValue = Math.round(Double.parseDouble(strByte.substring(endChar, endChar + 1)) * 100.0 / 100.0);
        int remainingValue = Integer.parseInt(strByte.substring(endChar, strByte.length()));
        // System.out.println("Val: " + remainingValue);
        String tmpStr = String.format("%.1f", (double) remainingValue / Math.pow(10, String.valueOf(remainingValue).length()));
        resultStr += tmpStr.substring(1, tmpStr.length()) + CONVERSION_CHARS.charAt(quotient);
        return resultStr;
    }

    class Solution {
        static class Node {

            int key;
            Node[] children;
            Node parent;

            Node(int cost) {
                this.key = cost;
                children = null;
                parent = null;
            }
        }

        static class SalesPath {
            static class Result {
                int minWeight;

                public Result() {
                    this.minWeight = Integer.MAX_VALUE;
                }
            }

            //static int minWeight = Integer.MAX_VALUE;
            int getCheapestCost(Node rootNode) {
                if (rootNode == null) {
                    return 0;
                }
                Result resultObject = new Result();
                getCheapestCost(rootNode, rootNode.key, resultObject); // Note
                return resultObject.minWeight;
            }

            void getCheapestCost(Node rootNode, int currentWeight, Result resultObject) {
                if (rootNode == null) {
                    return;
                }

                if (rootNode.children == null || rootNode.children.length == 0) {
                    // We are at leaf
                    resultObject.minWeight = Math.min(currentWeight, resultObject.minWeight);
                    return;
                }

                for (Node child : rootNode.children) {
                    getCheapestCost(child, currentWeight + child.key, resultObject);
                }
            }
        }

  /*********************************************
   * Driver program to test above method     *
   *********************************************/

class Solution {

  static List<Character> findPossibleCharacters(char[][] board, int rowPos, int colPos) {
    HashSet<Character> nineNumbers = new HashSet<>();
    List<Character> possibleNumbers = new ArrayList<>();
    
    for (int i = 0; i < board[rowPos].length; i++) {
      if (board[rowPos][i] != '.') {
        nineNumbers.add(board[rowPos][i]);
      }
    }
    
    for (int i = 0; i < board.length; i++) {
      if (board[i][colPos] != '.') {
        nineNumbers.add(board[rowPos][i]);
      }
    }
    
    // Find 3x3 block
    int startRowPos = 0;
    int startColPos = 0;
    if (rowPos < 3) {
      startRowPos = 0;
    } else if (rowPos < 6) {
      startRowPos = 3;
    } else if (rowPos < 9) {
      startRowPos = 6;
    }
    
    if (colPos < 3) {
      startColPos = 0;
    } else if (colPos < 6) {
      startColPos = 3;
    } else if (colPos < 9) {
      startColPos = 6;
    }   
    
    for (int i = startRowPos; i < startRowPos + 3; i++ ) {
      for (int j = startColPos; j < startColPos + 3; j++) {
        if (board[i][j] != '.') {
          nineNumbers.add(board[rowPos][i]);
        }
      }  
    }
    
    for (int i = 1; i <=9; i++) {
      if (!nineNumbers.contains(i)) {
        possibleNumbers.add((char)i);
      }
    }
    return possibleNumbers;
  }
  
  static boolean sudokuSolve(char[][] board) {
    return sudokuSolve(board, 0, 0);
  }
  
  static boolean sudokuSolve(char[][] board, int currentRow, int currentCol) {    
    boolean isEmptyBlockPresent = false;
    for (int i = currentRow; i < board.length; i++) {
      for (int j = currentCol; j < board[0].length; j++) {
        if (board[i][j] == '.') {
          isEmptyBlockPresent = true;
          List<Character> possibleEntries = findPossibleCharacters(board, i, j);
          if (possibleEntries.size() == 0) {
            return false;
          }
          
          for (Character entry : possibleEntries) {
            board[i][j] = entry;
            boolean isBoardSolved = sudokuSolve(board, currentRow, currentCol);
            if (isBoardSolved) {
              return true;
            }
          }
        }
      }
    }
    
    return true;
  }

  public static void main(String[] args) {

  }

}}


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
            //getDomainNames();
        }

        // Reverse an array in subset of N
        {
            List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            reverseSubsetsOfArray(nums, 5);
        }

        // Convert byte size to human readable format
        {
            /*
            long bytes1 = 0;
            long bytes2 = 123;
            long bytes3 = 1234;
            long bytes4 = 12345;
            long bytes5 = 123456;
            long bytes6 = 1234567;
            long bytes7 = 12345678;
            long bytes8 = 123456789;
            long bytes9 = 1000;
            long bytes10 = 10000;
            long bytes11 = 100000;
            long bytes12 = 1000000;
            long bytes13 = 1234567890;
            */
            long bytes1 = 0;
            long bytes2 = 987;
            long bytes3 = 9876;
            long bytes4 = 98766;
            long bytes5 = 987666;
            long bytes6 = 9876767;
            long bytes7 = 98767678;
            long bytes8 = 987676789;
            long bytes9 = 1000;
            long bytes10 = 10000;
            long bytes11 = 100000;
            long bytes12 = 1000000;
            long bytes13 = 1876767890;
            System.out.println(convertToHumanReadableForm(bytes1));
            System.out.println(convertToHumanReadableForm(bytes2));
            System.out.println(convertToHumanReadableForm(bytes3));
            System.out.println(convertToHumanReadableForm(bytes4));
            System.out.println(convertToHumanReadableForm(bytes5));
            System.out.println(convertToHumanReadableForm(bytes6));
            System.out.println(convertToHumanReadableForm(bytes7));
            System.out.println(convertToHumanReadableForm(bytes8));
            System.out.println(convertToHumanReadableForm(bytes9));
            System.out.println(convertToHumanReadableForm(bytes10));
            System.out.println(convertToHumanReadableForm(bytes11));
            System.out.println(convertToHumanReadableForm(bytes12));
            System.out.println(convertToHumanReadableForm(bytes13));
            /*
            System.out.println(Math.ceil(2.0 / 3.0));
            System.out.println(Math.ceil(3.0 / 3.0));
            System.out.println(Math.ceil(4.0 / 3.0));
            System.out.println(Math.ceil(5.0 / 3.0));
            System.out.println(Math.ceil(6.0 / 3.0));
            System.out.println(Math.ceil(7.0 / 3.0));
            System.out.println(Math.ceil(8.0 / 3.0));
            */
        }
    }
}

