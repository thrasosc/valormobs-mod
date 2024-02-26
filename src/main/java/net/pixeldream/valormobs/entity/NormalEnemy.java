package net.pixeldream.valormobs.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public abstract class NormalEnemy extends ValorEntity {
    public NormalEnemy(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = Enemy.XP_REWARD_MEDIUM;
    }
}
