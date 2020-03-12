package jp.dicenull.objmodel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ObjModelTab extends CreativeTabs {
    public static final CreativeTabs ModTab = new ObjModelTab("ObjModel");

    public ObjModelTab(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.ARROW);
    }
}
