package com.trading.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.trading.constants.TradingtypeEnum;
import com.trading.model.TradeEvent;

@Service
public class TradeService {

	private Map<TradeEvent, List<TradeEvent>> tradeEvents = new TreeMap<>(
			Comparator.comparing(TradeEvent::getAccount).thenComparing(Comparator.comparing(TradeEvent::getSecurity)));

	public void addTradeEvent(TradeEvent tradeEvent) {
		List<TradeEvent> events = tradeEvents.get(tradeEvent);
		if (events == null) {
			events = new ArrayList<TradeEvent>();
			tradeEvents.put(tradeEvent, events);
		}
		events.add(tradeEvent);
	}

	public void displayPositions() {
		tradeEvents.keySet().forEach(key -> {
			Long totalSecurities = getTotalSecurities(key);
			System.out.println(key.getAccount() + " " + key.getSecurity() + " " + totalSecurities);
			tradeEvents.get(key).stream().forEach(value -> {
				System.out.println(value.toString());
			});
		});
	}

	public Long getTotalSecurities(TradeEvent key) {
		Map<String, Map<Integer, Long>> quantityMap = new TreeMap<>();
		tradeEvents.get(key).stream().forEach(value -> {
			String quantityKey = value.getAccount() + value.getSecurity();
			Map<Integer, Long> quantityIdMap = quantityMap.get(quantityKey);
			if (quantityIdMap == null) {
				quantityIdMap = new TreeMap<>();
				quantityMap.put(quantityKey, quantityIdMap);
			}
			Long quantity = quantityIdMap.get(value.getId()) == null ? 0L : quantityIdMap.get(value.getId());
			if (value.getTradingType() == TradingtypeEnum.BUY) {
				quantityIdMap.put(value.getId(), quantity + value.getQuantiy());
			} else if (value.getTradingType() == TradingtypeEnum.SELL) {
				quantityIdMap.put(value.getId(), quantity - value.getQuantiy());
			} else if (value.getTradingType() == TradingtypeEnum.CANCEL) {
				quantityIdMap.put(value.getId(), 0L);
			}

		});

		return quantityMap.values().stream().flatMap(map -> map.values().stream())
				.collect(Collectors.summingLong(Long::longValue));
	}

}
