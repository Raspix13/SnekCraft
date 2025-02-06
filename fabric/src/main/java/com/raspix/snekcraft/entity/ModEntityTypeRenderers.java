package com.raspix.snekcraft.entity;

import com.raspix.snekcraft.entity.ball_python.BallPythonModel;
import com.raspix.snekcraft.entity.ball_python.BallPythonRenderer;
import com.raspix.snekcraft.entity.hognose.HognoseModel;
import com.raspix.snekcraft.entity.hognose.HognoseRenderer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class ModEntityTypeRenderers {
	
	
	public static void init() {
		EntityRendererRegistry.register(ModEntityTypes.HOGNOSE, context -> new HognoseRenderer(context));
		EntityRendererRegistry.register(ModEntityTypes.BALLPYTHON, context -> new BallPythonRenderer(context));
		
		EntityModelLayerRegistry.registerModelLayer(HognoseModel.LAYER_LOCATION, HognoseModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(BallPythonModel.LAYER_LOCATION, BallPythonModel::getTexturedModelData);
	}
}
