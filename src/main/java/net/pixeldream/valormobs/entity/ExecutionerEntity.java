package net.pixeldream.valormobs.entity;

import mod.azure.azurelib.ai.pathing.AzureNavigation;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.object.PlayState;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pixeldream.valormobs.entity.constant.DefaultAnimations;
import net.pixeldream.valormobs.goal.ExecutionerAttackGoal;

public class ExecutionerEntity extends AbstractStrongEntity {
    private float attackLength = 0;

    public ExecutionerEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        navigation = new AzureNavigation(this, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 80)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 6);
    }

    @Override
    protected void initGoals() {
        goalSelector.add(0, new SwimGoal(this));
        goalSelector.add(1, new ExecutionerAttackGoal(this, 0.55f, true));
        goalSelector.add(2, new WanderAroundFarGoal(this, 0.5f));
        goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        goalSelector.add(4, new LookAroundGoal(this));
        targetSelector.add(1, new ActiveTargetGoal<>(this, CowEntity.class, true));
        targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

        @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 3, state -> {
            if ((state.isMoving() || isAttacking()) && !handSwinging) {
                state.getController().setAnimation(DefaultAnimations.WALK);
                return PlayState.CONTINUE;
            }
            else if (handSwinging) {
                state.getController().setAnimation(DefaultAnimations.ATTACK);
                attackLength++;
                if (attackLength >= 1.2*60) {
                    handSwinging = false;
                    attackLength = 0;
                }
                return PlayState.CONTINUE;
            }
            state.getController().setAnimation(DefaultAnimations.IDLE);
            return PlayState.CONTINUE;
        }));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        playSound(SoundEvents.ENTITY_SKELETON_AMBIENT, 1.0f, 0.5f);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        playSound(SoundEvents.ENTITY_SKELETON_HURT, 1.0f, 0.5f);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        playSound(SoundEvents.ENTITY_SKELETON_DEATH, 1.0f, 0.5f);
        return null;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.ENTITY_SKELETON_STEP, 1.0f, 0.5f);
    }
}
