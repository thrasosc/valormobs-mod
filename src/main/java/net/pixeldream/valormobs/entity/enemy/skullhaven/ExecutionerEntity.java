package net.pixeldream.valormobs.entity.enemy.skullhaven;

import mod.azure.azurelib.ai.pathing.AzureNavigation;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
import net.pixeldream.valormobs.entity.AbstractStrongEntity;

public class ExecutionerEntity extends AbstractStrongEntity {
    public ExecutionerEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        navigation = new AzureNavigation(this, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 80)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 6);
    }
}
