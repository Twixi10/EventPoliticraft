package fr.politicraft.event.Commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.politicraft.event.Main;
import fr.politicraft.event.Builder.AniPoli;

public class mainCommand implements CommandExecutor {
	
	private HashMap<Player, AniPoli> confirmCommand = new HashMap<>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0) {

		} else if(args.length >= 1 && args[0].equals("create")) {
			if(args.length == 2) {
				AniPoli ap = new AniPoli(args[1].replace("&", "§"), ((Player)sender));
				if(Main.getCurentAnim() == null) {
					Main.setCurentAnim(ap);
					sender.sendMessage(Main.getMessage("messages.createAnim").replace("%name%", ap.getName()));
				} else {
					sender.sendMessage(Main.getMessage("messages.confirm").replace("%command%", "/event create " + args[1]));
					confirmCommand.put((Player) sender, ap);
				}
			} else if(args.length > 3) {
				String name = "";
				for (int i = 1; i < args.length; i++) {
					name += args[i] + " ";
				}
				AniPoli ap = new AniPoli(name.replace("&", "§"), ((Player)sender));
				if(Main.getCurentAnim() == null) {
					Main.setCurentAnim(ap);
					sender.sendMessage(Main.getMessage("messages.createAnim").replace("%name%", ap.getName()));
				} else {
					sender.sendMessage(Main.getMessage("messages.confirm").replace("%command%", "/event create " + name));
					confirmCommand.put((Player) sender, ap);
					
				}
			}
		} else if(args.length >= 1 && args[0].equals("confirm")) {
			if(confirmCommand.get((Player) sender) != null) {
				Main.setCurentAnim(confirmCommand.get((Player) sender));
				sender.sendMessage(Main.getMessage("messages.createAnim").replace("%name%", confirmCommand.get((Player) sender).getName()));
				confirmCommand.remove((Player) sender);
			} else {
				sender.sendMessage(Main.getMessage("messages.NothingToConfirm"));
			}
		
		} else if(args.length >= 1 && args[0].equals("config")) {
			if(Main.getCurentAnim() != null) {
				Main.getCurentAnim().openGuiConfiguration((Player) sender);
			} else {
				sender.sendMessage(Main.getMessage("messages.OpenWhenEventIsNull"));
			}
		}
		return false;
	}		

}
