import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para la clase LibroDeRecetas")
public class LibroDeRecetasTest {

    @Test
    @DisplayName("Agregar receta a libro vacío")
    void agregarRecetaALibroVacio() {
        LibroDeRecetas libro = new LibroDeRecetas(3);
        Receta receta = new Receta("Tortilla", 3, 3);
        assertTrue(libro.agregarReceta(receta), "Se debería poder agregar una receta a un libro vacío.");
        assertEquals(1, libro.numRecetas(), "El libro debería tener una receta.");
    }

    @Test
    @DisplayName("Agregar receta a libro con recetas")
    void agregarRecetaALibroConRecetas() {
        LibroDeRecetas libro = new LibroDeRecetas(3);
        Receta receta1 = new Receta("Tortilla", 3, 3);
        Receta receta2 = new Receta("Ensalada", 3, 3);
        libro.agregarReceta(receta1);
        assertTrue(libro.agregarReceta(receta2), "Se debería poder agregar una receta a un libro que no está lleno.");
        assertEquals(2, libro.numRecetas(), "El libro debería tener dos recetas.");
    }

    @Test
    @DisplayName("Agregar receta a libro completo")
    void agregarRecetaALibroCompleto() {
        LibroDeRecetas libro = new LibroDeRecetas(1);
        Receta receta1 = new Receta("Tortilla", 3, 3);
        Receta receta2 = new Receta("Ensalada", 3, 3);
        libro.agregarReceta(receta1);
        assertFalse(libro.agregarReceta(receta2), "No se debería poder agregar una receta a un libro lleno.");
        assertEquals(1, libro.numRecetas(), "El libro debería tener una receta.");
    }

    @ParameterizedTest
    @DisplayName("Buscar receta por nombre (existente, solo una)")
    @ValueSource(strings = {"Tortilla", "tortilla", "TORTILLA", "orti", "ort"})
    void buscarRecetaPorNombreExistenteUna(String texto) {
        LibroDeRecetas libro = new LibroDeRecetas(3);
        Receta receta1 = new Receta("Tortilla", 3, 3);
        Receta receta2 = new Receta("Ensalada", 3, 3);
        libro.agregarReceta(receta1);
        libro.agregarReceta(receta2);
        Receta[] resultados = libro.buscarRecetaPorNombre(texto);
        assertEquals("Tortilla", resultados[0].getNombre());
    }

    @ParameterizedTest
    @DisplayName("Buscar receta por nombre (existente, varias)")
    @ValueSource(strings = {"Tortilla", "tortilla", "TORTILLA", "orti", "ort"})
    void buscarRecetaPorNombreExistenteVarias(String texto) {
        LibroDeRecetas libro = new LibroDeRecetas(3);
        Receta receta1 = new Receta("Tortilla", 3, 3);
        Receta receta2 = new Receta("Tortilla de patatas", 3, 3);
        Receta receta3 = new Receta("Ensalada", 3, 3);
        libro.agregarReceta(receta1);
        libro.agregarReceta(receta2);
        libro.agregarReceta(receta3);
        Receta[] resultados = libro.buscarRecetaPorNombre(texto);
        assertEquals("Tortilla", resultados[0].getNombre());
        assertEquals("Tortilla de patatas", resultados[1].getNombre());
    }

    @ParameterizedTest
    @DisplayName("Buscar receta por nombre (no existente)")
    @ValueSource(strings = {"Pasta", "pasta", "PASTA", "macarrones", "mac"})
    void buscarRecetaPorNombreNoExistente(String texto) {
        LibroDeRecetas libro = new LibroDeRecetas(3);
        Receta receta1 = new Receta("Tortilla", 3, 3);
        Receta receta2 = new Receta("Ensalada", 3, 3);
        libro.agregarReceta(receta1);
        libro.agregarReceta(receta2);
        Receta[] resultados = libro.buscarRecetaPorNombre(texto);
        assertNull(resultados[0]);
    }

    @ParameterizedTest
    @DisplayName("Buscar receta por nombre (no existente, libro vacío)")
    @ValueSource(strings = {"Pasta", "pasta", "PASTA", "macarrones", "mac"})
    void buscarRecetaPorNombreNoExistenteLibroVacio(String texto) {
        LibroDeRecetas libro = new LibroDeRecetas(3);
        Receta[] resultados = libro.buscarRecetaPorNombre(texto);
        assertNull(resultados[0]);
    }

    @Test
    @DisplayName("Buscar receta por nombre vacío devuelve todas las recetas")
    void buscarRecetaPorNombreVacio() {
        LibroDeRecetas libro = new LibroDeRecetas(3);
        Receta receta1 = new Receta("Tortilla", 3, 3);
        Receta receta2 = new Receta("Ensalada", 3, 3);
        Receta receta3 = new Receta("Tortilla de patatas", 3, 3);
        libro.agregarReceta(receta1);
        libro.agregarReceta(receta2);
        libro.agregarReceta(receta3);
        Receta[] resultados = libro.buscarRecetaPorNombre("");
        assertEquals("Tortilla", resultados[0].getNombre());
        assertEquals("Ensalada", resultados[1].getNombre());
        assertEquals("Tortilla de patatas", resultados[2].getNombre());
    }

    @Test
    @DisplayName("Eliminar receta existente")
    void eliminarRecetaExistente() {
        LibroDeRecetas libro = new LibroDeRecetas(3);
        Receta receta = new Receta("Tortilla", 3, 3);
        libro.agregarReceta(receta);
        libro.eliminarReceta(receta);
        assertEquals(0, libro.numRecetas());
    }

    @Test
    @DisplayName("Eliminar receta no existente")
    void eliminarRecetaNoExistente() {
        LibroDeRecetas libro = new LibroDeRecetas(3);
        Receta receta1 = new Receta("Tortilla", 3, 3);
        Receta receta2 = new Receta("Ensalada", 3, 3);
        libro.agregarReceta(receta1);
        libro.eliminarReceta(receta2);
        assertEquals(1, libro.numRecetas());
    }

    @Test
    @DisplayName("Cargar recetas de archivo")
    void cargarRecetasDeArchivo() {
        String contenido = """
                Tortilla
                Huevo
                Patata
                INSTRUCCIONES
                Batir los huevos
                Freír la patata
                -----
                Ensalada
                Lechuga
                Tomate
                INSTRUCCIONES
                Lavar los ingredientes
                Cortar los ingredientes
                -----
                Gazpacho
                Tomate
                Pepino
                INSTRUCCIONES
                Triturar los ingredientes
                Enfriar en la nevera
                -----
                """;

        try {
            Path archivo = Files.createTempFile("recetas", "txt");
            Files.writeString(archivo, contenido);
            LibroDeRecetas libro = new LibroDeRecetas(5);
            libro.cargarRecetasDeArchivo(archivo.toString(), 5, 5);
            assertEquals(3, libro.numRecetas(), "Debería haber cargado 3 recetas.");
            Receta[] recetas = libro.buscarRecetaPorNombre("Ensalada");
            assertEquals("Ensalada", recetas[0].getNombre(), "Debería haber cargado la receta de la ensalada.");
            assertEquals("Lechuga", recetas[0].getIngredientes()[0], "Debería haber cargado los ingredientes de la ensalada.");
            assertEquals("Tomate", recetas[0].getIngredientes()[1], "Debería haber cargado los ingredientes de la ensalada.");
            assertEquals("Lavar los ingredientes", recetas[0].getInstrucciones()[0], "Debería haber cargado las instrucciones de la ensalada.");
            assertEquals("Cortar los ingredientes", recetas[0].getInstrucciones()[1], "Debería haber cargado las instrucciones de la ensalada.");
            recetas = libro.buscarRecetaPorNombre("Gazpacho");
            assertEquals("Gazpacho", recetas[0].getNombre(), "Debería haber cargado la receta del gazpacho.");
            assertEquals("Tomate", recetas[0].getIngredientes()[0], "Debería haber cargado los ingredientes del gazpacho.");
            assertEquals("Pepino", recetas[0].getIngredientes()[1], "Debería haber cargado los ingredientes del gazpacho.");
            assertEquals("Triturar los ingredientes", recetas[0].getInstrucciones()[0], "Debería haber cargado las instrucciones del gazpacho.");
            assertEquals("Enfriar en la nevera", recetas[0].getInstrucciones()[1], "Debería haber cargado las instrucciones del gazpacho.");
            recetas = libro.buscarRecetaPorNombre("Tortilla");
            assertEquals("Tortilla", recetas[0].getNombre(), "Debería haber cargado la receta de la tortilla.");
            assertEquals("Huevo", recetas[0].getIngredientes()[0], "Debería haber cargado los ingredientes de la tortilla.");
            assertEquals("Patata", recetas[0].getIngredientes()[1], "Debería haber cargado los ingredientes de la tortilla.");
            assertEquals("Batir los huevos", recetas[0].getInstrucciones()[0], "Debería haber cargado las instrucciones de la tortilla.");
            assertEquals("Freír la patata", recetas[0].getInstrucciones()[1], "Debería haber cargado las instrucciones de la tortilla.");
        } catch (IOException e) {
            fail("No debería lanzar excepción al cargar las recetas de un archivo existente.");
        }
    }

    @Test
    @DisplayName("Guardar recetas en archivo")
    void guardarRecetasEnArchivo() {
        try {
            Path archivo = Files.createTempFile("recetas", "txt");
            LibroDeRecetas libro = new LibroDeRecetas(5);
            Receta receta1 = new Receta("Tortilla", 3, 3);
            receta1.agregarIngrediente("Huevo");
            receta1.agregarIngrediente("Patata");
            receta1.agregarInstruccion("Batir los huevos");
            receta1.agregarInstruccion("Freír la patata");
            Receta receta2 = new Receta("Ensalada", 3, 3);
            receta2.agregarIngrediente("Lechuga");
            receta2.agregarIngrediente("Tomate");
            receta2.agregarInstruccion("Lavar los ingredientes");
            receta2.agregarInstruccion("Cortar los ingredientes");
            libro.agregarReceta(receta1);
            libro.agregarReceta(receta2);
            libro.guardarRecetasEnArchivo(archivo.toString());
            assertTrue(Files.exists(archivo), "El archivo debería existir tras guardar las recetas.");
            assertTrue(Files.size(archivo) > 0, "El archivo debería tener contenido tras guardar las recetas.");
            String contenido = Files.readString(archivo);
            String esperado = """
                    Tortilla
                    Huevo
                    Patata
                    INSTRUCCIONES
                    Batir los huevos
                    Freír la patata
                    -----
                    Ensalada
                    Lechuga
                    Tomate
                    INSTRUCCIONES
                    Lavar los ingredientes
                    Cortar los ingredientes
                    -----
                    """;
            assertEquals(esperado, contenido, "El contenido del archivo con las recetas no es correcto.");
        } catch (IOException e) {
            fail("No debería lanzar excepción al guardar las recetas en un archivo.");
        }
    }
}
