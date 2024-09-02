/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.marcobolaños.system;

import java.util.Scanner;

/**
 *
 * @author MARCO
 */
public class Encriptador {
    private static final char[] ALFABETO = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};

    // Matrices para la encriptación y desencriptación
    private static final int[][] A = {{1, 2}, {3, 4}};
    private static final int[][] B = {{5, 6}, {7, 8}};
    private static final int[][] C = {{9, 10}, {11, 12}};
    private static final int[][] M = {{13, 14}, {15, 16}};

    // Matriz inversa de M
    private static final double[][] INVERSA_M = {{-8, 7}, {7.5, -6.5}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Encriptar");
            System.out.println("2. Desencriptar");
            System.out.println("3. Salir del menú");
            System.out.print("Ingrese su opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    encriptarMensaje();
                    break;
                case 2:
                    desencriptarMensaje();
                    break;
                case 3:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    private static void encriptarMensaje() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la palabra a encriptar (máximo 15 letras): ");
        String palabra = scanner.nextLine().toUpperCase();

        StringBuilder mensajeEncriptado = new StringBuilder();

        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            int indice = obtenerIndice(caracter);

            // Realizar la multiplicación de la matriz M con la posición en el alfabeto
            int[] resultadoMultiplicacion = multiplicarMatriz(M, obtenerVectorPosicion(indice));

            // Ajustar los resultados para evitar valores negativos
            resultadoMultiplicacion[0] = (resultadoMultiplicacion[0] % (ALFABETO.length * ALFABETO.length) + (ALFABETO.length * ALFABETO.length)) % (ALFABETO.length * ALFABETO.length);
            resultadoMultiplicacion[1] = (resultadoMultiplicacion[1] % (ALFABETO.length * ALFABETO.length) + (ALFABETO.length * ALFABETO.length)) % (ALFABETO.length * ALFABETO.length);

            // Agregar el resultado al mensaje encriptado
            mensajeEncriptado.append(resultadoMultiplicacion[0]).append(" ").append(resultadoMultiplicacion[1]).append(" ");
        }

        System.out.println("Mensaje encriptado: " + mensajeEncriptado.toString().trim());
    }

    private static void desencriptarMensaje() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el mensaje encriptado: ");
        String[] valoresEncriptadosStr = scanner.nextLine().split(" ");

        StringBuilder mensajeDesencriptado = new StringBuilder();

        for (int i = 0; i < valoresEncriptadosStr.length; i += 2) {
            int valorEncriptado1 = Integer.parseInt(valoresEncriptadosStr[i]);
            int valorEncriptado2 = Integer.parseInt(valoresEncriptadosStr[i + 1]);

            // Realizar la multiplicación de la matriz inversa de M con el valor encriptado
            double[] resultadoMultiplicacion = multiplicarMatriz(INVERSA_M, new double[]{valorEncriptado1, valorEncriptado2});

            // Redondear el resultado al número entero más cercano
            int x = (int) Math.round(resultadoMultiplicacion[0]);
            int y = (int) Math.round(resultadoMultiplicacion[1]);

            // Ajustar las coordenadas para evitar valores negativos
            x = (x % ALFABETO.length + ALFABETO.length) % ALFABETO.length;
            y = (y % ALFABETO.length + ALFABETO.length) % ALFABETO.length;

            // Agregar el caracter al mensaje desencriptado
            mensajeDesencriptado.append(obtenerCaracter(x, y));
        }

        System.out.println("Mensaje desencriptado: " + mensajeDesencriptado.toString());
    }

    private static int obtenerIndice(char caracter) {
        for (int i = 0; i < ALFABETO.length; i++) {
            if (ALFABETO[i] == caracter) {
                return i;
            }
        }
        return ALFABETO.length - 1;
    }

    private static int[] obtenerVectorPosicion(int indice) {
        return new int[]{indice / ALFABETO.length, indice % ALFABETO.length};
    }

    private static int[] multiplicarMatriz(int[][] matriz, int[] vector) {
        int[] resultado = new int[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                resultado[i] += matriz[i][j] * vector[j];
            }
        }
        return resultado;
    }

    private static double[] multiplicarMatriz(double[][] matriz, double[] vector) {
        double[] resultado = new double[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                resultado[i] += matriz[i][j] * vector[j];
            }
        }
        return resultado;
    }

    private static char obtenerCaracter(int x, int y) {
        // Asegurar que las coordenadas estén dentro del rango del alfabeto
        x = (x % ALFABETO.length + ALFABETO.length) % ALFABETO.length;
        y = (y % ALFABETO.length + ALFABETO.length) % ALFABETO.length;

        return ALFABETO[x];
    }
}


