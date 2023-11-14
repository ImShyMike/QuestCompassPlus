package shymike.questcompassplus.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.PlayerSpawnPositionS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayNetworkHandler.class)
public class PlayerSpawnPositionMixin {

    @Inject(method = "onPlayerSpawnPosition", at = @At("RETURN"))
    private void onPlayerSpawnPosition(PlayerSpawnPositionS2CPacket packet, CallbackInfo ci) {
	BlockPos pos = packet.getPos();
        double x = pos.getX();
        double y = 100;
        double z = pos.getZ();
        
        // Print to console
        System.out.println("Compass Position: x=" + x + ", y=" + y + ", z=" + z);
        
        // Print to in-game chat
        MinecraftClient mc = MinecraftClient.getInstance();
        mc.inGameHud.getChatHud().addMessage(Text.literal("Compass Position: x=" + x + ", y=" + y + ", z=" + z));
    }
}