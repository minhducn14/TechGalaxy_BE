package iuh.fit.se.techgalaxy.service.impl;

import iuh.fit.se.techgalaxy.entities.Color;
import iuh.fit.se.techgalaxy.repository.ColorRepository;
import iuh.fit.se.techgalaxy.service.ColorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ColorServiceImpl implements ColorService {

    ColorRepository colorRepository;

    @Override
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    @Override
    public Color getColorById(String id) {
        return colorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Color> getColorsByIDs(List<String> ids) {
        return colorRepository.findColorsByIdIsIn(ids);
    }

}
