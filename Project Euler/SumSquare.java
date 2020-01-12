public class SumSquare
{
	public static void main(String [] args)
	{
		int sum1 = 0;
		int sum2 = 0;
		for(int x = 0; x <= 100; x++)
		{
			sum1 += Math.pow(x, 2);
			sum2 += x;
		}
		sum2 = (int)(Math.pow(sum2, 2) );
		System.out.println(sum2 - sum1);
	}
}
