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
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.command.argument.NumberRangeArgumentType;
import net.minecraft.command.argument.NumberRangeArgumentType.IntRangeArgumentType;

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
			    	context.getSource().sendFeedback(Text.literal("/qcp debug - For debugging"));
			 		return 1;
			 	})
		        .build();
		    
		    LiteralCommandNode<FabricClientCommandSource> toggleNode = ClientCommandManager
		    	.literal("toggle")
				.executes(context -> {
					Config.ModEnabledToggle();
					if (!Config.isModEnabled()) { RenderUtils.setText(new String[] {}); }
			    	context.getSource().sendFeedback(Text.literal("Quest Compass Plus is now " + (Config.isModEnabled() ? "enabled" : "disabled")));
			 		return 1;
			 	})
		        .build();
		    
		    LiteralCommandNode<FabricClientCommandSource> getterNode = ClientCommandManager
			    	.literal("get")
					.executes(context -> {
						Vec3d playerPos = mc.player.getPos();
						double distance = Math.round(DistanceCalculator.getDistance(playerPos.x, playerPos.y, playerPos.z, Config.x, Config.y, Config.z));
				    	context.getSource().sendFeedback(Text.literal("Compass Position: x=" + Config.x + ", y=" + Config.y + ", z=" + Config.z + ", d=" + distance));
				 		return 1;
				 	})
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
				    	ServerUtils.bypassMonumentaCheck();
				    	context.getSource().sendFeedback(Text.literal("Bypass is: " + (ServerUtils.isBypassOn() ? "enabled" : "disabled")));
				 		return 1;
				 	})
			        .build();
		    
		    LiteralCommandNode<FabricClientCommandSource> settingsNode = ClientCommandManager
			    	.literal("settings")
					.executes(context -> {
				    	context.getSource().sendFeedback(Text.literal("Settings List: chat_feedback"));
				 		return 1;
				 	})
			        .build();
		    
		    LiteralCommandNode<FabricClientCommandSource> chatFeedbackNode = ClientCommandManager
			    	.literal("chat_feedback")
					.executes(context -> {
						Config.chatFeedback = !Config.chatFeedback;
				    	context.getSource().sendFeedback(Text.literal("Chat feedback is now " + (Config.chatFeedback ? "enabled" : "disabled")));
				 		return 1;
				 	})
			        .build();

		    dispatcher.getRoot().addChild(mainNode);
		    mainNode.addChild(helpNode);
		    mainNode.addChild(toggleNode);
		    mainNode.addChild(getterNode);
		    mainNode.addChild(settingsNode);
		    settingsNode.addChild(chatFeedbackNode);
		    mainNode.addChild(debugNode);
		    debugNode.addChild(forceDebugNode);
		});
	}
}