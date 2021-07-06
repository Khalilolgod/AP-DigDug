package ir.ac.kntu.controller;

import ir.ac.kntu.model.Direction;
import ir.ac.kntu.model.Enemy;
import ir.ac.kntu.model.Tomato;

import java.util.Random;

public class EnemyController {
    Enemy enemy;
    Random random;

    public EnemyController(Enemy enemy){
        this.random = new Random();
        this.enemy = enemy;
    }

    Direction directionGen(){
        return Direction.values()[random.nextInt(Direction.values().length)];
    }

    public void move(){
        enemy.move(directionGen());
    }
}
