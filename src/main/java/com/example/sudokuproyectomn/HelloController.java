package com.example.sudokuproyectomn;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.example.sudokuproyectomn.model.SudokuModel;


public class HelloController {
    @FXML
    private Button btnNuevaPartida;

    @FXML
    private Button btnAyuda;
    @FXML
    private Button btnReiniciar;
    @FXML
    public void nuevaPartida() {

        modelo.limpiarTablero();

        System.out.println("Tablero limpio");

    }
    private SudokuModel modelo;
    public void initialize() {
        modelo = new SudokuModel();
    }

}

