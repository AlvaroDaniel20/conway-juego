import java.util.Random;
import java.util.Scanner;

public class JuegoDeLaVida {
    public static void main(String[] args) {
        boolean[][] tablero = new boolean[5][5];
        boolean[][] nuevoTablero = new boolean[5][5];
        Random r = new Random();
        Scanner sc = new Scanner(System.in);

        final int LONGITUDTABLERO = 5;
        int gen = 0;

        // Inicializar tablero aleatoriamente
        for (int i = 0; i < LONGITUDTABLERO; i++) {
            for (int j = 0; j < LONGITUDTABLERO; j++) {
                tablero[i][j] = r.nextBoolean();
            }
        }
        
        while (true) {
            System.out.println("Generación " + gen);
            
            // Mostrar tablero
            for (int i = 0; i < LONGITUDTABLERO; i++) {
                for (int j = 0; j < LONGITUDTABLERO; j++) {
                    System.out.print(tablero[i][j] ? "■ " : "□ ");
                }
                System.out.println();
            }

            // Calcular siguiente generación
            for (int i = 0; i < LONGITUDTABLERO; i++) {
                for (int j = 0; j < LONGITUDTABLERO; j++) {
                    int vecinosVivos = 0;
                    for (int di = -1; di <= 1; di++) {
                        for (int dj = -1; dj <= 1; dj++) {
                            if (di == 0 && dj == 0) continue;
                            int fi = (i + di + LONGITUDTABLERO) % 5;
                            int fj = (j + dj + LONGITUDTABLERO) % 5;
                            if (tablero[fi][fj]) vecinosVivos++;
                        }
                    }
                    if (tablero[i][j]) {
                        nuevoTablero[i][j] = vecinosVivos == 2 || vecinosVivos == 3;
                    } else {
                        nuevoTablero[i][j] = vecinosVivos == 3;
                    }
                }
            }

            // Actualizar tablero
            for (int i = 0; i < LONGITUDTABLERO; i++) {
                System.arraycopy(nuevoTablero[i], 0, tablero[i], 0, 5);
            }

            gen++;

            System.out.print("Presione Enter para la siguiente generación o 'q' para salir: ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("q")) {
                break;
            }
        }
        sc.close();
    }
}
