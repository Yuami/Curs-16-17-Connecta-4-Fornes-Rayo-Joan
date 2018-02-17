package Conecta4;

public class Jugador {

    private static int fichasPorJugador;
    private String name;
    private int fichas;
    private char ficha;
    private int partidasGanadas;
    private int partidasEmpatadas;
    private int partidasPerdidas;

    public Jugador(String name, char ficha) {
        this.name = name;
        this.ficha = ficha;
        partidasGanadas = 0;
        partidasEmpatadas = 0;
        partidasPerdidas = 0;
    }

    public static void setFichasPorJugador(int f, int c) {
        Jugador.fichasPorJugador = f * c /2;
    }

    public static int getFichasPorJugador(){
        return fichasPorJugador;
    }

    public String getName() {
        return name;
    }

    public int getFichas() {
        return fichas;
    }

    public char getFicha() {
        return ficha;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public int getPartidasEmpatadas() {
        return partidasEmpatadas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public void setPartidasEmpatadas(int partidasEmpatadas) {
        this.partidasEmpatadas = partidasEmpatadas;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }

    public void partidaGanada() {
        partidasGanadas++;
    }

    public void partidaEmpatada() {
        partidasEmpatadas++;
    }

    public void partidaPerdida() {
        partidasPerdidas++;
    }

    public void resetGanadas() {
        setPartidasGanadas(0);
    }

    public void resetEmpatadas() {
        setPartidasEmpatadas(0);
    }

    public void resetPerdidas() {
        setPartidasPerdidas(0);
    }

    public void setFicha(char ficha) {
        this.ficha = ficha;
    }

    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void showJugador() {
        System.out.println(name + ": " + fichas + "ficha(s)");
    }

    public void usarFicha() {
        fichas--;
    }

    @Override
    public String toString() {
        return  "Nombre: " + name + "\n" +
                "Ficha: " + ficha + "\n" +
                "Partidas ganadas: " + partidasGanadas + "\n" +
                "Partidas empatadas: " + partidasEmpatadas + "\n" +
                "Partidas perdidas: " + partidasPerdidas;
    }
}
