package com.kryze.fantastic.client;

import com.kryze.fantastic.client.render.entity.RenderChocobo;
import com.kryze.fantastic.server.entity.EntityHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class ClientHandler {

    public static void setup() {
        RenderingRegistry.registerEntityRenderingHandler(EntityHandler.CHOCOBO, RenderChocobo::new);
    }
}
