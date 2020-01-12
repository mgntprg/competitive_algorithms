public class LargestPrimeFinder {
    public static void main(String args[]) {
		long startTime = System.nanoTime();
        boolean b = false;
        int x = 2;
        while(!b)
        {
            if(600851475143L % x == 0)
            {
                long var = 600851475143L / x; //making x long to avoid PLOP possible loss of precision
                //it is evaluated from the right first, as a long, but can't 
                //be cast down to an int since java doesn't do that so var
                //must be long
                long z = 2;
                while(z < var)
                {
                    if(var % z == 0)
                    {
                        b = false;
                        break;
                    }
                    else
                        b = true;
                     z++;
                }
                if(b)
                {
                    System.out.println(600851475143L / (long)(x) );
                }
            }
            x++;
        }
        long endTime = System.nanoTime();
		double timeSpent = (endTime - startTime);
		System.out.println("Took " + timeSpent + " ns"); 
    }
}

