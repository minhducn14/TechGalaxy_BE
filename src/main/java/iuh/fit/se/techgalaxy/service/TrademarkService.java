package iuh.fit.se.techgalaxy.service;

public interface TrademarkService {
    void createTrademark(String name);
    void deleteTrademark(String name);
    void updateTrademark(String name, String newName);
    void getTrademark(String name);
    void getAllTrademarks();
}
