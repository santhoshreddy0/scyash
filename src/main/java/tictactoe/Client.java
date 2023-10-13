package tictactoe;

import tictactoe.controller.GameController;
import tictactoe.model.Game;
import tictactoe.model.Player;
import tictactoe.model.Symbol;
import tictactoe.model.types.GameState;
import tictactoe.model.types.PlayerType;
import tictactoe.strategy.winning.ColumnWinningRule;
import tictactoe.strategy.winning.GameWinningRule;
import tictactoe.strategy.winning.RowWinningRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// SIMULATOR.
public class Client {
    public static void main(String[] args) throws Exception {
        // user's perspective.
        GameController gameController = new GameController();
        Scanner sc = new Scanner(System.in);

        List<Player> players = new ArrayList<>();
        players.add(new Player("Mohsin", 1,
                new Symbol('X'),
                PlayerType.HUMAN,sc));

        players.add(new Player("Sridhar", 2,
                new Symbol('0'),
                PlayerType.HUMAN,sc));

        List<GameWinningRule> rules = new ArrayList<>();
        rules.add(new RowWinningRule());
        rules.add(new ColumnWinningRule());

        Game game = gameController.startGame(players, rules, 3);

        // FROM HERE.
        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            gameController.printBoard(game);

            System.out.println("Does someone want to do Undo? Press y OR n.. ");
            String undoStr = sc.next();

            if(undoStr.equals("y")){
                gameController.undo(game);
                continue;
            }

            gameController.makeMove(game);
        }

        if(game.getGameState().equals(GameState.DRAW)){
            System.out.println("Game is DRAW..");
        }

        if(game.getGameState().equals(GameState.END)){
            System.out.println("Game is won by Player: "
                    + gameController.getWinner(game).getName());
        }


    }
}
