import java.io.*;
import java.util.*;
import java.util.LinkedHashMap;

public class JavaDataStructures
{
    public static void main(String[] args)
    {
        // 1. QUEUE
        {
            System.out.println("Queue");

            // 1. Queue can be created through a linked list
            Queue<String> queue = new LinkedList<>();

            queue.add("hi");
            queue.add("how");
            queue.add("hello");
            queue.add("you");

            // 2. Print elements of the queue
            System.out.println("Queue Contents: " + queue);

            // 3. Remove First element from Queue
            System.out.println("Removing First: " + queue.remove());

            // Print elements of the queue
            System.out.println("Queue Contents after Remove: " + queue);

            // 4. Get the first element
            System.out.println("First Element: " + queue.element());

            // 5. Is queue empty
            System.out.println("Is empty: " + queue.isEmpty());
        }

        // 2. STACK
        {
            System.out.println("\nStack");

            // 1. Create a stack
            Stack<String> st = new Stack<>();

            // 2. Push elements into stack
            st.push("hi");
            st.push("how");
            st.push("hello");
            st.push("you");

            // 3. Print the stack
            System.out.println("Stack Contents: " + st);

            // 4. Remove top of the stack and print it
            System.out.println("Removing ToS: " + st.pop());

            // Print the stack
            System.out.println("Stack Contents after Remove: " + st);

            // 5. Get Top Of the Stack
            System.out.println("Top of Stack: " + st.peek());

            // 6. Is stack empty
            System.out.println("Is empty: " + st.empty());
        }

        // 3. LINKED_HASH_MAP
        // Should import LinkedHashMap
        {
            System.out.println("\nLinkedHashMap");

            // 1. Create LinkedHashMap
            // BOTH WORKS
            //Map<String, Integer> lhm = new LinkedHashMap<>();
            LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();

            // 2. Add elements to map
            lhm.put("hi", new Integer(2));
            lhm.put("how", new Integer(3));
            lhm.put("hello", new Integer(5));
            lhm.put("you", new Integer(3));

            // 3. Print Map Items
            for (Map.Entry<String, Integer> entry : lhm.entrySet())
            {
                System.out.print(entry.getKey()+" : "+entry.getValue() + ", ");
            }
            System.out.println();

            // 4. Contains Keys
            System.out.println("Contains Key 'hi': " + lhm.containsKey("hi"));
            System.out.println("Contains Key 'ha': " + lhm.containsKey("ha"));

            // 5. Increment Value corresponding to a key
            lhm.put("hello", lhm.get("hello") + 1);
        }

        // 4. LINKED LIST
        {
            System.out.println("\nLinkedList");

            // 1. Add elements to Linked List
            LinkedList<String> listStrings = new LinkedList<>();
            listStrings.add("hi");
            listStrings.add("how");
            listStrings.add("hello");
            listStrings.add("you");

            // 2. Print the list
            System.out.println(listStrings);

            // 3. Get first and Get last elements
            String first = listStrings.getFirst();
            String last = listStrings.getLast();

            System.out.println(first + ", " + last);

            // 4. addFirst(), addLast()

            // 5. removeFirst(), removeLast()
        }

        // 5. Bitset
        {
            System.out.println("\nBitSet");

            // 1. Create BitSet
            BitSet bits1 = new BitSet(8);
            BitSet bits2 = new BitSet(8);

            // 2. Set Bits
            bits1.set(0);
            bits1.set(2);
            bits1.set(4);
            bits1.set(6);

            bits2.set(1);
            bits2.set(3);
            bits2.set(6);
            bits2.set(7);

            System.out.println(bits1);
            System.out.println(bits2);

            // 3. AND
            bits1.and(bits2);
            System.out.println(bits1);
            
            // 4. OR
            bits1.or(bits2);
            System.out.println(bits1);
            
            // 5. XOR
            bits1.xor(bits2);
            System.out.println(bits1);
        }

        // 5b. Bits using INT
        {
            System.out.println("\nBitSet Using INT");

            int bits2 = 0b1000001;
            int bits1 = 0b1111111;
            bits2 &= bits1;
            System.out.println(Integer.toBinaryString(bits2));
        }

        // 6. HashMap
        {
        }
    }
}
