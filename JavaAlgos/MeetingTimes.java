import java.io.*;
import java.util.*;
import java.util.stream.*;

        // [1, 3], [5, 30], [6, 10], [7, 8], [8, 9], [11, 23], [11, 13], [21, 50], [22, 50]
        //      [1, 3], [5, 30]
        //      [6, 10], [11, 23],
        //      [7, 8], [8, 9], [11, 13], [21, 50]
        //      [22, 50]
        //
        // [1, 3], [5, 20], [6, 10], [11, 23], [11, 13], [21, 50], [22, 50]
        //      [1, 3], [5, 20], [21, 50]
        //      [6, 10], [11, 23]
        //      [11, 13], [22, 50]
        //
        // [1, 2], [3, 6], [4, 7], [7, 10], [8, 9]
        //      [1, 2], [3, 6], [7, 10]
        //      [4, 7], [8, 9]
        //
        // {1, 2}, {3, 6}, {4, 10}, {7, 8}, {9, 10}
        //      [1, 2], [3, 6], [7, 8], [9, 10]
        //      [4, 10]
 
class Interval{
    int start;
    int end;
    public Interval(int start, int end){
        this.start = start;
        this.end = end;
    }
}

class EndPoint{
    int timestamp;
    boolean isStart;
    public EndPoint(int timestamp, boolean isStart){
        this.isStart = isStart;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        // return "Time: " + timestamp + "; isStart: " + isStart;
        return timestamp + "::" + isStart;
    }
}

public class MeetingTimes {
    //input List<Interval> meetings
    //output boolean conflict return true; else false;

    public static boolean isConflict(List<Interval> meetings){
        //c.c 
        if (meetings == null || meetings.size() <= 1) return false;
        
        //1. sort by start time
        //2. loop the meetings cur.s < preE
        Collections.sort(meetings, (m1, m2) -> (m1.start - m2.start));
        Interval pre = meetings.get(0);
        for (int i = 1; i < meetings.size(); i ++) {
            Interval cur = meetings.get(i);
            if (cur.start < pre.end) return true;
            else{
                pre = cur;
            }
        }
        return false;
    }

    public static int countMeetingRooms(List<Interval> meetings){
        //c.c 
        if (meetings == null || meetings.size() == 0) return 0;
        
        //1. convert interval into endpoint       T: O(n) : S: O(n)
        //2. sort the endpoint with time, put end first if ther  T: O(nlogn) S:O(1)
        //3 loop the meetings and update global max T: O(n) S:O(1)
        //T: O(nlogn) S; S: O(n)
        List<EndPoint> list = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i ++) {
            Interval cur = meetings.get(i);
            int start = cur.start;
            int end = cur.end;
            list.add(new EndPoint(start, true));
            list.add(new EndPoint(end, false));
        }
        
        Collections.sort(list, new Comparator<EndPoint>(){
            @Override
            public int compare(EndPoint p1, EndPoint p2){
                if (p1.timestamp != p2.timestamp){
                    return p1.timestamp - p2.timestamp;
                }else{
                    if (p1.isStart) return 1; // --> p1.isStart
                    else return -1;
                }
            }
            });

        System.out.println(list);

        int meeting = 0;
        int curMeet = 0;
        for (int i = 0; i < list.size(); i ++) {
            EndPoint cur = list.get(i);
            if (cur.isStart){
                curMeet ++;
            }else curMeet --;
            meeting = Math.max(meeting, curMeet);
        }
        return meeting;
    }

    public static void main(String[] args) {
        System.out.println("Meeting Scheduler");

        Interval a1 = new Interval(1, 3);
        Interval a2 = new Interval(5, 30);
        Interval a3 = new Interval(6, 10);
        Interval a4 = new Interval(7, 8);
        Interval a5 = new Interval(8, 9);
        Interval a6 = new Interval(11, 23);
        Interval a7 = new Interval(11, 13);
        Interval a8 = new Interval(21, 50);
        Interval a9 = new Interval(22, 50);

        Interval b1 = new Interval(1, 3);
        Interval b2 = new Interval(5, 20);
        Interval b3 = new Interval(6, 10);
        Interval b4 = new Interval(11, 23);
        Interval b5 = new Interval(11, 13);
        Interval b6 = new Interval(21, 50);
        Interval b7 = new Interval(22, 50);

        Interval c1 = new Interval(1, 2);
        Interval c2 = new Interval(3, 6);
        Interval c3 = new Interval(4, 7);
        Interval c4 = new Interval(7, 10);
        Interval c5 = new Interval(8, 9);

        Interval d1 = new Interval(1, 2);
        Interval d2 = new Interval(3, 6);
        Interval d3 = new Interval(4, 10);
        Interval d4 = new Interval(7, 8);
        Interval d5 = new Interval(9, 10);

        Interval e1 = new Interval(1, 2);
        Interval e2 = new Interval(2, 3);
        Interval e3 = new Interval(2, 4);
        Interval e4 = new Interval(3, 5);

        List<Interval> sample1 = Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9);
        List<Interval> sample2 = Arrays.asList(b1, b2, b3, b4, b5, b6, b7);
        List<Interval> sample3 = Arrays.asList(c1, c2, c3, c4, c5);
        List<Interval> sample4 = Arrays.asList(d1, d2, d3, d4, d5);
        List<Interval> sample5 = Arrays.asList(e1, e2, e3, e4);

        System.out.println(countMeetingRooms(sample1));
        System.out.println(countMeetingRooms(sample2));
        System.out.println(countMeetingRooms(sample3));
        System.out.println(countMeetingRooms(sample4));
        System.out.println(countMeetingRooms(sample5));
    }

}
