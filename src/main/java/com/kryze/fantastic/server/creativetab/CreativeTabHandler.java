package com.kryze.fantastic.server.creativetab;

import com.kryze.fantastic.server.item.ItemHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum CreativeTabHandler {
    INSTANCE;

    public ItemGroup creativeTab;

    public void onlnit() {
        creativeTab = new ItemGroup("fantastic.creativeTab") {
            @Override
            @OnlyIn(Dist.CLIENT)
            public ItemStack createIcon() {
                return new ItemStack(ItemHandler.GYSAHL.get());
            }
        };
    }
}
