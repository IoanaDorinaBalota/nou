package business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {

	public boolean validatePassword(String password)
	{
		return true;
	}
	
	public boolean validateName(String name)
	{
		boolean value=true;
	    String pattern="(([a-z]|[A-Z])+\\s*)+";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(name);
		if(name.matches(pattern))
			{System.out.println("corect name");value=true;}
		else 
		{
			System.out.println("incorect name");
			value=false;
		}
		  
		return value;
		
	}
	public boolean validateIdentityCardNumber(String number)
	{
		boolean value=true;
	    String pattern="[0-9]{6}";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(number);
		if(number.matches(pattern))
			{System.out.println("corect icn");value=true;}
		else 
		{
			System.out.println("incorect icn");
			value=false;
		}
		  
		return value;
	}
	
	public boolean validatePNC(String PNC)
	{
		boolean value=true;
	    String pattern="(1|2)([0-9]{2})"
	    		+ "((01((0[1-9]{1})|1[0-9]{1}|2[0-9]{1}|3[01]{1}))|"
	    		+ "(02((0[1-9]{1})|1[0-9]{1}|2[0-9]{1}))|"
	    		+ "(03((0[1-9]{1})|1[0-9]{1}|2[0-9]{1}|3[01]{1}))|"
	    		+ "(04((0[1-9]{1})|1[0-9]{1}|2[0-9]{1}|30)|"
	    		+ "(05((0[1-9]{1})|1[0-9]{1}|2[0-9]{1}|3[01]{1})))|"
	    		+ "(06((0[1-9]{1})|1[0-9]{1}|2[0-9]{1}|3[0]{1}))|"
	    		+ "(07((0[1-9]{1})|1[0-9]{1}|2[0-9]{1}|3[01]{1}))|"
	    		+ "(08((0[1-9]{1})|1[0-9]{1}|2[0-9]{1}|3[01]{1}))|"
	    		+ "(09((0[1-9]{1})|1[0-9]{1}|2[0-9]{1}|3[0]{1}))|"
	    		+ "(10((0[1-9]{1})|1[0-9]{1}|2[0-9]{1}|3[01]{1}))|"
	    		+ "(11((0[1-9]{1})|1[0-9]{1}|2[0-9]{1}|3[0]{1}))|"
	    		+ "(12((0[1-9]{1})|1[0-9]{1}|2[0-9]{1}|3[01]{1})))"
	    		+ "[0-9]{6}";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(PNC);
		if(PNC.matches(pattern))
			{System.out.println("corect pnc");value=true;}
		else 
		{
			System.out.println("incorect pnc");
			value=false;
		}
		  
		return value;
	}
	public boolean validateAddress(String address)
	{
		/* permite doar numere si litere urmate de spatiu*/
		boolean value=true;
	   // String pattern="([a-zA-Z]+)|(\\s+)|([0-9]*)";
		 String pattern="([a-zA-Z0-9]+\\s?)+";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(address);
		if(address.matches(pattern))
			{System.out.println("corect address");value=true;}
		else 
		{
			System.out.println("incorect address");
			value=false;
		}
		  
		return value;
	}
	
	public boolean validateBankCardNumber(String card)
	{
		boolean value=true;
		
		System.out.println(card);
		String pattern="[A-Z]{2}[0-9]{2}[A-Z]{4}[0-9A-Z]{16}";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(card);
		System.out.println(card);
		if(card.matches(pattern))
			{System.out.println("corect bank card number");value=true;}
		else 
		{
		
			System.out.println("incorect bank card number");
			
			value=false;
		}
		  
		return value;
	}
	
	public boolean validateMoney(String money)
	{
		boolean value=true;
		
		System.out.println(money);
		String pattern="[0-9]+\\.[0-9]+";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(money);
		System.out.println(money);
		if(money.matches(pattern))
			{System.out.println("corect money");value=true;}
		else 
		{
		
			System.out.println("incorect money");
			
			value=false;
		}
		  
		return value;
	}
}
