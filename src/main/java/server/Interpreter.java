package server;

import server.actions.Attack;
import server.actions.Move;
import game.Game;
import characters.Character;

public class Interpreter {

    private Game game;

    public Interpreter(Game game) {
        this.game = game;
    }

    public String interpret(String command, String direction,
            Character character) {
        switch (command) {
            case "move":
                Move moveCommand = new Move(direction);
                return moveCommand.execute(character, game);

            case "attack":
                Attack attackCommand = new Attack();
                return attackCommand.execute(character, game);

            default:
                // Comando desconocido
                return "Unknown command.";
        }
    }
}
