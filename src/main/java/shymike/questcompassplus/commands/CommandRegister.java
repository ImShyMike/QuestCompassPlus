package shymike.questcompassplus.commands;

import shymike.questcompassplus.config.Config;
import shymike.questcompassplus.utils.DistanceCalculator;
import shymike.questcompassplus.utils.RenderUtils;
import shymike.questcompassplus.utils.ServerUtils;

import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
//import net.minecraft.command.argument.NumberRangeArgumentType;
//import net.minecraft.command.argument.NumberRangeArgumentType.IntRangeArgumentType;

public class CommandRegister {
	static public void run() {
		MinecraftClient mc = MinecraftClient.getInstance();
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			LiteralCommandNode<FabricClientCommandSource> mainNode = ClientCommandManager
				.literal("qcp")
				.executes(context -> {
			        throw new SimpleCommandExceptionType(Text.literal("Invalid usage. Use \"/qcp help\" for more information.")).create();
			    })
			    .build();
		
		    LiteralCommandNode<FabricClientCommandSource> helpNode = ClientCommandManager
		    	.literal("help")
				.executes(context -> {
			    	context.getSource().sendFeedback(Text.literal("Quest Compass Plus commands:"));
			    	context.getSource().sendFeedback(Text.literal("/qcp help - Display this help message"));
			    	context.getSource().sendFeedback(Text.literal("/qcp toggle - Toggle the mod on/off"));
			    	context.getSource().sendFeedback(Text.literal("/qcp get - Get current quest location"));
			    	context.getSource().sendFeedback(Text.literal("/qcp settings - Change settings"));
			    	context.getSource().sendFeedback(Text.literal("/qcp debug - For debugging"));
			 		return 1;
			 	})
		        .build();
		    
		    LiteralCommandNode<FabricClientCommandSource> toggleNode = ClientCommandManager
		    	.literal("toggle")
				.executes(context -> {
					Config.toggleIsModEnabled();
					if (!Config.isModEnabled) { RenderUtils.line1 = ""; RenderUtils.line2 = ""; } else { RenderUtils.line1 = "Compass Position: " + (int)RenderUtils.x + " " + (int)RenderUtils.y + " " + (int)RenderUtils.z; }
			    	context.getSource().sendFeedback(Text.literal("Quest Compass Plus is now " + (Config.isModEnabled ? "enabled" : "disabled")));
			 		return 1;
			 	})
		        .build();
		    
		    LiteralCommandNode<FabricClientCommandSource> getterNode = ClientCommandManager
			    	.literal("get")
					.executes(context -> {
						Vec3d playerPos = mc.player.getPos();
						double distance = Math.round(DistanceCalculator.getDistance2D(playerPos.x, playerPos.z, Config.x, Config.z));
						if (Config.waypointCopy) {
							context.getSource().sendFeedback(Text.literal("Compass Position: x=" + (int)Config.x + ", y=" + (int)Config.y + ", z=" + (int)Config.z + ", distance=" + (int)distance).styled(style -> style
									.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/jm wpedit [name:\"Quest Location\", x:"+(int)Config.x+", y:"+(int)Config.y+", z:"+(int)Config.z+", dim:minecraft:overworld]"))
									.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Click to make waypoint!")))));
						} else if (Config.chatFeedback) {
							context.getSource().sendFeedback(Text.literal("Compass Position: x=" + (int)Config.x + ", y=" + (int)Config.y + ", z=" + (int)Config.z + ", distance=" + (int)distance).styled(style -> style
					    			.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, (int)Config.x+" "+(int)Config.y+" "+(int)Config.z))
					    			.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("Click to copy coordinates to clipboard!")))));
						}
						return 1;})
			        .build();
		    
		    LiteralCommandNode<FabricClientCommandSource> debugNode = ClientCommandManager
			    	.literal("debug")
					.executes(context -> {
				    	context.getSource().sendFeedback(Text.literal("Is On Monumenta: " + ServerUtils.isOnMonumenta()));
				 		return 1;
				 	})
			        .build();
		    
		    LiteralCommandNode<FabricClientCommandSource> forceDebugNode = ClientCommandManager
			    	.literal("force")
					.executes(context -> {
				    	ServerUtils.bypass = !ServerUtils.bypass;
				    	context.getSource().sendFeedback(Text.literal("Bypass is now " + (ServerUtils.bypass ? "enabled" : "disabled")));
				 		return 1;
				 	})
			        .build();
		    
		    LiteralCommandNode<FabricClientCommandSource> settingsNode = ClientCommandManager
			    	.literal("settings")
					.executes(context -> {
				    	context.getSource().sendFeedback(Text.literal("Settings List: chat_feedback, require_compass, waypoint_create"));
				 		return 1;
				 	})
			        .build();
		    
		    LiteralCommandNode<FabricClientCommandSource> chatFeedbackNode = ClientCommandManager
			    	.literal("chat_feedback")
					.executes(context -> {
						Config.toggleChatFeedback();
				    	context.getSource().sendFeedback(Text.literal("Chat feedback is now " + (Config.chatFeedback ? "enabled" : "disabled")));
				 		return 1;
				 	})
			        .build();
		    
		    LiteralCommandNode<FabricClientCommandSource> requireCompassNode = ClientCommandManager
			    	.literal("require_compass")
					.executes(context -> {
						Config.toggleRequireCompass();
				    	context.getSource().sendFeedback(Text.literal("Compass required is now " + (Config.requireCompass ? "required" : "not required")));
				 		return 1;
				 	})
			        .build();
		    
		    LiteralCommandNode<FabricClientCommandSource> waypointCreateNode = ClientCommandManager
			    	.literal("waypoint_create")
					.executes(context -> {
						Config.toggleWaypointCopy();
				    	context.getSource().sendFeedback(Text.literal("Waypoint create is now " + (Config.waypointCopy ? "enabled" : "disabled")));
				 		return 1;
				 	})
			        .build();

		    dispatcher.getRoot().addChild(mainNode);
			    mainNode.addChild(helpNode);
			    mainNode.addChild(toggleNode);
			    mainNode.addChild(getterNode);
			    mainNode.addChild(settingsNode);
			    	settingsNode.addChild(chatFeedbackNode);
			    	settingsNode.addChild(requireCompassNode);
			    	settingsNode.addChild(waypointCreateNode);
			    mainNode.addChild(debugNode);
			    	debugNode.addChild(forceDebugNode);
		});
	}
}