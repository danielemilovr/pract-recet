import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para la clase PlanificadorSemanal")
public class PlanificadorSemanalTest {

    @ParameterizedTest
    @DisplayName("Agregar comida a un día específico")
    @ValueSource(strings = {"Tortilla", "Salmorejo", "Gazpacho Andaluz"})
    void agregarComidaADiaEspecifico(String nombreReceta) {
        PlanificadorSemanal planificador = new PlanificadorSemanal();
        Receta receta = new Receta(nombreReceta, 3, 3);
        planificador.agregarComida(0, receta);
        assertTrue(planificador.toString().contains(nombreReceta));
    }

    @ParameterizedTest
    @DisplayName("Agregar comida a varios días")
    @CsvSource({
            "Tortilla, Ensalada, Gazpacho",
            "Pizza, Salmorejo, Bacalao al pil pil",
            "Nuggets de pollo, Tarta de queso, Ramen"
    })
    void agregarComidaAVariosDias(String nombreReceta, String nombreReceta2, String nombreReceta3) {
        PlanificadorSemanal planificador = new PlanificadorSemanal();
        Receta receta1 = new Receta(nombreReceta, 3, 3);
        Receta receta2 = new Receta(nombreReceta2, 3, 3);
        Receta receta3 = new Receta(nombreReceta3, 3, 3);
        planificador.agregarComida(0, receta1);
        planificador.agregarComida(1, receta2);
        planificador.agregarComida(2, receta3);
        String plan = planificador.toString();
        assertTrue(plan.contains(nombreReceta));
        assertTrue(plan.contains(nombreReceta2));
        assertTrue(plan.contains(nombreReceta3));
    }

    @ParameterizedTest
    @DisplayName("Agregar comida a un día ya ocupado")
    @CsvSource({
            "Tortilla, Ensalada",
            "Pizza, Salmorejo",
            "Nuggets de pollo, Tarta de queso"
    })
    void agregarComidaADiaOcupado(String nombreReceta, String nombreReceta2) {
        PlanificadorSemanal planificador = new PlanificadorSemanal();
        Receta receta1 = new Receta(nombreReceta, 3, 3);
        Receta receta2 = new Receta(nombreReceta2, 3, 3);
        planificador.agregarComida(4, receta1);
        planificador.agregarComida(4, receta2);
        String plan = planificador.toString();
        assertFalse(plan.contains(nombreReceta));
        assertTrue(plan.contains(nombreReceta2));
    }

    @Test
    @DisplayName("Representación textual del planificador semanal sin recetas")
    void planificadorSemanalSinRecetas() {
        String esperado = """
                -----------------------------------------------------------------------------
                 Lunes      Martes     Miércoles  Jueves     Viernes    Sábado     Domingo  \s
                -----------------------------------------------------------------------------
                                                                                            \s
                -----------------------------------------------------------------------------
                
                """;
        PlanificadorSemanal planificador = new PlanificadorSemanal();
        String plan = planificador.toString();
        assertEquals(esperado, plan);
    }

    @Test
    @DisplayName("Representación textual del planificador semanal algunas recetas")
    void planificadorSemanalConRecetas() {
        String esperado = """
                ---------------------------------------------------------------------------------------------------------------------------------------------------
                 Lunes                Martes               Miércoles            Jueves               Viernes              Sábado               Domingo            \s
                ---------------------------------------------------------------------------------------------------------------------------------------------------
                 Tortilla de Patatas                                            Gazpacho Andaluz                                               Tarta de Manzana   \s
                ---------------------------------------------------------------------------------------------------------------------------------------------------
                
                """;
        PlanificadorSemanal planificador = new PlanificadorSemanal();
        Receta receta1 = new Receta("Tortilla de Patatas", 3, 3);
        Receta receta2 = new Receta("Tarta de Manzana", 3, 3);
        Receta receta3 = new Receta("Gazpacho Andaluz", 3, 3);
        planificador.agregarComida(0, receta1);
        planificador.agregarComida(6, receta2);
        planificador.agregarComida(3, receta3);
        String plan = planificador.toString();
        assertEquals(esperado, plan);
    }

    @Test
    @DisplayName("Guardar plan en archivo")
    void guardarPlanEnArchivo() throws IOException {
        PlanificadorSemanal planificador = new PlanificadorSemanal();
        Receta receta1 = new Receta("Tortilla de Patatas", 3, 3);
        Receta receta2 = new Receta("Tarta de Manzana", 3, 3);
        Receta receta3 = new Receta("Gazpacho Andaluz", 3, 3);
        planificador.agregarComida(0, receta1);
        planificador.agregarComida(6, receta2);
        planificador.agregarComida(3, receta3);

        Path ficheroTemporal = Files.createTempFile("semana", "tmp");

        planificador.guardarPlanEnArchivo(ficheroTemporal.toString());

        assertTrue(Files.exists(ficheroTemporal));
        assertTrue(Files.size(ficheroTemporal) > 0);

        String esperado = """
                Lunes: Tortilla de Patatas
                Martes: ---
                Miércoles: ---
                Jueves: Gazpacho Andaluz
                Viernes: ---
                Sábado: ---
                Domingo: Tarta de Manzana
                """;
        String contenido = Files.readString(ficheroTemporal);
        assertEquals(esperado, contenido);
        Files.deleteIfExists(ficheroTemporal);
    }
}