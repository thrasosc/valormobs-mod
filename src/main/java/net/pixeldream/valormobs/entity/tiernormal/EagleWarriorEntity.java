package net.pixeldream.valormobs.entity.tiernormal;

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
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.pixeldream.valormobs.entity.EasyEnemy;
import net.pixeldream.valormobs.entity.ValorEntity;
import net.pixeldream.valormobs.entity.ai.FlyControl;
import net.pixeldream.valormobs.entity.constant.DefaultAnimations;
import net.pixeldream.valormobs.entity.task.CustomMeleeAttack;
import net.pixeldream.valormobs.entity.tierhard.ExecutionerEntity;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomFlyingTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;

public class EagleWarriorEntity extends EasyEnemy {

    public EagleWarriorEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        moveControl = new FlyControl(this);
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .add(Attributes.ATTACK_KNOCKBACK, 1);
    }

    //TODO: Implement flying behaviour, nothing works now.
    @Override
    public BrainActivityGroup<ValorEntity> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<ExecutionerEntity>(
                        new TargetOrRetaliate<>().attackablePredicate(entity -> entity.isAlive() && !(entity instanceof ValorEntity) && (!(entity instanceof Player player) || !player.isCreative())),
                        new SetPlayerLookTarget<>(),
                        new SetRandomLookTarget<>()),
                new OneRandomBehaviour<>(
                        new SetRandomFlyingTarget<>().speedModifier(0.25f),
                        new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 100))));
    }

    @Override
    public BrainActivityGroup<ValorEntity> getCoreTasks() { // These are the tasks that run all the time (usually)
        return BrainActivityGroup.coreTasks(
                new LookAtTarget<>(),   // Have the entity turn to face and look at its current look target
                new MoveToWalkTarget<>());  // Walk towards the current walk target
    }

    @Override
    public BrainActivityGroup<ValorEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>(),
                new SetWalkTargetToAttackTarget<>().speedMod((owner, target) -> 0.45f),
                new CustomMeleeAttack<>(10));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "livingController", 3, event -> {
            if (event.isMoving() && !swinging) {
                if (isAggressive()) {
                    return event.setAndContinue(DefaultAnimations.RUN);
                }
                return event.setAndContinue(DefaultAnimations.WALK);
            }
            return event.setAndContinue(DefaultAnimations.IDLE);
        })).add(new AnimationController<>(this, "attackController", 3, event -> {
            swinging = false;
            return PlayState.STOP;
        }).triggerableAnim("melee", DefaultAnimations.ATTACK));
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
