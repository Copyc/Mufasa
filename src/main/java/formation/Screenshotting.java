package formation;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.cropper.indent.BlurFilter;
import ru.yandex.qatools.ashot.cropper.indent.IndentCropper;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
* This class provide methods for working with screenshots
*/
public class Screenshotting {

	/**
	 * 
	 * @param driver
	 * @return	byte array of screenshot image
	 */
	@Attachment(value = "Screenshot of web page", type = "image/png")
	public static byte[] takeWebBrowserScreenshotImage(WebDriver driver) {
		return getByteArrayFromImage(
				getBufferedImage(
						takeBrowserScreen(driver)), "png");
	}
	/**
	 * 
	 * @param driver
	 * @param webElement
	 * @param blur 'yes' if should be blurred, 'no' if just an element, 'yes' is by default  
	 * @return	byte array of element's screenshot image
	 */
	@Attachment(value = "Screenshot with highlighted element: ")
	public static byte[] takeWebElementScreenshotImage(WebDriver driver, WebElement webElement, Boolean... blur) {
		return getByteArrayFromImage(
				getBufferedImage(
						blur.length > 0 && !blur[0]
						?takeElementScreen(driver, webElement)
						:takeElementScreenWithBlur(driver, webElement)), "png");
	}
	
	/**
	 * Convert buffered image into byte array
	 * @param buffered image
	 * @param format of image
	 * @return byte array from image
	 */
	private static byte[] getByteArrayFromImage(BufferedImage image, String format) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, format, baos);
		} catch (IOException e) {
			throw new RuntimeException(e.getLocalizedMessage() + "Cannot write screenshot into ImageIO");
		}
		byte[] imageBytes = baos.toByteArray();

		if (imageBytes.length == 0) {
			throw new RuntimeException("Converted byte array for screenshot is empty.");
		}
		return imageBytes;
	}
	
	/**
	 * Gets buffered image from ashot screenshot
	 * @param screenshot
	 * @return buffered image
	 */
	private static BufferedImage getBufferedImage(Screenshot screenshot) {
		return screenshot.getImage();
	}

	/**
	 * Takes screenshot of whole browser window
	 * @param driver
	 * @return screenshot image
	 */
	private static Screenshot takeBrowserScreen(WebDriver driver) {
		return new AShot()
//				.coordsProvider(new WebDriverCoordsProvider())
				.shootingStrategy(ShootingStrategies.viewportPasting(100))
				.takeScreenshot(driver);
	}
	/**
	 * Takes screenshot of provided web element
	 * @param driver
	 * @param webElement to picture
	 * @return screenshot image
	 */
	private static Screenshot takeElementScreen(WebDriver driver, WebElement webElement) {
		return new AShot()
				.takeScreenshot(driver, webElement);
	}
	/**
	 * Takes screenshot of provided web element with blurred area around it
	 * @param driver
	 * @param webElement to picture
	 * @return screenshot image
	 */
	private static Screenshot takeElementScreenWithBlur(WebDriver driver, WebElement webElement) {
		return new AShot()
//				.coordsProvider(new WebDriverCoordsProvider())
				.imageCropper(
						new IndentCropper().addIndentFilter(new BlurFilter()))
				.takeScreenshot(driver, webElement);
	}
}
