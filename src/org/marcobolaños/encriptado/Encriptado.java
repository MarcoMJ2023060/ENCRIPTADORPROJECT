/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.marcobolaños.encriptado;

import java.util.Scanner;

/**
 *
 * @author MARCO
 */
public class Encriptado {
     // Definición de un arreglo que representa el alfabeto y los espacios
    private static final char[] ALFABETO = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};
 
    // Método principal que inicia la ejecución del programa
    public static void main(String[] args) {
        // Crear un objeto Scanner para la entrada de datos
        Scanner scanner = new Scanner(System.in);
 
        // Bucle principal del programa
        while (true) {
            // Menú de opciones
            System.out.println("Menu:");
            System.out.println("1. Encriptar");
            System.out.println("2. Desencriptar");
            System.out.println("3. Salir del menú");
            System.out.print("Ingrese su opción: ");
 
            // Leer la opción del usuario
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
 
            // Tomar acciones según la opción seleccionada
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
 
    // Método para encriptar un mensaje ingresado por el usuario
    private static void encriptarMensaje() {
        // Crear un objeto Scanner para la entrada de datos
        Scanner scanner = new Scanner(System.in);
        // Solicitar al usuario que ingrese la palabra a encriptar
        System.out.print("Ingrese la palabra a encriptar (máximo 15 letras): ");
        String palabra = scanner.nextLine().toUpperCase();
 
        // StringBuilder para almacenar el mensaje encriptado
        StringBuilder mensajeEncriptado = new StringBuilder();
 
        // Recorrer cada caracter de la palabra
        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            // Obtener el índice del caracter en el alfabeto
            int indice = obtenerIndice(caracter);
 
            // Agregar el valor encriptado al StringBuilder
            mensajeEncriptado.append(indice + 1).append(" ");
        }
 
        // Imprimir el mensaje encriptado
        System.out.println("Mensaje encriptado: " + mensajeEncriptado.toString().trim());
    }
 
    // Método para desencriptar un mensaje ingresado por el usuario
    private static void desencriptarMensaje() {
        // Crear un objeto Scanner para la entrada de datos
        Scanner scanner = new Scanner(System.in);
        // Solicitar al usuario que ingrese la clave para desencriptar
        System.out.print("Ingrese la clave para desencriptar (por ejemplo, 1 2): ");
        String[] claveStr = scanner.nextLine().split(" ");
 
        // StringBuilder para almacenar el mensaje desencriptado
        StringBuilder mensajeDesencriptado = new StringBuilder();
 
        // Recorrer cada valor de la clave
        for (String valorStr : claveStr) {
            // Convertir el valor a entero
            int valor = Integer.parseInt(valorStr);
            // Obtener el caracter correspondiente al valor
            char caracter = obtenerCaracter(valor);
 
            // Agregar el caracter al StringBuilder
            mensajeDesencriptado.append(caracter);
        }
 
        // Imprimir el mensaje desencriptado
        System.out.println("Mensaje desencriptado: " + mensajeDesencriptado.toString());
    }
 
    // Método para obtener el índice de un caracter en el alfabeto
    private static int obtenerIndice(char caracter) {
        for (int i = 0; i < ALFABETO.length; i++) {
            if (ALFABETO[i] == caracter) {
                return i;
            }
        }
        return ALFABETO.length - 1; // Carácter no válido, debería ser espacio
    }
 
    // Método para obtener el caracter correspondiente a un índice
    private static char obtenerCaracter(int indice) {
        if (indice >= 1 && indice <= ALFABETO.length) {
            return ALFABETO[indice - 1];
        } else {
            return ALFABETO[ALFABETO.length - 1]; // Carácter no válido, debería ser espacio
        }
    }
}

