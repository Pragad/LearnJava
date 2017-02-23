import java.io.*;
import java.util.*;

public class HashMap_ArrayList {

    public static void main(String[] args) {
        Map<String, List<Integer>> myMap = new HashMap<>();

		for (int i = 0; i < 10; i++)
        {
            if (i <= 3) {

                continue;
            }
            else if (i <= 6) {
                continue;
            }
            else
            {
                continue;
            }
        }
        /*
		if(itemsList == null) {
			itemsList = new ArrayList<Item>();
			itemsList.add(myItem);
			items.put(mapKey, itemsList);
		} else {
			// add if item is not already in list
			if(!itemsList.contains(myItem)) itemsList.add(myItem);
		}
        */
    }
}
