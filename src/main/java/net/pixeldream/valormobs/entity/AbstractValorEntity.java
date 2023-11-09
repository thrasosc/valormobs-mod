package net.pixeldream.valormobs.entity;

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
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;

public abstract class AbstractValorEntity extends PathfinderMob implements GeoEntity, Enemy {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    public static final EntityDataAccessor<Integer> STATE = SynchedEntityData.defineId(AbstractValorEntity.class, EntityDataSerializers.INT);


    protected AbstractValorEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
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

    protected void produceParticles(ParticleOptions parameters) {
        for(int i = 0; i < 5; ++i) {
            double d = this.random.nextGaussian() * 0.02;
            double e = this.random.nextGaussian() * 0.02;
            double f = this.random.nextGaussian() * 0.02;
            this.level().addParticle(parameters, this.getRandomX(1.0), this.getRandomY() + 1.0, this.getRandomZ(1.0), d, e, f);
        }
    }

    @Override
    public void die(DamageSource damageSource) {
        produceParticles(ParticleTypes.POOF);
        super.die(damageSource);
    }
}