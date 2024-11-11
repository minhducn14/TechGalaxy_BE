package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.dto.response.TrademarkResponse;
import iuh.fit.se.techgalaxy.service.impl.TrademarkServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
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
                .data(new HashSet<>(Set.of(trademarkServiceImpl.createTrademark(name))))
                .build());
    }
}
