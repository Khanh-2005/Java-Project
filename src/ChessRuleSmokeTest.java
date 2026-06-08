import java.util.HashMap;

public class ChessRuleSmokeTest {

    public static void main(String[] args) {
        testFoolsMate();
        testCannotExposeOwnKing();
        testBotMakesBlackMove();
        System.out.println("All chess rule smoke tests passed.");
    }

    private static void testFoolsMate() {
        Pieces pieces = new Pieces();
        ChessGameController controller = new ChessGameController(pieces);

        click(controller, "f2");
        click(controller, "f3");
        click(controller, "e7");
        click(controller, "e5");
        click(controller, "g2");
        click(controller, "g4");
        click(controller, "d8");
        MoveOutcome outcome = click(controller, "h4");

        assertTrue(outcome.isGameOver(), "Fool's mate should end the game.");
        assertTrue(outcome.getGameMessage().contains("checkmate"), "Fool's mate should be checkmate.");
    }

    private static void testCannotExposeOwnKing() {
        HashMap<Coordinate, Piece> board = new HashMap<>();
        King whiteKing = new King(COLOUR.W, new Coordinate("e1"));
        Rook whiteRook = new Rook(COLOUR.W, new Coordinate("e2"));
        King blackKing = new King(COLOUR.B, new Coordinate("a8"));
        Rook blackRook = new Rook(COLOUR.B, new Coordinate("e8"));

        board.put(whiteKing.getCoords(), whiteKing);
        board.put(whiteRook.getCoords(), whiteRook);
        board.put(blackKing.getCoords(), blackKing);
        board.put(blackRook.getCoords(), blackRook);

        Pieces pieces = new Pieces(board);
        ChessGameController controller = new ChessGameController(pieces);

        click(controller, "e2");
        click(controller, "f2");

        assertTrue(pieces.getPieces().get(new Coordinate("e2")) == whiteRook,
                "Pinned rook must not move and expose its own king.");
        assertTrue(pieces.getPieces().get(new Coordinate("f2")) == null,
                "Illegal pinned move must not create a piece on the destination.");
    }

    private static void testBotMakesBlackMove() {
        Pieces pieces = new Pieces();
        ChessGameController controller = new ChessGameController(pieces, GameMode.HUMAN_VS_BOT);

        click(controller, "e2");
        click(controller, "e4");
        MoveOutcome botOutcome = controller.playBotTurnIfNeeded();

        assertTrue(botOutcome.getMoveHistory().trim().split(" ").length >= 2,
                "Bot mode should add a black move after white moves.");
        assertTrue(controller.getTurn() == COLOUR.W,
                "After the bot moves, it should be White's turn again.");
    }

    private static MoveOutcome click(ChessGameController controller, String coordinate) {
        return controller.click(new Coordinate(coordinate));
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition)
            throw new AssertionError(message);
    }
}
