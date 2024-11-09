package net.pixeldream.valormobs.entity.tierspirit;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.pixeldream.valormobs.entity.SpiritualEntity;
import org.ladysnake.blabber.Blabber;
import org.ladysnake.blabber.impl.client.BlabberClient;
import org.ladysnake.blabber.impl.common.settings.BlabberSetting;

import static net.pixeldream.valormobs.ValorMobs.MOD_ID;

public class PriestEntity extends SpiritualEntity {

    public PriestEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        super.tick();
        // check if finished dialogue then despawn entity
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!level().isClientSide()) {
            ServerPlayer serverPlayer = player.getServer().getPlayerList().getPlayer(player.getUUID());
            Blabber.startDialogue(serverPlayer, new ResourceLocation(MOD_ID, "intro"), this);
            this.kill();
        }
        return super.mobInteract(player, hand);
    }
}