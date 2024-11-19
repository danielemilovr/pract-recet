import java.io.IOException;

public class LibroDeRecetas {

    public LibroDeRecetas(int maxRecetasEnLibro) {
        // Inicialización del libro de recetas
    }

    public boolean agregarReceta(Receta receta) {
        // Añade una receta al libro de recetas
        return false; // @todo MODIFICAR PARA DEVOLVER SI SE HA AÑADIDO LA RECETA
    }

    public Receta[] buscarRecetaPorNombre(String texto) {
        // Busca recetas por su nombre y devuelve todas las encontradas
        return null; // @todo MODIFICAR PARA DEVOLVER LAS RECETAS ENCONTRADAS
    }

    public void guardarRecetasEnArchivo(String nombreArchivo) throws IOException {
        // Guarda las recetas en un archivo de texto
    }

    public void cargarRecetasDeArchivo(String nombreArchivo, int maxIngredientes, int maxInstrucciones) throws IOException {
        // Carga las recetas desde un archivo de texto
    }

    public boolean recetasCompletas() {
        // Comprueba si el libro de recetas está completo
        return false; // @todo MODIFICAR PARA DEVOLVER SI ESTÁ COMPLETO
    }

    public int numRecetas() {
        // Devuelve el número de recetas en el libro
        return 0; // @todo MODIFICAR PARA DEVOLVER EL NÚMERO DE RECETAS
    }

    public void eliminarReceta(Receta seleccionada) {
        // Elimina una receta del libro de recetas
    }
}

