package view;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Personagem {
    private SimpleIntegerProperty id;
    private SimpleStringProperty nome;
    private SimpleIntegerProperty nivel;
    private SimpleIntegerProperty vida;

    public Personagem(int id, String nome, int nivel, int vida) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.nivel = new SimpleIntegerProperty(nivel);
        this.vida = new SimpleIntegerProperty(vida);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public int getNivel() {
        return nivel.get();
    }

    public void setNivel(int nivel) {
        this.nivel.set(nivel);
    }

    public int getVida() {
        return vida.get();
    }

    public void setVida(int vida) {
        this.vida.set(vida);
    }
}