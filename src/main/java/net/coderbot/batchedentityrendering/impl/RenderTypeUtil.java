package net.coderbot.batchedentityrendering.impl;

import net.minecraft.client.renderer.RenderType;
import org.lwjgl.opengl.GL11;

public class RenderTypeUtil {
    public static boolean isTriangleStripDrawMode(RenderType renderType) {
        return renderType.mode() == GL11.GL_TRIANGLE_STRIP;
    }
}
