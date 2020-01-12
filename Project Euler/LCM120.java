public class LCM120 {
    public static void main(String args[]) {
		long startTime = System.nanoTime();
        boolean b = true; 
        boolean d = true;
        int i = 2520;
        while(b)
        {
            int c = 2;
            while(c< 20)
            {
                if(i%c != 0)
                {
                   b = true; break;
                }
                else
                {
                    b = false;
                }
                c++;
            }
           
            i+=20;
        }
        System.out.println(i-20);
        long endTime = System.nanoTime();
		double timeSpent = (endTime - startTime);
		System.out.println("Took " + timeSpent + " ns"); 
    }
}
