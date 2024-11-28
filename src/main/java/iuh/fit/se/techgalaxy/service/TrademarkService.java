package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.response.TrademarkResponse;

import java.util.List;

public interface TrademarkService {
    TrademarkResponse createTrademark(String name);

    boolean deleteTrademark(String id);

    TrademarkResponse updateTrademark(String name, String newName);

    TrademarkResponse getTrademarkByName(String name);

    List<TrademarkResponse> getAllTrademarks();

    TrademarkResponse getByID(String id);
}
