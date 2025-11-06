/*
   3
   1 2 5
   2 0 3
   3 4 4
*/

import java.util.*;

public class FCFS{

    static class process
    {
        int id, at, bt, ct, tat, wt;
        process(int id, int at, int bt)
        {
            this.id = id;
            this.at = at;
            this.bt = bt;
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
        double TotalWT = 0;
        double TotalTAT = 0;
        int index = 0;

        for(process p : processes)
        {
            if(time < p.at)
                time = p.at;

            time += p.bt;

            p.ct = time;

            p.tat = p.ct - p.at;
            TotalTAT += p.tat;

            p.wt = p.tat - p.bt;
            TotalWT += p.wt;
        }

        for(process p : processes)
        {
            System.out.println(p.id +"\t"+p.at+"\t"+p.bt+"\t"+p.ct+"\t"+p.tat+"\t"+p.wt);
        }

        System.out.printf("\nAvg TAT: %.2f", TotalTAT/n);
        System.out.printf("\nAvg WT: %.2f", TotalWT/n);

    }
}