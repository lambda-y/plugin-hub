package com.example.renameitem;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RenameIngameItemPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(RenameIngameItemPlugin.class);
		RuneLite.main(args);
	}
}