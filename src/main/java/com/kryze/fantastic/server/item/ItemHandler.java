package com.kryze.fantastic.server.item;

import com.kryze.fantastic.Fantastic;
import com.kryze.fantastic.server.creativetab.CreativeTabHandler;
import com.kryze.fantastic.server.entity.EntityHandler;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemHandler {

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, Fantastic.MOD_ID);

    public static final RegistryObject<Item> CHOCOBO_SPAWN_EGG = REGISTER.register("spawn_egg_chocobo", () -> new SpawnEggItem(EntityHandler.CHOCOBO, 0x4da744, 0x316f5d, new Item.Properties().group(CreativeTabHandler.INSTANCE.creativeTab)));

    public static final RegistryObject<ItemGysahl> GYSAHL = REGISTER.register("gysahl", () -> new ItemGysahl(new Item.Properties().group(CreativeTabHandler.INSTANCE.creativeTab)));
}

