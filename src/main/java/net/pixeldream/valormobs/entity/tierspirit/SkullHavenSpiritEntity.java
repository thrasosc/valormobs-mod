package net.pixeldream.valormobs.entity.tierspirit;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.pixeldream.valormobs.entity.SpiritEntity;
import org.ladysnake.blabber.Blabber;

import static net.pixeldream.valormobs.ValorMobs.MOD_ID;

public class SkullHavenSpiritEntity extends SpiritEntity {

    public SkullHavenSpiritEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!level().isClientSide()) {
            ServerPlayer serverPlayer = player.getServer().getPlayerList().getPlayer(player.getUUID());
            Blabber.startDialogue(serverPlayer, new ResourceLocation(MOD_ID, "basic_dialogue"), this);
        }
//        if (level().isClientSide) {
//            Minecraft.getInstance().setScreen(new SkullHavenScreen1());
//        }
        return super.mobInteract(player, hand);
    }
}