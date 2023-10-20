package net.pixeldream.valormobs.entity;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.pixeldream.valormobs.screen.skullhaven.SkullHavenScreen1;

public class SkullHavenSpiritEntity extends AbstractSpiritEntity {

    public SkullHavenSpiritEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (getWorld().isClient) {
            MinecraftClient.getInstance().setScreen(new SkullHavenScreen1());
        }
        return super.interactMob(player, hand);
    }
}