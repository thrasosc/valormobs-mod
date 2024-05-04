package net.pixeldream.valormobs.entity.tiernormal;

import mod.azure.azurelib.ai.pathing.AzureNavigation;
import mod.azure.azurelib.core.animation.AnimatableManager;
import mod.azure.azurelib.core.animation.AnimationController;
import mod.azure.azurelib.core.object.PlayState;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.pixeldream.valormobs.entity.NormalEnemy;
import net.pixeldream.valormobs.entity.ValorEntity;
import net.pixeldream.valormobs.entity.constant.DefaultAnimations;
import net.pixeldream.valormobs.entity.task.CustomMeleeAttack;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.ReactToUnreachableTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.navigation.SmoothGroundNavigation;

public class JaguarWarriorEntity extends NormalEnemy {
    public JaguarWarriorEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        navigation = new SmoothGroundNavigation(this, level);
    }

    public static AttributeSupplier.Builder setAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .add(Attributes.ATTACK_KNOCKBACK, 1);
    }

    @Override
    public BrainActivityGroup<ValorEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>(),
                new SetWalkTargetToAttackTarget<>().speedMod((owner, target) -> 0.45f),
                new CustomMeleeAttack<>(10),
                new ReactToUnreachableTarget<>().timeBeforeReacting(entity -> 10).reaction((livingEntity, aBoolean) -> {
                    //                    if (aBoolean) {
                    this.addEffect(new MobEffectInstance(MobEffects.JUMP, 40, 2, false, false), this);
//                            this.setJumping(true);
//                            this.jumpFromGround();
                    this.jumpControl.jump();
                    //                    }
                })
//                new FirstApplicableBehaviour<>(
//                        new ReactToUnreachableTarget<>().timeBeforeReacting(entity -> 10).reaction((livingEntity, aBoolean) -> {
//                            //                    if (aBoolean) {
//                            this.addEffect(new MobEffectInstance(MobEffects.JUMP, 800, 5, false, false), this);
////                            this.setJumping(true);
////                            this.jumpFromGround();
//                            this.jumpControl.jump();
//                            //                    }
//                        })
//                )
        );
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
        playSound(SoundEvents.OCELOT_AMBIENT, 1.0f, 0.1f);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        playSound(SoundEvents.OCELOT_HURT, 1.0f, 0.1f);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        playSound(SoundEvents.OCELOT_DEATH, 1.0f, 0.1f);
        return null;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.WOLF_STEP, 1.0f, 1.0f);
    }
}
