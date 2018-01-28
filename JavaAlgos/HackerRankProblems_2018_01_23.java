import java.io.*;
import java.util.*;
public class Solution {
    
    public static String findCyclicString (String input) {
        HashMap<Character, Integer> hmap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if(!(hmap.containsKey(input.charAt(i)))) {
                sb.append(Character.toString(input.charAt(i)));
                hmap.put(input.charAt(i), 0);
            } else {
                hmap.put(input.charAt(i), hmap.get(input.charAt(i))+1);
                if(input.charAt(i) == 'z') {
                    sb.append(Character.toString((char)(((int)'a' - 1) + hmap.get(input.charAt(i)))));
                } else {
                    sb.append(Character.toString((char)((int) input.charAt(i) + (int)hmap.get(input.charAt(i)))));
                }
               
           }
        }
        return sb.toString();
    }

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner s = new Scanner(System.in);
        int numEntries = s.nextInt();
        s.nextLine();
        List<String> strEntries = new ArrayList<>();
        for (int i = 0; i < numEntries; i++) {
            int strLen = s.nextInt();            
            s.nextLine();
            strEntries.add(s.nextLine());
        }
        for (String entry : strEntries) {
            System.out.println(findCyclicString(entry));
        }
    }
}


int isPresent(node* root, int val)
{
    if (root == NULL) 
    {
        return 0;
    }
    
    if (root->val == val) 
    {
        return 1;
    } 
    
    if (root->val > val)
    {
        return isPresent(root->left, val);
    } 
    else 
    {
        return isPresent(root->right, val);
    }
}

/*
 * Complete the function below.
 */

void splitString(const std::string &s, char delim, vector<std::string> &elems) {
    std::stringstream ss(s);
    std::string item;
    while (std::getline(ss, item, delim)) {
        elems.push_back(item);
    }
    return;
}

vector <int> getMinimumUniqueSum(vector <string> arr) {
    vector <int> result;
    for (int i = 0; i < arr.size(); i++)
    {
        cout << arr[i] << endl;
        vector<string> nums;
        splitString(arr[i], ' ', nums);
        int startNum = stoi(nums[0]);
        int endNum = stoi(nums[1]);
        result.push_back(floor(sqrt(endNum)) - ceil(sqrt(startNum)) + 1);
    }
    return result;
}


