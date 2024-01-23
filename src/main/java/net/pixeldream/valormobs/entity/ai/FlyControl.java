package net.pixeldream.valormobs.entity.ai;

import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;
import net.pixeldream.valormobs.entity.ValorEntity;

public class FlyControl extends MoveControl {
    protected final ValorEntity valorEntity;
    private int courseChangeCooldown;

    public FlyControl(ValorEntity valorEntity) {
        super(valorEntity);
        this.valorEntity = valorEntity;
    }

    @Override
    public void tick() {
        if (valorEntity.isAggressive()) {
            if (operation == Operation.MOVE_TO) {
                if (courseChangeCooldown-- <= 0) {
                    courseChangeCooldown += valorEntity.getRandom().nextInt(5) + 2;
                    var vector3d = new Vec3(wantedX - valorEntity.getX(), wantedY - valorEntity.getY(), wantedZ - valorEntity.getZ());
                    final var d0 = vector3d.length();
                    vector3d = vector3d.normalize();
                    if (canReach(vector3d, Mth.ceil(d0)))
                        valorEntity.setDeltaMovement(valorEntity.getDeltaMovement().add(vector3d.scale(0.1D)));
                    else operation = Operation.WAIT;
                }
            } else {
                operation = Operation.WAIT;
                valorEntity.setZza(0.0F);
            }
        } else if (operation == Operation.MOVE_TO) {
            operation = Operation.WAIT;
            final var d0 = wantedX - valorEntity.getX();
            final var d1 = wantedZ - valorEntity.getZ();
            final var d2 = wantedY - valorEntity.getY();
            final var d3 = d0 * d0 + d2 * d2 + d1 * d1;
            if (d3 < 2.5000003E-7F) {
                valorEntity.setZza(0.0F);
                return;
            }
            final var f9 = (float) (Mth.atan2(d1, d0) * (180F / (float) Math.PI)) - 90.0F;
            valorEntity.setYRot(rotlerp(mob.getYRot(), f9, 90.0F));
            valorEntity.setSpeed((float) 0.25D);
            final var blockpos = mob.blockPosition();
            final var blockstate = mob.level().getBlockState(blockpos);
            final var voxelshape = blockstate.getCollisionShape(mob.level(), blockpos);
            if (d2 > mob.getEyeHeight() && d0 * d0 + d1 * d1 < Math.max(1.0F,
                    mob.getBbWidth()) || !voxelshape.isEmpty() && mob.getY() < voxelshape.max(
                    Direction.Axis.Y) + blockpos.getY() && !blockstate.is(BlockTags.DOORS) && !blockstate.is(
                    BlockTags.FENCES))
                operation = Operation.JUMPING;
        } else if (operation == Operation.JUMPING) {
            mob.setSpeed((float) 0.25D);
            if (mob.onGround()) operation = Operation.WAIT;
        } else {
            operation = Operation.WAIT;
            valorEntity.setZza(0.0F);
        }
    }

    private boolean canReach(Vec3 direction, int steps) {
        var axisalignedbb = mob.getBoundingBox();
        for (var i = 1; i < steps; ++i) {
            axisalignedbb = axisalignedbb.move(direction);
            if (!mob.level().noCollision(valorEntity, axisalignedbb)) return false;
        }
        return true;
    }
}