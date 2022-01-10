package com.kryze.fantastic;

import com.kryze.fantastic.client.ClientHandler;
import com.kryze.fantastic.server.creativetab.CreativeTabHandler;
import com.kryze.fantastic.server.entity.EntityHandler;
import com.kryze.fantastic.server.item.ItemHandler;
import com.kryze.fantastic.server.sound.SoundHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;

@Mod(Fantastic.MOD_ID)
@Mod.EventBusSubscriber(modid = Fantastic.MOD_ID)
public class Fantastic {

    public static final String MOD_ID = "fantastic";

    public Fantastic() {
        GeckoLib.initialize();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
        EntityHandler.register();
        ItemHandler.REGISTER.register(bus);
        CreativeTabHandler.INSTANCE.onlnit();
        SoundHandler.SOUND_EVENTS.register(bus);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
        ClientHandler.setup();
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
    }

    public static ResourceLocation createResourceLocation(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}