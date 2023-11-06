package net.pixeldream.valormobs.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class AbstractSmallEntity extends AbstractValorEntity {
    public AbstractSmallEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = Monster.SMALL_MONSTER_XP;
    }
}
