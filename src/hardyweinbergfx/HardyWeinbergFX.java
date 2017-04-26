package hardyweinbergfx;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author KusyMat
 */
public class HardyWeinbergFX extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Slider suwak_A = zrobSuwak();
        TextField wartosc_A = zrobTextField();
        Label label_A = new Label ("Frekwencja allelu A");
        
         Slider suwak_a = zrobSuwak();
        TextField wartosc_a = zrobTextField();
        Label label_a = new Label ("Frekwencja allelu a");
        
        StringConverter<Number> tlumacz = new NumberStringConverter();
        
        Bindings.bindBidirectional(wartosc_A.textProperty(), suwak_A.valueProperty(), tlumacz);
        Bindings.bindBidirectional(wartosc_a.textProperty(), suwak_a.valueProperty(), tlumacz);
        
        suwak_a.setOnMousePressed((event) -> {
            suwak_a.valueProperty().unbind();
            suwak_A.valueProperty().bind(suwak_a.valueProperty().subtract(1.0).negate());
        });
        suwak_A.setOnMousePressed((event) -> {
           suwak_A.valueProperty().unbind();
           suwak_a.valueProperty().bind(suwak_A.valueProperty().subtract(1.0).negate());
        });
        
        wartosc_a.setOnKeyPressed((event) -> {
           suwak_a.valueProperty().unbind();
           suwak_A.valueProperty().bind(suwak_a.valueProperty().subtract(1.0).negate());
        });
        
        wartosc_A.setOnKeyPressed((event) -> {
           suwak_A.valueProperty().unbind();
           suwak_a.valueProperty().bind(suwak_A.valueProperty().subtract(1.0).negate());
        });
        
        DoubleBinding pAA = suwak_A.valueProperty().multiply(suwak_A.valueProperty());
        DoubleBinding pAa = suwak_A.valueProperty().multiply(suwak_a.valueProperty()).multiply(2);
        DoubleBinding paa = suwak_a.valueProperty().multiply(suwak_a.valueProperty());
        
        DoubleBinding suma = pAA.add(pAa).add(paa);
                
        Label pAATxtLabel = new Label("P AA");
        Label pAALabel = zrobLabel(pAA);
        
        Label pAaTxtLabel = new Label("P Aa");
        Label pAaLabel = zrobLabel(pAa);
        
        Label paaTxtLabel = new Label("P aa");
        Label paaLabel = zrobLabel(paa);
        
        Label sumaTxtLabel = new Label("Suma");
        Label sumaLabel = zrobLabel(suma);
        
        GridPane root = new GridPane();
        root.setPadding(new Insets(5));
         root.setHgap(10);
        root.setVgap(10);
        
        root.add(label_A, 0, 1);
        root.add(suwak_A, 1, 1);
        root.add(wartosc_A, 2, 1);
        
        root.add(label_a, 0, 2);
        root.add(suwak_a, 1, 2);
        root.add(wartosc_a, 2, 2);
        
        root.add(pAATxtLabel, 3, 1);
        root.add(pAALabel, 4, 1);
        
        root.add(pAaTxtLabel, 3, 2);
        root.add(pAaLabel, 4, 2);
        
        root.add(paaTxtLabel, 3, 3);
        root.add(paaLabel, 4, 3);
        
        root.add(sumaTxtLabel, 3, 4);
        root.add(sumaLabel, 4, 4);
        
        Scene scene = new Scene(root, 800, 200);
        
        primaryStage.setTitle("Hardy-Weinberg FX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

     public static Slider zrobSuwak() {
        Slider suwak = new Slider(0, 1, 0.5);       
        suwak.setMajorTickUnit(0.1);
        suwak.setMinorTickCount(9);
        suwak.setShowTickMarks(true);
        suwak.setShowTickLabels(true);
        suwak.setMinWidth(500);
        suwak.setBlockIncrement(0.1);
        suwak.setSnapToTicks(true);
        
        return suwak;
    }
    
    
    public static TextField zrobTextField () {
        TextField tf = new TextField();
      
        tf.setMaxWidth(43);
        tf.setMinWidth(43);
        return tf;
    }
    
    public static Label zrobLabel(DoubleBinding v) {
        Label l = new Label();
        l.setMinWidth(50);
        l.setMaxWidth(50);
        l.textProperty().bind(v.asString());
        return l;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
