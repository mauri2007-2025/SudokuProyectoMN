package com.example.sudokuproyectomn;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import com.example.sudokuproyectomn.model.SudokuModel;

import java.util.ArrayList;
import java.util.Optional;

public class HelloController {

    @FXML
    private TextField txt00;
    @FXML
    private TextField txt01;
    @FXML
    private TextField txt02;
    @FXML
    private TextField txt03;
    @FXML
    private TextField txt04;
    @FXML
    private TextField txt05;
    @FXML
    private TextField txt10;
    @FXML
    private TextField txt11;
    @FXML
    private TextField txt12;
    @FXML
    private TextField txt13;
    @FXML
    private TextField txt14;
    @FXML
    private TextField txt15;
    @FXML
    private TextField txt20;
    @FXML
    private TextField txt21;
    @FXML
    private TextField txt22;
    @FXML
    private TextField txt23;
    @FXML
    private TextField txt24;
    @FXML
    private TextField txt25;
    @FXML
    private TextField txt30;
    @FXML
    private TextField txt31;
    @FXML
    private TextField txt32;
    @FXML
    private TextField txt33;
    @FXML
    private TextField txt34;
    @FXML
    private TextField txt35;
    @FXML
    private TextField txt40;
    @FXML
    private TextField txt41;
    @FXML
    private TextField txt42;
    @FXML
    private TextField txt43;
    @FXML
    private TextField txt44;
    @FXML
    private TextField txt45;
    @FXML
    private TextField txt50;
    @FXML
    private TextField txt51;
    @FXML
    private TextField txt52;
    @FXML
    private TextField txt53;
    @FXML
    private TextField txt54;
    @FXML
    private TextField txt55;

    private ArrayList<TextField> celdas;

    @FXML
    public void nuevaPartida() {

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

        alerta.setTitle("Nueva Partida");
        alerta.setHeaderText("Iniciar nueva partida");
        alerta.setContentText("¿Desea comenzar una nueva partida?");

        Optional<ButtonType> resultado = alerta.showAndWait();

        if (resultado.isPresent() &&
                resultado.get() == ButtonType.OK) {

            modelo.limpiarTablero();
            limpiarEstilos();
            modelo.generarPartida();
            actualizarVista();
            bloquearCasillasIniciales();
            System.out.println("Nueva partida creada");
        }
    }


    private void limpiarVista() {

        for (TextField celda : celdas) {
            celda.clear();
        }
    }

    private void limpiarEstilos() {

        for (TextField celda : celdas) {
            celda.setStyle("");
        }
    }

    @FXML
    private Button btnAyuda;
    @FXML
    private Button btnReiniciar;

    @FXML
    private void usarAyuda() {

    }

    @FXML
    private void reiniciar() {

        modelo.limpiarTablero();
        limpiarVista();
        limpiarEstilos();
        
        for (TextField celda : celdas) {
            celda.setEditable(true);
        }

    }

    private SudokuModel modelo;

    public void initialize() {
        modelo = new SudokuModel();
        celdas = new ArrayList<>();

        celdas.add(txt00);
        celdas.add(txt10);
        celdas.add(txt20);
        celdas.add(txt30);
        celdas.add(txt40);
        celdas.add(txt50);

        celdas.add(txt01);
        celdas.add(txt11);
        celdas.add(txt21);
        celdas.add(txt31);
        celdas.add(txt41);
        celdas.add(txt51);

        celdas.add(txt02);
        celdas.add(txt12);
        celdas.add(txt22);
        celdas.add(txt32);
        celdas.add(txt42);
        celdas.add(txt52);

        celdas.add(txt03);
        celdas.add(txt13);
        celdas.add(txt23);
        celdas.add(txt33);
        celdas.add(txt43);
        celdas.add(txt53);

        celdas.add(txt04);
        celdas.add(txt14);
        celdas.add(txt24);
        celdas.add(txt34);
        celdas.add(txt44);
        celdas.add(txt54);

        celdas.add(txt05);
        celdas.add(txt15);
        celdas.add(txt25);
        celdas.add(txt35);
        celdas.add(txt45);
        celdas.add(txt55);

        for (int indice = 0; indice < celdas.size(); indice++) {

            TextField celda = celdas.get(indice);

            final int fila = indice / 6;
            final int columna = indice % 6;

            celda.textProperty().addListener((obs, viejo, nuevo) -> {

                if (!nuevo.matches("[1-6]?")) {

                    celda.setText(viejo);
                    return;
                }

                if (nuevo.isEmpty()) {

                    modelo.setValor(fila, columna, 0);

                } else {

                    modelo.setValor(fila, columna,
                            Integer.parseInt(nuevo));
                    if (!modelo.esMovimientoValido(
                            fila,
                            columna,
                            Integer.parseInt(nuevo))) {

                        celda.setStyle(
                                "-fx-border-color:red;" +
                                        "-fx-border-width:2;"
                        );

                    } else {

                        celda.setStyle("");
                    }
                }

            });

        }

    }

    private void actualizarVista() {

        int indice = 0;

        for (int fila = 0; fila < 6; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                int valor = modelo.getValor(fila, columna);

                if (valor == 0) {
                    celdas.get(indice).setText("");
                } else {
                    celdas.get(indice).setText(String.valueOf(valor));
                }

                indice++;
            }
        }
    }
    private void bloquearCasillasIniciales() {

        int indice = 0;

        for (int fila = 0; fila < 6; fila++) {

            for (int columna = 0; columna < 6; columna++) {

                if (modelo.getValor(fila, columna) != 0) {

                    celdas.get(indice).setEditable(false);

                } else {

                    celdas.get(indice).setEditable(true);

                }

                indice++;
            }
        }
    }
}