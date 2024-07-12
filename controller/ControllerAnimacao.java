/* ***************************************************************
 * Autor............: Hercules Sampaio Oliveira
 * Matricula........: 202310486
 * Inicio...........: 18/06/2024
 * Ultima alteracao.: 05/07/2024
 * Nome.............: ControllerPrincipal.java
 * Funcao...........: Classe responsavel por controlar a instancia de objetos necessarios
 * na tela e os metodos utilizados na animacao.
 *************************************************************** */

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Kart;

public class ControllerAnimacao implements Initializable {

  Stage stage; // Criacao do Stage da segunda scene
  Scene scene; // Criacao da segunda scene
  Parent root; // Criacao do Parent do Loader de transicao da segunda para a primeira scene

  //Criacao dos ImageViews dos karts que serao passados ao respectico objeto
  @FXML
  private ImageView kartVermelho, kartAzul, kartLaranja, kartVerde, kartBranco, kartRoxo, kartRosa, kartAmarelo;
  //Criacao dos Sliders de controle de velocidade dos karts
  @FXML
  private Slider veloVermelho, veloAzul, veloLaranja, veloVerde, veloBranco, veloRoxo, veloRosa, veloAmarelo;
  //Declaracao das ImageViews dos percursos de cada kart
  @FXML
  private ImageView t1, t2, t3, t4, t5, t6, t7, t8;
  //Declaracao dos botoes de controle de visibilidade dos percursos
  @FXML
  private Button percVermelho, percAzul, percLaranja, percVerde, percBranco, percRoxo, percRosa, percAmarelo;

  @FXML
  private Button botaoVoltar;    // Criacao do botao de voltar para a primeira scene

  @FXML
  private Button botaoReset;     // Criacao do botao de Reset

  private Kart vermelho, azul, laranja, verde, branco, roxo, rosa, amarelo;  //Criacao dos karts
  //Variaveis booleanas que garantem que um kart pode comecar o percurso adquirindo ou liberando um semaforo especifico
  public boolean protetorVAz = true, protetorAzV = true, protetorVB = true, protetorLB = true, protetorVRx = true;
  public boolean protetorAzR = true, protetorLR = true, protetorVdR = true, protetorBR = true, protetorRRx = true;
  public boolean protetorVA = true, protetorVdA = true, protetorRxA = true;
  //Criacao dos semaforos utilizados para comunicacao dos karts
  private Semaphore[] sVAz = new Semaphore[2];     //Array de semaforos vermelho-azul
  private Semaphore[] sVL = new Semaphore[3];      //Array de semaforos vermelho-laranja
  private Semaphore[] sAzL = new Semaphore[2];     //Array de semaforos aul-laranja
  private Semaphore[] sVVd = new Semaphore[6];     //Array de semaforos vermelho-verde
  private Semaphore[] sAzVd = new Semaphore[4];    //Array de semaforos azul-verde
  private Semaphore[] sLVd = new Semaphore[4];     //Array de semaforos laranja-verde
  private Semaphore[] sVB = new Semaphore[2];      //Array de semaforos vermelho-branco
  private Semaphore[] sAzB = new Semaphore[1];     //Array de semaforos azul-branco
  private Semaphore[] sLB = new Semaphore[2];      //Array de semaforos laranja-branco
  private Semaphore[] sBVd = new Semaphore[2];     //Array de semaforos branco-verde
  private Semaphore[] sVRx = new Semaphore[6];     //Array de semaforos vermelho-roxo
  private Semaphore[] sAzRx = new Semaphore[4];    //Array de semaforos azul-roxo
  private Semaphore[] sLRx = new Semaphore[4];     //Array de semaforos laranja-roxo
  private Semaphore[] sVdRx = new Semaphore[4];    //Array de semaforos verde-roxo
  private Semaphore[] sBRx = new Semaphore[3];     //Array de semaforos branco-roxo
  private Semaphore[] sVR = new Semaphore[3];      //Array de semaforos vermelho-rosa
  private Semaphore[] sAzR = new Semaphore[2];     //Array de semaforos azul-rosa
  private Semaphore[] sLR = new Semaphore[1];      //Array de semaforos laranja-rosa
  private Semaphore[] sVdR = new Semaphore[3];     //Array de semaforos verde-rosa
  private Semaphore[] sBR = new Semaphore[1];      //Array de semaforos branco-rosa
  private Semaphore[] sRRx = new Semaphore[3];     //Array de semaforos roxo-rosa
  private Semaphore[] sVA = new Semaphore[5];      //Array de semaforos vermelho-amarelo
  private Semaphore[] sAAz = new Semaphore[2];     //Array de semaforos azul-amarelo
  private Semaphore[] sLA = new Semaphore[4];      //Array de semaforos laranja-amarelo
  private Semaphore[] sVdA = new Semaphore[2];      //Array de semaforos verde-amarelo
  private Semaphore[] sBA = new Semaphore[2];      //Array de semaforos branco-amarelo
  private Semaphore[] sRxA = new Semaphore[4];      //Array de semaforos roxo-amarelo
  private Semaphore[] sAR = new Semaphore[3];      //Array de semaforos rosa-amarelo

  /* ***************************************************************
   * Metodo: initialize
   * Funcao: Inicializar os objetos na tela apos receber os metodos de construcao da animacao
   * Parametros: URL location e ResourceBundle resources
   * Retorno: void
   ***************************************************************** */
  @Override
  public void initialize(URL location, ResourceBundle resources) {

    iniciarThreads();      //Chamada de inicializacao das threads para o initialize()
    iniciarSemaforos();    //Chamada de inicializacao dos semaforos para o initialize()
    //Os percursos iniciam nao visiveis ao usuario quando a animacao comeca
    t1.setVisible(false);
    t2.setVisible(false);
    t3.setVisible(false);
    t4.setVisible(false);
    t5.setVisible(false);
    t6.setVisible(false);
    t7.setVisible(false);
    t8.setVisible(false);
    
  }

  /* ***************************************************************
   * Metodo: iniciarThreads
   * Funcao: Instancia os karts que percorrem na tela da animacao e passa o controller
   * Parametros: nao ha parametros
   * Retorno: void
   ***************************************************************** */
  public void iniciarThreads(){

    this.vermelho = new Kart(kartVermelho, 0);
    vermelho.setController(this);

    this.azul = new Kart(kartAzul, 1);
    azul.setController(this);

    this.laranja = new Kart(kartLaranja, 2);
    laranja.setController(this);

    this.verde = new Kart(kartVerde, 3);
    verde.setController(this);

    this.branco = new Kart(kartBranco, 4);
    branco.setController(this);

    this.roxo = new Kart(kartRoxo, 5);
    roxo.setController(this);

    this.rosa = new Kart(kartRosa, 6);
    rosa.setController(this);

    this.amarelo = new Kart(kartAmarelo, 7);
    amarelo.setController(this);

    vermelho.start();
    azul.start();
    laranja.start();
    verde.start();
    branco.start();
    roxo.start();
    rosa.start();
    amarelo.start();

  }

  /* ***************************************************************
   * Metodo: iniciarSemaforos
   * Funcao: Instancia os semaforos usados no controller da animacao em cada posicao do vetor
   * correspondente ao conjunto de semaforos
   * Parametros: nao ha parametros
   * Retorno: void
   ***************************************************************** */
  public void iniciarSemaforos(){

    for(int i=0; i<sVAz.length; i++){
      sVAz[i] = new Semaphore(1);
    }

    for(int i=0; i<sVL.length; i++){
      sVL[i] = new Semaphore(1);
    }

    for(int i=0; i<sAzL.length; i++){
      sAzL[i] = new Semaphore(1);
    }

    for(int i=0; i<sVVd.length; i++){
      sVVd[i] = new Semaphore(1);
    }

    for(int i=0; i<sAzVd.length; i++){
      sAzVd[i] = new Semaphore(1);
    }

    for(int i=0; i<sLVd.length; i++){
      sLVd[i] = new Semaphore(1);
    }

    for(int i=0; i<sVB.length; i++){
      sVB[i] = new Semaphore(1);
    } 

    for(int i=0; i<sAzB.length; i++){
      sAzB[i] = new Semaphore(1);
    } 

    for(int i=0; i<sLB.length; i++){
      sLB[i] = new Semaphore(1);
    } 

    for(int i=0; i<sBVd.length; i++){
      sBVd[i] = new Semaphore(1);
    }

    for(int i=0; i<sVRx.length; i++){
      sVRx[i] = new Semaphore(1);
    }

    for(int i=0; i<sAzRx.length; i++){
      sAzRx[i] = new Semaphore(1);
    }

    for(int i=0; i<sLRx.length; i++){
      sLRx[i] = new Semaphore(1);
    }

    for(int i=0; i<sVdRx.length; i++){
      sVdRx[i] = new Semaphore(1);
    }

    for(int i=0; i<sBRx.length; i++){
      sBRx[i] = new Semaphore(1);
    }
     
    for(int i=0; i<sVR.length; i++){
      sVR[i] = new Semaphore(1);
    }
     
    for(int i=0; i<sAzR.length; i++){
      sAzR[i] = new Semaphore(1);
    }

    for(int i=0; i<sLR.length; i++){
      sLR[i] = new Semaphore(1);
    }

    for(int i=0; i<sVdR.length; i++){
      sVdR[i] = new Semaphore(1);
    }

    for(int i=0; i<sBR.length; i++){
      sBR[i] = new Semaphore(1);
    }

    for(int i=0; i<sRRx.length; i++){
      sRRx[i] = new Semaphore(1);
    }

    for(int i=0; i<sVA.length; i++){
      sVA[i] = new Semaphore(1);
    }

    for(int i=0; i<sAAz.length; i++){
      sAAz[i] = new Semaphore(1);
    }

    for(int i=0; i<sLA.length; i++){
      sLA[i] = new Semaphore(1);
    }

    for(int i=0; i<sVdA.length; i++){
      sVdA[i] = new Semaphore(1);
    }

    for(int i=0; i<sBA.length; i++){
      sBA[i] = new Semaphore(1);
    }

    for(int i=0; i<sRxA.length; i++){
      sRxA[i] = new Semaphore(1);
    }

    for(int i=0; i<sAR.length; i++){
      sAR[i] = new Semaphore(1);
    }

  }

  /* ***************************************************************
   * Metodo: botaoVoltar
   * Funcao: Suporta o clique para que a acao de transicao de scene seja executada e o arquivo fxml seja
   * carregado, bem como reinicia os parametros de operacao dos metodos utilizados.
   * Parametros: ActionEvent event, para que a acao seja suportada e execute as linhas de codigo corretamente
   * Retorno: void
   ***************************************************************** */
  @FXML
  public void botaoVoltar(ActionEvent e) throws IOException{

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmlPrincipal.fxml")); // Carrega o arquivo fxml
    root = loader.load();  // Carrega a primeira scene
    scene = new Scene(root);  // Passa o valor do Parent para a primeira scene
    stage = (Stage) botaoVoltar.getScene().getWindow();  // Atribui a acao do botao a iniciar a scene anterior no Stage
    stage.setScene(scene);  // Adiciona a scene no Stage
    stage.show();  // Inicia o Stage

  }

  /* ***************************************************************
   * Metodo: botaoReset
   * Funcao: Reinicia os parametros de operacao dos metodos utilizados
   * Parametros: ActionEvent event, para que a acao seja suportada e execute as linhas de codigo corretamente
   * Retorno: void
   ****************************************************************** */
  @FXML
  private void botaoReset(ActionEvent e) throws IOException{
    //Encerramento seguido da reposicao de novas threads no reset do programa
    Kart.rodando = false;
    Kart.rodando = true;

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxmlAnimacao.fxml")); // Carrega o arquivo fxml
    root = loader.load(); // Carrega a scene novamente
    scene = new Scene(root); // Passa o valor do Parent para a nova scene
    stage = (Stage) botaoReset.getScene().getWindow(); // Atribui a acao do botao de reset a nova scene no Stage
    stage.setScene(scene); // Adiciona a scene no Stage
    stage.show(); // Inicia o Stage

  }

  /* ***************************************************************
   * Metodo: percorrer
   * Funcao: Faz um switch do int que representa o numero do kart e dentro do respectivo case
   * executa os passos de movimentacao e comunicacao necessarios
   * Parametros: int numero, que representa uma especie de identificador dos karts
   * Retorno: void
   ***************************************************************** */
  public void percorrer(int numero) throws InterruptedException{

    switch(numero){

      case 0:{
        //O semaforo vermelho-azul-0 comeca adquirido pelo vermelho a fim de evitar conflito na volta inicial
        if(protetorVAz){sVAz[0].acquire();}
        //O semaforo vermelho-branco-0 comeca adquirido pelo vermelho a fim de evitar conflito na volta inicial
        if(protetorVB){sVB[0].acquire();}
        
        vermelho.rotacionar(90);
        vermelho.moveDir(100);
        sVVd[0].acquire();                  //semaforo vermelho-verde-0
        vermelho.moveDir(155);
        sVRx[0].acquire();                  //semaforo vermelho-roxo-0
        sVA[0].acquire();                   //semaforo vermelho-amarelo-0
        vermelho.moveDir(243);
        sVRx[0].release();                  //semaforo vermelho-roxo-0
        sVB[0].release();                   //semaforo vermelho-branco-0
        vermelho.moveDir(161);
        sVVd[0].release();                  //semaforo vermelho-verde-0
        sVA[0].release();                   //semaforo vermelho-amarelo-0
        vermelho.moveDir(120);
        vermelho.rotacionar(180);
        vermelho.moveBaixo(135);
        sVL[0].acquire();                  //semaforo vermelho-laranja-0
        vermelho.moveBaixo(165);
        sVRx[1].acquire();                 //semaforo vermelho-roxo-1
        sVVd[1].acquire();                 //semaforo vermelho-verde-1
        vermelho.moveBaixo(50);
        vermelho.rotacionar(-90);
        sVAz[0].release();                 //semaforo vermelho-azul-0
        sVL[0].release();                  //semaforo vermelho-laranja-0
        vermelho.moveEsq(120);
        sVA[1].acquire();                  //semaforo vermelho-amarelo-1
        vermelho.moveEsq(80);
        sVVd[1].release();                 //semaforo vermelho-verde-1
        sVA[1].release();                  //semaforo vermelho-amarelo-1
        vermelho.moveEsq(88);
        sVB[1].acquire();                  //semaforo vermelho-branco-1
        vermelho.moveEsq(35);
        vermelho.rotacionar(180);
        sVRx[1].release();                 //semaforo vermelho-roxo-1
        vermelho.moveBaixo(132);
        sVL[1].acquire();                 //semaforo vermelho-laranja-1
        sVRx[2].acquire();                //semaforo vermelho-roxo-2
        sVR[0].acquire();                 //semaforo vermelho-rosa-0
        vermelho.moveBaixo(42);
        vermelho.rotacionar(90);
        sVB[1].release();                 //semaforo vermelho-branco-1
        vermelho.moveDir(120);
        sVVd[2].acquire();                //semaforo vermelho-verde-2
        sVA[2].acquire();                 //semaforo vermelho-amarelo-2
        vermelho.moveDir(40);
        vermelho.rotacionar(180);
        sVL[1].release();                  //semaforo vermelho-laranja-1
        sVRx[2].release();                 //semaforo vermelho-roxo-2
        sVR[0].release();                  //semaforo vermelho-rosa-0
        vermelho.moveBaixo(170);
        vermelho.rotacionar(90);
        sVVd[2].release();                 //semaforo vermelho-verde-2
        sVA[2].release();                  //semaforo vermelho-amarelo-2
        vermelho.moveDir(125);
        sVAz[1].acquire();                 //semaforo vermelho-azul-1
        sVR[1].acquire();                  //semaforo vermelho-rosa-1
        vermelho.moveDir(37);
        vermelho.rotacionar(180);
        vermelho.moveBaixo(176);
        vermelho.rotacionar(-90);
        vermelho.moveEsq(120);
        sVVd[3].acquire();                 //semaforo vermelho-verde-3
        sVA[3].acquire();                  //semaforo vermelho-amarelo-3
        vermelho.moveEsq(163);
        sVRx[3].acquire();                 //semaforo vermelho-roxo-3
        vermelho.moveEsq(40);
        vermelho.rotacionar(0);
        sVAz[1].release();                  //semaforo vermelho-azul-1
        sVVd[3].release();                  //semaforo vermelho-verde-3
        sVR[1].release();                   //semaforo vermelho-rosa-1
        sVA[3].release();                   //semaforo vermelho-amarelo-3
        vermelho.moveCima(175);
        vermelho.rotacionar(-90);
        sVRx[3].release();                  //semaforo vermelho-roxo-3
        vermelho.moveEsq(120);
        sVRx[4].acquire();                  //semaforo vermelho-roxo-4
        sVR[2].acquire();                   //semaforo vermelho-rosa-2
        sVVd[4].acquire();                  //semaforo vermelho-verde-4
        sVA[4].acquire();                   //semaforo vermelho-amarelo-4
        vermelho.moveEsq(40);
        vermelho.rotacionar(180);
        vermelho.moveBaixo(140);
        sVAz[0].acquire();                  //semaforo vermelho-azul-0
        protetorVAz = false;                //Confirma a passagem na primeira volta para o protetorVAz
        protetorVB = false;                 //Confirma a passagem na primeira volta para o protetorVB
        vermelho.moveBaixo(36);
        vermelho.rotacionar(-90);
        sVRx[4].release();                  //semaforo vermelho-roxo-4
        sVA[4].release();                   //semaforo vermelho-amarelo-4
        vermelho.moveEsq(190);
        sVVd[4].release();                  //semaforo vermelho-verde-4
        vermelho.moveEsq(134);
        vermelho.rotacionar(0);
        vermelho.moveCima(311);
        sVVd[5].acquire();                  //semaforo vermelho-verde-5
        sVL[2].acquire();                   //semaforo vermelho-laranja-2
        sVB[0].acquire();                   //semaforo vermelho-branco-0
        sVRx[5].acquire();                  //semaforo vermelho-roxo-5
        vermelho.moveCima(80);
        sVR[2].release();                   //semaforo vermelho-rosa-2
        vermelho.moveCima(164);
        sVVd[5].release();                  //semaforo vermelho-verde-5
        sVRx[5].release();                  //semaforo vermelho-roxo-5
        vermelho.moveCima(180);
        sVL[2].release();                   //semaforo vermelho-laranja-2
        vermelho.moveCima(135);
        vermelho.setX(0);
        vermelho.setY(0);

        break;

      }

      case 1:{

        azul.rotacionar(0);
        azul.moveCima(175);
        //O semaforo vermelho-azul-1 nao recebe release na volta inicial a fim de evitar conflito
        if(!protetorAzV){sVAz[1].release();}
        azul.moveCima(100);
        sVAz[0].acquire();                //semaforo vermelho-azul-0
        sAzL[0].acquire();                //semaforo azul-laranja-0
        sAzVd[0].acquire();               //semaforo azul-verde-0
        sAzRx[0].acquire();               //semaforo-azul-roxo-0
        azul.moveCima(75);
        //O semaforo azul-rosa-1 nao recebe release na volta inicial a fim de evitar conflito
        if(!protetorAzR){sAzR[1].release();}
        azul.moveCima(180);
        sAzVd[0].release();               //semaforo azul-verde-0
        sAzRx[0].release();               //semaforo azul-roxo-0
        azul.moveCima(170);
        sAzL[0].release();                //semaforo azul-laranja-0
        azul.moveCima(139);
        protetorAzV = false;              //Confirma a passagem na primeira volta para o protetorAzV
        azul.rotacionar(-90);
        azul.moveEsq(125);
        sAzVd[1].acquire();                //semaforo azul-verde-1
        sAAz[0].acquire();                 //semaforo azul-amarelo-0
        azul.moveEsq(159);
        sAzB[0].acquire();                 //semaforo azul-branco-0
        sAzRx[1].acquire();                //semaforo azul-roxo-1
        azul.moveEsq(232);
        sAzRx[1].release();                //semaforo azul-roxo-1
        sAAz[0].release();                 //semaforo azul-amarelo-0
        azul.moveEsq(161);
        sAzVd[1].release();                //semaforo azul-verde-1
        azul.moveEsq(130);
        azul.rotacionar(180);
        azul.moveBaixo(130);
        sAzL[1].acquire();                 //semaforo azul-laranja-1
        azul.moveBaixo(175);
        sAzVd[2].acquire();                //semaforo azul-verde-2
        sAzRx[2].acquire();                //semaforo azul-roxo-2
        azul.moveBaixo(176);
        sAzR[0].acquire();                 //semaforo azul-rosa-0
        protetorAzR = false;               //Confirma a passagem na primeira volta para o protetorAzR
        azul.moveBaixo(74);
        sAzL[1].release();                 //semaforo azul-laranja-1
        sAzVd[2].release();                //semaforo azul-verde-2
        sAzB[0].release();                 //semaforo azul-branco-0
        sAzRx[2].release();                //semaforo azul-roxo-2
        azul.moveBaixo(110);
        sAAz[1].acquire();                 //semaforo azul-amarelo-1
        azul.moveBaixo(206);
        azul.rotacionar(90);
        azul.moveDir(125);
        sAzVd[3].acquire();                //semaforo azul-verde-3
        azul.moveDir(160);
        sVAz[1].acquire();                 //semaforo vermelho-azul-1
        sAzRx[3].acquire();                //semaforo azul-roxo-3
        azul.moveDir(80);
        sAzR[0].release();                 //semaforo azul-rosa-0-rateio
        sVAz[0].release();                 //semaforo vermelho-azul-0
        sAzR[1].acquire();                 //semaforo azul-rosa-1-rateio
        azul.moveDir(147);
        sAzRx[3].release();                //semaforo azul-roxo-3
        azul.moveDir(175);
        sAzVd[3].release();                //semaforo azul-verde-3
        sAAz[1].release();                 //semaforo azul-amarelo-1
        azul.moveDir(120);
        azul.setX(0);
        azul.setY(0);

        break;

      }

      case 2:{

        laranja.rotacionar(90);
        laranja.moveDir(90);
        sLVd[0].acquire();                   //semaforo laranja-verde-0
        laranja.moveDir(75);
        sLVd[0].release();                   //semaforo laranja-verde-0
        laranja.moveDir(85);           
        sLRx[0].acquire();                   //semaforo laranja-roxo-0
        sLA[0].acquire();                    //semaforo laranja-amarelo-0
        laranja.moveDir(70);           
        sLRx[0].release();                   //semaforo laranja-roxo-0
        sLA[0].release();                    //semaforo laranja-amarelo-0
        laranja.moveDir(95);           
        sLB[1].acquire();                    //semaforo laranja-branco-1
        sLRx[1].acquire();                   //semaforo laranja-roxo-1
        laranja.moveDir(69);
        sLB[1].release();                    //semaforo laranja-branco-1
        sLRx[1].release();                   //semaforo laranja-roxo-1             
        laranja.moveDir(85);             
        sLVd[1].acquire();                   //semaforo laranja-verde-1
        sLA[1].acquire();                    //semaforo laranja-amarelo-1
        laranja.moveDir(70);
        sLVd[1].release();                   //semaforo laranja-verde-1        
        sLA[1].release();                    //semaforo laranja-amarelo-0    
        laranja.moveDir(96);             
        sVL[0].acquire();                    //semaforo vermleho-laranja-0
        sAzL[0].acquire();                   //semaforo azul-laranja-0
        laranja.moveDir(36);
        laranja.rotacionar(180);
        laranja.moveBaixo(130);
        sLRx[2].acquire();                   //semaforo laranja-roxo-2
        sVL[1].acquire();                    //semaforo vermelho-laranja-1
        sLVd[2].acquire();                   //semaforo laranja-verde-2
        laranja.moveBaixo(81);
        sVL[0].release();                    //semaforo vermelho-laranja-0
        laranja.moveBaixo(96);
        sLR[0].acquire();                    //semaforo laranja-rosa-0
        laranja.moveBaixo(39);
        laranja.rotacionar(-90);
        sAzL[0].release();                   //semaforo azul-laranja-0
        laranja.moveEsq(125);
        sLA[2].acquire();                    //semaforo laranja-amarelo-2
        laranja.moveEsq(75);
        sLVd[2].release();                   //semaforo laranja-verde-2
        sLA[2].release();                    //semaforo laranja-amarelo-2
        laranja.moveEsq(80);
        sLB[0].acquire();                    //semaforo laranja-branco-0
        laranja.moveEsq(70);
        sVL[1].release();                    //semaforo vermelho-laranja-1
        sLRx[2].release();                   //semaforo laranja-roxo-2
        laranja.moveEsq(93);      
        sLRx[3].acquire();                   //semaforo laranja-roxo-3    
        sLA[3].acquire();                    //semaforo laranja-amarelo-3
        laranja.moveEsq(74); 
        sLA[3].release();                    //semaforo laranja-amarelo-0     
        laranja.moveEsq(93);       
        sLVd[3].acquire();                   //semaforo laranja-verde-3
        laranja.moveEsq(64);
        laranja.moveEsq(96);
        sAzL[1].acquire();                   //semaforo azul-laranja-1
        sVL[2].acquire();                    //semaforo vermelho-laranja-2
        laranja.moveEsq(34);
        laranja.rotacionar(0);
        sLR[0].release();                    //semaforo laranja-rosa-0
        laranja.moveCima(215);
        sLVd[3].release();                   //semaforo laranja-verde-3
        sLRx[3].release();                   //semaforo laranja-roxo-3
        laranja.moveCima(135);
        sAzL[1].release();                   //semaforo azul-laranja-1
        sVL[2].release();                    //semaforo vermelho-laranja-2
        sLB[0].release();                    //semaforo laranja-branco-0
        laranja.setX(0);
        laranja.setY(0);

        break;

      }

      case 3:{
        //O semaforo verde-amarelo-0 comeca adquirido pelo verde a fim de evitar conflito na volta inicial
        if(protetorVdA){sVdA[0].acquire();}
        verde.rotacionar(0);
        verde.moveCima(108);
        sVVd[2].acquire();             //semaforo vermelho-verde-2
        verde.moveCima(174);
        sLVd[2].acquire();             //semaforo laranja-verde-2
        sVdRx[0].acquire();            //semaforo verde-roxo-0
        sVdR[0].acquire();             //semaforo verde-rosa-0
        verde.moveCima(35);
        verde.rotacionar(90);
        sVVd[2].release();             //semaforo vermelho-verde-2
        sVdA[0].release();             //semaforo verde-amarelo-0
        protetorVdA = false;           //Confirma a passagem na primeira volta para o protetorVdA 
        verde.moveDir(125);
        sAzVd[0].acquire();            //semaforo azul-verde-0
        verde.moveDir(37);
        verde.rotacionar(0);
        sVdR[0].release();             //semaforo verde-rosa-0
        verde.moveCima(130);
        sVVd[1].acquire();             //semaforo vermelho-verde-1
        verde.moveCima(44);
        verde.rotacionar(-90);
        sAzVd[0].release();            //semaforo azul-verde-0
        sLVd[2].release();             //semaforo laranja-verde-2
        verde.moveEsq(122);
        sVdA[1].acquire();             //semaforo verde-amarelo-1
        verde.moveEsq(40);
        verde.rotacionar(0);
        sVVd[1].release();              //semaforo vermelho-verde-1
        sVdRx[0].release();             //semaforo verde-roxo-0
        verde.moveCima(132);
        sLVd[1].acquire();              //semaforo laranja-verde-1
        verde.moveCima(74);
        sLVd[1].release();              //semaforo laranja-verde-1
        verde.moveCima(106);
        sAzVd[1].acquire();             //semaforo azul-verde-1
        sVVd[0].acquire();              //semaforo vermelho-verde-0
        verde.moveCima(35);
        verde.rotacionar(-90);
        verde.moveEsq(122);
        sBVd[1].acquire();              //semaforo branco-verde-1
        sVdRx[1].acquire();              //semaforo verde-roxo-1
        verde.moveEsq(235);
        sVdRx[1].release();             //semaforo verde-roxo-1
        verde.moveEsq(123);
        verde.rotacionar(180);
        sVVd[0].release();              //semaforo vermelho-verde-0
        sAzVd[1].release();             //semaforo azul-verde-1
        sBVd[1].release();              //semaforo branco-verde-1
        sVdA[1].release();              //semaforo verde-amarelo-1
        verde.moveBaixo(138);
        sLVd[0].acquire();              //semaforo laranja-verde-0
        verde.moveBaixo(70);
        sLVd[0].release();              //semaforo laranja-verde-0
        verde.moveBaixo(100);
        sVdRx[2].acquire();             //semaforo verde-roxo-2
        verde.moveBaixo(39);
        verde.rotacionar(-90);
        verde.moveEsq(125);
        sBVd[0].acquire();              //semaforo branco-verde-0
        sAzVd[2].acquire();             //semaforo azul-verde-2
        sLVd[3].acquire();              //semaforo laranja-verde-3
        sVdR[1].acquire();              //semaforo verde-rosa-1
        sVVd[5].acquire();              //semaforo vermelho-verde-5
        verde.moveEsq(38);
        verde.rotacionar(180);
        verde.moveBaixo(137);
        verde.moveBaixo(37);
        verde.rotacionar(90);
        sVVd[5].release();            //semaforo vermelho-verde-5
        sAzVd[2].release();           //semaforo azul-verde-2
        verde.moveDir(162);
        verde.rotacionar(180);
        sLVd[3].release();            //semaforo laranja-verde-3
        sBVd[0].release();            //semaforo branco-verde-0
        sVdRx[2].release();           //semaforo verde-roxo-2
        sVdR[1].release();            //semaforo verde-rosa-1
        verde.moveBaixo(310);
        sVVd[4].acquire();            //semaforo vermelho-verde-4
        sAzVd[3].acquire();           //semafoto azul-verde-3
        sVdR[2].acquire();            //semaforo verde-rosa-2
        verde.moveBaixo(39);
        verde.rotacionar(90);
        verde.moveDir(125);
        sVdRx[3].acquire();           //semaforo verde-roxo-3
        sVdA[0].acquire();            //semaforo verde-amarelo-0
        verde.moveDir(70);
        sVVd[4].release();            //semaforo vermelho-verde-4  
        verde.moveDir(85);
        sVVd[3].acquire();            //semaforo vermelho-verde-3
        verde.moveDir(80);
        sVdRx[3].release();           //semaforo verde-roxo-3
        verde.moveDir(81);
        verde.moveDir(40);
        sVVd[3].release();            //semaforo vermelho-verde-3
        sAzVd[3].release();           //semaforo azul-verde-3
        sVdR[2].release();            //semaforo verde-rosa-2
        verde.setX(0);
        verde.setY(0);

        break;
        
      }

      case 4:{
        //O semaforo laranja-branco-0 comeca adquirido pelo branco a fim de evitar conflito na volta inicial
        if(protetorLB){sLB[0].acquire();}
        //O semaforo branco-rosa-0 comeca adquirido pelo branco a fim de evitar conflito na volta inicial
        if(protetorBR){sBR[0].acquire();}
        branco.rotacionar(-90);
        branco.moveEsq(75);
        sBRx[0].acquire();                  //semaforo branco-roxo-0
        sBA[0].acquire();                   //semaforo branco-amarelo-0
        branco.moveEsq(75);
        sBA[0].release();                   //semaforo branco-amarelo-0
        branco.moveEsq(90);
        sBVd[0].acquire();                  //semaforo branco-verde-0
        branco.moveEsq(160);
        sAzB[0].acquire();                  //semaforo azul-branco-0
        sVB[0].acquire();                   //semaforo vermelho-branco-0
        branco.moveEsq(37);
        branco.rotacionar(0);
        sBR[0].release();                   //semaforo branco-rosa-0
        protetorBR = false;                 //Confirma a passagem na primeira volta para o protetorBR
        branco.moveCima(210);
        sBVd[0].release();                  //semaforo branco-verde-0
        sBRx[0].release();                  //semaforo barnco-roxo-0
        branco.moveCima(180);
        sLB[0].release();                   //semaforo laranja-branco-0
        protetorLB = false;                 //Confirma a passagem na primeira volta para o protetorAzV
        branco.moveCima(132);
        branco.rotacionar(90);
        branco.moveDir(123);
        sBVd[1].acquire();                  //semaforo branco-verde-1
        branco.moveDir(160);
        sBRx[1].acquire();                  //semaforo branco-roxo-1
        sBA[1].acquire();                   //semaforo branco-amarelo-1
        branco.moveDir(200);
        branco.rotacionar(180);
        sVB[0].release();                   //semaforo vermelho-branco-0
        sAzB[0].release();                  //semaforo azul-branco-0
        sBVd[1].release();                  //semaforo branco-verde-1
        sBA[1].release();                   //semaforo branco-amarelo-1
        branco.moveBaixo(137);
        sLB[1].acquire();                   //semaforo laranja-branco-1
        branco.moveBaixo(76);
        sLB[1].release();                   //semaforo laranja-branco-1
        branco.moveBaixo(100);
        sVB[1].acquire();                   //semaforo vermelho-branco-1
        branco.moveBaixo(70);
        sBRx[1].release();                  //semaforo branco-roxo-1
        branco.moveBaixo(100);
        sLB[0].acquire();                   //semaforo laranja-branco-0
        sBRx[2].acquire();                  //semaforo branco-roxo-2
        sBR[0].acquire();                   //semaforo branco-rosa-0
        branco.moveBaixo(42);
        sVB[1].release();                   //semaforo vermelho-branco-1
        sBRx[2].release();                  //semaforo branco-roxo-2
        branco.setX(0);
        branco.setY(0);

        break;

      }

      case 5:{

        roxo.rotacionar(0);
        roxo.moveCima(176);
        //O semaforo vermelho-roxo-3 nao recebe release na volta inicial a fim de evitar conflito
        if(!protetorVRx){sVRx[3].release();}
        roxo.moveCima(100);
        sVRx[2].acquire();                 //semaforo vermelho-roxo-2
        sLRx[2].acquire();                 //semaforo laranja-roxo-2
        sBRx[2].acquire();                 //semaforo branco-roxo-2
        sRRx[0].acquire();                 //semaforo roxo-rosa-0
        roxo.moveCima(42);
        roxo.rotacionar(90);
        sBRx[2].release();                 //semaforo branco-roxo-2
        roxo.moveDir(125);
        sVdRx[0].acquire();                //semaforo verde-roxo-0
        sRxA[0].acquire();                 //semaforo roxo-amarelo-0
        roxo.moveDir(75);
        sVRx[2].release();                 //semaforo vermelho-roxo-2
        sRxA[0].release();                 //semaforo roxo-amarelo-0
        roxo.moveDir(83);
        sAzRx[0].acquire();                //semaforo azul-roxo-0
        roxo.moveDir(40);
        roxo.rotacionar(0);
        sRRx[0].release();                 //semaforo roxo-rosa-0
        roxo.moveCima(132);
        sVRx[1].acquire();                 //semaforo vermelho-roxo-1
        roxo.moveCima(40);
        roxo.rotacionar(-90);
        sAzRx[0].release();                //semaforo azul-roxo-0
        sLRx[2].release();                 //semaforo laranja-roxo-2
        roxo.moveEsq(128);
        sRxA[1].acquire();                 //semaforo roxo-amarelo-1
        roxo.moveEsq(70);
        sVdRx[0].release();                //semaforo verde-roxo-0
        sRxA[1].release();                 //semaforo roxo-amarelo-1
        roxo.moveEsq(85);
        sBRx[1].acquire();                 //semaforo branco-roxo-1
        roxo.moveEsq(40);
        roxo.rotacionar(0);
        sVRx[1].release();                 //semaforo vermelho-roxo-1
        roxo.moveCima(134);
        sLRx[1].acquire();                 //semaforo laranja-roxo-1
        roxo.moveCima(70);
        sLRx[1].release();                 //semaforo laranja-roxo-1
        roxo.moveCima(105);
        sVRx[0].acquire();                 //semaforo vermelho-roxo-0
        sAzRx[1].acquire();                //semaforo azul-roxo-1
        sVdRx[1].acquire();                //semaforo verde-roxo-1
        sRxA[2].acquire();                 //semaforo roxo-amarelo-2
        roxo.moveCima(40);
        roxo.rotacionar(-90);
        roxo.moveEsq(160);
        roxo.rotacionar(180);
        sVRx[0].release();                 //semaforo vermelho-roxo-0
        sAzRx[1].release();                //semaforo azul-roxo-1
        sVdRx[1].release();                //semaforo verde-roxo-1
        sBRx[1].release();                 //semaforo-branco-roxo-1
        roxo.moveBaixo(138);
        sLRx[0].acquire();                 //semaforo laranja-roxo-0
        roxo.moveBaixo(70);
        sLRx[0].release();                 //semaforo laranja-roxo-0
        roxo.moveBaixo(140);
        roxo.rotacionar(-90);
        sRxA[2].release();                 //semaforo roxo-amarelo-2
        roxo.moveEsq(120);
        sVdRx[2].acquire();                //semaforo verde-roxo-2
        roxo.moveEsq(165);
        sBRx[0].acquire();                 //semaforo branco-roxo-0
        sAzRx[2].acquire();                //semaforo azul-roxo-2
        sLRx[3].acquire();                 //semaforo laranja-roxo-3
        sRRx[1].acquire();                 //semaforo roxo-rosa-1
        sVRx[5].acquire();                 //semaforo vermelho-roxo-5
        roxo.moveEsq(37);
        roxo.rotacionar(180);
        roxo.moveBaixo(137);
        roxo.moveBaixo(37);
        roxo.rotacionar(90);
        sVRx[5].release();                 //semaforo vermelho-roxo-5
        sAzRx[2].release();                //semaforo azul-roxo-2
        roxo.moveDir(197);
        sVdRx[2].release();                //semaforo verde-roxo-2
        roxo.moveDir(85);
        sVRx[4].acquire();                 //semaforo vermelho-roxo-4
        sRxA[3].acquire();                 //semaforo roxo-amarelo-3
        roxo.moveDir(40);
        roxo.rotacionar(180);
        sLRx[3].release();                 //semaforo laranja-roxo-3
        sBRx[0].release();                 //semaforo branco-roxo-0
        sRRx[1].release();                 //semaforo roxo-rosa-1
        roxo.moveBaixo(134);
        roxo.moveBaixo(174);
        sAzRx[3].acquire();                //semaforo azul-roxo-3
        sVdRx[3].acquire();                //semaforo verde-roxo-3
        sRRx[2].acquire();                 //semaforo roxo-rosa-2
        roxo.moveBaixo(42);
        roxo.rotacionar(90);
        sVRx[4].release();                 //semaforo vermelho-roxo-4
        roxo.moveDir(100);
        sVRx[3].acquire();                 //semaforo vermelho-roxo-3
        protetorVRx = false;               //Confirma a passagem na primeira volta para o protetorVRx
        roxo.moveDir(60);
        sAzRx[3].release();                //semaforo azul-roxo-3
        sVdRx[3].release();                //semaforo verde-roxo-3
        sRRx[2].release();                 //semaforo roxo-rosa-2
        sRxA[3].release();                 //semaforo roxo-amarelo-3
        roxo.setX(0);
        roxo.setY(0);

        break;

      }

      case 6:{
        //O semaforo laranja-rosa-0 comeca adquirido pelo rosa a fim de evitar conflito na volta inicial
        if(protetorLR){sLR[0].acquire();}
        //O semaforo verde-rosa-0 comeca adquirido pelo rosa a fim de evitar conflito na volta inicial
        if(protetorVdR){sVdR[0].acquire();}
        //O semaforo roxo-rosa-0 comeca adquirido pelo rosa a fim de evitar conflito na volta inicial
        if(protetorRRx){sRRx[0].acquire();}
        rosa.rotacionar(-90);
        rosa.moveEsq(80);
        sVR[0].acquire();               //semaforo vermelho-rosa-0
        sAR[0].acquire();               //semaforo rosa-amarelo-0
        rosa.moveEsq(74);
        sVdR[0].release();              //semaforo verde-rosa-0
        sAR[0].release();               //semaforo rosa-amarelo-0
        protetorVdR = false;            //Confirma a passagem na primeira volta para o protetorVdR
        rosa.moveEsq(92);
        sBR[0].acquire();               //semaforo branco-rosa-0
        rosa.moveEsq(64);
        sVR[0].release();               //semaforo vermelho-rosa-0
        sRRx[0].release();
        protetorRRx = false;            //Confirma a passagem na primeira volta para o protetorRRx
        rosa.moveEsq(93);
        sRRx[1].acquire();              //semaforo roxo-rosa-1
        sAR[1].acquire();               //semaforo rosa-amarelo-1
        sVdR[1].acquire();              //semaforo verde-rosa-1 
        rosa.moveEsq(70);
        sAR[1].release();               //semaforo rosa-amarelo-1
        rosa.moveEsq(92);
        rosa.moveEsq(160);
        sAzR[0].acquire();              //semaforo azul-rosa-0
        sVR[2].acquire();               //semaforo vermelho-rosa-2
        rosa.moveEsq(40);
        rosa.rotacionar(180);
        sLR[0].release();               //semaforo laranja-rosa-0
        protetorLR = false;             //Confirma a passagem na primeira volta para o protetorLR
        sVdR[1].release();              //semaforo verde-rosa-1
        sBR[0].release();               //semaforo branco-rosa-0
        sRRx[1].release();              //semafoto roxo-rosa-1
        rosa.moveBaixo(350);
        rosa.rotacionar(90);
        rosa.moveDir(130);
        sVdR[2].acquire();              //semaforo verde-rosa-2
        rosa.moveDir(160);
        sAzR[0].release();              //semaforo azul-rosa-0-rateio 
        sAzR[1].acquire();              //semaforo azul-rosa-1-rateio
        sRRx[2].acquire();              //semaforo roxo-rosa-2
        sAR[2].acquire();               //semaforo rosa-amarelo-2
        rosa.moveDir(70);
        sVR[2].release();               //semaforo vermelho-rosa-2
        rosa.moveDir(88);
        sVR[1].acquire();               //semaforo vermelho-rosa-1
        rosa.moveDir(70);
        sRRx[2].release();              //semaforo roxo-rosa-2
        rosa.moveDir(166);
        sVdR[2].release();              //semaforo verde-rosa-2
        sAR[2].release();               //semaforo rosa-amarelo-2
        rosa.moveDir(124);
        rosa.rotacionar(0);
        rosa.moveCima(215);
        sVR[1].release();               //semaforo vermelho-rosa-1
        rosa.moveCima(100);
        sVdR[0].acquire();              //semaforo verde-rosa-0
        sLR[0].acquire();               //semaforo laranja-rosa-0
        sRRx[0].acquire();              //semaforo roxo-rosa-0
        rosa.moveCima(35);
        sAzR[1].release();              //semaforo azul-rosa-1
        rosa.setX(0);
        rosa.setY(0);

        break;

      }

      case 7:{
        //O semaforo vermelho-amarelo-4 comeca adquirido pelo amarelo a fim de evitar conflito na volta inicial
        if(protetorVA){sVA[4].acquire();}
        //O semaforo roxo-amarelo-3 comeca adquirido pelo amarelo a fim de evitar conflito na volta inicial
        if(protetorRxA){sRxA[3].acquire();}
        amarelo.rotacionar(0);
        amarelo.moveCima(176);
        sVA[4].release();                    //semaforo vermelho-amarelo-4
        protetorVA = false;                  //Confirma a passagem na primeira volta para o protetorVA
        amarelo.moveCima(100);
        sLA[3].acquire();                    //semaforo laranja-amarelo-3
        sBA[0].acquire();                    //semaforo branco-amarelo-0
        sAR[1].acquire();                    //semaforo rosa-amarelo-1
        amarelo.moveCima(74);
        sLA[3].release();                    //semaforo laranja-amarelo-3
        sBA[0].release();                    //semaforo branco-amarelo-0
        sRxA[3].release();                   //semaforo roxo-amarelo-3
        sAR[1].release();                    //semaforo rosa-amarelo-1
        protetorRxA = false;                 //Confirma a passagem na primeira volta para o protetorRxA
        amarelo.moveCima(103);
        sRxA[2].acquire();                   //semaforo roxo-amarelo-3
        amarelo.moveCima(167);
        sLA[0].acquire();                    //semaforo laranja-amarelo-0
        amarelo.moveCima(78);
        sLA[0].release();                    //semaforo laranja-amarelo-0
        amarelo.moveCima(100);
        sVA[0].acquire();                    //semaforo vermelho-amarelo-0
        sAAz[0].acquire();                   //semaforo azul-amarelo-0
        sVdA[1].acquire();                   //semaforo verde-amarelo-1
        sBA[1].acquire();                    //semaforo branco-amarelo-1
        amarelo.moveCima(40);
        amarelo.rotacionar(90);
        amarelo.moveDir(200);
        sBA[1].release();                    //semaforo branco-amarelo-1
        sRxA[2].release();                   //semaforo roxo-amarelo-2
        amarelo.moveDir(121);
        amarelo.rotacionar(180);
        sVA[0].release();                    //semaforo vermelho-amarelo-0
        sAAz[0].release();                   //semaforo azul-amarelo-0
        amarelo.moveBaixo(137);
        sLA[1].acquire();                    //semaforo laranja-amarelo-1
        amarelo.moveBaixo(73);
        sLA[1].release();                    //semaforo laranja-amarelo-0
        amarelo.moveBaixo(100);
        sVA[1].acquire();                    //semaforo vermelho-amarelo-1
        sRxA[1].acquire();                   //semaforo roxo-amarelo-1
        amarelo.moveBaixo(70);
        sVA[1].release();                    //semaforo vermelho-amarelo-1
        sVdA[1].release();                   //semaforo verde-amarelo-0
        sRxA[1].release();                   //semaforo roxo-amarelo-1
        amarelo.moveBaixo(105);
        sVA[2].acquire();                    //semaforo vermelho-amarelo-2  
        sVdA[0].acquire();                   //semaforo verde-amarelo-0
        sLA[2].acquire();                    //semaforo laranja-amarelo-2
        sRxA[0].acquire();                   //semaforo roxo-amarelo-0
        sAR[0].acquire();                    //semaforo rosa-amarelo-0
        amarelo.moveBaixo(75);
        sLA[2].release();                    //semaforo laranja-amarelo-2
        sRxA[0].release();                   //semaforo roxo-amarelo-0
        sAR[0].release();                    //semaforo rosa-amarelo-0
        amarelo.moveBaixo(165);
        sVA[2].release();                    //semaforo vermelho-amarelo-2 
        amarelo.moveBaixo(110); 
        sVA[3].acquire();                    //semaforo vermelho-amarelo-3
        sAAz[1].acquire();                   //semaforo azul-amarelo-1
        sAR[2].acquire();                    //semaforo rosa-amarelo-2
        amarelo.moveBaixo(35); 
        amarelo.rotacionar(-90);
        amarelo.moveEsq(120);
        sRxA[3].acquire();                   //semaforo roxo-amarelo-3
        amarelo.moveEsq(80);
        sVA[3].release();                    //semaforo vermelho-amarelo-3
        amarelo.moveEsq(80);
        sVA[4].acquire();                    //semaforo vermelho-amarelo-4
        amarelo.moveEsq(40);
        sAAz[1].release();                   //semaforo azul-amarelo-1
        sVdA[0].release();                   //semaforo verde-amarelo-0
        sAR[2].release();                    //semaforo rosa-amarelo-2
        amarelo.setX(0);
        amarelo.setY(0);

        break;
        
      }

    }

  }

  /* ***************************************************************
   * Metodo: percVermelho
   * Funcao: Mostra ou oculta o percurso do kart especifico
   * Parametros: ActionEvent event, para que a acao seja suportada e execute as linhas de codigo corretamente
   * Retorno: void
   ****************************************************************** */
  public void percVermelho(ActionEvent e){
    if(!t1.isVisible()){
      t1.setVisible(true);
    } else {
      t1.setVisible(false);
    }
  }

  /* ***************************************************************
   * Metodo: percAzul
   * Funcao: Mostra ou oculta o percurso do kart especifico
   * Parametros: ActionEvent event, para que a acao seja suportada e execute as linhas de codigo corretamente
   * Retorno: void
   ****************************************************************** */
  public void percAzul(ActionEvent e){
    if(!t2.isVisible()){
      t2.setVisible(true);
    } else {
      t2.setVisible(false);
    }
  }

  /* ***************************************************************
   * Metodo: percLaranja
   * Funcao: Mostra ou oculta o percurso do kart especifico
   * Parametros: ActionEvent event, para que a acao seja suportada e execute as linhas de codigo corretamente
   * Retorno: void
   ****************************************************************** */
  public void percLaranja(ActionEvent e){
    if(!t3.isVisible()){
      t3.setVisible(true);
    } else {
      t3.setVisible(false);
    }
  }

  /* ***************************************************************
   * Metodo: percVerde
   * Funcao: Mostra ou oculta o percurso do kart especifico
   * Parametros: ActionEvent event, para que a acao seja suportada e execute as linhas de codigo corretamente
   * Retorno: void
   ****************************************************************** */
  public void percVerde(ActionEvent e){
    if(!t6.isVisible()){
      t6.setVisible(true);
    } else {
      t6.setVisible(false);
    }
  }

  /* ***************************************************************
   * Metodo: percBranco
   * Funcao: Mostra ou oculta o percurso do kart especifico
   * Parametros: ActionEvent event, para que a acao seja suportada e execute as linhas de codigo corretamente
   * Retorno: void
   ****************************************************************** */
  public void percBranco(ActionEvent e){
    if(!t7.isVisible()){
      t7.setVisible(true);
    } else {
      t7.setVisible(false);
    }
  }

  /* ***************************************************************
   * Metodo: percRoxo
   * Funcao: Mostra ou oculta o percurso do kart especifico
   * Parametros: ActionEvent event, para que a acao seja suportada e execute as linhas de codigo corretamente
   * Retorno: void
   ****************************************************************** */
  public void percRoxo(ActionEvent e){
    if(!t8.isVisible()){
      t8.setVisible(true);
    } else {
      t8.setVisible(false);
    }
  }

  /* ***************************************************************
   * Metodo: percRosa
   * Funcao: Mostra ou oculta o percurso do kart especifico
   * Parametros: ActionEvent event, para que a acao seja suportada e execute as linhas de codigo corretamente
   * Retorno: void
   ****************************************************************** */
  public void percRosa(ActionEvent e){
    if(!t4.isVisible()){
      t4.setVisible(true);
    } else {
      t4.setVisible(false);
    }
  }

  /* ***************************************************************
   * Metodo: percAmarelo
   * Funcao: Mostra ou oculta o percurso do kart especifico
   * Parametros: ActionEvent event, para que a acao seja suportada e execute as linhas de codigo corretamente
   * Retorno: void
   ****************************************************************** */
  public void percAmarelo(ActionEvent e){
    if(!t5.isVisible()){
      t5.setVisible(true);
    } else {
      t5.setVisible(false);
    }
  }

  /* ***************************************************************
   * Metodo: getVelocidade
   * Funcao: Faz um Switch baseado no id do kart que foi passado e converte o valor do Slider
   * para int e o retorna para que seja utilizado nos metodos de moviemntacao
   * Parametros: int numero, que equivale ao id do respectivo kart
   * Retorno: int conversion, que equivale ao valor convertido de Slider para int
   ****************************************************************** */
  public int getVelocidade(int numero){

    switch(numero){

      case 0:{

        if(veloVermelho.getValue() == 10){
          while(veloVermelho.getValue() == 10){
            try{
              Thread.sleep(1);
            } catch(Exception e){e.printStackTrace();}
          }
        }

        double aux = veloVermelho.getValue();
        int conversion = (int) aux;
        return conversion;

      }
      
      case 1:{

        if(veloAzul.getValue() == 10){
          while(veloAzul.getValue() == 10){
            try{
              Thread.sleep(1);
            } catch(Exception e){e.printStackTrace();}
          }
        }

        double aux = veloAzul.getValue();
        int conversion = (int) aux;
        return conversion;

      }

      case 2:{

        if(veloLaranja.getValue() == 10){
          while(veloLaranja.getValue() == 10){
            try{
              Thread.sleep(1);
            } catch(Exception e){e.printStackTrace();}
          }
        }

        double aux = veloLaranja.getValue();
        int conversion = (int) aux;
        return conversion;

      }

      case 3:{

        if(veloVerde.getValue() == 10){
          while(veloVerde.getValue() == 10){
            try{
              Thread.sleep(1);
            } catch(Exception e){e.printStackTrace();}
          }
        }

        double aux = veloVerde.getValue();
        int conversion = (int) aux;
        return conversion;

      }

      case 4:{

        if(veloBranco.getValue() == 10){
          while(veloBranco.getValue() == 10){
            try{
              Thread.sleep(1);
            } catch(Exception e){e.printStackTrace();}
          }
        }

        double aux = veloBranco.getValue();
        int conversion = (int) aux;
        return conversion;

      }

      case 5:{

        if(veloRoxo.getValue() == 10){
          while(veloRoxo.getValue() == 10){
            try{
              Thread.sleep(1);
            } catch(Exception e){e.printStackTrace();}
          }
        }

        double aux = veloRoxo.getValue();
        int conversion = (int) aux;
        return conversion;

      }

      case 6:{

        if(veloRosa.getValue() == 10){
          while(veloRosa.getValue() == 10){
            try{
              Thread.sleep(1);
            } catch(Exception e){e.printStackTrace();}
          }
        }

        double aux = veloRosa.getValue();
        int conversion = (int) aux;
        return conversion;

      }
      
      case 7:{

        if(veloAmarelo.getValue() == 10){
          while(veloAmarelo.getValue() == 10){
            try{
              Thread.sleep(1);
            } catch(Exception e){e.printStackTrace();}
          }
        }

        double aux = veloAmarelo.getValue();
        int conversion = (int) aux;
        return conversion;

      }

      default:
      return 0;

    }

  }

}
