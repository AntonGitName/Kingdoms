package view.units;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.units.UnitModel;

public final class KnightView extends UnitView {
	private static final Image image;

	static {
		try {
			image = ImageIO.read(new File(IMAGES_FOLDER + "knight.png"));
		} catch (IOException e) {
			throw new ExceptionInInitializerError("Cannot load knight.png");
		}
	}

	public KnightView(UnitModel unitModel) {
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
