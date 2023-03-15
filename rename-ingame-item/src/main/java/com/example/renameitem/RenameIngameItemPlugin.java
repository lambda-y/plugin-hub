package com.example.renameitem;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Provides;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;

@Slf4j
@PluginDescriptor(
	name = "rename-ingame-item"
)
public class RenameIngameItemPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private RenameIngameItemConfig config;

	private List<String> itemNames = new ArrayList<>();

	@Override
	protected void startUp() throws Exception
	{
		log.info("rename-ingame-item started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("rename-ingame-item stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "rename-ingame-item says " + config.greeting(), null);
		}
	}

	@Provides
	RenameIngameItemConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RenameIngameItemConfig.class);
	}

	@VisibleForTesting
	List<String> getNamesList()
	{
		final String configItems = config.getItemNamesToReplace();

		if (configItems.isEmpty())
		{
			return Collections.emptyList();
		}

		return Text.fromCSV(configItems);
	}

	void rebuild()
	{
		itemNames = getNamesList();

		client.getItemContainers();
	}
}
