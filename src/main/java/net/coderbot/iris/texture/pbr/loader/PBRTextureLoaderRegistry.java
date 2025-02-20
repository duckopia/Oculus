package net.coderbot.iris.texture.pbr.loader;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureMap;

import javax.annotation.Nullable;

public class PBRTextureLoaderRegistry {
	public static final PBRTextureLoaderRegistry INSTANCE = new PBRTextureLoaderRegistry();

	static {
		INSTANCE.register(SimpleTexture.class, new SimplePBRLoader());
		INSTANCE.register(TextureMap.class, new AtlasPBRLoader());
	}

	private final Map<Class<?>, PBRTextureLoader<?>> loaderMap = new HashMap<>();

	public <T extends AbstractTexture> void register(Class<? extends T> clazz, PBRTextureLoader<T> loader) {
		loaderMap.put(clazz, loader);
	}

	@SuppressWarnings("unchecked")
	@Nullable
	public <T extends AbstractTexture> PBRTextureLoader<T> getLoader(Class<? extends T> clazz) {
		return (PBRTextureLoader<T>) loaderMap.get(clazz);
	}
}
