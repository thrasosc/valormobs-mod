package net.pixeldream.valormobs.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.pixeldream.valormobs.screen.skullhaven.SkullHavenScreen1;

public class SkullHavenSpiritEntity extends AbstractSpiritEntity {

    public SkullHavenSpiritEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (level().isClientSide) {
            Minecraft.getInstance().setScreen(new SkullHavenScreen1());
        }
        return super.mobInteract(player, hand);
    }
}