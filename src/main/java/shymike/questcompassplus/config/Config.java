package shymike.questcompassplus.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class Config {
	public static ConfigClassHandler<Config> HANDLER = ConfigClassHandler.createBuilder(Config.class)
			.id(new Identifier("quest-compass-plus", "clientConfig"))
			.serializer(config -> GsonConfigSerializerBuilder.create(config)
					.setPath(FabricLoader.getInstance().getConfigDir().resolve("quest-compass-plus-config.json5"))
					.setJson5(true)
					.build()
			)
			.build();

	@SerialEntry
	public static boolean isModEnabled = true;
	@SerialEntry
	public static boolean chatFeedback = false;
	@SerialEntry
    public static boolean waypointCopy = false;
	@SerialEntry
    public static boolean requireCompass = true;

    public static double x = 0, y = 0, z = 0;
    public static int color = 0;

	static public void toggleIsModEnabled() { isModEnabled = !isModEnabled; onUpdate(); }
    static public void toggleChatFeedback() { chatFeedback = !chatFeedback; onUpdate(); }
	static public void toggleWaypointCopy() { waypointCopy = !waypointCopy; onUpdate(); }
	static public void toggleRequireCompass() { requireCompass = !requireCompass; onUpdate(); }
	static public void setCoordinates(double X, double Y, double Z) { x = X; y = Y; z = Z; onUpdate(); }
	static public void setColor(int value) { color = value; onUpdate(); }

	static private void onUpdate() {
		Config.HANDLER.save();
	}
}