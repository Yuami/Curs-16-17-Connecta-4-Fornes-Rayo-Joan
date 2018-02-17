package Conecta4;

public class Tablero {

    private static int filas;
    private static int columnas;
    private static char[][] tablero;

     Tablero(int filas, int columnas) {
        Tablero.filas = filas;
        Tablero.columnas = columnas;
    }

    public static void cambiarDimensiones(int filas, int columnas) throws IllegalArgumentException{
        setFilas(filas);
        setColumnas(columnas);
        char[][] tablero = new char[filas][columnas];
        setTablero(tablero);
    }

    public static void setColumnas(int columnas) throws IllegalArgumentException {
        if (columnas < 5) {
            throw new IllegalArgumentException("El numero de columnas es demasiado pequeño! (Minimo 5 columnas)");
        }
        if (columnas > 9) {
            throw new IllegalArgumentException("El numero de columnas es demasiado grande! (Maximo 8 columnas)");
        }
        if (columnas % 2 == 0) {
            throw new IllegalArgumentException("El numero de columnas no puede ser par!");
        }
        Tablero.columnas = columnas;
    }

    public static void setFilas(int filas) throws IllegalArgumentException {
        if (filas < 4) {
            throw new IllegalArgumentException("El numero de filas es demasiado pequeño! (Minimo 4 filas)");
        }
        if (filas > 8) {
            throw new IllegalArgumentException("El numero de filas es demasiado grande! (Maximo 8 filas)");
        }
        if (filas % 2 == 1) {
            throw new IllegalArgumentException("El numero de filas no puede ser impar!");
        }
        Tablero.filas = filas;
    }

    public static void setTablero(char[][] tablero) {
        Tablero.tablero = tablero;
    }

    public static char getFichaBasica() {
        return (char) 1;
    }

    public static int getColumnas() {
        return columnas;
    }

    public static int getFilas() {
        return filas;
    }

    public static char[][] getTablero() {
        return tablero;
    }

    public static void showDimensiones() {
        System.out.println("Dimensiones: " + filas + "x" + columnas);
    }

    public static void colocarFicha( Jugador jugador, int columna) {

        for (int i = 0; i < tablero[0].length; i++) {
            if (tablero[filas - 1 - i][columna] == getFichaBasica()) {
                tablero[filas - 1 - i][columna] = jugador.getFicha();
                jugador.usarFicha();
                Juego.setUltimaColumna(columna);
                Juego.setUltimaFila(filas - 1 -i);
                break;
            }
        }
    }

    public static void showTablero() {
        for (char[] aTablero : tablero) {
            for (int j = 0; j < tablero[0].length; j++) {
                    System.out.print("\t" + aTablero[j]);
            }
            System.out.println("");
        }

        for (int i = 1; i <= columnas; i++) {
            System.out.print("\t" + i);
        }
        System.out.println("");
    }

}
