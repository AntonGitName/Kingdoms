package view.units;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.units.UnitModel;

public final class ArcherView extends UnitView {

	private static final Image image;// = ImageIO.read(new File("peasant.png"));
	static {
		try {
			image = ImageIO.read(new File(IMAGES_FOLDER + "archer.png"));
		} catch (IOException e) {
			throw new ExceptionInInitializerError("Cannot load archer.png");
		}
	}

	public ArcherView(UnitModel unitModel) {
		super(unitModel);
	}

	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public int getX() {
		return unitModel.getX();
	}

	@Override
	public int getY() {
		return unitModel.getY();
	}

}
