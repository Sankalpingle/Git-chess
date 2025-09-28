import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(Color color) { super(color); }

    @Override
    public char fenChar() { return color == Color.WHITE ? 'R' : 'r'; }

    @Override
    public List<Position> legalTargets(Board board, Position from) {
        List<Position> res = new ArrayList<>();
        Queen.addRays(board, from, res, new int[][]{
            {-1,0},{1,0},{0,-1},{0,1}
        });
        return res;
    }
}
