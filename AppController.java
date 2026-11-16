package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import model.Database;
import model.PersonagemRepository;

public class AppController implements Initializable {
    private Database database;
    private PersonagemRepository personagemRepo;
    
    @FXML
    private TextField idField;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField nivelField;

    @FXML
    private TextField vidaField;

    @FXML
    private Button adicionarButton;

    @FXML
    private TableView<view.Personagem> tabela;

    @FXML
    private TableColumn<view.Personagem, Number> idCol;

    @FXML
    private TableColumn<view.Personagem, String> nomeCol;

    @FXML
    private TableColumn<view.Personagem, Number> nivelCol;

    @FXML
    private TableColumn<view.Personagem, Number> vidaCol;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            database = new Database("personagens.sqlite");
            personagemRepo = new PersonagemRepository(database.getConnection());

            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
            nivelCol.setCellValueFactory(new PropertyValueFactory<>("nivel"));
            vidaCol.setCellValueFactory(new PropertyValueFactory<>("vida"));

            tabela.setItems(loadAllPersonagens());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private ObservableList<view.Personagem> loadAllPersonagens() throws Exception {
        ObservableList<view.Personagem> lista = FXCollections.observableArrayList();

        List<model.Personagem> personagens = personagemRepo.listarTodos();

        for (model.Personagem personagem : personagens) {
            lista.add(new view.Personagem(personagem.getId(), personagem.getNome(), personagem.getNivel(), personagem.getVida()));
        }

        return lista;
    }
    
    @FXML
    private void adicionarPersonagem() {
        try {
            String nome = nomeField.getText();
            int nivel = Integer.parseInt(nivelField.getText());
            int vida = Integer.parseInt(vidaField.getText());

            model.Personagem personagem = new model.Personagem(nome, nivel, vida);

            personagemRepo.salvar(personagem);

            tabela.setItems(loadAllPersonagens());

            nomeField.clear();
            nivelField.clear();
            vidaField.clear();

        } catch (NumberFormatException e) {
            System.out.println("Preencha nível e vida com números válidos.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}