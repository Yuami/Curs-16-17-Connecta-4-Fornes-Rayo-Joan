package Conecta4;

import java.util.Scanner;

public class Juego {

    private static Jugador[] jugadores;
    private static int turno;
    private static int ultimaFila;
    private static int ultimaColumna;

    public static void setUltimaFila(int ultimaFila) {
        Juego.ultimaFila = ultimaFila;
    }

    public static void setUltimaColumna(int ultimaColumna) {
        Juego.ultimaColumna = ultimaColumna;
    }

    private static Jugador jugador() {
        return jugadores[getTurno()];
    }

    private static int getTurno() {
        return turno;
    }

    private static void cambiarTurno() {
        turno = (++turno) % 2;
    }

    public static void startJuego() {

        Tablero.cambiarDimensiones(6, 7);

        jugadores = new Jugador[2];
        jugadores[0] = new Jugador("1", '◉');
        jugadores[1] = new Jugador("2", '▽');

        System.out.println("Bienvenid@ al Conecta 4!");
        System.out.println("----");
        boolean volver = true;

        while (volver) {
            Tablero.showDimensiones();
            System.out.println("Jugadores");
            for (Jugador jugador : jugadores) {
                System.out.println(jugador.getName());
            }
            System.out.println("----");

            char eleccion = menuInicio();

            switch (eleccion) {
                case '1':
                    inicializar();
                    empezarJugar();
                    break;
                case '2':
                    cambiarNombreJugadores();
                    break;
                case '3':
                    cambiarDimensones();
                    break;
                case '4':
                    menuEstadisticas();
                    break;
                case '5':
                    volver = false;
                    break;
                default:
                    System.out.println("----");
                    System.out.println("\033[31mNo es una opcion valida!\u001B[0m");
            }
        }
        System.out.println("----");
        System.out.println("Gracias por jugar!");
    }

    private static void usarFicha(int columna) {
        if (Tablero.getTablero()[0][columna] == Tablero.getFichaBasica()) {
            Tablero.colocarFicha(jugador(), columna);
            cambiarTurno();
        } else {
            System.out.println("----");
            System.out.println("Esa columna ya esta llena!");
        }
    }

    private static char menuInicio() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1- Empezar a jugar");
        System.out.println("2- Cambiar nombre jugador");
        System.out.println("3- Cambiar dimensiones tablero");
        System.out.println("4- Estadisticas");
        System.out.println("5- Salir");
        System.out.print("> ");
        return scan.next().charAt(0);
    }

    private static void cambiarDimensones() {
        Scanner scan = new Scanner(System.in);
        try {

            System.out.print("Escribe el numero de filas que quieres: ");
            int fil = scan.nextInt();
            System.out.print("Escribe el numero de columnas que quieres: ");
            int col = scan.nextInt();

            Tablero.cambiarDimensiones(fil, col);

        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }

        System.out.println("----");
    }

    private static void cambiarNombreJugadores() {
        Scanner scan = new Scanner(System.in);
        int n;

        System.out.println("----");
        System.out.println("Elige el jugador que quieres cambiar el nombre");
        for (int i = 0; i < jugadores.length; i++) {
            System.out.println((i + 1) + "- " + jugadores[i].getName());
        }
        System.out.println("3- Volver");
        System.out.print("> ");
        String nombre = scan.next();

        switch (nombre) {
            case "1":
                n = 0;
                break;
            case "2":
                n = 1;
                break;
            case "3":
                System.out.println("Volviendo al menu principal");
                System.out.println("----");
                return;
            default:
                System.out.println("----");
                System.out.println("El numero introducido no corresponde a ningun jugador!");
                System.out.println("----");
                return;
        }
        System.out.print("Elige el nombre para el jugador elegido: ");
        jugadores[n].setName(scan.next());
        System.out.println("----");
    }

    private static void empezarJugar() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            if (jugadores[0].getFichas() == 0 && jugadores[1].getFichas() == 0) {
                jugadores[0].partidaEmpatada();
                jugadores[1].partidaEmpatada();

                System.out.println("----");
                Tablero.showTablero();
                System.out.println();

                System.out.println("Ha habido un empate!");
                System.out.println("----");

                showEstaditicas();
                break;
            }

            System.out.println("----");
            Tablero.showTablero();
            Tablero.showDimensiones();
            for (Jugador jugador : jugadores) {
                jugador.showJugador();
            }

            System.out.println("----");
            System.out.println("Le toca a " + (jugador().getName()));
            System.out.print("Elige una columna: ");
            int columnaElegida = comprovarEntrada(scan.next());
            if (columnaElegida == -1) {
                System.out.println("No es una columna valida");
                System.out.println("----");
            } else {
                usarFicha(columnaElegida - 1);
            }
            if (comprovacionMejorada()) {
                cambiarTurno();

                Tablero.showTablero();
                System.out.println();

                jugador().partidaGanada();
                System.out.println(jugador().getName() + " a ganado la partida en el ultimo movimiento!");
                System.out.println("----");

                cambiarTurno();
                jugador().partidaPerdida();
                showEstaditicas();
                break;
            }
        }

    }

    private static void menuEstadisticas() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1- Mostrar estadisticas");
        System.out.println("2- Resetear estadisticas");
        System.out.println("3- Volver");
        System.out.print("> ");
        switch (scan.next().charAt(0)) {
            case '1':
                showEstaditicas();
                break;
            case '2':
                for (Jugador jugador : jugadores) {
                    jugador.resetGanadas();
                    jugador.resetEmpatadas();
                    jugador.resetPerdidas();
                }

                System.out.println("Estadisticas reseteadas correctamente");
                System.out.println("----");
                showEstaditicas();
                break;
            case '3':
                System.out.println("Volviendo al menu principal");
                break;
            default:
                System.out.println("----");
                System.out.println("\033[31mNo es una opcion valida!\u001B[0m");
        }
    }

    private static void showEstaditicas() {
        int partidasTotales = jugadores[0].getPartidasGanadas() + jugadores[0].getPartidasEmpatadas() + jugadores[0].getPartidasPerdidas();

        System.out.println("----");

        if (partidasTotales > 0) {
            System.out.println("Estadisticas Generales");
            System.out.println("Partidas totales: " + partidasTotales);
            for (Jugador jugador : jugadores)
                System.out.println(jugador.getName() + " tiene un porcentaje de victoria del " + jugador.getPartidasGanadas() / partidasTotales * 100 + "%");
            System.out.println();
        }

        System.out.println("Estadisticas Actuales");
        System.out.println("----");

        for (Jugador jugador : jugadores) {
            System.out.println(jugador);
            System.out.println();
        }

        System.out.println("----");
    }

    private static int comprovarEntrada(String num) {
        if (num.chars().allMatch(Character::isDigit)) {
            int stringToInt = Integer.parseInt(num);
            if (stringToInt >= 1 && stringToInt <= Tablero.getColumnas()) {
                return stringToInt;
            }
        }
        return -1;
    }

    private static void inicializar() {

        int filas = Tablero.getTablero().length;
        int columnas = Tablero.getTablero()[0].length;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Tablero.getTablero()[i][j] = Tablero.getFichaBasica();
            }
        }
        for (Jugador jugadore : jugadores) {
            jugadore.setFichas(Tablero.getColumnas() * Tablero.getFilas() / 2);
        }
    }


    private static boolean comprovacionMejorada() {
        char[][] tableroComp = Tablero.getTablero();
        int fil = tableroComp.length;
        int col = tableroComp[0].length;
        int filCompUF, colCompUC;

        int[][] array = {{-1, -1},
                {0, -1},
                {1, -1}, {-1, 0}};

        for (int[] comp : array) {
            int contComp = 0;
            for (int j = 0; j < 2; j++) {

                if (comp == array[array.length - 1] && j == 1){
                    break;
                }
                
                for (int i = 1; i < 4; i++) {
                    int filComp = comp[0] * i;
                    int colComp = comp[1] * i;

                    if (j == 0) {
                        filCompUF = filComp + ultimaFila;
                        colCompUC = colComp + ultimaColumna;
                    } else {
                        filCompUF = -filComp + ultimaFila;
                        colCompUC = -colComp + ultimaColumna;
                    }

                    if (filCompUF < 0 || filCompUF >= fil ||
                            colCompUC < 0 || colCompUC >= col) {
                        break;
                    }

                    char comprovando = tableroComp[ultimaFila][ultimaColumna];


                    if (comprovando == tableroComp[filCompUF][colCompUC]) {
                        contComp++;
                        if (contComp == 3) {
                            return true;
                        }
                    }

                }
            }
        }

        return false;
    }

}
