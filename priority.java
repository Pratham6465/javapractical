/*
    3
    1 0 4 2
    2 1 2 1
    3 2 6 3
*/

import java.util.*;

public class priority
{
    static class process
    {
        int id, at, bt, pt, ct, tat, wt;
        boolean taken = false;
        process(int id, int at, int bt, int pt)
        {
            this.id = id;
            this.at = at;
            this.bt = bt;
            this.pt = pt;
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
            int pt = sobj.nextInt();

            process p = new process(id, at, bt, pt);
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
            int minPT = Integer.MAX_VALUE;

            for(process p : processes)
            {

                if(p.at <= time && p.taken == false && minPT > p.pt)
                {
                    curr = p;
                    minPT = p.pt;
                }
            }

            if(curr == null)
            {
                time++;
                continue;
            }

            time += curr.bt;
            completed++;
            curr.taken = true;
            curr.ct = time;

            curr.tat = curr.ct - curr.at;
            TotalTAT += curr.tat;

            curr.wt = curr.tat - curr.bt;
            TotalWT += curr.wt;

        }

        for(process p : processes)
        {
            System.out.println(p.id +"\t"+p.at+"\t"+p.bt+"\t"+p.ct+"\t"+p.tat+"\t"+p.wt);
        }

        System.out.printf("\nAvg TAT: %.2f", TotalTAT/n);
        System.out.printf("\nAvg WT: %.2f", TotalWT/n);

    }
}