package platzi.play.contenido;

public class Documental extends Contenido implements Promocionable{
    private String narrador;

    public Documental(String titulo, int duracion, Genero genero) {
        super(titulo, duracion, genero);
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo la documental " + getTitulo()+" narrado por "+getNarrador());
    }
    public Documental(String titulo, int duracion, Genero genero, double calificacion, String narrador) {
        super(titulo, duracion, genero, calificacion);
        this.narrador = narrador;
    }

    @Override
    public String promocionar() {
        return "Descubre el documental"+this.getTitulo()+" narrado por "+narrador+". Ahora en platzi play.";
    }
    public String getNarrador() {
        return narrador;
    }


}
