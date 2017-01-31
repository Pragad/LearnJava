import java.io.*;
import java.util.*;

class ShareDetails {
    public ShareDetails(String name, String volumeName) {

        this.name = name;
        this.volumeName = volumeName;
    }
    public String name;
    public String volumeName;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        sb.append(":");
        sb.append(volumeName);
        return sb.toString();
    }
}

public class PrintMap
{
    public static void main(String[] args)
    {
        System.out.println("hi");
        Map<String, Map<String, ShareDetails>> shares = new HashMap<>();

        ShareDetails sh1 = new ShareDetails("sh1", "vol1");
        ShareDetails sh2 = new ShareDetails("sh2", "vol2");
        ShareDetails sh3 = new ShareDetails("sh3", "vol3");
        ShareDetails sh4 = new ShareDetails("sh4", "vol4");
        
        Map <String, ShareDetails> m1 = new HashMap<>();
        m1.put("a", sh1);
        m1.put("b", sh2);

        Map <String, ShareDetails> m2 = new HashMap<>();
        m2.put("c", sh3);
        m2.put("d", sh4);

        shares.put("vs1", m1);
        shares.put("vs2", m2);

        for (Map.Entry<String, Map<String, ShareDetails>> entry : shares.entrySet())
        {
            System.out.println(entry.getKey());
            Map <String, ShareDetails> mapVShare= entry.getValue();

            for (Map.Entry<String, ShareDetails> e : mapVShare.entrySet())
            {
                System.out.println(e.getKey()+" : "+entry.getValue().toString());
            }
        }
    }
}

