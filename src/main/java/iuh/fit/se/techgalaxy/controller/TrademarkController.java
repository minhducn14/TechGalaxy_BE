package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.request.TrademarkRequest;
import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.TrademarkResponse;
import iuh.fit.se.techgalaxy.entities.Trademark;
import iuh.fit.se.techgalaxy.exception.AppException;
import iuh.fit.se.techgalaxy.exception.ErrorCode;
import iuh.fit.se.techgalaxy.service.impl.TrademarkServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.ErrorReportConfiguration.Builder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/trademarks")
public class TrademarkController {
	TrademarkServiceImpl trademarkServiceImpl;

	@PostMapping
	public ResponseEntity<DataResponse<TrademarkResponse>> createTrademark(@RequestParam String name) {

		return ResponseEntity.ok(DataResponse.<TrademarkResponse>builder()
				.data(new HashSet<>(Set.of(trademarkServiceImpl.createTrademark(name)))).build());
	}

	@GetMapping("/{id}")
    public ResponseEntity<DataResponse<TrademarkResponse>> getTrademarkBuID(@PathVariable String id){
    	TrademarkResponse trademark = trademarkServiceImpl.getByID(id);
    	
    	return ResponseEntity.ok(DataResponse.<TrademarkResponse>builder()
                .data(List.of(trademark))
                .build());
    }

	@PutMapping
    public ResponseEntity<DataResponse<TrademarkResponse>>update(@RequestBody TrademarkRequest trademarkRequest){
		TrademarkResponse trademark = trademarkServiceImpl.updateTrademark(trademarkRequest.getId(),trademarkRequest.getName());
		
		return ResponseEntity.ok(DataResponse.<TrademarkResponse>builder()
                .data(List.of(trademark))
                .build());
		
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Object>> delete(@PathVariable String id){
    	Boolean trademark = trademarkServiceImpl.deleteTrademark(id);
    	
    	return ResponseEntity.ok(DataResponse.<Object>builder().message("Delete " + id + " success").build());

    }
	
	@GetMapping
	public ResponseEntity<DataResponse<TrademarkResponse>> getTrademark(){
    	List<TrademarkResponse> trademark = trademarkServiceImpl.getAllTrademarks();
    	
    	return ResponseEntity.ok(DataResponse.<TrademarkResponse>builder()
                .data(trademark)
                .build());
    }
}
