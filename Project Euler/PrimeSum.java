public class PrimeSum{
    public static void main(String args[]) {
		long sum = 0; //need long to hold it.
        PrimeSum mc = new PrimeSum();
        for(int y = 3; y < 2000000; y++  ){
            if(isPrime(y) )
            {
                sum += y; 
            } 
         }
        System.out.println( 2 + sum);
    }
    public static boolean isPrime(int x)
    {
        for(int n = 2; n <= (int)(Math.sqrt(x)) + 1; n++)
        {
            if(x%n == 0)
                return false;
        }
        return true;
    }
}
