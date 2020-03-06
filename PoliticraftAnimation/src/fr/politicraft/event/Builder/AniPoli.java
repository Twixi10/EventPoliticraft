package fr.politicraft.event.Builder;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.politicraft.event.Main;

public class AniPoli {
	
	private String name;
	private Player owner;
	private Inventory configGUI;
	private boolean isConsolationLot;
	private int consolationLot;
	private boolean isOpen;
	private boolean broadcastAnnonces;
	private int winLot;
	private boolean isConfigured;
	
	
	
	
	
	
	public AniPoli(String name, Player owner) {
		super();
		this.name = name;
		this.owner = owner;
		this.configGUI = Bukkit.createInventory(null, 9*6, Main.getMessage("gui.configuration.title").replace("%title%", name));
		this.isConsolationLot = false;
		this.isOpen = false;
		this.broadcastAnnonces = false;
		this.isConfigured = false;
		this.consolationLot = 0;
		
		this.reloadConfigGui();
		
	}
	
	
	
	
	public void reloadConfigGui() {
		Integer[] slot = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53 };
        Integer[] array;
        for (int length = (array = slot).length, i = 0; i < length; ++i) {
            final Integer slt = array[i];
            this.configGUI.setItem((int)slt, ItemBuilder.createItem(Material.GREEN_STAINED_GLASS_PANE, 1, 0, "§a", ""));
        }
        this.configGUI.setItem(10, ItemBuilder.createItem(Material.HOPPER, 1, 0, (isOpen?"§cFermer les inscriptions":"§aOuvrir les inscriptions"), Main.getMessage("gui.items.openItemLore").split("\n")));
        this.configGUI.setItem(11, ItemBuilder.createItem(Material.BELL, 1, 0, (broadcastAnnonces? "§cDésactiver les annonces":"§aActiver les annonces"), "","§7Cet item permet d'activer ou désactiver", 
        		"§7les messages d'annonces"));
        this.configGUI.setItem(12, ItemBuilder.createItem(Material.NOTE_BLOCK, 1, 0, (isConsolationLot?"§cDésactiver le lot de consolation":"§aActiver le lot de consolation"), "",
        		"§7ACette item permet d'activer ou désactiver","§7le lot de consolation.", "", "§7Actuelement le lot de consolation est de : " + consolationLot));
        this.configGUI.setItem(13, ItemBuilder.createItem(Material.DIAMOND_BLOCK, 1, 0, "§eDéfinir le lot du Vainqueur", "","§7Cliquez ici pour définir le lot du vainqueur"));
        this.configGUI.setItem(16, ItemBuilder.createItem(Material.BOOK, 1, 0, "§6Récapitulatif de l'event :", "",
        		"§eNom de l'event : §6" + this.name,
        		"§eCréateur de l'event : §3" + this.owner.getName(),
        		"§eLot de Consolation : " + (isConsolationLot?"§aActivé":"§cDésactivé"),
        		"§eValeur du lot de Consolation : §3" + consolationLot,
        		"§eStatut des inscriptions : " + (isOpen?"§aOuvert":"§cFermé"),
        		"§eAnnonce sur le serveur : " + (broadcastAnnonces?"§aActivé":"§cDésactivé")));
//        this.configGUI.setItem(19, ItemBuilder.createItem((!isOpen?Material.REDSTONE_BLOCK:Material.EMERALD_BLOCK), 1, 0, "§a"));
//        this.configGUI.setItem(20, ItemBuilder.createItem((!broadcastAnnonces?Material.REDSTONE_BLOCK:Material.EMERALD_BLOCK), 1, 0, "§a"));
//        this.configGUI.setItem(21, ItemBuilder.createItem((!isConsolationLot?Material.REDSTONE_BLOCK:Material.EMERALD_BLOCK), 1, 0, "§a"));
        this.configGUI.setItem(37, ItemBuilder.createItem(Material.PAPER, 1, 0, "§eRenommer l'event", "","§7Cet item permet de renommer l'event"));
        this.configGUI.setItem(38, ItemBuilder.createItem(Material.CHEST, 1, 0, "§eAfficher le liste des inscrits", "","§7Cet item vous ouvre un menu qui contient", "tout les joueurs inscrits"));
        this.configGUI.setItem(39, ItemBuilder.createItem(Material.ENDER_EYE, 1, 0, "§eChoisir le type d'inscription", "",
        		"§e >> Inscription Solo",
        		"§7 Inscription par Team",
        		"§7 Inscription par Team personnalisé (crée par l'annimateur",
        		"",
        		"§7Clique sur cet item pour modifier le paramètre."));
        this.configGUI.setItem(43, ItemBuilder.createItem(Material.DISPENSER, 1, 0, "§7Faire le Tirrage", "", "§7Cliquez ici pour entre dans le menu de tirrage."));
	}
	
	public void openGuiConfiguration(Player p) {
		p.openInventory(this.configGUI);
	}
	
	
	
	public void GUI(String str, Player p) {
		if(str.equals("#1consolation_lot")) {
			Inventory inv = Bukkit.createInventory(null, 6*9, "Définir le lot de consolation");
			
			inv.setItem(18, ItemBuilder .createItem(Material.RED_STAINED_GLASS_PANE, 1, 0, "§c- 10", ""));
			inv.setItem(19, ItemBuilder.createItem(Material.RED_STAINED_GLASS_PANE, 1, 0, "§c- 5", ""));
			inv.setItem(20, ItemBuilder.createItem(Material.RED_STAINED_GLASS_PANE, 1, 0, "§c- 1", ""));
			
			
			inv.setItem(22, ItemBuilder.createItem(Material.MUSIC_DISC_BLOCKS, 1, 0, null, "", "§eCatégorie : §6"));
			

			inv.setItem(39, ItemBuilder.createItem(Material.REDSTONE_BLOCK, 1, 0, "§cAnnuler !", "","§eVous annuler l'achat et revenez","§eau shop."));
			inv.setItem(41, ItemBuilder.createItem(Material.EMERALD_BLOCK, 1, 0, "§aAcheter !", ""));
			
			inv.setItem(24, ItemBuilder.createItem(Material.GREEN_STAINED_GLASS_PANE, 1, 0, "§a+ 1", ""));
			inv.setItem(25, ItemBuilder.createItem(Material.GREEN_STAINED_GLASS_PANE, 1, 0, "§a+ 5", ""));
			inv.setItem(26, ItemBuilder.createItem(Material.GREEN_STAINED_GLASS_PANE, 1, 0, "§a+ 10", ""));
			
			inv.setItem(49, ItemBuilder.createItem(Material.ANVIL, 1, 0, "§bAcheter par stack", ""));
			
			p.openInventory(inv); 
		}
	}
	
	
	
	
	
	public boolean isConfigured() {
		return isConfigured;
	}
	public void setConfigured(boolean isConfigured) {
		this.isConfigured = isConfigured;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Player getOwner() {
		return owner;
	}
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	public Inventory getConfigGUI() {
		return configGUI;
	}
	public void setConfigGUI(Inventory configGUI) {
		this.configGUI = configGUI;
	}
	public boolean haveConsolationLot() {
		return isConsolationLot;
	}
	public void setConsolationLot(boolean isConsolationLot) {
		this.isConsolationLot = isConsolationLot;
	}
	public int getConsolationLot() {
		return consolationLot;
	}
	public void setConsolationLot(int consolationLot) {
		this.consolationLot = consolationLot;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public boolean haveBroadcastAnnonces() {
		return broadcastAnnonces;
	}
	public void setBroadcastAnnonces(boolean broadcastAnnonces) {
		this.broadcastAnnonces = broadcastAnnonces;
	}
	public int haveWinLot() {
		return winLot;
	}
	public void setWinLot(int winLot) {
		this.winLot = winLot;
	}



	
	
	

}
