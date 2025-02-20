package net.coderbot.iris.gl.image;

import java.util.function.IntSupplier;

import org.lwjgl.opengl.GL15;

import net.coderbot.iris.gl.IrisRenderSystem;

public class ImageBinding {
	private final int imageUnit;
	private final int internalFormat;
	private final IntSupplier textureID;

	public ImageBinding(int imageUnit, int internalFormat, IntSupplier textureID) {
		this.textureID = textureID;
		this.imageUnit = imageUnit;
		this.internalFormat = internalFormat;
	}

	public void update() {
		// We can assume that image bindings are supported here as either the EXT extension or 4.2 core, as otherwise ImageLimits
		// would report that zero image units are supported.
		// RRe36: I'm not sure if its perfectly fine to always have it be layered, but according to Balint its *probably* fine. Still might need to verify that though.
		IrisRenderSystem.bindImageTexture(imageUnit, textureID.getAsInt(), 0, true, 0, GL15.GL_READ_WRITE, internalFormat);
	}
}
