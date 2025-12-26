package net.mrqx.ironsspellbooks.foodspelling.mixin;

import io.redspace.ironsspellbooks.api.util.Utils;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Utils.class)
public class MixinIssUtils {
    @Inject(method = "canImbue(Lnet/minecraft/world/item/ItemStack;)Z", at = @At("HEAD"), remap = false, cancellable = true)
    private static void injectCanImbue(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        if (itemStack.isEdible()) {
            cir.setReturnValue(true);
        }
    }
}
