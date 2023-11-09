package net.pixeldream.valormobs.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;

public abstract class AbstractStrongEntity extends AbstractValorEntity {
    protected AbstractStrongEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.xpReward = Enemy.XP_REWARD_LARGE;
    }
}
