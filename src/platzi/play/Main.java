package platzi.play;

import platzi.play.contenido.*;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.plataforma.Plataforma;
import platzi.play.util.FileUtils;
import platzi.play.util.ScannerUtils;

import java.util.List;

public class Main {
    public static final String NOMBRE_PLATAFORMA = "Platzi Play";
    public static final String VERSION = "1.0.0";
    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int REPRODUCIR = 6;
    public static final int BUSCAR_POR_TIPO = 7;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);
        cargarPeliculas(plataforma);

        System.out.println("mas de " + plataforma.getDuracionTotal() + " minutos de contenido. ");
        plataforma.getContenidoPromocionable().forEach(promocionable -> System.out.println(promocionable.promocionar()));

        while (true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                    INGRESE UNA DE LAS SIGUIENTES OPCIONES: 
                    1. Agregar contenido
                    2. Mostar todo
                    3. Buscar por titulo
                    4. Buscar por genero
                    5. Ver populares
                    6. Reproducir
                    6. Buscar por tipo de contenido
                    8. Eliminar
                    9. Salir
                    """);
            System.out.println("Opcion elegida: " + opcionElegida);

            switch (opcionElegida) {
                case AGREGAR -> {
                    int tipoDeContenido = ScannerUtils.capturarNumero("Que tipo de contenido quieres agregar:\n 1.Pelicula\n 2.Documental");
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("duracion del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("calificacion del contenido");

                    try {
                        if (tipoDeContenido == 1) {
                            plataforma.agregar(new Pelicula(nombre, duracion, genero, calificacion));
                        }else {
                            String narrador = ScannerUtils.capturarTexto("Narrador del documental: ");
                            plataforma.agregar(new Documental(nombre, duracion, genero, calificacion,narrador));
                        }

                    } catch (PeliculaExistenteException e) {
                        System.out.println(e.getMessage());
                    }


                }
                case MOSTRAR_TODO -> {
                    List<ResumenContenido> contenidosResumidos = plataforma.getResumenes();
                    contenidosResumidos.forEach(resumen -> System.out.println(resumen.toString()));
                }
                case BUSCAR_POR_TITULO -> {
                    String nombreBuscado = ScannerUtils.capturarTexto("Nombre del contenido a buscar ");
                    Contenido contenido = plataforma.buscarPorTitulo(nombreBuscado);
                    if (contenido != null) {
                        System.out.println(contenido.obtnerFichaTecnica());
                    } else {
                        System.out.println(nombreBuscado + " no existe dentro de " + plataforma.getNombre());
                    }
                }
                case BUSCAR_POR_GENERO -> {
                    Genero generoBuscado = ScannerUtils.capturarGenero("Genero del contenido a buscar ");
                    List<Contenido> contenidoPorGenero = plataforma.buscarPorGenero(generoBuscado);
                    System.out.println(contenidoPorGenero.size() + " encontrados para el genero " + generoBuscado);
                    contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtnerFichaTecnica() + "\n"));
                }
                case VER_POPULARES -> {
                    int cantidad = ScannerUtils.capturarNumero("Cantidad de elementos a mostrar ");
                    List<Contenido> contenidoPopulares = plataforma.getPopulares(cantidad);
                    contenidoPopulares.forEach(contenido -> System.out.println(contenido.obtnerFichaTecnica() + "\n"));
                }
                case REPRODUCIR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido a reproducir ");
                    Contenido contenido = plataforma.buscarPorTitulo(nombre);

                    if (contenido != null) {
                        plataforma.reproducir(contenido);
                    } else {
                        System.out.println(nombre + " no existe dentro de " + plataforma.getNombre());
                    }
                }
                case BUSCAR_POR_TIPO -> {
                    int tipoDeContenido = ScannerUtils.capturarNumero("Que tipo de contenido quieres agregar:\n 1.Pelicula\n 2.Documental");
                    if (tipoDeContenido == 1) {
                        List<Pelicula> peliculas=plataforma.getPeliculas();
                        peliculas.forEach(pelicula -> System.out.println(pelicula.obtnerFichaTecnica()+"\n"));
                    }else {
                        List<Documental> documentales=plataforma.getDocumental();
                        documentales.forEach(documental -> System.out.println(documental.obtnerFichaTecnica()+"\n"));
                    }
                }
                case ELIMINAR -> {
                    String nombreAEliminar = ScannerUtils.capturarTexto("Nombre del contenido a eliminar ");
                    Contenido contenido = plataforma.buscarPorTitulo(nombreAEliminar);
                    if (contenido != null) {
                        plataforma.eliminar(contenido);
                        System.out.println(nombreAEliminar + " eliminado!");
                    } else {
                        System.out.println(nombreAEliminar + " no existe");
                    }
                }
                case SALIR -> System.exit(0);
            }

        }

//        String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
//        String genero = ScannerUtils.capturarTexto("genero del contenido");
//        int duracion = ScannerUtils.capturarNumero("duracion del contenido");
//        double calificacion = ScannerUtils.capturarDecimal("calificacion del contenido");
//
//        Contenido pelicula = new Contenido(nombre, duracion, genero, calificacion);
//        Contenido pelicula2 = new Contenido("Avatar", 300, "fantasia" );
//
//        plataforma.agregar(pelicula);
//        plataforma.agregar(pelicula2);
//
//        System.out.println("Numero de elementos en la plataforma: "+plataforma.getContenido().size() );
//        plataforma.eliminar(pelicula2);
//        plataforma.mostrarTitulos();
//
//        System.out.println(pelicula.obtnerFichaTecnica());
//
//
//        Usuario usuario = new Usuario("Juan", "juan@mail.com");
//
//        usuario.ver(pelicula);
//
//        System.out.println(pelicula.obtnerFichaTecnica());
    }

    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.getContenido().addAll(FileUtils.leerContenido());
    }
}
