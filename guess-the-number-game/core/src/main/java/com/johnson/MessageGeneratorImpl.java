package com.johnson;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageGeneratorImpl implements MessageGenerator{
    private static final Logger log = (Logger) LoggerFactory.getLogger(MessageGeneratorImpl.class);

    private final Game game;

    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    @PostConstruct
    public void init(){
        log.info("game = {}", game);
    }
    @Override
    public String getMainMessage() {
        return "Number is between " + game.getSmallest() + " and " + game.getBiggest() +
                " can you guess it?";
    }

    @Override
    public String getResultMessage() {
        if (game.isGameWon()){
            return "You guessed it the number was" + game.getNumber();
        } else if (game.isGameLost()){
            return "You lost";
        } else if (!game.isValidNumberRange()){
            return "Invalid value range";
        } else if (game.getRemainingGuesses() == game.getGuessCount()){
            return "What is your first guess";
        } else {
            String direction = "Lower";
            if (game.getGuess() < game.getNumber()){
                direction = "Higher";
            }

            return direction + "You have " + game.getRemainingGuesses() + " guesses left";
        }
    }
}