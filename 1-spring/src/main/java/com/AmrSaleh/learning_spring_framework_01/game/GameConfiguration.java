package com.AmrSaleh.learning_spring_framework_01.game;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
