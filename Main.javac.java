import java.util.*;

public class Main {
public static void main(String[] args) {
Game g = new Game();
Scanner sc = new Scanner(System.in);
System.out.println("Simple Chess. Enter moves like: e2 e4");
while (true) {
g.print();
System.out.println(g.status());
System.out.print("> ");
String from = sc.next();
if (from.equalsIgnoreCase("quit")) break;
String to = sc.next();
boolean ok;
try {
ok = g.makeMoveAlgebraic(from, to);
} catch (Exception e) {
ok = false;
}
if (!ok) System.out.println("Illegal move. Try again.");
}
sc.close();
}
}