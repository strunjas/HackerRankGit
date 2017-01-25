/**
 * Created by sstrunjas on 12/4/16.
 */
import java.util.*;
public class MeetingAvailability {
    public class Range {
        int start;
        int end;

        public Range(int ss, int se) {
            start = ss;
            end = se;

        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

    }

    public class PersonsCalendar {
        List<Range>[] year_calendar;

        public PersonsCalendar() {
            year_calendar = new ArrayList[365];
            for (int i = 0; i < 365; i++)
                year_calendar[i] = new ArrayList<Range>();
        }

        public boolean AddMeeting(String start, String end, int day) {
            String[] start_a = start.split(":");
            String[] end_a = end.split(":");
            int start_int = Integer.parseInt(start_a[0]) * 60 + Integer.parseInt(start_a[1]);
            int end_int = Integer.parseInt(end_a[0]) * 60 + Integer.parseInt(end_a[1]);
            Range r = new Range(start_int, end_int);
            int search_index = Collections.binarySearch(year_calendar[day - 1], r, Comparator.comparing(Range::getStart).thenComparing(Range::getEnd));
            if (search_index < 0)
                search_index = -1 * search_index - 1;
            if (year_calendar[day - 1].size() == 0) {
                year_calendar[day - 1].add(r);
                return true;
            } else if (search_index == year_calendar[day - 1].size()) {
                if (r.start >= year_calendar[day - 1].get(year_calendar[day - 1].size() - 1).end) {
                    year_calendar[day - 1].add(year_calendar[day - 1].size(), r);
                    return true;
                } else
                    return false;
            } else if (r.start == year_calendar[day - 1].get(search_index).start) {
                return false;
            } else {
                if (r.end <= year_calendar[day - 1].get(search_index).start) {
                    year_calendar[day - 1].add(r);
                    Collections.sort(year_calendar[day - 1], Comparator.comparing(Range::getStart).thenComparing(Range::getEnd));
                    return true;
                } else return false;
            }
        }

        public List<Range> getAvailability(int day_in_year) {
            int current_min = 0;
            List<Range> result = new ArrayList();
            for (Range r : year_calendar[day_in_year - 1]) {
                if (current_min < r.start) {
                    result.add(new Range(current_min, r.start));
                }
                current_min = r.end;
            }
            if (current_min < 24 * 60) {
                result.add(new Range(current_min, 24 * 60));
            }
            return result;
        }


    }

    public static List<Range> getAvailableTimesForTwo(List<Range> avail_p1, List<Range> avail_p2) {
        MeetingAvailability m = new MeetingAvailability();
        List<Range> ret = new ArrayList();
        int index_p1 = 0;
        int index_p2 = 0;
        while (index_p1 < avail_p1.size() && index_p2 < avail_p2.size()) {
            if (avail_p1.get(index_p1).end <= avail_p2.get(index_p2).start)
                index_p1++;
            else if (avail_p2.get(index_p2).end <= avail_p1.get(index_p1).start)
                index_p2++;
            else {
                Range new_r = m.new Range(Math.max(avail_p1.get(index_p1).start, avail_p2.get(index_p2).start),
                        Math.min(avail_p1.get(index_p1).end, avail_p2.get(index_p2).end));
                ret.add(new_r);
                if (avail_p1.get(index_p1).end > avail_p2.get(index_p2).end)
                    index_p2++;
                else if (avail_p2.get(index_p2).end > avail_p1.get(index_p1).end)
                    index_p1++;
                else {
                    index_p1++;
                    index_p2++;
                }
            }
        }
        return ret;


    }

    public static List<Range> getAvailableTimes(PersonsCalendar[] people, int day) {
        MeetingAvailability m = new MeetingAvailability();
        List<Range>[] individual_avail = new ArrayList[people.length];
        for (int i = 0; i < individual_avail.length; i++)
            individual_avail[i] = people[i].getAvailability(day - 1);
        List<Range> first = new ArrayList();
        for (Range r : individual_avail[0]) {
            first.add(r);
        }
        for (int j = 1; j < individual_avail.length; j++) {

            first = getAvailableTimesForTwo(first, individual_avail[j]);
            if (first.size() == 0)
                return first;

        }

        return first;
    }

    public static Range findMinMax(int[] indices, List<List<Range>> input, MeetingAvailability m) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < indices.length; i++) {
            if (indices[i] < input.get(i).size()) {
                if (min == input.get(i).get(indices[i]).start) {
                    if (max < input.get(i).get(indices[i]).end)
                        max = input.get(i).get(indices[i]).end;
                } else if (min > input.get(i).get(indices[i]).start) {
                    min = input.get(i).get(indices[i]).start;
                    max = input.get(i).get(indices[i]).end;
                }

            }
        }
        return m.new Range(min, max);
    }

    public static boolean endReached(int[] indices, List<List<Range>> input) {
        for (int i = 0; i < indices.length; i++) {
            if (indices[i] < input.get(i).size())
                return false;
        }
        return true;
    }

    public static List<Range> findAvailability(List<List<Range>> input) {
        MeetingAvailability m = new MeetingAvailability();
        List<Range> ret = new ArrayList<Range>();
        int[] current_index = new int[input.size()];

        int global_max = 0;
        for (int i = 0; i < current_index.length; i++) {
            current_index[i] = 0;
        }
        boolean end_reached = false;
        while (!end_reached) {
            Range min_max_pair = findMinMax(current_index, input, m);
            if (min_max_pair.start > global_max) {
                Range r = m.new Range(global_max, min_max_pair.start);
                ret.add(r);
            }
            global_max = min_max_pair.end;
            for (int j = 0; j < current_index.length; j++) {
                if (current_index[j] < input.get(j).size()) {
                    if (input.get(j).get(current_index[j]).start < global_max) {
                        global_max = Math.max(input.get(j).get(current_index[j]).end, global_max);
                        current_index[j]++;
                    }
                }
            }

            end_reached = endReached(current_index, input);


        }

        if (global_max < 23) {
            ret.add(m.new Range(global_max, 23));
        }
        return ret;
    }

    public static void main(String[] args){
        MeetingAvailability m = new MeetingAvailability();
        List l = new ArrayList<List<Range>>();
        List<Range> sched1 = new ArrayList();
        sched1.add(m.new Range(4,5));
        sched1.add(m.new Range(6,10));
        sched1.add(m.new Range(12,14));
        Collections.sort(sched1, Comparator.comparing(Range::getStart).thenComparing(Range::getEnd));
        l.add(sched1);
        List<Range> sched2 = new ArrayList();
        sched2.add(m.new Range(4,5));
        sched2.add(m.new Range(5,9));
        sched2.add(m.new Range(13,16));
        Collections.sort(sched2, Comparator.comparing(Range::getStart).thenComparing(Range::getEnd));
        l.add(sched2);
        List<Range> sched3 = new ArrayList();
        sched3.add(m.new Range(11,14));

        l.add(sched3);

        List<Range> res = findAvailability(l);
        for(Range r:res){
            System.out.println(r.start + "-" + r.end);
        }



    }

  /*  public static void main(String[] args) {
        MeetingAvailability m = new MeetingAvailability();
        PersonsCalendar p1 = m.new PersonsCalendar();
        boolean added = p1.AddMeeting("10:30", "11:00", 10);
        if (added)
            System.out.println("Meeting added");
        else
            System.out.println("Scheduling conflict");
        added = p1.AddMeeting("11:00", "12:30", 10);
        if (added)
            System.out.println("Meeting added");
        else
            System.out.println("Scheduling conflict");
        added = p1.AddMeeting("14:30", "16:00", 10);
        if (added)
            System.out.println("Meeting added");
        else
            System.out.println("Scheduling conflict");
        added = p1.AddMeeting("13:00", "14:00", 10);
        if (added)
            System.out.println("Meeting added");
        else
            System.out.println("Scheduling conflict");

        added = p1.AddMeeting("15:30", "16:00", 10);
        if (added)
            System.out.println("Meeting added");
        else
            System.out.println("Scheduling conflict");

        PersonsCalendar p2 = m.new PersonsCalendar();
        added = p2.AddMeeting("11:00", "11:30", 10);
        if (added)
            System.out.println("Meeting added");
        else
            System.out.println("Scheduling conflict");
        added = p2.AddMeeting("9:00", "9:30", 10);
        if (added)
            System.out.println("Meeting added");
        else
            System.out.println("Scheduling conflict");
        added = p2.AddMeeting("13:30", "14:30", 10);
        if (added)
            System.out.println("Meeting added");
        else
            System.out.println("Scheduling conflict");

        List<Range> available = getAvailableTimesForTwo(p1.getAvailability(10), p2.getAvailability(10));
        for (Range r : available)
            System.out.println("(" + r.start / 60 + ":" + r.start % 60 + " - " + r.end / 60 + ":" + r.end % 60 + ")");

    }*/
}



