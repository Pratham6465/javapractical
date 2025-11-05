/*
 15
 3
 1 7 2 3 1 7 6 8 3 4 9 5 3 6 2
 */
 
 
import java.util.*;

public class FIFO
{
    public static void main(String []arg)
    {
        Scanner sobj = new Scanner(System.in);
        
        System.out.print("Enter the number of pages: ");
        int n = sobj.nextInt();

        System.out.print("Enter the frame size: ");
        int frame = sobj.nextInt();

        System.out.println("Enter the pages: ");

        Queue<Integer> q = new LinkedList<>();
        Set<Integer> s = new HashSet<>();

        int fault = 0;

        for(int i = 0; i<n; i++)
        {
            int x = sobj.nextInt();
            if(!s.contains(x))
            {
                if(s.size() == frame)
                {
                    int removed = q.remove();
                    s.remove(removed);
                }

                s.add(x);
                q.add(x);
                fault++;
            }
        }

        System.out.println("Total number of page faults: " + fault);

    }
}