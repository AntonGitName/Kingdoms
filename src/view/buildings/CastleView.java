package view.buildings;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.buildings.BuildingModel;

public final class CastleView extends BuildingView {

	private static final Image image;// = ImageIO.read(new File("peasant.png"));
	static {
		try {
			image = ImageIO.read(new File(IMAGES_FOLDER + "castle.png"));
		} catch (IOException e) {
			throw new ExceptionInInitializerError("Cannot load castle.png");
		}
	}
	
	public CastleView(BuildingModel buildingModel) {
		super(buildingModel);
	}

	@Override
	public Image getImage() {
		return image;
	}

}
