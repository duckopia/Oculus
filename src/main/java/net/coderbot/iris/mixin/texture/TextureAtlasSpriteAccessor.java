package net.coderbot.iris.mixin.texture;

import net.minecraft.client.resources.data.AnimationMetadataSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import com.mojang.blaze3d.platform.NativeImage;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.metadata.animation.AnimationMetadataSection;

@Mixin(TextureAtlasSprite.class)
public interface TextureAtlasSpriteAccessor {
	@Accessor("animationMetadata")
	AnimationMetadataSection getMetadata();

	@Accessor("mainImage")
	NativeImage[] getMainImage();

	@Accessor("x")
	int getX();

	@Accessor("y")
	int getY();

	@Accessor("frame")
	int getFrame();

	@Accessor("frame")
	void setFrame(int frame);

	@Accessor("subFrame")
	int getSubFrame();

	@Accessor("subFrame")
	void setSubFrame(int subFrame);

	@Invoker("upload")
	void callUpload(int frameIndex);
}
