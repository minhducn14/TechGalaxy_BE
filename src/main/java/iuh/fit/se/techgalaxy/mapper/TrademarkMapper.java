package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.TrademarkRequest;
import iuh.fit.se.techgalaxy.dto.response.TrademarkResponse;
import iuh.fit.se.techgalaxy.entities.Trademark;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrademarkMapper {
    TrademarkMapper INSTANCE = Mappers.getMapper(TrademarkMapper.class);

    TrademarkResponse toTrademarkResponse(Trademark trademark);

    TrademarkRequest toTrademarkRequest(Trademark trademark);

    Trademark toTrademarkFromRequest(TrademarkRequest trademarkRequest);

    Trademark toTrademarkFromResponse(TrademarkResponse trademarkResponse);
}
