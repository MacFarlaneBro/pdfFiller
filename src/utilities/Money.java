package utilities;

public class Money {
	
	private String moneyString;
	
	public Money(String value){
		
		StringBuilder temp = new StringBuilder();
		value = new StringBuilder(value).reverse().toString();
		
		for(int i = 1, length = value.length(); i <= length; i++){
	
			temp.append(value.charAt(i-1));
			if(i%3 == 0) temp.append(',');
		}
		moneyString = temp.reverse().toString();
	}
	
	@Override
	public String toString(){
		return moneyString;
	}
	
}
