package fr.politicraft.event;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import fr.politicraft.event.Builder.AniPoli;
import fr.politicraft.event.Commands.mainCommand;
import fr.politicraft.event.Listeners.ClickEventConfigGUI;

public class Main extends JavaPlugin {
	
	public static String logo;
	private static FileConfiguration messages;
	private static FileConfiguration configs;
	
	private static Main instance;
	private static AniPoli currentAnim;
	
	public static Main getInstance() {
		return instance;
	}
	
	public FileConfiguration getConfig() {
		return configs;
	}
	
	public static FileConfiguration getMessages() {
		return messages;
	}
	
	public static AniPoli getCurentAnim() {
		return currentAnim;
	}
	
	public static void setCurentAnim(AniPoli ap) {
		currentAnim = ap;
	}
	
	
	
	@Override
	public void onEnable() {
		if(logo == null) {logo = "[" + getName() + "]";}

		initConfigMessage();
		initConfig();
		loadLogo();
		
		instance = this;
		
		this.getCommand("event").setExecutor((CommandExecutor)new mainCommand());
		this.getServer().getPluginManager().registerEvents((Listener)new ClickEventConfigGUI(), this);
		
	}
	
	@Override
	public void onDisable() {

	}
	

	
	
	private void loadLogo() {
		logo = configs.getString("logo").replace("&", "§");
		if(logo == null) {
			logo = getName();
		}
	}
	
	private void initConfigMessage() {
		System.out.println(logo + " §aChargement du fichier §emessages.yml§a en cours ...");
		File message =  new File("plugins/" + getName() + "/messages.yml");
		if (message.exists()) {
			System.out.println(logo + " §aLe fichier §emessages.yml§a a été chargé avec succès !");
			try {
				messages = new YamlConfiguration();
				messages.load(message);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			
		} else {
			System.out.println(" ");
			System.out.println(logo + " §cLe fichier §emessages.yml§c n'existe pas !");
			System.out.println(logo + " §6Création en cours !");
			System.out.println(" ");
			message.getParentFile().mkdir();
			saveResource("messages.yml", false);
			messages = new YamlConfiguration();
			try {
				messages.load(message);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			if (message.exists()) {
				System.out.println(logo + " §aLe fichier §emessages.yml§a a été chargé avec succès !");
			} else {
				System.out.println(logo + " §4ERREUR ! Le fichier §emessages.yml n'arrive pas a se créer ...");
				System.out.println(logo + " §4ERREUR ! Le plugin se désactive ...");
				System.out.println(logo + " §4ERREUR ! Contactez le développeur du plugin pour plus d'information ...");
				Bukkit.getPluginManager().disablePlugins();
			}
		}
	}
	
	private void initConfig() {
		System.out.println(logo + " §aChargement du fichier §econfig.yml§a en cours ...");
		File config =  new File("plugins/" + getName() + "/config.yml");
		if (config.exists()) {
			System.out.println(logo + " §aLe fichier §econfig.yml §aa été chargé avec succèss !");
			try {
				configs = new YamlConfiguration();
				configs.load(config);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			
		} else {
			System.out.println(" ");
			System.out.println(logo + " §cLe fichier §econfig.yml§c n'existe pas !");
			System.out.println(logo + " §6Création en cours !");
			System.out.println(" ");
			config.getParentFile().mkdir();
			saveResource("config.yml", false);
			configs = new YamlConfiguration();
			try {
				configs.load(config);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
			if (config.exists()) {
				System.out.println(logo + " §aLe fichier §econfig.yml§a a été chargé avec succès !");
			} else {
				System.out.println(logo + " §4ERREUR ! Le fichier §econfig.yml n'arrive pas a se créer ...");
				System.out.println(logo + " §4ERREUR ! Le plugin se désactive ...");
				System.out.println(logo + " §4ERREUR ! Contactez le développeur du plugin pour plus d'information ...");
				Bukkit.getPluginManager().disablePlugins();
			}
		}
	}

	public static String getMessage(String s) {
		return messages.getString(s).replace("&", "§").replace("%logo%", logo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
