/*
    3
    2
    1 0 5
    2 4 2
    3 5 4
*/

import java.util.*;

public class Round_Robin
{
    static class process
    {
        int id, at, bt, rt, ct, tat, wt;
        process(int id, int at, int bt)
        {
            this.id = id;
            this.at = at;
            this.bt = bt;
            this.rt = bt;
        }
    }

    public static void main(String []arg)
    {
        Scanner sobj = new Scanner(System.in);

        System.out.print("Enter the number of process: ");
        int n = sobj.nextInt();

        System.out.print("Enter the time quantom: ");
        int TQ = sobj.nextInt();

        List<process> processes = new ArrayList<>();

        for(int i = 0; i<n; i++)
        {
            int id = sobj.nextInt();
            int at = sobj.nextInt();
            int bt = sobj.nextInt();

            process p = new process(id, at, bt);
            processes.add(p);
        }

        processes.sort(Comparator.comparingInt(p -> p.at));

        Queue<process> q = new LinkedList<>();
        Set<process> added = new HashSet<>();

        q.add(processes.get(0));
        added.add(processes.get(0));

        int time = 0;
        double TotalWT = 0;
        double TotalTAT = 0;
        int iCnt = 0;

        while(q.isEmpty() == false)
        {
            process curr = q.remove();

            if(time<curr.at)
                time = curr.at;

            int execTime = Math.min(curr.rt, TQ);
            curr.rt -= execTime;
            time += execTime;

            for(process p : processes)
            {
                if(p.at <= time && p.rt > 0 && !added.contains(p))
                {
                    q.add(p);
                    added.add(p);
                }
            }


            if(curr.rt > 0)
                q.add(curr);
            else
            {
                curr.ct = time;
                curr.tat = curr.ct - curr.at;
                curr.wt = curr.tat - curr.bt;

                TotalTAT += curr.tat;
                TotalWT += curr.wt;
            }

            
        }

        for(process p : processes)
        {
            System.out.println(p.id +"\t"+p.at+"\t"+p.bt+"\t"+p.ct+"\t"+p.tat+"\t"+p.wt);
        }

        System.out.printf("\nAvg TAT: %.2f", TotalTAT/n);
        System.out.printf("\nAvg WT: %.2f", TotalWT/n);

    }
}