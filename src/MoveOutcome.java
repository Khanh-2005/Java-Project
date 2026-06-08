import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MoveOutcome {

    public enum Type {
        NONE,
        SELECTED,
        MOVED,
        INVALID
    }

    private final Type type;
    private final Set<Coordinate> highlightedMoves;
    private final String moveHistory;
    private final String gameMessage;
    private final boolean gameOver;

    private MoveOutcome(Type type, Set<Coordinate> highlightedMoves, String moveHistory, String gameMessage, boolean gameOver) {
        this.type = type;
        this.highlightedMoves = new HashSet<>(highlightedMoves);
        this.moveHistory = moveHistory;
        this.gameMessage = gameMessage;
        this.gameOver = gameOver;
    }

    public static MoveOutcome none(String moveHistory, String gameMessage) {
        return new MoveOutcome(Type.NONE, Collections.emptySet(), moveHistory, gameMessage, false);
    }

    public static MoveOutcome selected(Set<Coordinate> highlightedMoves, String moveHistory, String gameMessage) {
        return new MoveOutcome(Type.SELECTED, highlightedMoves, moveHistory, gameMessage, false);
    }

    public static MoveOutcome moved(String moveHistory, String gameMessage, boolean gameOver) {
        return new MoveOutcome(Type.MOVED, Collections.emptySet(), moveHistory, gameMessage, gameOver);
    }

    public static MoveOutcome invalid(String moveHistory, String gameMessage) {
        return new MoveOutcome(Type.INVALID, Collections.emptySet(), moveHistory, gameMessage, false);
    }

    public Type getType() {
        return type;
    }

    public Set<Coordinate> getHighlightedMoves() {
        return Collections.unmodifiableSet(highlightedMoves);
    }

    public String getMoveHistory() {
        return moveHistory;
    }

    public String getGameMessage() {
        return gameMessage;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
