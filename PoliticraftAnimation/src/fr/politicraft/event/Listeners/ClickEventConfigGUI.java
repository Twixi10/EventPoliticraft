package fr.politicraft.event.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.politicraft.event.Main;
import fr.politicraft.event.Builder.AniPoli;

public class ClickEventConfigGUI implements Listener {
	
	@EventHandler
	public static void onClick(InventoryClickEvent e) {
		if(e.getWhoClicked() != null) {
			if(e.getCurrentItem() != null) {
				if(e.getView().getTitle().contains(Main.getMessage("gui.configuration.title").replace("%title%", Main.getCurentAnim().getName()))) {
					e.setCancelled(true);
					AniPoli ap = Main.getCurentAnim();
					if(e.getSlot() == 10) {
						if(ap.isOpen()) {
							ap.setOpen(false);
						} else {
							ap.setOpen(true);
						}
					} else if(e.getSlot() == 11) {
						if(ap.haveBroadcastAnnonces()) {
							ap.setBroadcastAnnonces(false);
						} else {
							ap.setBroadcastAnnonces(true);
						}
					} else if(e.getSlot() == 12) {
						if(e.getClick().equals(ClickType.RIGHT)) {
							if(ap.haveConsolationLot()) {
								ap.setConsolationLot(false);
							} else {
								ap.setConsolationLot(true);
							}
						} else if(e.getClick().equals(ClickType.LEFT)) {
							ap.GUI("#1consolation_lot", (Player)e.getWhoClicked());
						}
					}
					ap.reloadConfigGui();
					
				}
			}
		}
	}

}
