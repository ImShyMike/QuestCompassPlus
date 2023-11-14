package shymike.questcompassplus;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuestCompassPlus implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("quest-compass-plus");

	@Override
	public void onInitialize() {
		LOGGER.info("QuestCompassPlus Loading!");
	}
}