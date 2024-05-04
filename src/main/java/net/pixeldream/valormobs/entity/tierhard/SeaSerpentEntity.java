package net.pixeldream.valormobs.entity.tierhard;

import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.object.PlayState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.pixeldream.valormobs.entity.HardEnemy;
import net.pixeldream.valormobs.entity.ValorEntity;
import net.pixeldream.valormobs.entity.constant.DefaultAnimations;
import net.pixeldream.valormobs.entity.task.CustomMeleeAttack;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomSwimTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.navigation.SmoothGroundNavigation;

public class SeaSerpentEntity extends HardEnemy {
    public SeaSerpentEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.navigation = new WaterBoundPathNavigation(this, level);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 80)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 6)
                .add(Attributes.ATTACK_KNOCKBACK, 2)
                .add(Attributes.MOVEMENT_SPEED, 1.2);
    }

    @Override
    public BrainActivityGroup<ValorEntity> getCoreTasks() { // These are the tasks that run all the time (usually)
        return BrainActivityGroup.coreTasks(
                // Have the entity turn to face and look at its current look target
                new LookAtTarget<>(),
                // Walk towards the current walk target
                new MoveToWalkTarget<>()
        );
    }

    @Override
    public BrainActivityGroup<ValorEntity> getFightTasks() { // These are the tasks that handle fighting
        return BrainActivityGroup.fightTasks(
                // Cancel fighting if the target is no longer valid
                new InvalidateAttackTarget<>(),
                // Set the walk target to the attack target
                new SetWalkTargetToAttackTarget<>().speedMod((owner, target) -> 0.9f),
                // Melee attack the target if close enough
                new CustomMeleeAttack<>(10)
        );
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "livingController", 3, event -> {
//            if ((event.isMoving() || isAggressive()) && !swinging) {
//                return event.setAndContinue(DefaultAnimations.WALK);
//            }
            return event.setAndContinue(DefaultAnimations.IDLE);
        })).add(new AnimationController<>(this, "attackController", 3, event -> {
            swinging = false;
            return PlayState.STOP;
        }).triggerableAnim("melee", DefaultAnimations.MELEE));
    }

    @Override
    public BrainActivityGroup<ValorEntity> getIdleTasks() {  // These are the tasks that run when the mob isn't doing anything else (usually)
        // Run only one of the below behaviours, trying each one in order. Include the generic type because JavaC is silly
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<SeaSerpentEntity>(
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
                        new SetRandomSwimTarget<>().setRadius(20).speedModifier(0.25f),
                        // Do nothing for 30-100 ticks
                        new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 100))
                )
        );
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        final var waterpathnavigator = new WaterBoundPathNavigation(this, level);
        waterpathnavigator.setCanFloat(true);
        return waterpathnavigator;
    }

//    @Override
//    protected SoundEvent getHurtSound(DamageSource damageSource) {
//        playSound(SoundEvents.DOLPHIN_HURT, 1.0f, 0.5f);
//        return null;
//    }
//
//    @Override
//    protected SoundEvent getDeathSound() {
//        playSound(SoundEvents.DOLPHIN_DEATH, 1.0f, 0.5f);
//        return null;
//    }
//
//    @Override
//    protected SoundEvent getAmbientSound() {
//        if (this.isInWater()) {
//            playSound(SoundEvents.DOLPHIN_AMBIENT_WATER, 1.0f, 0.5f);
//        } else {
//            playSound(SoundEvents.DOLPHIN_AMBIENT, 1.0f, 0.5f);
//        }
//        return null;
//    }
//
//    @Override
//    protected SoundEvent getSwimSplashSound() {
//        playSound(SoundEvents.DOLPHIN_SPLASH, 1.0f, 0.5f);
//        return null;
//    }
//
//    @Override
//    protected SoundEvent getSwimSound() {
//        playSound(SoundEvents.DOLPHIN_SWIM, 1.0f, 0.5f);
//        return null;
//    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public MobType getMobType() {
        return MobType.WATER;
    }

    protected void handleAirSupply(int i) {
        if (this.isAlive() && !this.isInWaterOrBubble()) {
            this.setAirSupply(i - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(this.damageSources().drown(), 2.0F);
            }
        } else {
            this.setAirSupply(300);
        }
    }

    @Override
    public void baseTick() {
        int i = this.getAirSupply();
        super.baseTick();
        this.handleAirSupply(i);
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return false;
    }
}
