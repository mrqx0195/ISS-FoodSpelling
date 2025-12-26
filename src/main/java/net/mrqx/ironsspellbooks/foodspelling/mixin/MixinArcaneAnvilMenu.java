package net.mrqx.ironsspellbooks.foodspelling.mixin;

import io.redspace.ironsspellbooks.gui.arcane_anvil.ArcaneAnvilMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ArcaneAnvilMenu.class)
public class MixinArcaneAnvilMenu {
    @Inject(method = "createResult()V", at = @At(
            value = "INVOKE",
            target = "Lio/redspace/ironsspellbooks/api/spells/ISpellContainer;getOrCreate(Lnet/minecraft/world/item/ItemStack;)Lio/redspace/ironsspellbooks/api/spells/ISpellContainer;",
            remap = false
    ), locals = LocalCapture.CAPTURE_FAILHARD)
    private void injectCreateResult(CallbackInfo ci, ItemStack result) {
        result.setCount(1);
    }
}
