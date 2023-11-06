package net.pixeldream.valormobs.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class AbstractStrongEntity extends AbstractValorEntity {
    public AbstractStrongEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = Monster.STRONG_MONSTER_XP;
    }
}
