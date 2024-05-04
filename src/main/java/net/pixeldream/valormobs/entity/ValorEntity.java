package net.pixeldream.valormobs.entity;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import mod.azure.azurelib.animatable.GeoEntity;
import mod.azure.azurelib.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelib.core.animatable.instance.SingletonAnimatableInstanceCache;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FloatToSurfaceOfFluid;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.custom.UnreachableTargetSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;

import java.util.List;

public abstract class ValorEntity extends Monster implements GeoEntity, SmartBrainOwner<ValorEntity> {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(ValorEntity.class, EntityDataSerializers.INT);

    protected ValorEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public int getAttckingState() {
        return entityData.get(STATE);
    }

    public void setAttackingState(int time) {
        entityData.set(STATE, time);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(STATE, 0);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setAttackingState(compound.getInt("state"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("state", getAttckingState());
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected void customServerAiStep() {
        tickBrain(this);
    }

//    Sensors are the 'senses' of your entity. They're the continually-running tasks that pick up external input and save it into the brain of the entity, for use in behaviours.
    @Override
    public List<ExtendedSensor<ValorEntity>> getSensors() {
        return ObjectArrayList.of(
                new NearbyLivingEntitySensor<>(),
                new HurtBySensor<>(),
                new UnreachableTargetSensor<>()
        );
    }

    @Override
    public BrainActivityGroup<ValorEntity> getCoreTasks() { // These are the tasks that run all the time (usually)
        return BrainActivityGroup.coreTasks(
                // Float in fluid so as not to drown
                new FloatToSurfaceOfFluid<>(),
                // Have the entity turn to face and look at its current look target
                new LookAtTarget<>(),
                // Walk towards the current walk target
                new MoveToWalkTarget<>()
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
                        new SetRandomWalkTarget<>().setRadius(20).speedModifier(0.25f),
                        // Do nothing for 30-100 ticks
                        new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 100))
                )
        );
    }

    @Override
    public BrainActivityGroup<ValorEntity> getFightTasks() { // These are the tasks that handle fighting
        return BrainActivityGroup.fightTasks(
                // Cancel fighting if the target is no longer valid
                new InvalidateAttackTarget<>(),
                // Set the walk target to the attack target
                new SetWalkTargetToAttackTarget<>().speedMod((owner, target) -> 0.55f),
                // Melee attack the target if close enough
                new AnimatableMeleeAttack<>(10)
        );
    }

    @Override
    protected Brain.Provider<?> brainProvider() {
        return new SmartBrainProvider<>(this);
    }

    protected void produceParticles(ParticleOptions parameters) {
        for(int i = 0; i < 5; ++i) {
            double d = this.random.nextGaussian() * 0.02;
            double e = this.random.nextGaussian() * 0.02;
            double f = this.random.nextGaussian() * 0.02;
            this.level().addParticle(parameters, this.getRandomX(1.0), this.getRandomY() + 1.0, this.getRandomZ(1.0), d, e, f);
        }
    }
}