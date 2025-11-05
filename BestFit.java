/*
4
5
100 500 200 300 600
212 417 112 426
*/

import java.util.*;

class BestFit
{
    static void print(int []arr, int []memory, int n)
    {
        System.out.println("\nMemory" + "\t -> \t" + "Process");
        for(int i = 0; i<n; i++)
        {
            System.out.println(memory[i] + "\t -> \t" + arr[i]);
        }
    }

    public static void main(String []arg)
    {
        Scanner sobj = new Scanner(System.in);
        int n, m;

        System.out.print("Enter the number of processes: ");
        n = sobj.nextInt();
        System.out.print("Enter the number of Memory blocks: ");
        m = sobj.nextInt();

        int []block = new int[m];
        int []memory = new int[m];

        Arrays.fill(block, -1);

        System.out.println("\nEnter the "+m+" memory blocks:- ");
        for(int i = 0; i<m; i++)
        {
            memory[i] = sobj.nextInt();
        }

        System.out.println("\nEnter the processes: ");
        for(int j = 0; j<n; j++)
        {
            int x = sobj.nextInt();
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for(int i = 0; i<m; i++)
            {
                if(block[i] == -1 && memory[i] >= x && min>memory[i])
                {
                    min = memory[i];
                    minIndex = i;
                }
            }

            if(minIndex != -1)
                block[minIndex] = x;
        }

        print(block, memory, m);
    }
}