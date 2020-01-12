/** Must check what the largest overall value is, not just what the largest x is
 * Therefore, only counting in reverse won't work. You have to check until you get 
 * to the minimum (100) and then sort it 
 * */
public class LargestPalindrome {
    public static void main(String args[]) {
            int x = 999;
            int y = 999;
            int z = 0;
            int largest = 0;
            String s;
            boolean f;
            while(x > 100  )
            {
                y = 999;
                while(y>100)
                {
                    f  = false;
                    z = x*y;
                    s = Integer.toString(z);
                    int index = 0;
                    while(index < (s.length()/2) )
                    {
                        if(s.charAt(index) == s.charAt(s.length() - 1 - index) )
                        {
                        }
                        else
                            f = true;
                        index++;
                    }
                    if(!f)
                    {
                        if(z > largest )
                            largest = Integer.parseInt(s);
                    }
                    y--;
                }
                x--;
            }
            System.out.println(largest);
        }
    }
