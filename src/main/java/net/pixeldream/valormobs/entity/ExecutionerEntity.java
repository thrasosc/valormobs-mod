package net.pixeldream.valormobs.entity;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mod.azure.azurelib.ai.pathing.AzureNavigation;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.object.PlayState;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.pixeldream.valormobs.entity.constant.DefaultAnimations;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;

import java.util.List;

public class ExecutionerEntity extends AbstractStrongEntity implements SmartBrainOwner<ExecutionerEntity> {
    private float attackLength = 0;

    public ExecutionerEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        navigation = new AzureNavigation(this, level);
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80)
                .add(Attributes.MOVEMENT_SPEED, 0.5f)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 6)
                .add(Attributes.ATTACK_KNOCKBACK, 6);
    }

//    @Override
//    protected void initGoals() {
//        goalSelector.add(0, new SwimGoal(this));
//        goalSelector.add(1, new ExecutionerAttackGoal(this, 0.55f, true));
//        goalSelector.add(2, new WanderAroundFarGoal(this, 0.5f));
//        goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
//        goalSelector.add(4, new LookAroundGoal(this));
//        targetSelector.add(1, new ActiveTargetGoal<>(this, CowEntity.class, true));
//        targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
//    }

    @Override
    protected Brain.Provider<?> brainProvider() {
        return new SmartBrainProvider<>(this);
    }

    @Override
    protected void customServerAiStep() {
        tickBrain(this);
    }

    @Override
    public List<ExtendedSensor<ExecutionerEntity>> getSensors() {
        return ObjectArrayList.of(
                new NearbyLivingEntitySensor<>(), // This tracks nearby entities
                new HurtBySensor<>()                // This tracks the last damage source and attacker
        );
    }

        @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 3, state -> {
            if ((state.isMoving() || isAggressive()) && !swinging) {
                state.getController().setAnimation(DefaultAnimations.WALK);
                return PlayState.CONTINUE;
            }
            else if (swinging) {
                state.getController().setAnimation(DefaultAnimations.ATTACK);
                attackLength++;
                if (attackLength >= 1.2*60) {
                    swinging = false;
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
        playSound(SoundEvents.SKELETON_AMBIENT, 1.0f, 0.5f);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        playSound(SoundEvents.SKELETON_HURT, 1.0f, 0.5f);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        playSound(SoundEvents.SKELETON_DEATH, 1.0f, 0.5f);
        return null;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.SKELETON_STEP, 1.0f, 0.5f);
    }
}
