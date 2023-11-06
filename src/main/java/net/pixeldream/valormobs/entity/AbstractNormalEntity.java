package net.pixeldream.valormobs.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;

public class AbstractNormalEntity extends AbstractValorEntity{
    public AbstractNormalEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = Monster.NORMAL_MONSTER_XP;
    }
}
