import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChessGameController {

    private final Pieces pieces;
    private final GameMode gameMode;
    private final Random random = new Random();
    private COLOUR turn = COLOUR.W;
    private Piece selectedPiece;
    private int fullMoveNumber;
    private final StringBuilder moveHistory = new StringBuilder();
    private String gameMessage = "";
    private boolean gameOver;

    public ChessGameController(Pieces pieces) {
        this(pieces, GameMode.HUMAN_VS_HUMAN);
    }

    public ChessGameController(Pieces pieces, GameMode gameMode) {
        this.pieces = pieces;
        this.gameMode = gameMode;
        updateGameState();
    }

    public Pieces getPieces() {
        return pieces;
    }

    public COLOUR getTurn() {
        return turn;
    }

    public String getMoveHistory() {
        return moveHistory.toString();
    }

    public String getGameMessage() {
        return gameMessage;
    }

    public MoveOutcome click(Coordinate coordinate) {
        if (gameOver || isBotTurn() || coordinate == null || !Coordinate.inBoard(coordinate))
            return MoveOutcome.none(getMoveHistory(), gameMessage);

        Piece clickedPiece = pieces.getPieces().get(coordinate);

        if (selectedPiece == null) {
            if (isCurrentTurnPiece(clickedPiece))
                return select(clickedPiece);
            return MoveOutcome.none(getMoveHistory(), gameMessage);
        }

        if (selectedPiece.isValidMove(coordinate, turn))
            return moveSelectedPiece(coordinate);

        if (isCurrentTurnPiece(clickedPiece))
            return select(clickedPiece);

        selectedPiece = null;
        return MoveOutcome.invalid(getMoveHistory(), gameMessage);
    }

    public MoveOutcome playBotTurnIfNeeded() {
        if (!isBotTurn())
            return MoveOutcome.none(getMoveHistory(), gameMessage);

        List<BotMove> legalMoves = collectLegalMoves(turn);
        if (legalMoves.size() == 0) {
            updateGameState();
            return MoveOutcome.moved(getMoveHistory(), gameMessage, gameOver);
        }

        BotMove botMove = legalMoves.get(random.nextInt(legalMoves.size()));
        selectedPiece = botMove.piece;
        return moveSelectedPiece(botMove.destination);
    }

    private MoveOutcome select(Piece piece) {
        selectedPiece = piece;
        return MoveOutcome.selected(piece.getPotentialMoves(), getMoveHistory(), gameMessage);
    }

    private MoveOutcome moveSelectedPiece(Coordinate destination) {
        Piece movingPiece = selectedPiece;
        pieces.makeMove(destination, movingPiece);

        appendMove(destination, movingPiece);
        selectedPiece = null;
        turn = COLOUR.not(turn);
        updateGameState();

        return MoveOutcome.moved(getMoveHistory(), gameMessage, gameOver);
    }

    private boolean isCurrentTurnPiece(Piece piece) {
        return piece != null && piece.getColour() == turn;
    }

    private boolean isBotTurn() {
        return gameMode == GameMode.HUMAN_VS_BOT && turn == COLOUR.B && !gameOver;
    }

    private List<BotMove> collectLegalMoves(COLOUR colour) {
        List<BotMove> legalMoves = new ArrayList<>();
        for (Piece piece : pieces.getColourPieces(colour).values()) {
            for (Coordinate destination : piece.getPotentialMoves()) {
                legalMoves.add(new BotMove(piece, destination));
            }
        }
        return legalMoves;
    }

    private void appendMove(Coordinate destination, Piece movingPiece) {
        if (movingPiece.getColour() == COLOUR.W) {
            fullMoveNumber++;
            moveHistory.append(fullMoveNumber).append(". ");
        }
        moveHistory.append(ChessIO.moveString(pieces, destination, movingPiece)).append(" ");
    }

    private void updateGameState() {
        if (pieces.isMate(turn)) {
            gameMessage = COLOUR.not(turn).toString() + " won by checkmate.";
            gameOver = true;
        } else if (pieces.isStalemateFor(turn)) {
            gameMessage = "Draw by stalemate.";
            gameOver = true;
        } else if (pieces.isDraw()) {
            gameMessage = "It's a draw.";
            gameOver = true;
        } else if (pieces.isCheck(turn)) {
            gameMessage = turn.toString() + " is in check.";
        } else {
            gameMessage = turn.toString() + " to move.";
        }
    }

    private static class BotMove {
        private final Piece piece;
        private final Coordinate destination;

        private BotMove(Piece piece, Coordinate destination) {
            this.piece = piece;
            this.destination = destination;
        }
    }
}
