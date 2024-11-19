public class Receta {

    public Receta(String nombre, int maxIngredientes, int maxInstrucciones) {
        // Inicialización de la receta
    }

    public String getNombre() {
        // Devuelve el nombre de la receta
        return null; // @todo MODIFICAR PARA DEVOLVER EL NOMBRE DE LA RECETA
    }

    public String[] getIngredientes() {
        // Devuelve los ingredientes de la receta
        return null; // @todo MODIFICAR PARA DEVOLVER LOS INGREDIENTES DE LA RECETA
    }

    public String[] getInstrucciones() {
        // Devuelve las instrucciones de la receta
        return null; // @todo MODIFICAR PARA DEVOLVER LAS INSTRUCCIONES DE LA RECETA
    }

    public boolean agregarIngrediente(String ingrediente) {
        // Añade un ingrediente a la receta
        return false; // @todo MODIFICAR PARA DEVOLVER SI SE HA AÑADIDO EL INGREDIENTE
    }

    public boolean agregarInstruccion(String instruccion) {
        // Añade una instrucción a la receta
        return false; // @todo MODIFICAR PARA DEVOLVER SI SE HA AÑADIDO LA INSTRUCCIÓN
    }

    public boolean ingredientesCompletos() {
        // Comprueba si la receta tiene el máximo de ingredientes
        return false; // @todo MODIFICAR PARA DEVOLVER SI ESTÁN COMPLETOS LOS INGREDIENTES
    }

    public boolean instruccionesCompletas() {
        // Comprueba si la receta tiene el máximo de instrucciones
        return false; // @todo MODIFICAR PARA DEVOLVER SI ESTÁN COMPLETAS LAS INSTRUCCIONES
    }

    public int numIngredientes() {
        // Devuelve el número de ingredientes de la receta
        return 0; // @todo MODIFICAR PARA DEVOLVER EL NÚMERO DE INGREDIENTES
    }

    public int numInstrucciones() {
        // Devuelve el número de instrucciones de la receta
        return 0; // @todo MODIFICAR PARA DEVOLVER EL NÚMERO DE INSTRUCCIONES
    }

    @Override
    public String toString() {
        // Devuelve una representación en forma de cadena de la receta
        return null; // @todo MODIFICAR PARA DEVOLVER LA CADENA CORRECTA
    }

    public String toRawString() {
        // Devuelve una representación en forma de cadena de la receta sin formato
        return null; // @todo MODIFICAR PARA DEVOLVER LA CADENA CORRECTA
    }

    public int getMaxIngredientes() {
        // Devuelve el máximo de ingredientes permitidos en la receta
        return 0; // @todo MODIFICAR PARA DEVOLVER EL MÁXIMO DE INGREDIENTES
    }

    public int getMaxInstrucciones() {
        // Devuelve el máximo de instrucciones permitidas en la receta
        return 0; // @todo MODIFICAR PARA DEVOLVER EL MÁXIMO DE INSTRUCCIONES
    }
}

