package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.dto.request.ImgProductFeedbackRequest;
import iuh.fit.se.techgalaxy.dto.response.ImgProductFeedbackResponse;
import iuh.fit.se.techgalaxy.entities.ImgProductFeedback;
import iuh.fit.se.techgalaxy.exception.AppException;
import iuh.fit.se.techgalaxy.exception.ErrorCode;
import iuh.fit.se.techgalaxy.mapper.ImgProductFeedbackMapper;
import iuh.fit.se.techgalaxy.repository.ImgProductFeedbackRepository;
import iuh.fit.se.techgalaxy.service.ImgProductFeedbackService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImgProductFeedbackServiceImpl implements ImgProductFeedbackService {

    ImgProductFeedbackRepository imgProductFeedbackRepository;
    ImgProductFeedbackMapper imgProductFeedbackMapper;

    @Override

    public ImgProductFeedbackResponse createImgFeedback(String productFeedbackId, ImgProductFeedbackRequest imgProductFeedbackRequest) {
        ImgProductFeedback imgProductFeedback = imgProductFeedbackMapper.toEntity(imgProductFeedbackRequest);
        //

        ImgProductFeedback imgProductFeedbackCreate = imgProductFeedbackRepository.save(imgProductFeedback);


        ImgProductFeedbackResponse imgProductFeedbackResponse = imgProductFeedbackMapper.toRespondedto(imgProductFeedbackCreate);
        //
        return imgProductFeedbackResponse;
    }

    @Override
    public boolean deleteImgFeedback(String id) {
        ImgProductFeedback imgProductFeedack = imgProductFeedbackRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.IMAGE_FEEDBACK_NOTFOUND));
        if (imgProductFeedack != null) {
            imgProductFeedbackRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ImgProductFeedbackResponse updateImgFeedback(String id, String newImgPath) {
        ImgProductFeedback imgProductFeedack = imgProductFeedbackRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.IMAGE_FEEDBACK_NOTFOUND));
        if (imgProductFeedack != null) {
            imgProductFeedack.setImagePath(newImgPath);

            return imgProductFeedbackMapper.toRespondedto(imgProductFeedbackRepository.save(imgProductFeedack));
        }
        return null;
    }

    @Override
    public String getImgPath(String id) {
        // TODO Auto-generated method stub
        ImgProductFeedback imgProductFeedack = imgProductFeedbackRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.IMAGE_FEEDBACK_NOTFOUND));
        if (imgProductFeedack != null) {
            return imgProductFeedack.getImagePath();
        }
        return null;


    }


}


