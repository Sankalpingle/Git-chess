import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Color color) { super(color); }

    @Override
    public char fenChar() { return color == Color.WHITE ? 'N' : 'n'; }

    @Override
    public List<Position> legalTargets(Board board, Position from) {
        List<Position> res = new ArrayList<>();
        int[][] jumps = {{-2,-1},{-2,1},{-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,1}};
        for (int[] j : jumps) {
            int r = from.getRow() + j[0], c = from.getCol() + j[1];
            if (r < 0 || r > 7 || c < 0 || c > 7) continue;
            Position to = new Position(r, c);
            Piece q = board.get(to);
            if (q == null || q.color() != this.color) res.add(to);
        }
        return res;
    }
}
