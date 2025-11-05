/*
 10
 3
 1 3 4 1 2 3 5 6 7 3
 */
import java.util.*;

public class LRU
{
    public static void main(String []arg)
    {
        Scanner sobj = new Scanner(System.in);
        
        System.out.print("Enter the number of pages: ");
        int n = sobj.nextInt();

        System.out.print("Enter the frame size: ");
        int frame = sobj.nextInt();

        System.out.println("Enter the pages: ");

        List<Integer> arr = new ArrayList<>();

        int fault = 0;

        for(int i = 0; i<n; i++)
        {
            int x = sobj.nextInt();
            if(!arr.contains(x))
            {
                if(arr.size() == frame)
                {
                    arr.remove(0);
                }

                arr.add(x);
                fault++;
            }
            else
            {
                arr.remove(Integer.valueOf(x));
                arr.add(x);
            }
        }

        System.out.println("Total number of page faults: " + fault);

    }
}