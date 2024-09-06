package com.raspix.snekcraft.entity.generics;

import com.raspix.snekcraft.SnekCraft;

public class GenePool {
	private int[] species;
	private int[] percentage;
	
	public GenePool(int[] species, int[] percentage) {
		this.species = species;
		this.percentage = percentage;
	}
	
	public int getGene(int chance) {
		int cumulative = 0;
		for(int i = 0;i < species.length;++i) {
			if(chance > percentage[i] + cumulative) {
				cumulative += percentage[i];
			}else {
				return species[i];
			}
		}
		SnekCraft.logger.warn("You messed up your coding, idiot");
		return species[species.length-1];
	}
}
