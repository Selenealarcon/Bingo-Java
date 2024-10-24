/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bingo;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author selenealarcon
 */
public class BingoSelene {

    public static final String LILA = "\u001B[35m";
    public static final String RESET = "\u001B[0m";
    public static final String VERD = "\u001B[32m";
    public static final String VERMELL = "\u001B[31m";
    public static final String GROC = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner teclat = new Scanner(System.in);
        Random random = new Random();
        boolean jugar = false;
        System.out.println(LILA + "    ____  _______   ___    _______   __________  ________   ___    __       ____  _____   ____________ ");
        System.out.println(LILA + "   / __ )/ ____/ | / / |  / /  _/ | / / ____/ / / /_  __/  /   |  / /      / __ )/  _/ | / / ____/ __ \\");
        System.out.println(LILA + "  / __  / __/ /  |/ /| | / // //  |/ / / __/ / / / / /    / /| | / /      / __  |/ //  |/ / / __/ / / /");
        System.out.println(LILA + " / /_/ / /___/ /|  / | |/ // // /|  / /_/ / /_/ / / /    / ___ |/ /___   / /_/ // // /|  / /_/ / /_/ /");
        System.out.println(LILA + "/_____/_____/_/ |_/  |___/___/_/ |_/\\____/\\____/ /_/    /_/  |_/_____/  /_____/___/_/ |_/\\____/\\____/\n" + RESET);
        do { //COMENÇA PARTIDA
            boolean continuar = false;
            boolean bingo = false;
            char opcioContinuar = 0;
            boolean linia = false;
            int continuaJugant = 0;
            System.out.println(GROC + " ____    __    ____  ____  ____  ____     __      _  _  _____  _  _    __ ");
            System.out.println(GROC + "(  _ \\  /__\\  (  _ \\(_  _)(_  _)(  _ \\   /__\\    ( \\( )(  _  )( \\/ )  /__\\ ");
            System.out.println(GROC + " )___/ /(__)\\  )   /  )(   _)(_  )(_) ) /(__)\\    )  (  )(_)(  \\  /  /(__)\\");
            System.out.println(GROC + "(__)  (__)(__)(_)\\_) (__) (____)(____/ (__)(__)  (_)\\_)(_____)  \\/  (__)(__)" + RESET);
            int[][] cartro = new int[3][9];
            boolean[] repetits = new boolean[20];
            int bola = 0;
            generaCartro(cartro);
            mostraCartro(cartro);
            do { //SEGUENT NÚMERO
                generaBola(bola, repetits, cartro);
                mostraCartro(cartro);
                if (linia == false && comprobaLinia(cartro) == true) {
                    linia = true;
                }
                if (comprobaBingo(cartro) == true) {
                    numerosGenerats(repetits);
                    numerosCartro(cartro);
                    bingo = true;
                }
                if (bingo == false) {
                    do { //PREGUNTA SEGUENT NÚMERO
                        System.out.print("\nSegüent número(s/n)?: ");
                        opcioContinuar = teclat.next().charAt(0);
                        if (opcioContinuar == 's' || opcioContinuar == 'S') {
                            continuar = true;
                        } else if (opcioContinuar == 'n' || opcioContinuar == 'N') {
                            continuar = false;
                        } else {
                            System.out.println(VERMELL + "Introdueix una opció valida." + RESET);
                        }
                    } while (opcioContinuar != 's' && opcioContinuar != 'S' && opcioContinuar != 'n' && opcioContinuar != 'N');
                }

            } while (continuar && bingo == false);
            do { //PREGUNTA TORNAR A JUGAR
                System.out.print("\nVols tornar a jugar?(s/n): ");
                continuaJugant = teclat.next().charAt(0);
                if (continuaJugant == 's' || opcioContinuar == 'S') {
                    jugar = true;
                } else if (continuaJugant == 'n' || continuaJugant == 'N') {
                    jugar = false;
                } else {
                    System.out.println(VERMELL + "Introdueix una opció valida." + RESET);
                }
            } while (continuaJugant != 's' && continuaJugant != 'S' && continuaJugant != 'n' && continuaJugant != 'N');

        } while (jugar);
    }

    /**
     * Pasa a positiu els números de l'array bidimensional i els mostra.
     *
     * @param cartro array bidimensional amb la informació del cartro
     */
    public static void numerosCartro(int[][] cartro) {
        System.out.println("\n\nEls números del teu cartró eren: ");
        for (int i = 0; i < cartro.length; i++) {
            for (int j = 0; j < cartro[i].length; j++) {
                if (cartro[i][j] < 0) {
                    System.out.print((cartro[i][j] * -1) + " ");
                }
            }
            System.out.println("");
        }
    }

    /**
     * Mostra els números que han sortit al bombo durant la partida.
     *
     * @param repetits array de booleans que indica si un número ha sortit o no.
     */
    public static void numerosGenerats(boolean[] repetits) {
        int contNums = 0;
        System.out.println("\nAquests són els números que han sortit:");
        for (int i = 0; i < repetits.length; i++) {
            if (repetits[i] == true) {
                System.out.print((i + 1) + " ");
                contNums++;
                if (contNums == 20) {
                    System.out.println("");
                    contNums = 0;
                }
            }
        }
    }

    /**
     * Genera els números de l'array bidimensional(cartro).
     *
     * @param cartro array bidimensional amb la informació del cartro
     */
    public static void generaCartro(int[][] cartro) {
        Random random = new Random();
        int numCartro = 0;
        int contBombo = 0;
        boolean repetit = false;
        for (int i = 0; i < cartro.length; i++) {
            for (int j = 0; j < cartro[i].length; j++) {
                do {
                    numCartro = random.nextInt(20) + 1;
                    repetit = comprobaRepetit(cartro, numCartro);
                } while (repetit);
                cartro[i][j] = numCartro;
            }
            do {
                int bomboRandom = random.nextInt(8) + 1;
                if (cartro[i][bomboRandom] != 0) {
                    cartro[i][bomboRandom] = 0;
                    contBombo++;
                }
            } while (contBombo < 4);
            contBombo = 0;
        }
    }

    /**
     * Recorre l'array bidimensional en busca del número generat per comprobar
     * si ja ha sortit o no.
     *
     * @param cartro array bidimensional amb la informació del cartro.
     * @param numCartro número que és busca.
     * @return si el número generat esta repetit o no.
     */
    public static boolean comprobaRepetit(int[][] cartro, int numCartro) {
        boolean repetit = false;
        for (int i = 0; i < cartro.length; i++) {
            for (int j = 0; j < cartro[i].length; j++) {
                if (cartro[i][j] == numCartro) {
                    repetit = true;
                }
            }
        }
        return repetit;
    }

    /**
     * Mostra l'array bidimensional amb els números i l'estil del cartro.
     *
     * @param cartro array bidimensional amb la informació del cartro
     */
    public static void mostraCartro(int[][] cartro) {
        System.out.println("\nEl teu cartro és:");
        for (int i = 0; i < cartro.length; i++) {
            // System.out.print("| ");
            for (int j = 0; j < cartro[i].length; j++) {
                if (cartro[i][j] < 0) {
                    System.out.print("| X ");
                } else if (cartro[i][j] == 0) {
                    System.out.print("| @ ");
                } else {
                    System.out.printf("|%2d ", cartro[i][j]);
                }
            }
            System.out.println("|");
        }
    }

    /**
     *
     * @param bola número generat aleatoriament.
     * @param repetits array amb els números que han sortit.
     * @param cartro array bidimensional amb la informació del cartro.
     * @return número generat
     */
    public static int generaBola(int bola, boolean[] repetits, int[][] cartro) {
        Random random = new Random();
        do {
            bola = random.nextInt(20) + 1;
        } while (repetits[bola - 1] == true);
        System.out.printf("\nNou número: %d\n\n", bola);
        marcaNumero(bola, cartro);
        repetits[bola - 1] = true;
        return bola;
    }

    /**
     * Recorre l'array bidimensional en busca del número generat i si el troba
     * el marca.
     *
     * @param bola número generat aleatoriament.
     * @param cartro array bidimensional amb la informació del cartro.
     */
    public static void marcaNumero(int bola, int[][] cartro) {
        for (int i = 0; i < cartro.length; i++) {
            for (int j = 0; j < cartro[i].length; j++) {
                if (bola == cartro[i][j]) {
                    System.out.println("Aquest número el tens, el marco amb 'X'\n");
                    cartro[i][j] = (0 - cartro[i][j]);
                }
            }
        }
    }

    /**
     * Recorre cada fila de l'array bidimensional comprobant si alguna esta
     * plena.
     *
     * @param cartro array bidimensional amb la informació del cartro.
     * @return boolean que indica si ha trobat una fila plena.
     */
    public static boolean comprobaLinia(int[][] cartro) {
        int contLinia = 0;
        boolean linia = false;
        for (int i = 0; i < cartro.length; i++) {
            for (int j = 0; j < cartro[i].length; j++) {
                if (cartro[i][j] < 0) {
                    contLinia++;
                    if (contLinia == 5) {
                        linia = true;
                        System.out.println("");
                        System.out.println(CYAN + "  ###       ##                ##");
                        System.out.println(CYAN + "   ##");
                        System.out.println(CYAN + "   ##      ###     #####     ###       ####");
                        System.out.println(CYAN + "   ##       ##     ##  ##     ##         ##");
                        System.out.println(CYAN + "   ##       ##     ##  ##     ##      #####");
                        System.out.println(CYAN + "   ##       ##     ##  ##     ##     ##  ##");
                        System.out.println(CYAN + "  ####     ####    ##  ##    ####    #####" + RESET);
                    }
                }
            }
            contLinia = 0;
        }
        return linia;
    }

    /**
     * Recorre l'array bidimensional comprobant si esta plena.
     *
     * @param cartro array bidimensional amb la informació del cartro.
     * @return boolean que indica si l'array esta plena.
     */
    public static boolean comprobaBingo(int[][] cartro) {
        boolean bingo = false;
        int contBingo = 0;
        for (int i = 0; i < cartro.length; i++) {
            for (int j = 0; j < cartro[i].length; j++) {
                if (cartro[i][j] < 0) {
                    contBingo++;
                    if (contBingo == 15) {
                        bingo = true;
                        System.out.println("");
                        System.out.println(VERD + "      :::::::::       :::::::::::       ::::    :::       ::::::::       ::::::::");
                        System.out.println(VERD + "     :+:    :+:          :+:           :+:+:   :+:      :+:    :+:     :+:    :+:");
                        System.out.println(VERD + "    +:+    +:+          +:+           :+:+:+  +:+      +:+            +:+    +:+ ");
                        System.out.println(VERD + "   +#++:++#+           +#+           +#+ +:+ +#+      :#:            +#+    +:+   ");
                        System.out.println(VERD + "  +#+    +#+          +#+           +#+  +#+#+#      +#+   +#+#     +#+    +#+    ");
                        System.out.println(VERD + " #+#    #+#          #+#           #+#   #+#+#      #+#    #+#     #+#    #+#    ");
                        System.out.println(VERD + "#########       ###########       ###    ####       ########       ########     " + RESET);
                    }
                }
            }
        }
        return bingo;
    }
}
