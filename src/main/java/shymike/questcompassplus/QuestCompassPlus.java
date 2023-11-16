package shymike.questcompassplus;

import shymike.questcompassplus.commands.CommandRegister;
import shymike.questcompassplus.utils.RenderUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class QuestCompassPlus implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("quest-compass-plus");

    @Override
    public void onInitializeClient() {
        LOGGER.info("QuestCompassPlus Loading!");
        
        HudRenderCallback.EVENT.register(new RenderUtils());
        CommandRegister.run();
    }
}
