package com.example.renameitem;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("renameingameitem")
public interface RenameIngameItemConfig extends Config
{
	@ConfigItem(
		keyName = "greeting",
		name = "Welcome Greeting",
		description = "The message to show to the user when they login"
	)
	default String greeting()
	{
		return "Hello";
	}

	@ConfigItem(
		keyName = "enable",
		name = "enable Name swaps",
		description = "When looking at a item, it is referred to a different name",
		position = 4
	)
	default boolean preferSoonest()
	{
		return false;
	}

	@ConfigItem(
		position = 7,
		keyName = "namesToChange",
		name = "Change name of items",
		description = "List of the RuneScape item name to the preferred name '(RS_name1, Preferred_name1), (RS_name2, Preferred_name2) '"
	)
	default String getItemNamesToReplace()
	{
		return "";
	}
}
