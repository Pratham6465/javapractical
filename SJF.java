/*
    3
    1 0 6
    2 2 8
    3 4 3
*/

import java.util.*;

public class SJF
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

        int time = 0; 
        int index = 0;
        double TotalWT = 0;
        double TotalTAT = 0;
        int completed = 0;

        while(completed != n)
        {
            process curr = null;
            int minTime = Integer.MAX_VALUE;

            for(process p : processes)
            {

                if(p.at <= time && p.rt > 0 && minTime > p.rt)
                {
                    curr = p;
                    minTime = p.rt;
                }
            }

            if(curr == null)
            {
                time++;
                continue;
            }

            time++;
            curr.rt--;

            if(curr.rt == 0)
            {
                completed++;
                curr.ct = time;

                curr.tat = curr.ct - curr.at;
                TotalTAT += curr.tat;

                curr.wt = curr.tat - curr.bt;
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