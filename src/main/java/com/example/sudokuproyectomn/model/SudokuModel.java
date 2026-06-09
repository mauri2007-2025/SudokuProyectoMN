package com.example.sudokuproyectomn.model;

import java.util.ArrayList;
import java.util.Random;

public class SudokuModel implements Validable {

    private ArrayList<ArrayList<Integer>> tablero;
    private ArrayList<ArrayList<Integer>> solucion;


    public void generarSolucion() {

        solucion = new ArrayList<>();

        solucion.add(new ArrayList<>(java.util.List.of(1,2,3,4,5,6)));
        solucion.add(new ArrayList<>(java.util.List.of(4,5,6,1,2,3)));
        solucion.add(new ArrayList<>(java.util.List.of(2,3,4,5,6,1)));
        solucion.add(new ArrayList<>(java.util.List.of(5,6,1,2,3,4)));
        solucion.add(new ArrayList<>(java.util.List.of(3,4,5,6,1,2)));
        solucion.add(new ArrayList<>(java.util.List.of(6,1,2,3,4,5)));

        mezclarNumeros();
    }

    public int getSolucion(int fila, int columna) {
        return solucion.get(fila).get(columna);
    }

    private void intercambiarNumeros(int num1, int num2) {

        for (int fila = 0; fila < 6; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                int valor = solucion.get(fila).get(columna);

                if (valor == num1) {

                    solucion.get(fila).set(columna, -1);

                } else if (valor == num2) {

                    solucion.get(fila).set(columna, num1);
                }
            }
        }

        for (int fila = 0; fila < 6; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                if (solucion.get(fila).get(columna) == -1) {

                    solucion.get(fila).set(columna, num2);
                }
            }
        }
    }

    private void mezclarNumeros() {

        Random random = new Random();

        for (int i = 0; i < 10; i++) {

            int num1 = random.nextInt(6) + 1;
            int num2 = random.nextInt(6) + 1;

            while (num1 == num2) {
                num2 = random.nextInt(6) + 1;
            }

            intercambiarNumeros(num1, num2);
        }
    }

    public SudokuModel() {

        tablero = new ArrayList<>();

        for (int i = 0; i < 6; i++) {

            ArrayList<Integer> fila = new ArrayList<>();

            for (int j = 0; j < 6; j++) {
                fila.add(0);
            }

            tablero.add(fila);
        }

        generarSolucion();
    }

    public int getValor(int fila, int columna) {
        return tablero.get(fila).get(columna);
    }

    public void setValor(int fila, int columna, int valor) {
        tablero.get(fila).set(columna, valor);
    }

    @Override
    public boolean esMovimientoValido(int fila,
                                      int columna,
                                      int numero) {

        return validarFila(fila, columna, numero)
                && validarColumna(fila, columna, numero)
                && validarBloque(fila, columna, numero);
    }

    private boolean validarFila(int fila, int columna, int numero) {

        for (int col = 0; col < 6; col++) {

            if (col != columna &&
                    tablero.get(fila).get(col) == numero) {

                return false;
            }
        }

        return true;
    }

    private boolean validarColumna(int fila, int columna, int numero) {

        for (int fil = 0; fil < 6; fil++) {

            if (fil != fila &&
                    tablero.get(fil).get(columna) == numero) {

                return false;
            }
        }

        return true;
    }

    private boolean validarBloque(int fila,
                                  int columna,
                                  int numero) {

        int filaInicio = (fila / 2) * 2;
        int columnaInicio = (columna / 3) * 3;

        for (int i = filaInicio; i < filaInicio + 2; i++) {

            for (int j = columnaInicio;
                 j < columnaInicio + 3;
                 j++) {

                if ((i != fila || j != columna) &&
                        tablero.get(i).get(j) == numero) {

                    return false;
                }
            }
        }

        return true;
    }

    Random random = new Random();

    public void generarPartida() {

        limpiarTablero();

        generarSolucion();

        for (int filaInicio = 0; filaInicio < 6; filaInicio += 2) {

            for (int columnaInicio = 0; columnaInicio < 6; columnaInicio += 3) {

                revelarDosPistasBloque(
                        filaInicio,
                        columnaInicio
                );
            }
        }
    }

    private void revelarDosPistasBloque(
            int filaInicio,
            int columnaInicio) {

        Random random = new Random();

        int pistas = 0;

        while (pistas < 2) {

            int fila =
                    filaInicio + random.nextInt(2);

            int columna =
                    columnaInicio + random.nextInt(3);

            if (tablero.get(fila).get(columna) == 0) {

                tablero.get(fila).set(
                        columna,
                        solucion.get(fila).get(columna)
                );

                pistas++;
            }
        }
    }

    public void limpiarTablero() {

        for (int fila = 0; fila < 6; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                tablero.get(fila).set(columna, 0);
            }
        }
    }

    public boolean tableroCompleto() {

        for (int fila = 0; fila < 6; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                if (tablero.get(fila).get(columna)
                        != solucion.get(fila).get(columna)) {

                    return false;
                }
            }
        }

        return true;
    }
}