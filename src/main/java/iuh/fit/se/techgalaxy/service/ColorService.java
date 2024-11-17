package iuh.fit.se.techgalaxy.service;

import iuh.fit.se.techgalaxy.entities.Color;

import java.util.List;

public interface ColorService {
    public List<Color> getAllColors();
    public Color getColorById(String id);

}
