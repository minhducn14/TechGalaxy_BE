package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.entities.Color;

import java.util.List;

public interface ColorService {
    List<Color> getAllColors();

    Color getColorById(String id);

    List<Color> getColorsByIDs(List<String> ids);

}
