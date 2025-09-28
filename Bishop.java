import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Color color) { super(color); }

    @Override
    public char fenChar() { return color == Color.WHITE ? 'B' : 'b'; }

    @Override
    public List<Position> legalTargets(Board board, Position from) {
        List<Position> res = new ArrayList<>();
        Queen.addRays(board, from, res, new int[][]{
            {-1,-1},{-1,1},{1,-1},{1,1}
        });
        return res;
    }
}
