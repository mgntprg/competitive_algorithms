import java.util.*;
import java.io.*;

/**
 * Program to determine how many times a player wins given 1000
 * hands.
 * Problem 54, eulersproject
 * 
 * @since December 1, 2017
 */
 
public class PokerHands
{
	private Scanner readFile;
	private int score;
	private ArrayList<Character> cards;
	private ArrayList<String> ranks;
	
	public PokerHands()
	{
		cards = new ArrayList<Character>();
		cards.addAll(Arrays.asList('1','2','3','4','5','6','7','8','9'
		,'T','J','Q','K','A') );
		ranks = new ArrayList<String>();
		score = 0;
	}
	public static void main(String [] args)
	{
		PokerHands PH = new PokerHands();
		PH.game();	
	}
	public void game()
	{
		
		try
		{
			readFile = new Scanner(new File("poker.txt") );
		}
		catch(IOException e)
		{
			System.exit(1);
		}
		
		String fileLine = "";
		
		while(readFile.hasNextLine() )
		{
			fileLine = readFile.nextLine();
			determineWinner(fileLine);
		}
		System.out.println(score);
	}
	
	public void determineWinner(String round)
	{	
		rankings("H", true);
		rankings("P", true);
		rankings("TP2", true);
		rankings("TP1", true);
		rankings("TK", true);
		rankings("CS", false);
		rankings("FS", false);
		rankings("FHT", true);
		rankings("FK", true);
		rankings("CF", false);
		rankings("RF", false);
		
		//14 marks end of hand for P1, 15 starting hand for P2
		//H, S, C, D - hearts, spades, diamonds, clubs
		String hand1 = round.substring(0, 14);
		String hand2 = round.substring(15, round.length() - 1);
		ArrayList<String> player1 = pointSystem(hand1);
		ArrayList<String> player2 = pointSystem(hand2);
		
		boolean roundOver = false;
		while(!roundOver)
		{
		
			//if the index is higher for P1, that means the winner is P1
			if(ranks.indexOf(player1.get(0)) > 
			ranks.indexOf(player2.get(0) ) )
			{
				score++;
				roundOver = true;
			}
			else if(ranks.indexOf(player1.get(0)) <
			ranks.indexOf(player2.get(0) ) )
				roundOver = true;		
			else if(ranks.indexOf(player1.get(0)) ==
			ranks.indexOf(player2.get(0) ) )
			{
				//if they are equal, check next condition
				player1.remove(0);
				player2.remove(0);
			}
			
		}
		
	}
	
	public ArrayList<String> pointSystem(String hand)
	{
		ArrayList<String> hierarchy = new ArrayList<String>();
		ArrayList<Character> sequential = new ArrayList<Character>();
		
		char pairOf = 'a';
		char pairOf2 = 'a';
		char threeOf = 'a'; 
		char fourOf = 'a';
		char min = 'a';
		
		//going to add each card in hand to the array, sort it
		//and check if its consecutive
	
		for(int count = 0; count < hand.length(); count += 3)
			sequential.add(hand.charAt(count) );
	
		//bubble sorting
		int n = sequential.size();
        for (int count = 0; count < n-1; count++)
            for (int j = 0; j < n-count -1; j++)
                if (cards.indexOf(sequential.get(j)) > 
					cards.indexOf(sequential.get(j+1) ) )
                {
                    // swap temp and arr[i]
                    char temp = sequential.get(j);
                    sequential.set(j, sequential.get(j+1) );     
					sequential.set(j+1, temp); 
                }
        
        boolean isConsecutive = false;
        //looping through the array to see if the character above it
        //is the next in the sequence
        for(int check = 0; check < sequential.size() - 1; check++ )
        {
			if(cards.indexOf(sequential.get(check) ) + 1
			 == cards.indexOf(sequential.get(check + 1) )  )
				isConsecutive = true;
			else
			{
				isConsecutive = false;
				break;
			}
		}
          
		boolean pairs = false;
		boolean twoPairs = false;
		int val = 0;
		
		//goes through and sees if there is a pair in this hand
		//by checking the length when a certain character is replaced
		for(val = 0; val < hand.length(); val+= 3)
		{
			 pairs = hand.replaceAll("" + hand.charAt(val), "")
			.length() == hand.length() - 2;
			if(pairs)
			{
				pairOf = hand.charAt(val);
				break;
			}
		}
		
		//to check for two pairs, we first check if there is one pair
		//we then remove that pair, and put it into a new variable (tempHand)
		//then we check that to see if there is a pair in that.
		if(pairs)
		{
			//want to keep indices the same, so going to replace w/ space
			String tempHand = hand.replaceAll("" + hand.charAt(val), " ");
			
			for(int next = 0; next < tempHand.length(); next += 3)
			{
				 twoPairs = tempHand.replaceAll("" + tempHand.charAt(next), "")
				.length() == tempHand.length() - 2;
				if(twoPairs)
				{
					pairOf2 = tempHand.charAt(next);
					break;
				}
			}
			
		}
		
		//if we have a full suit, then the length of the hand when 
		//each suit symbol is deleted should decrease by 5
		boolean fullSuit = hand.length() -
		hand.replaceAll("S","").length() == 5 ||
		hand.length() - hand.replaceAll("H","").length() == 5 ||
		hand.length() - hand.replaceAll("C","").length() == 5 ||
		hand.length() - hand.replaceAll("D","").length() == 5;
		
		//if we are removing 1 number / char, and all are removed or 
		//four are removed, then we can conclude that we have four of a kind
		boolean fourOfKind = false;
		
		if(hand.replaceAll("" + hand.charAt(0), "")
		.length() <= hand.length() - 4 )
		{
			fourOfKind = true;
			fourOf = hand.charAt(0);
		}
		else if( hand.replaceAll("" + hand
		.charAt(3), "").length() <= hand.length() - 4)
		{
			fourOfKind = true;
			fourOf = hand.charAt(3);
		}
	
	
		boolean threeOfKind = false;
		//same situation as four of a kind, except we check if the length
		//is 3 less
		if(hand.replaceAll("" + hand.charAt(0), "").length() == 
		hand.length() - 3 )
		{
			threeOfKind = true;
			threeOf = hand.charAt(0);
		}
		else if(hand.replaceAll("" + hand
		.charAt(3), "").length() == hand.length() - 3)
		{
			threeOfKind = true;
			threeOf = hand.charAt(3);
		}
		else if(hand.replaceAll("" + hand.charAt(6), "").length() == 
		hand.length() - 3 )
		{
			threeOfKind = true;
			threeOf = hand.charAt(2);
		}
		
		//if- else statements adding to the arraylist string in 
		//descending order to make it easy later
		
		if( fullSuit )
		{
			//royal flush
			if(hand.contains("T") && hand.contains("J") && hand.
			contains("Q") && hand.contains("K") && hand.contains("A") )
				hierarchy.add("RF");
		}
		//straight flush
		if( fullSuit && isConsecutive)
			hierarchy.add("CF");
		//4 same values
		if(fourOfKind)
			hierarchy.add("FK" + fourOf);
		//three of a kind 
		if(threeOfKind && (pairs && threeOf != pairOf) )
		{
			//want to make sure that the pair and threes aren't the same
			//full house
			hierarchy.add("FHT" + threeOf);
			//hierarchy.add("FHP" + pairOf);
		}
		if( fullSuit ) 
			hierarchy.add("FS");
		if(isConsecutive)
			hierarchy.add("CS");
		if( threeOfKind )
			hierarchy.add("TK" + threeOf);
		if(pairs)
		{
			//two different pairs
			if(twoPairs)
			{
				if(cards.indexOf(pairOf) > cards.indexOf(pairOf2) )
				{
					hierarchy.add("TP1" + pairOf );
					hierarchy.add("TP2" + pairOf2);
				}
				else
				{
					hierarchy.add("TP1" + pairOf2 );
					hierarchy.add("TP2" + pairOf);
				}
			}
			else
				hierarchy.add("P" + pairOf);
		
		}
		//indicates highest value
		hierarchy.add("H"+ sequential.get(sequential.size() - 1) );
		
		return hierarchy;
	}
	/** Description:Method sets the rankings in order given the prefix
	 * 	@param		rankingPrefix - the prefix e.g "TP" for two pairs
	 * 				initialize - whether or not it matters if there are
	 * 				values, for example, a full suit, the values don't 
	 *				matter
	 * 				addRanks - the array with the rankd
	 * */
	public void rankings(String rankingPrefix, boolean initialize)
	{
		if(initialize)
			for(char c : cards)
				ranks.add(rankingPrefix + c);
		else
			ranks.add(rankingPrefix);
	}
}
