package net.pixeldream.valormobs.entity.ai;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;
import net.pixeldream.valormobs.entity.ValorEntity;

public class FloatControl extends MoveControl {
    private final ValorEntity valorEntity;
    private int courseChangeCooldown;

    public FloatControl(ValorEntity valorEntity) {
        super(valorEntity);
        this.valorEntity = valorEntity;
    }

    @Override
    public void tick() {
        if (operation == Operation.MOVE_TO && courseChangeCooldown-- <= 0) {
            courseChangeCooldown += valorEntity.getRandom().nextInt(5) + 2;
            var vector3d = new Vec3(wantedX - valorEntity.getX(), wantedY - valorEntity.getY(), wantedZ - valorEntity.getZ());
            final var d0 = vector3d.length();
            vector3d = vector3d.normalize();
            if (canReach(vector3d, Mth.ceil(d0)))
                valorEntity.setDeltaMovement(valorEntity.getDeltaMovement().add(vector3d.scale(0.1D)));
            else operation = Operation.WAIT;
        }
    }

    private boolean canReach(Vec3 vec3, int number) {
        var axisalignedbb = valorEntity.getBoundingBox();

        for (var i = 1; i < number; ++i) {
            axisalignedbb = axisalignedbb.move(vec3);
            if (!valorEntity.level().noCollision(valorEntity, axisalignedbb)) return false;
        }

        return true;
    }
}