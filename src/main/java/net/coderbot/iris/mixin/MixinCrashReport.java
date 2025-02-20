package net.coderbot.iris.mixin;

import net.coderbot.iris.Iris;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Adds the current shaderpack and number of changed options to crash reports
 */
@Mixin(CrashReport.class)
public abstract class MixinCrashReport {
    @Shadow
    public abstract CrashReportCategory getCategory();

    @Inject(at = @At("RETURN"), method = "populateEnvironment")
    private void fillSystemDetails(CallbackInfo info) {
        if (Iris.getCurrentPackName() == null) return; // this also gets called at startup for some reason

        getCategory().addDetail("Loaded Shaderpack", () -> {
            StringBuilder sb = new StringBuilder(Iris.getCurrentPackName() + (Iris.isFallback() ? " (fallback)" : ""));
            Iris.getCurrentPack().ifPresent(pack -> {
                sb.append("\n\t\t");
                sb.append(pack.getProfileInfo());
            });
            return sb.toString();
        });
    }
}
