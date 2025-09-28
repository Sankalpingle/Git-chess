import java.util.List;

public abstract class Piece {
    public enum Color { WHITE, BLACK }
    protected Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color color() { return color; }

    public abstract char fenChar(); // for display

    // Generate pseudo-legal moves (not checking self-check). Implement in subclasses.
    public abstract List<Position> legalTargets(Board board, Position from);
}