package shymike.questcompassplus.mixin;

import shymike.questcompassplus.config.Config;
import shymike.questcompassplus.utils.WaypointManager;
import shymike.questcompassplus.utils.ServerUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.PlayerSpawnPositionS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayNetworkHandler.class)
public class PlayerSpawnPositionMixin {
    private double xL = 0, yL = 0, zL = 0;
    private MinecraftClient mc = MinecraftClient.getInstance();
    @Inject(method = "onPlayerSpawnPosition", at = @At("RETURN"))
    private void onPlayerSpawnPosition(PlayerSpawnPositionS2CPacket packet, CallbackInfo ci) {
		BlockPos pos = packet.getPos();
		double x = pos.getX(), y = pos.getY(), z = pos.getZ();
		Config.setCoordinates(x,y,z);
		if (ServerUtils.isOnMonumenta()) {
			// Anti spam
			if (x != xL || y != yL || z != zL) {
			    this.xL = x;
			    this.yL = y;
			    this.zL = z;
			    WaypointManager.create(mc, x, y, z);
			}
		}
    }
}