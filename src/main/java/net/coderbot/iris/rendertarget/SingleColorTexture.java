package net.coderbot.iris.rendertarget;

import java.nio.ByteBuffer;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import net.coderbot.iris.gl.GlResource;
import net.coderbot.iris.gl.IrisRenderSystem;
import net.coderbot.iris.gl.texture.TextureUploadHelper;

public class SingleColorTexture extends GlResource {
	public SingleColorTexture(int red, int green, int blue, int alpha) {
		super(IrisRenderSystem.createTexture(GL11.GL_TEXTURE_2D));
		ByteBuffer pixel = BufferUtils.createByteBuffer(4);
		pixel.put((byte) red);
		pixel.put((byte) green);
		pixel.put((byte) blue);
		pixel.put((byte) alpha);
		pixel.position(0);

		int texture = getGlId();

		IrisRenderSystem.texParameteri(texture, GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		IrisRenderSystem.texParameteri(texture, GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		IrisRenderSystem.texParameteri(texture, GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		IrisRenderSystem.texParameteri(texture, GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);

		TextureUploadHelper.resetTextureUploadState();
		IrisRenderSystem.texImage2D(texture, GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, 1, 1, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, pixel);
	}

	public int getTextureId() {
		return getGlId();
	}

	@Override
	protected void destroyInternal() {
		GlStateManager.deleteTexture(getGlId());
	}
}
