package net.pixeldream.valormobs.entity.tierhard;

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
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.pixeldream.valormobs.entity.HardEnemy;
import net.pixeldream.valormobs.entity.ValorEntity;
import net.pixeldream.valormobs.entity.constant.DefaultAnimations;
import net.pixeldream.valormobs.entity.task.CustomMeleeAttack;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;

import java.util.Random;

public class SetAnimalEntity extends HardEnemy {

    public SetAnimalEntity(EntityType<? extends Monster> entityType, Level level) {
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
    public BrainActivityGroup<ValorEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>(),
                new SetWalkTargetToAttackTarget<>().speedMod((owner, target) -> 0.3f),
                new CustomMeleeAttack<>(15));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "livingController", 3, event -> {
            if (event.isMoving() && !swinging) {
                return event.setAndContinue(DefaultAnimations.WALK);
            }
            return event.setAndContinue(DefaultAnimations.IDLE);
        })).add(new AnimationController<>(this, "attackController", 3, event -> {
            swinging = false;
            return PlayState.STOP;
        }).triggerableAnim("melee", new Random().nextInt(0, 3) == 0 ? DefaultAnimations.ATTACK_2 : DefaultAnimations.ATTACK));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        playSound(SoundEvents.PILLAGER_AMBIENT, 1.0f, 0.1f);
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        playSound(SoundEvents.PILLAGER_HURT, 1.0f, 0.1f);
        return null;
    }

    @Override
    protected SoundEvent getDeathSound() {
        playSound(SoundEvents.PILLAGER_DEATH, 1.0f, 0.1f);
        return null;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        playSound(SoundEvents.RAVAGER_STEP, 1.0f, 2.0f);
    }
}
