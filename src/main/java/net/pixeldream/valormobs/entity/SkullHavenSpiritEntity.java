package net.pixeldream.valormobs.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class SkullHavenSpiritEntity extends AbstractSpiritEntity {

    public SkullHavenSpiritEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

//    @Override
//    public ActionResult interactMob(PlayerEntity player, Hand hand) {
//        if (getWorld().isClient) {
//            MinecraftClient.getInstance().setScreen(new SkullHavenScreen1());
//        }
//        return super.interactMob(player, hand);
//    }
}