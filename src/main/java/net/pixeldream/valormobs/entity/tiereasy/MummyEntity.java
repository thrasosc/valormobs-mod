package net.pixeldream.valormobs.entity.tiereasy;

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
import net.pixeldream.valormobs.entity.constant.DefaultAnimations;
import net.pixeldream.valormobs.entity.tierhard.ExecutionerEntity;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;

public class MummyEntity extends EasyEnemy {

    public MummyEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        navigation = new AzureNavigation(this, level);
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .add(Attributes.ATTACK_KNOCKBACK, 1);
    }

    @Override
    public BrainActivityGroup<ValorEntity> getIdleTasks() { // These are the tasks that run when the mob isn't doing anything else (usually)
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<ExecutionerEntity>(    // Run only one of the below behaviours, trying each one in order. Include the generic type because JavaC is silly
                        new TargetOrRetaliate<>().attackablePredicate(entity -> entity.isAlive() && !(entity instanceof ValorEntity) && (!(entity instanceof Player player) || !player.isCreative())),  // Set the attack target and walk target based on nearby entities
                        new SetPlayerLookTarget<>(),    // Set the look target for the nearest player
                        new SetRandomLookTarget<>()),   // Set a random look target
                new OneRandomBehaviour<>(   // Run a random task from the below options
                        new SetRandomWalkTarget<>().speedModifier(0.5f),    // Set a random walk target to a nearby position
                        new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 100))));   // Do nothing for 1.5->3 seconds
    }

    @Override
    public BrainActivityGroup<ValorEntity> getFightTasks() {    // These are the tasks that handle fighting
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>(), // Cancel fighting if the target is no longer valid
                new SetWalkTargetToAttackTarget<>().speedMod(0.75f), // Set the walk target to the attack target
                new AnimatableMeleeAttack<>(20));   // Melee attack the target if close enough
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
