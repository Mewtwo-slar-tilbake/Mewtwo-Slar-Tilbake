package com.kaijumon.game.model.items;

public class PotionFactory {

    public static Item getRandomPotion(){
        int random = (int) (Math.random() * 4);
        switch (random){
            case 1:
                int randomSpeed = (int) (Math.random() * 5) + 1;
                return new SpeedPotion("Speed Potion", "Increases speed by " + randomSpeed, randomSpeed);
            case 2:
                int randomAttack = (int) (Math.random() * 5) + 1;
                return new BattlePotion("Attack Potion", "Increases attack by " + randomAttack, randomAttack, 0);
            case 3:
                int randomDefense = (int) (Math.random() * 5) + 1;
                return new BattlePotion("Defense Potion", "Increases defense by " + randomDefense, 0, randomDefense);
            default:
                int randomHp = (int) (Math.random() * 50 - 15 + 1) + 15;
                return new HealthPotion("Health Potion", "Heals " + randomHp + " HP", randomHp);
        }
    }
}
