package org.cookeddiamond.challengeplugin;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Random;

public class DropEventListeners implements Listener {

    int itemIDOffset = new Random().nextInt();
    public void setSeed(int seed) {
        itemIDOffset = seed;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material blockType = block.getType();
        Collection<ItemStack> drops = block.getDrops();
        int dropsCount = 0;
        for (ItemStack stack:  drops) {
            dropsCount += stack.getAmount();
        }
        int stackSize = Math.max(1, dropsCount);
        Material randomItem = getRandomMaterial(blockType);
        ItemStack randomDrop = new ItemStack(randomItem, stackSize);

        block.getWorld().dropItemNaturally(block.getLocation(), randomDrop);


        event.setCancelled(true);
        block.setType(Material.AIR);
    }

    @EventHandler
    public void onEntityDrop(EntityDeathEvent event) {
        Collection<ItemStack> drops = event.getDrops();
        for (ItemStack stack: drops) {
            int stackSize = Math.max(1, stack.getAmount());
            Material randomItem = getRandomMaterial(stack.getType());
            ItemStack randomDrop = new ItemStack(randomItem, stackSize);

            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), randomDrop);
        }
    }

    private Material getRandomMaterial(Material original) {
        Material[] materials = Material.values();
        Random random = new Random(original.hashCode() + itemIDOffset);
        Material randomMaterial = materials[random.nextInt(materials.length)];
        if (!randomMaterial.isAir()
            && randomMaterial.isItem()) {
            return randomMaterial;
        }
        return getRandomMaterial(randomMaterial);
    }
}
