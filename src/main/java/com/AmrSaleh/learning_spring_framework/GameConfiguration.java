package com.AmrSaleh.learning_spring_framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.AmrSaleh.learning_spring_framework.game.GameConsole;
import com.AmrSaleh.learning_spring_framework.game.GameRunner;
import com.AmrSaleh.learning_spring_framework.game.PacManGame;

@Configuration
public class GameConfiguration {
    @Bean
    GameConsole game(){
        return new PacManGame();
    }

    @Bean
    GameRunner gameRunner(GameConsole game){
        return new GameRunner(game);
    }
}
