package Conecta4;

public class Tablero {

    private int filas;
    private int columnas;
    private char[][] tablero;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }

    public void cambiarDimensiones(int filas, int columnas) throws IllegalArgumentException{
        setFilas(filas);
        setColumnas(columnas);
    }

    public void setColumnas(int columnas) throws IllegalArgumentException {
        if (columnas < 5) {
            throw new IllegalArgumentException("El numero de columnas es demasiado pequeño! (Minimo 5 columnas)");
        }
        if (columnas > 9) {
            throw new IllegalArgumentException("El numero de columnas es demasiado grande! (Maximo 8 columnas)");
        }
        if (columnas % 2 == 0) {
            throw new IllegalArgumentException("El numero de columnas no puede ser par!");
        }
        this.columnas = columnas;
    }

    public void setFilas(int filas) throws IllegalArgumentException {
        if (filas < 4) {
            throw new IllegalArgumentException("El numero de filas es demasiado pequeño! (Minimo 4 filas)");
        }
        if (filas > 8) {
            throw new IllegalArgumentException("El numero de filas es demasiado grande! (Maximo 8 filas)");
        }
        if (filas % 2 == 1) {
            throw new IllegalArgumentException("El numero de filas no puede ser impar!");
        }
        this.filas = filas;
    }

    public void setTablero(char[][] tablero) {
        this.tablero = tablero;
    }

    public char getFichaBasica() {
        return (char) 1;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getFilas() {
        return filas;
    }

    public char[][] getTablero() {
        return tablero;
    }

    public void showDimensiones() {
        System.out.println("Dimensiones: " + filas + "x" + columnas);
    }

    public int colocarFicha( Jugador jugador, int columna) {

        for (int i = 0; i < tablero[0].length; i++) {
            if (tablero[filas - 1 - i][columna] == getFichaBasica()) {
                tablero[filas - 1 - i][columna] = jugador.getFicha();
                jugador.usarFicha();
                Juego.setUltimaColumna(columna);
                Juego.setUltimaFila(filas - 1 -i);
                return filas - 1 - i;
            }
        }
        return -1;
    }

    public void showTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == getFichaBasica()) {
                    System.out.print("\t" + getFichaBasica());
                } else {
                    System.out.print("\t" + tablero[i][j]);
                }
            }
            System.out.println("");
        }

        for (int i = 1; i <= columnas; i++) {
            System.out.print("\t" + i);
        }
        System.out.println("");
    }

}
