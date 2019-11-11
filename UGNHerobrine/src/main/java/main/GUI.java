package main;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
	
@SuppressWarnings("deprecation")
public abstract class GUI implements Listener {
	
	private Inventory _gui;
	
	public GUI(int rows, String title) {
		rows = Math.max(Math.min(rows, 6), 1);
		_gui = Bukkit.createInventory(null, rows * 9, ChatColor.translateAlternateColorCodes('&', title));
		Main.GetInstance().getServer().getPluginManager().registerEvents(this, Main.GetInstance());
		System.out.println("Created GUI option 1");
	}
	
	public GUI(int rows) {
		rows = Math.max(Math.min(rows, 6), 1);
		_gui = Bukkit.createInventory(null, rows * 9);
		System.out.println("Created GUI option 2");
	}
	
	@EventHandler
	public void Click(InventoryClickEvent e) {
		if (e.getClickedInventory() == null)return;
		if (IsEqualTo(e.getClickedInventory())) {
			OnClick(e);
		}
		else if (IsEqualTo(e.getInventory()) && (e.getClick() == ClickType.SHIFT_LEFT || e.getClick() == ClickType.SHIFT_RIGHT)){
			OnClick(e);
		}
	}
	
	@EventHandler
	public void Drag(InventoryDragEvent e) {
		if (!IsEqualTo(e.getInventory()))return;
		OnDrag(e);
	}
	
	@EventHandler
	public void Open(InventoryOpenEvent e) {
		if (!IsEqualTo(e.getInventory()))return;
		OnOpen(e);
	}
	
	@EventHandler
	public void Close(InventoryCloseEvent e) {
		if (!IsEqualTo(e.getInventory()))return;
		OnClose(e);
	}

	protected void OnClick(InventoryClickEvent e) {}
	protected void OnDrag(InventoryDragEvent e) {}
	protected void OnMove(InventoryMoveItemEvent e) {}
	protected void OnOpen(InventoryOpenEvent e) {}
	protected void OnClose(InventoryCloseEvent e) {}

	public void ShowTo(Player viewer) {
		viewer.openInventory(_gui);
	}
	
	public boolean IsEqualTo(Inventory inventory) {
		return (inventory.getViewers().equals(_gui.getViewers()));
	}
	
	protected boolean isSimilar(ItemStack a, ItemStack b) {
		return a.isSimilar(b);
	}
	
	public int getSize() {
		return _gui.getSize();
	}

	
	public int getMaxStackSize() {
		return _gui.getMaxStackSize();
	}

	
	public void setMaxStackSize(int size) {
		_gui.setMaxStackSize(size);
	}

	
	public ItemStack getItem(int index) {
		return _gui.getItem(index);
	}

	
	public void setItem(int index, ItemStack item) {
		_gui.setItem(index, item);
	}
	
	public void setItem(int x, int y, ItemStack item) {
		setItem(Math.max(Math.min(((x-1) + ((y-1) * 9)), 53), 0), item);
	}

	
	public HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
		return _gui.addItem(items);
	}

	
	public HashMap<Integer, ItemStack> removeItem(ItemStack... items) throws IllegalArgumentException {
		return _gui.removeItem(items);
	}

	
	public ItemStack[] getContents() {
		return _gui.getContents();
	}

	
	public void setContents(ItemStack[] items) throws IllegalArgumentException {
		_gui.setContents(items);
	}

	
	public ItemStack[] getStorageContents() {
		return _gui.getStorageContents();
	}

	
	public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
		_gui.setStorageContents(items);
	}

	
	public boolean contains(Material material) throws IllegalArgumentException {
		return _gui.contains(material);
	}

	
	public boolean contains(ItemStack item) {
		return _gui.contains(item);
	}

	
	public boolean contains(Material material, int amount) throws IllegalArgumentException {
		return _gui.contains(material, amount);
	}

	
	public boolean contains(ItemStack item, int amount) {
		return _gui.contains(item, amount);
	}

	
	public boolean containsAtLeast(ItemStack item, int amount) {
		return _gui.containsAtLeast(item, amount);
	}

	
	public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
		return _gui.all(material);
	}

	
	public HashMap<Integer, ? extends ItemStack> all(ItemStack item) {
		return _gui.all(item);
	}

	
	public int first(Material material) throws IllegalArgumentException {
		return _gui.first(material);
	}

	
	public int first(ItemStack item) {
		return _gui.first(item);
	}

	
	public int firstEmpty() {
		return _gui.firstEmpty();
	}

	
	public void remove(Material material) throws IllegalArgumentException {
		_gui.remove(material);
	}

	
	public void remove(ItemStack item) {
		_gui.remove(item);
	}

	
	public void clear(int index) {
		_gui.clear(index);
	}

	
	public void clear() {
		_gui.clear();
	}

	
	public List<HumanEntity> getViewers() {
		return _gui.getViewers();
	}
	
	public InventoryType getType() {
		return _gui.getType();
	}

	
	public InventoryHolder getHolder() {
		return _gui.getHolder();
	}

	
	public ListIterator<ItemStack> iterator() {
		return _gui.iterator();
	}

	
	public ListIterator<ItemStack> iterator(int index) {
		return _gui.iterator(index);
	}

	
	public Location getLocation() {
		return _gui.getLocation();
	}
	
	
	protected static ItemStack getSkull(String skinURL, String wantedName) {
		ItemStack head = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
        if(skinURL.isEmpty())return head;
        
        ItemMeta headMeta = head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", skinURL).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(headMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        headMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', wantedName));
        head.setItemMeta(headMeta);
        return head;
    }
}
