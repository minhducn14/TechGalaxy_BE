package iuh.fit.se.techgalaxy.mapper;

import iuh.fit.se.techgalaxy.dto.request.ImgProductFeedbackRequest;
import iuh.fit.se.techgalaxy.dto.response.ImgProductFeedbackResponse;
import iuh.fit.se.techgalaxy.entities.ImgProductFeedback;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImgProductFeedbackMapper {
    ImgProductFeedbackResponse toRespondedto(ImgProductFeedback imgProductFeedback);

    iuh.fit.se.techgalaxy.entities.ImgProductFeedback toEntity(ImgProductFeedbackRequest imgFeedbackRequest);
}
