package com.katas.snakesAndLaders;

import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class Dices {
    private int[] nums = new int[2];

    public Dices() {
    }

    public int[] throwDices(){
        nums[0]= (int)(Math.random()*6+1);
        nums[1]= (int)(Math.random()*6+1);

        return nums;
    }

    public int sumOf(){
        int sum = nums[0] + nums[1];
        return sum;
    }

    public Boolean equalNumbers(){
        return nums[0] == nums[1];
    }
}
