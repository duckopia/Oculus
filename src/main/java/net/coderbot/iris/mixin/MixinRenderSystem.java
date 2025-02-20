package net.coderbot.iris.mixin;

import net.minecraft.client.renderer.OpenGlHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.coderbot.iris.Iris;
import net.coderbot.iris.gl.GLDebug;
import net.coderbot.iris.gl.IrisRenderSystem;

@Mixin(OpenGlHelper.class)
public class MixinRenderSystem {
	@Inject(method = "initializeTextures", at = @At("RETURN"))
	private static void iris$onRendererInit(CallbackInfo ci) {
		GLDebug.initRenderer();
		IrisRenderSystem.initRenderer();
		Iris.onRenderSystemInit();
	}
}
