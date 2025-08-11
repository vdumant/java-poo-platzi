package platzi.play.contenido;

import java.time.LocalDate;

public abstract class Contenido {
    private String titulo;
    private String descripcion;
    private int duracion;
    private Genero genero;
    private LocalDate fechaEstreno;
    private double calificacion;
    private boolean disponible;

    public Contenido() {
    }

    public Contenido(String titulo, int duracion, Genero genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.fechaEstreno = LocalDate.now();
        this.disponible = true;
    }

    public Contenido(String titulo, int duracion, Genero genero, double calificacion) {
        this(titulo, duracion, genero);
        this.calificar(calificacion);
    }

    public abstract  void reproducir();

    public String obtnerFichaTecnica() {
        return titulo + "(" + fechaEstreno.getYear() + ")\n" +
                "Genero: " + genero + "\n" +
                "Calificacion: " + calificacion + "/5";
    }

    public void calificar(double calificacion) {
        if (calificacion >= 0 && calificacion <= 5) {
            this.calificacion = calificacion;
        }
    }

    public boolean esPopular() {
        return calificacion >= 4;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public Genero getGenero() {
        return genero;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
