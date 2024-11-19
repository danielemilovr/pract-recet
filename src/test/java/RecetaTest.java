import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para la clase Receta")
public class RecetaTest {

    @Test
    @DisplayName("Constructor de Receta")
    void constructorReceta() {
        Receta receta = new Receta("Tortilla", 3, 3);
        assertEquals("Tortilla", receta.getNombre(), "El nombre de la receta no es el esperado.");
        assertEquals(3, receta.getMaxIngredientes(), "El número máximo de ingredientes no es el esperado.");
        assertEquals(3, receta.getMaxInstrucciones(), "El número máximo de instrucciones no es el esperado.");
    }

    @Test
    @DisplayName("Añadir ingrediente a receta vacía")
    void agregarIngredienteARecetaVacia() {
        Receta receta = new Receta("Tortilla", 3, 3);
        assertTrue(receta.agregarIngrediente("Huevo"), "Debería poder añadir un ingrediente a una receta vacía.");
        assertEquals(1, receta.numIngredientes(),
                "El número de ingredientes debería ser 1 tras añadir un ingrediente a una receta vacía.");
    }

    @Test
    @DisplayName("Añadir ingrediente a receta con ingredientes")
    void agregarIngredienteARecetaConIngredientes() {
        Receta receta = new Receta("Tortilla", 3, 3);
        receta.agregarIngrediente("Huevo");
        assertTrue(receta.agregarIngrediente("Patata"), "Debería poder añadir un ingrediente a una receta no completa.");
        assertEquals(2, receta.numIngredientes(),
                "El número de ingredientes debería ser 2 tras añadir un ingrediente a una receta con un ingrediente.");
    }

    @Test
    @DisplayName("Añadir ingrediente a receta completa")
    void agregarIngredienteARecetaCompleta() {
        Receta receta = new Receta("Tortilla", 1, 3);
        receta.agregarIngrediente("Huevo");
        assertFalse(receta.agregarIngrediente("Sal"),
                "No se debería poder añadir un ingrediente a una receta completa.");
        assertEquals(1, receta.numIngredientes(),
                "El número de ingredientes no debería cambiar si no se añade el ingrediente.");
    }

    @Test
    @DisplayName("Añadir instrucción a receta vacía")
    void agregarInstruccionARecetaVacia() {
        Receta receta = new Receta("Tortilla", 3, 3);
        assertTrue(receta.agregarInstruccion("Batir los huevos"),
                "Debería poder añadir una instrucción a una receta vacía.");
        assertEquals(1, receta.numInstrucciones(),
                "El número de instrucciones debería ser 1 tras añadir una instrucción a una receta vacía.");
    }

    @Test
    @DisplayName("Añadir instrucción a receta con instrucciones")
    void agregarInstruccionARecetaConInstrucciones() {
        Receta receta = new Receta("Tortilla", 3, 3);
        receta.agregarInstruccion("Batir los huevos");
        assertTrue(receta.agregarInstruccion("Añadir sal"),
                "Debería poder añadir una instrucción a una receta no completa.");
        assertEquals(2, receta.numInstrucciones(),
                "El número de instrucciones debería ser 2 tras añadir una instrucción a una receta con una instrucción.");
    }

    @Test
    @DisplayName("Añadir instrucción a receta completa")
    void agregarInstruccionARecetaCompleta() {
        Receta receta = new Receta("Tortilla", 3, 1);
        receta.agregarInstruccion("Batir los huevos");
        assertFalse(receta.agregarInstruccion("Añadir sal"),
                "No se debería poder añadir una instrucción a una receta completa.");
        assertEquals(1, receta.numInstrucciones(),
                "El número de instrucciones no debería cambiar si no se añade la instrucción.");
    }

    @Test
    @DisplayName("Comprobar receta con ingredientes completos")
    void ingredientesCompletos() {
        Receta receta = new Receta("Tortilla", 1, 3);
        receta.agregarIngrediente("Huevo");
        assertTrue(receta.ingredientesCompletos(), "Debería decir que los ingredientes están completos.");
    }

    @Test
    @DisplayName("Comprobar receta con ingredientes no completos")
    void ingredientesNoCompletos() {
        Receta receta = new Receta("Tortilla", 2, 3);
        receta.agregarIngrediente("Huevo");
        assertFalse(receta.ingredientesCompletos(), "Debería decir que los ingredientes no están completos.");
    }

    @Test
    @DisplayName("Comprobar receta con instrucciones completas")
    void instruccionesCompletas() {
        Receta receta = new Receta("Tortilla", 3, 1);
        receta.agregarInstruccion("Batir los huevos");
        assertTrue(receta.instruccionesCompletas(), "Debería decir que las instrucciones están completas.");
    }

    @Test
    @DisplayName("Comprobar receta con instrucciones no completas")
    void instruccionesNoCompletas() {
        Receta receta = new Receta("Tortilla", 3, 2);
        receta.agregarInstruccion("Batir los huevos");
        assertFalse(receta.instruccionesCompletas(), "Debería decir que las instrucciones no están completas.");
    }

    @ParameterizedTest
    @CsvSource({
            "Tortilla, Huevo, Patata, Batir los huevos, Freír la patata",
            "Tarta de queso, Queso, Huevo, Mezclar los ingredientes, Hornear a 180ºC",
            "Ensalada, Lechuga, Tomate, Lavar los ingredientes, Cortar los ingredientes"
    })
    @DisplayName("Formato textual de la receta para representar en pantalla")
    void toStringFormatoCorrecto(String nombre, String ingrediente1, String ingrediente2, String instruccion1, String instruccion2) {
        Receta receta = new Receta(nombre, 3, 3);
        receta.agregarIngrediente(ingrediente1);
        receta.agregarIngrediente(ingrediente2);
        receta.agregarInstruccion(instruccion1);
        receta.agregarInstruccion(instruccion2);
        String expected = String.format("Receta: %s\nIngredientes:\n- %s\n- %s\nInstrucciones:\n1. %s\n2. %s\n", nombre, ingrediente1, ingrediente2, instruccion1, instruccion2);
        assertEquals(expected, receta.toString(), "El formato devuelto por el método toString() no es el correcto.");
    }

    @ParameterizedTest
    @DisplayName("Formato textual de la receta para guardar en archivo (raw)")
    @CsvSource({
            "Tortilla, Huevo, Patata, Batir los huevos, Freír la patata",
            "Tarta de queso, Queso, Huevo, Mezclar los ingredientes, Hornear a 180ºC",
            "Ensalada, Lechuga, Tomate, Lavar los ingredientes, Cortar los ingredientes"
    })
    void toRawStringFormatoCorrecto(String nombre, String ingrediente1, String ingrediente2, String instruccion1, String instruccion2) {
        Receta receta = new Receta(nombre, 3, 3);
        receta.agregarIngrediente(ingrediente1);
        receta.agregarIngrediente(ingrediente2);
        receta.agregarInstruccion(instruccion1);
        receta.agregarInstruccion(instruccion2);
        String expected = String.format("%s\n%s\n%s\nINSTRUCCIONES\n%s\n%s\n-----\n", nombre, ingrediente1, ingrediente2, instruccion1, instruccion2);
        assertEquals(expected, receta.toRawString());
    }
}