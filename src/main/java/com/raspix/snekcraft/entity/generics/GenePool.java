package com.raspix.snekcraft.entity.generics;

public class GenePool{
    private int[] species;
    private int[] percentage;

    public GenePool(int[] species, int[] percentage){
        this.species = species;
        this.percentage = percentage;
    }

    public int GetGene(int chance){
        int cumulative = 0;
        for(int i = 0; i < species.length; i++){
            if(chance > percentage[i] + cumulative){ // does this need to  be >=?
                cumulative += percentage[i];
            }else {
                return species[i];
            }
        }
        System.out.println("You messed up your coding, idiot");
        return species[species.length-1]; //this right?
    }

}
