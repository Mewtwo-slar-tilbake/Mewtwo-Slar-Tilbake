package com.kaijumon.game.model.map;

import java.awt.*;

public interface IMapLoader {
    boolean[][] getLayerData(String mapPath, String layerName);

    Point getMapDimensions(String mapPath);


}
