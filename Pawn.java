import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Color color) { super(color); }

    @Override
    public char fenChar() { return color == Color.WHITE ? 'P' : 'p'; }

    @Override
    public List<Position> legalTargets(Board board, Position from) {
    List<Position> res = new ArrayList<>();
    int dir = (color == Color.WHITE) ? -1 : 1; // white moves up (toward row 0)
    int startRow = (color == Color.WHITE) ? 6 : 1;

        // one step
        int r1 = from.getRow() + dir;
        if (r1 >= 0 && r1 < 8) {
            Position fwd = new Position(r1, from.getCol());
            if (board.isEmpty(fwd)) {
                res.add(fwd);
                // two steps from start if clear
                if (from.getRow() == startRow) {
                    Position fwd2 = new Position(from.getRow() + 2*dir, from.getCol());
                    if (board.isEmpty(fwd2)) res.add(fwd2);
                }
            }
        }
        // captures
        int[] dc = {-1, 1};
        for (int d : dc) {
            int c = from.getCol() + d;
            int r = from.getRow() + dir;
            if (r >= 0 && r < 8 && c >= 0 && c < 8) {
                Position cap = new Position(r, c);
                if (board.isEnemy(cap, color)) res.add(cap);
            }
        }
        // en passant and promotion can be added later
        return res;
    }
}
