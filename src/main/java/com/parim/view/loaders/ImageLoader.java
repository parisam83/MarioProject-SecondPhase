package com.parim.view.loaders;

import com.parim.model.components.TileObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImageLoader {
    private static ImageLoader instance;
    private HashMap<String, Image> imagesCollection = new HashMap<>();

    public Image LoadObject(String directory) {
        if (imagesCollection.containsKey(directory)) {
            return imagesCollection.get(directory);
        }

        Image image;
        try {
            image = ImageIO.read(new File("src/main/resources/objects/" + directory + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        image = image.getScaledInstance((int) TileObject.getSIZE(), (int) TileObject.getSIZE(), Image.SCALE_SMOOTH);
        imagesCollection.put(directory, image);
        return image;
    }

    public static ImageLoader getInstance() {
        if (instance == null) instance = new ImageLoader();
        return instance;
    }
}
