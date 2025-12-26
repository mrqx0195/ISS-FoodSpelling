package net.mrqx.ironsspellbooks.foodspelling.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import io.redspace.ironsspellbooks.gui.arcane_anvil.ArcaneAnvilMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArcaneAnvilMenu.class)
public class MixinArcaneAnvilMenu {
    @Inject(method = "createResult()V", at = @At(
            value = "INVOKE",
            target = "Lio/redspace/ironsspellbooks/api/spells/ISpellContainer;getOrCreate(Lnet/minecraft/world/item/ItemStack;)Lio/redspace/ironsspellbooks/api/spells/ISpellContainer;",
            remap = false
    ))
    private void injectCreateResult(CallbackInfo ci, @Local(name = "result") ItemStack result) {
        result.setCount(1);
    }
}
