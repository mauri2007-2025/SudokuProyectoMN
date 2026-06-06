module com.example.sudokuproyectomn {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sudokuproyectomn to javafx.fxml;
    exports com.example.sudokuproyectomn;
}