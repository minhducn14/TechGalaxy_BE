package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.response.TrademarkResponse;
import iuh.fit.se.techgalaxy.entities.Trademark;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrademarkMapper {

    TrademarkResponse toTrademarkResponse(Trademark trademark);
}
