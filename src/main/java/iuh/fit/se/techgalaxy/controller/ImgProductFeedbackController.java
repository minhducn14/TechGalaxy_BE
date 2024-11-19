package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.ImgProductFeedbackRequest;
import iuh.fit.se.techgalaxy.dto.request.ProductFeedbackRequest;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.ImgProductFeedbackResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductFeedbackResponse;
import iuh.fit.se.techgalaxy.dto.response.ProductVariantResponse;
import iuh.fit.se.techgalaxy.service.ImgProductFeedbackService;
import iuh.fit.se.techgalaxy.service.impl.ImgProductFeedbackServiceImpl;
import iuh.fit.se.techgalaxy.service.impl.ProductFeedbackServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/product-feedbacks/{productFeedbackId}/img-feedbacks")
public class ImgProductFeedbackController {

	ImgProductFeedbackServiceImpl imgProductFeedbackServiceImpl;


    // Create ImgProductFeedback
    @PostMapping
    public ResponseEntity<DataResponse<ImgProductFeedbackResponse>> createImgFeedback(@PathVariable String productFeedbackId, @RequestBody ImgProductFeedbackRequest request) {
    	 Set<ImgProductFeedbackResponse> createdImgFeedback = new HashSet<>();
    	 createdImgFeedback.add(imgProductFeedbackServiceImpl.createImgFeedback(productFeedbackId, request));
         return ResponseEntity.ok(DataResponse.<ImgProductFeedbackResponse>builder().data(createdImgFeedback).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<ImgProductFeedbackResponse>> updateImgFeedback(@RequestBody ImgProductFeedbackRequest imgProductFeedbackRequest){
   		ImgProductFeedbackResponse imgproductFeedback = imgProductFeedbackServiceImpl.updateImgFeedback(imgProductFeedbackRequest.getId(),imgProductFeedbackRequest.getImagePath());
		return ResponseEntity.ok(DataResponse.<ImgProductFeedbackResponse>builder()
                .data(List.of(imgproductFeedback))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<ImgProductFeedbackResponse>> deleteImgFeedback(@PathVariable String id) {
    	   boolean isDeleted = imgProductFeedbackServiceImpl.deleteImgFeedback(id);
           return ResponseEntity.ok(DataResponse.<ImgProductFeedbackResponse>builder().message("Delete " + id + " success").build());
    }
}
