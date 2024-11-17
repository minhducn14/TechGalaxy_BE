package iuh.fit.se.techgalaxy.controller;

import iuh.fit.se.techgalaxy.dto.response.DataResponse;
import iuh.fit.se.techgalaxy.entities.Color;
import iuh.fit.se.techgalaxy.service.impl.ColorServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("/colors")
public class ColorController {
    ColorServiceImpl colorServiceImpl;

    @GetMapping
    public ResponseEntity<DataResponse<Color>> getAllColor() {
        return ResponseEntity
                .ok(DataResponse.<Color>builder().data(colorServiceImpl.getAllColors()).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Color>> getColorById(String id) {
        return ResponseEntity.ok(DataResponse.<Color>builder().data(List.of( colorServiceImpl.getColorById(id))).build());
    }

}
