import java.util.ArrayList;
import java.util.List;

public class Vertice {
  final int x;
  final int y;

  final int valore;

  boolean lock;

  List<Vertice> adiacenti;

  public Vertice(int x, int y, int valore) {
    this.x = x;
    this.y = y;

    this.valore = valore;

    this.adiacenti = new ArrayList<>();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(x);
    sb.append('-');
    sb.append(y);
    return sb.toString();
  }

  public void lock() {
    this.lock = true;
  }

  public boolean isLocked() {
    return this.lock;
  }

  public void addAdiacente(Vertice v) {
    this.adiacenti.add(v);
  }
}
