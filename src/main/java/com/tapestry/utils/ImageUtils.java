package com.tapestry.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageUtils
{
	public static ByteArrayInputStream inputStringFrom(final String url)
	{
		try
		{
			byte[] data = ImageUtils.readBytesFromURL(url);

			ByteArrayInputStream inputStream = new ByteArrayInputStream(data);

			return inputStream;
		}

		catch (IOException e)
		{
			e.printStackTrace();

			return null;
		}

	}

	public static byte[] readBytesFromURL(String urlString) throws IOException
	{
		URI uri = Paths.get(urlString).toUri();

		try (InputStream inputStream = uri.toURL().openStream())
		{
			byte[] data = inputStream.readAllBytes();
			return data;
		}
	}

	public static ImageIcon imageFrom(final String urlPath)
	{
		try
		{
			URI uri = Paths.get(urlPath).toUri();

			final BufferedImage bufferedImage = ImageIO.read(uri.toURL());

			return new ImageIcon(bufferedImage);
		}
		catch (final MalformedURLException e)
		{
			e.printStackTrace();

			return null;
		}
		catch (final IOException e)
		{
			e.printStackTrace();

			return null;
		}
	}

	public static ImageIcon resizeImage(final String urlPath, final int targetWidth, final int targetHeight)
	{
		try
		{
			URI uri = Paths.get(urlPath).toUri();

			final BufferedImage bufferedImage = ImageIO.read(uri.toURL());

			bufferedImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);

			final Image resultingImage = bufferedImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);

			final BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

			outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);

			return new ImageIcon(outputImage);
		}

		catch (final Exception e)
		{
			e.printStackTrace();

			return null;
		}
	}
}
