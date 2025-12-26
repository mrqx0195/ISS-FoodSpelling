package net.mrqx.ironsspellbooks.foodspelling.event;

import io.redspace.ironsspellbooks.api.magic.SpellSelectionManager;
import io.redspace.ironsspellbooks.api.registry.SpellRegistry;
import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import io.redspace.ironsspellbooks.api.spells.SpellData;
import io.redspace.ironsspellbooks.player.ClientMagicData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FoodEventHandler {
    @SubscribeEvent
    public static void onLivingEntityUseItemEventFinish(LivingEntityUseItemEvent.Finish event) {
        ItemStack item = event.getItem();
        if (item.isEdible() && event.getEntity() instanceof Player player) {
            ISpellContainer spellContainer = ISpellContainer.getOrCreate(item);
            SpellData scrollSlot = spellContainer.getSpellAtIndex(0);
            AbstractSpell spell = scrollSlot.getSpell();
            if (!spell.equals(SpellRegistry.none())) {
                if (player.level().isClientSide) {
                    if (!ClientMagicData.isCasting()) {
                        ClientMagicData.getSyncedSpellData(player).isSpellLearned(spell);
                    }
                } else {
                    String castingSlot = SpellSelectionManager.MAINHAND;
                    spell.attemptInitiateCast(item, spell.getLevelFor(scrollSlot.getLevel(), player), player.level(), player, CastSource.SCROLL, false, castingSlot);
                }
            }
        }
    }
}
