package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.response.TrademarkResponse;
import iuh.fit.se.techgalaxy.entities.Trademark;
import iuh.fit.se.techgalaxy.mapper.TrademarkMapper;
import iuh.fit.se.techgalaxy.repository.TrademarkRepository;
import iuh.fit.se.techgalaxy.service.TrademarkService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TrademarkServiceImpl implements TrademarkService {
    TrademarkRepository trademarkRepository;
    TrademarkMapper trademarkMapper;
    @Override
    public TrademarkResponse createTrademark(String name) {
        Trademark trademark = new Trademark();
        trademark.setName(name);
        return trademarkMapper.toTrademarkResponse(trademarkRepository.save(trademark));
    }

    @Override
    public void deleteTrademark(String name) {

    }

    @Override
    public void updateTrademark(String name, String newName) {

    }

    @Override
    public void getTrademark(String name) {

    }

    @Override
    public void getAllTrademarks() {

    }
}
