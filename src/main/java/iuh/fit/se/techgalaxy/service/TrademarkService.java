package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.dto.response.TrademarkResponse;

public interface TrademarkService {
    TrademarkResponse createTrademark(String name);
    void deleteTrademark(String name);
    void updateTrademark(String name, String newName);
    void getTrademark(String name);
    void getAllTrademarks();
}
