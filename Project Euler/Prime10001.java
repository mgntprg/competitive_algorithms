public class Prime10001
{
	public static void main(String [] args)
	{
		int x = 6;
		int num = 14;
	
		while(x < 10001)
		{
			if(PrimeSum.isPrime(num) )
				x++;
			num++;
		}
		System.out.println(num);
	}
}
