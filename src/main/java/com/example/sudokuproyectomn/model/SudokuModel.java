package com.example.sudokuproyectomn.model;

import java.util.ArrayList;

public class SudokuModel implements Validable {

    private ArrayList<ArrayList<Integer>> tablero;

    public SudokuModel() {

        tablero = new ArrayList<>();

        for (int i = 0; i < 6; i++) {

            ArrayList<Integer> fila = new ArrayList<>();

            for (int j = 0; j < 6; j++) {
                fila.add(0);
            }

            tablero.add(fila);
        }
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


    public void generarPartida() {
        setValor(0, 0, 1);
        setValor(0, 3, 4);

        setValor(1, 1, 5);
        setValor(1, 4, 2);

        setValor(2, 2, 4);
        setValor(2, 5, 1);

        setValor(3, 0, 5);
        setValor(3, 3, 2);

        setValor(4, 1, 4);
        setValor(4, 4, 1);

        setValor(5, 2, 2);
        setValor(5, 5, 5);
    }
    public void limpiarTablero() {

        for (int fila = 0; fila < 6; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                tablero.get(fila).set(columna, 0);
            }
        }
    }
}