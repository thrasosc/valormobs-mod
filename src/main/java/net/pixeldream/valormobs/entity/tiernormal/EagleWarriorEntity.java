package net.pixeldream.valormobs.entity.tiernormal;

import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animatable.instance.SingletonAnimatableInstanceCache;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.pixeldream.valormobs.ValorMobs;
import net.pixeldream.valormobs.entity.NormalEnemy;
import net.pixeldream.valormobs.entity.ValorEntity;
import net.pixeldream.valormobs.entity.constant.DefaultAnimations;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomFlyingTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import org.jetbrains.annotations.NotNull;

public class EagleWarriorEntity extends NormalEnemy {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public EagleWarriorEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.navigation = new FlyingPathNavigation(this, level);
    }

//    public EagleWarriorEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
//        super(entityType, level);
////        navigation = new AzureNavigation(this, level);
//        navigation = new FlyingPathNavigation(this, level);
//        moveControl = new FlyControl(this);
//    }

    public static AttributeSupplier.Builder setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .add(Attributes.ATTACK_KNOCKBACK, 1)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.FLYING_SPEED, 0.6);
    }

    @Override
    public BrainActivityGroup<ValorEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>(),
                new SetWalkTargetToAttackTarget<>().speedMod((owner, target) -> 1.2f),
                new AnimatableMeleeAttack<>(5)
        );
    }

    @Override
    public BrainActivityGroup<ValorEntity> getIdleTasks() {  // These are the tasks that run when the mob isn't doing anything else (usually)
        // Run only one of the below behaviours, trying each one in order. Include the generic type because JavaC is silly
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<ValorEntity>(
                        // Set the attack target and walk target based on nearby entities
                        new TargetOrRetaliate<>().alertAlliesWhen((mob, entity) -> this.isAggressive()).attackablePredicate(
                                entity -> entity.isAlive() && (!(entity instanceof Player player) || !player.isCreative()) && !(entity instanceof ValorEntity)
                        ),
                        // Set the look target for the nearest player
                        new SetPlayerLookTarget<>(),
                        // Set a random look target
                        new SetRandomLookTarget<>()
                ),
                // Run a random task from the below options
                new OneRandomBehaviour<>(
                        // Set a random walk target to a nearby position
                        new SetRandomFlyingTarget<>().setRadius(20).speedModifier(0.25f),
                        // Do nothing for 30-100 ticks
                        new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 100))
                )
        );
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level worldIn) {
        final var flyingpathnavigator = new FlyingPathNavigation(this, worldIn);
        flyingpathnavigator.setCanOpenDoors(false);
        flyingpathnavigator.setCanFloat(true);
        flyingpathnavigator.setCanPassDoors(true);
        return flyingpathnavigator;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, @NotNull BlockState state, @NotNull BlockPos pos) {

    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "livingController", 3, event -> {
//            if (this.getDeltaMovement().y > 0) {
//                return event.setAndContinue(DefaultAnimations.FLY);
//            }
//            return event.setAndContinue(DefaultAnimations.IDLE);
            return event.setAndContinue(DefaultAnimations.FLY);
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        playSound(SoundEvents.PARROT_AMBIENT, 1.0f, 0.4f);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        playSound(SoundEvents.PARROT_HURT, 1.0f, 0.4f);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        playSound(SoundEvents.PARROT_DEATH, 1.0f, 0.4f);
        return null;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.PARROT_FLY, 1.0f, 0.5f);
    }
}
