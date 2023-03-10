import java.util.ArrayList;
import java.util.List;

public class Grafo {
  Vertice[][] valori;

  // aggiunge solo in un senso
  public void addAdiacente(int fromX, int fromY, int toX, int toY) {
    valori[fromX][fromY].addAdiacente(valori[toX][toY]);
  }

  public Grafo(Input input) {

    this.valori = new Vertice[input.c][input.r];

    for (int y = 0; y < input.r; y++) {
      for (int x = 0; x < input.c; x++) {
        if (input.rawMatrix[x][y] == Integer.MAX_VALUE) { // wormhole
          valori[x][y] = new Vertice(x, y, input.rawMatrix[x][y], true);
        } else { // no wormhole
          valori[x][y] = new Vertice(x, y, input.rawMatrix[x][y], false);
        }

      }
    }

    List<Vertice> wormholes = new ArrayList<>();

    for (int y = 0; y < input.r; y++) {
      for (int x = 0; x < input.c; x++) {

        // angolo alto sinistra
        if (y == 0 && x == 0) {
          valori[x][y].addAdiacente(valori[x][input.r - 1]); // up
          valori[x][y].addAdiacente(valori[x][y + 1]); // down
          valori[x][y].addAdiacente(valori[x + 1][y]); // right
          valori[x][y].addAdiacente(valori[input.c - 1][y]); // left

          continue;
        }

        // angolo alto destra
        if (y == 0 && x == input.c - 1) {
          valori[x][y].addAdiacente(valori[x][input.r - 1]); // up
          valori[x][y].addAdiacente(valori[x][y + 1]); // down
          valori[x][y].addAdiacente(valori[0][y]); // right
          valori[x][y].addAdiacente(valori[x - 1][y]); // left

          continue;
        }

        // angolo basso sinistra
        if (y == input.r - 1 && x == 0) {
          valori[x][y].addAdiacente(valori[x][y - 1]); // up
          valori[x][y].addAdiacente(valori[x][0]); // down
          valori[x][y].addAdiacente(valori[x + 1][y]); // right
          valori[x][y].addAdiacente(valori[input.c - 1][y]); // left

          continue;
        }

        // angolo basso destra
        if (y == input.r - 1 && x == input.c - 1) {
          valori[x][y].addAdiacente(valori[x][y - 1]); // up
          valori[x][y].addAdiacente(valori[x][0]); // down
          valori[x][y].addAdiacente(valori[0][y]); // right
          valori[x][y].addAdiacente(valori[x - 1][y]); // left

          continue;
        }

        // prima riga
        if (y == 0) {
          valori[x][y].addAdiacente(valori[x][input.r - 1]); // up
          valori[x][y].addAdiacente(valori[x][y + 1]); // down
          valori[x][y].addAdiacente(valori[x + 1][y]); // right
          valori[x][y].addAdiacente(valori[x - 1][y]); // left

          continue;
        }

        // ultima riga
        if (y == input.r - 1) {
          valori[x][y].addAdiacente(valori[x][y - 1]); // up
          valori[x][y].addAdiacente(valori[x][0]); // down
          valori[x][y].addAdiacente(valori[x + 1][y]); // right
          valori[x][y].addAdiacente(valori[x - 1][y]); // left

          continue;
        }

        // prima colonna
        if (x == 0) {
          valori[x][y].addAdiacente(valori[x][y - 1]); // up
          valori[x][y].addAdiacente(valori[x][y + 1]); // down
          valori[x][y].addAdiacente(valori[x + 1][y]); // right
          valori[x][y].addAdiacente(valori[input.c - 1][y]); // left

          continue;
        }

        // ultima colonna
        if (x == input.c - 1) {
          valori[x][y].addAdiacente(valori[x][y - 1]); // up
          valori[x][y].addAdiacente(valori[x][y + 1]); // down
          valori[x][y].addAdiacente(valori[0][y]); // right
          valori[x][y].addAdiacente(valori[x - 1][y]); // left

          continue;
        }

        valori[x][y].addAdiacente(valori[x][y - 1]); // up
        valori[x][y].addAdiacente(valori[x][y + 1]); // down
        valori[x][y].addAdiacente(valori[x + 1][y]); // right
        valori[x][y].addAdiacente(valori[x - 1][y]); // left

        if (valori[x][y].wormhole) {
          wormholes.add(valori[x][y]);
        }

      }
    }

    for (int i = 0; i < wormholes.size(); i++) {
      Vertice worm = wormholes.get(i);

      // adiacenti
      for (Vertice vertice : worm.adiacenti) {

        for (int j = 0; j < wormholes.size(); j++) {
          if (i == j) {
            continue;
          }

          vertice.addAdiacente(wormholes.get(j));
        }

      }
    }
  }

  public String debugToString() {
    StringBuilder sb = new StringBuilder();
    for (int y = 0; y < valori[0].length; y++) {
      for (int x = 0; x < valori.length; x++) {
        sb.append(valori[x][y]);
        sb.append(' ');
      }
      sb.append('\n');
    }
    return sb.toString();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int y = 0; y < valori[0].length; y++) {
      for (int x = 0; x < valori.length; x++) {
        sb.append(valori[x][y].toString());
        sb.append(' ');
        sb.append(valori[x][y].adiacenti);
        sb.append('\n');
      }
    }
    return sb.toString();
  }

  public void pulisciGrafo() {
    for (int y = 0; y < valori[0].length; y++) {
      for (int x = 0; x < valori.length; x++) {
        valori[x][y].sommaIntermedia = Integer.MIN_VALUE;
        valori[x][y].step = -1;
      }
    }
  }
}
