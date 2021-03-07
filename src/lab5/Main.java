package lab5;
import lab5.market.MarketState;
import lab5.market.MarketView;

public class Main{
	public static void main(String[] args) {
			MarketState marketState = new MarketState();
			
			MarketView view = new MarketView(marketState);
			marketState.recivedEvent();
			marketState.recivedEvent();
	}
}
