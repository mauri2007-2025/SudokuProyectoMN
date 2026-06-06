package com.example.sudokuproyectomn.model;

public class SudokuModel implements Validable {

    private int[][] tablero;

    public SudokuModel() {
        tablero = new int[6][6];
    }

    @Override
    public boolean esMovimientoValido(int fila, int columna, int numero) {
        return true;
    }

    public void limpiarTablero() {

        for (int fila = 0; fila < 6; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                tablero[fila][columna] = 0;
            }
        }
    }
}