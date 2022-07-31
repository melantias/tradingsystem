package com.trading;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.trading.constants.TradingtypeEnum;
import com.trading.model.TradeEvent;
import com.trading.service.TradeService;

@ExtendWith(MockitoExtension.class)
public class TradeServiceTest {
	
	@InjectMocks
    private TradeService tradeService;
	
	
	private List<TradeEvent> tradeEvents;
	
	
	@BeforeEach
    public void setup(){
		tradeEvents = new ArrayList<TradeEvent>();
		TradeEvent tradeEvent1 = new TradeEvent();
		tradeEvent1.setAccount("ACC1");
		tradeEvent1.setSecurity("SEC1");
		tradeEvent1.setId(1);
		tradeEvent1.setQuantiy(100L);
		tradeEvent1.setTradingType(TradingtypeEnum.BUY);
		
		TradeEvent tradeEvent2 = new TradeEvent();
		tradeEvent2.setAccount("ACC1");
		tradeEvent2.setSecurity("SEC1");
		tradeEvent2.setId(2);
		tradeEvent2.setQuantiy(50L);
		tradeEvent2.setTradingType(TradingtypeEnum.SELL);
		
		TradeEvent tradeEvent3 = new TradeEvent();
		tradeEvent3.setAccount("ACC2");
		tradeEvent3.setSecurity("SEC1");
		tradeEvent3.setId(3);
		tradeEvent3.setQuantiy(100L);
		tradeEvent3.setTradingType(TradingtypeEnum.fromString("BUY"));
		
		TradeEvent tradeEvent4 = new TradeEvent();
		tradeEvent4.setAccount("ACC2");
		tradeEvent4.setSecurity("SEC1");
		tradeEvent4.setId(3);
		tradeEvent4.setQuantiy(0L);
		tradeEvent4.setTradingType(TradingtypeEnum.CANCEL);
		tradeEvents.add(tradeEvent1);
		tradeEvents.add(tradeEvent2);
		tradeEvents.add(tradeEvent3);
		tradeEvents.add(tradeEvent4);
	}
	
	
	@DisplayName("JUnit test for cancel")
    @Test
    public void givenTradeEvents_whenCancel_getTotalSecurities(){
		TradeEvent tradeEvent4 = new TradeEvent();
		tradeEvent4.setAccount("ACC2");
		tradeEvent4.setSecurity("SEC1");
		tradeEvent4.setId(3);
		tradeEvent4.setQuantiy(0L);
		tradeEvent4.setTradingType(TradingtypeEnum.CANCEL);
		tradeEvents.stream().forEach(event -> {
			tradeService.addTradeEvent(event);
		});
		
		
		Long totalSecurities = tradeService.getTotalSecurities(tradeEvent4);
		assertTrue(totalSecurities == 0L);
	}
	
	
	@DisplayName("JUnit test for buy and sell")
    @Test
    public void givenTradeEvents_whenSell_getTotalSecurities(){
		TradeEvent tradeEvent2 = new TradeEvent();
		tradeEvent2.setAccount("ACC1");
		tradeEvent2.setSecurity("SEC1");
		tradeEvent2.setId(2);
		tradeEvent2.setQuantiy(50L);
		tradeEvent2.setTradingType(TradingtypeEnum.SELL);
		tradeEvents.stream().forEach(event -> {
			tradeService.addTradeEvent(event);
		});
		
		
		Long totalSecurities = tradeService.getTotalSecurities(tradeEvent2);
		assertTrue(totalSecurities == 50L);
	}

}
