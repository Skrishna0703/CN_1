import java.util.*;
class Test
{
    static int ray[] = {10,20,30,40,50};
    static int largest()
    {
        int i;
        int max = ray[0];
        int min = ray[0];
        

        for (i = 1 ; i < ray.length; i++)
            if (ray[i] > max)
             ray[i] = min;
             max = ray[i];
        return max;
        return min;
        

    }
    public static void main(String[] args)
    {System.out.println("print largest & Second largest "+ largest());}
}