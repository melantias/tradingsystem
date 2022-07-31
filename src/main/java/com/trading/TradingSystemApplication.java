package com.trading;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.trading.constants.TradingtypeEnum;
import com.trading.model.TradeEvent;
import com.trading.service.TradeService;

@SpringBootApplication
public class TradingSystemApplication implements CommandLineRunner{
	
	@Autowired
	private TradeService tradingService;
	
	private static Logger LOG = LoggerFactory
		      .getLogger(TradingSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TradingSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("Starting Trade!");
		Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputTokens = input.split(" ");
        Long numEvents = Long.valueOf(inputTokens[1]);
        for(long i=0;i<numEvents;i++) {
        	String eventsInput = scanner.nextLine();
        	String[] event = eventsInput.split(" ");
        	if(event.length!=5) {
        		LOG.error("Invalid event "+ eventsInput);
        		continue;
        	}
        	TradingtypeEnum tradingType = TradingtypeEnum.fromString(event[1]);
        	if(tradingType != null) {
        		TradeEvent tradeEvent = new TradeEvent();
        		tradeEvent.setId(Integer.valueOf(event[0]));
        		tradeEvent.setTradingType(tradingType);
        		tradeEvent.setAccount(event[2]);
        		tradeEvent.setSecurity(event[3]);
        		tradeEvent.setQuantiy(Long.valueOf(event[4]));
        		tradingService.addTradeEvent(tradeEvent);
        	}
        }
        System.out.println("Output (positions)");
        tradingService.displayPositions();
        LOG.info("Closig Trade!");
        scanner.close();
	}

}
