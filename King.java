import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(Color color) { super(color); }

    @Override
    public char fenChar() { return color == Color.WHITE ? 'K' : 'k'; }

    @Override
    public List<Position> legalTargets(Board board, Position from) {
        List<Position> res = new ArrayList<>();
        int[] d = {-1, 0, 1};
        for (int dr : d) for (int dc : d) {
            if (dr == 0 && dc == 0) continue;
            int nr = from.getRow() + dr, nc = from.getCol() + dc;
            if (nr < 0 || nr > 7 || nc < 0 || nc > 7) continue;
            Position to = new Position(nr, nc);
            Piece q = board.get(to);
            if (q == null || q.color() != color) res.add(to);
        }
        return res;
    }
}
