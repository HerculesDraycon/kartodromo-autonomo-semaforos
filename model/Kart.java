/* ***************************************************************
 * Autor............: Hercules Sampaio Oliveira
 * Matricula........: 202310486
 * Inicio...........: 18/06/2024
 * Ultima alteracao.: 05/07/2024
 * Nome.............: Kart.java
 * Funcao...........: Eh a classe do objeto Kart que atribui os parametros no seu construtor
 * e possui os metodos necessarios para a manipulacao do objeto.
 *************************************************************** */

package model;

import controller.ControllerAnimacao;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Kart extends Thread{

  public ImageView kart;     //ImageView correspondente ao kart
  public int numero;         //Numero correspondente ao kart
  public double x, y;        //Coordenadas que serao passadas nos karts
  public static boolean rodando = true;    //Variavel booleana de controle de execucao das threads

  /* ***************************************************************
   * Metodo: Construtor
   * Funcao: Define a passagem de parametros do objeto que sera instanciado
   * Parametros: ImageView kart, int numero
   * Retorno: nao ha retorno
   ***************************************************************** */
  public Kart(ImageView kart, int numero) {
    this.kart = kart;
    this.numero = numero;
  }
  //Instancia e metodo set do controller para que a instancia seja passada para a classe que extende Thread
  ControllerAnimacao controller = new ControllerAnimacao();
  public void setController(ControllerAnimacao controller){
    this.controller = controller;
  }

  /* ***************************************************************
   * Metodo: run
   * Funcao: O metodo eh responsavel pelo laco de execucao das threads e chama o metodo que executa
   * as animacoes dos carros
   * Parametros: nao ha parametros
   * Retorno: void
   ***************************************************************** */
  @Override
  public void run() {
    
    while(rodando){

      try{
        controller.percorrer(numero);
      } catch(Exception e){
        e.printStackTrace();
      }

    }

  }

  /* ***************************************************************
   * Metodo: moveEsq
   * Funcao: Recebe por parametro um inteiro que sera iterado no laco e executa a
   * animacao de movimento do kart de acordo com a direcao que foi passada
   * Parametros: int px, que corresponde a quantidade de pixels que o kart devera seguir
   * Retorno: void
   ***************************************************************** */
  public void moveEsq(int px){

    for(int i=0; i<px; i++){

      x--;
      double posicao = x;

      Platform.runLater(() -> kart.setX(posicao));
      try{
        Thread.sleep(controller.getVelocidade(numero));
      } catch (Exception e){
        e.printStackTrace();
      }

    }

  }

  /* ***************************************************************
   * Metodo: moveDir
   * Funcao: Recebe por parametro um inteiro que sera iterado no laco e executa a
   * animacao de movimento do kart de acordo com a direcao que foi passada
   * Parametros: int px, que corresponde a quantidade de pixels que o kart devera seguir
   * Retorno: void
   ***************************************************************** */
  public void moveDir(int px){

    for(int i=0; i<px; i++){

      x++;
      double posicao = x;

      Platform.runLater(() -> kart.setX(posicao));
      try{
        Thread.sleep(controller.getVelocidade(numero));
      } catch (Exception e){
        e.printStackTrace();
      }

    }

  }

  /* ***************************************************************
   * Metodo: moveCima
   * Funcao: Recebe por parametro um inteiro que sera iterado no laco e executa a
   * animacao de movimento do kart de acordo com a direcao que foi passada
   * Parametros: int px, que corresponde a quantidade de pixels que o kart devera seguir
   * Retorno: void
   ***************************************************************** */
  public void moveCima(int px){

    for(int i=0; i<px; i++){

      y--;
      double posicao = y;

      Platform.runLater(() -> kart.setY(posicao));
      try{
        Thread.sleep(controller.getVelocidade(numero));
      } catch (Exception e){
        e.printStackTrace();
      }

    }

  }

  /* ***************************************************************
   * Metodo: moveCima
   * Funcao: Recebe por parametro um inteiro que sera iterado no laco e executa a
   * animacao de movimento do kart de acordo com a direcao que foi passada
   * Parametros: int px, que corresponde a quantidade de pixels que o kart devera seguir
   * Retorno: void
   ***************************************************************** */
  public void moveBaixo(int px){

    for(int i=0; i<px; i++){

      y++;
      double posicao = y;

      Platform.runLater(() -> kart.setY(posicao));
      try{
        Thread.sleep(controller.getVelocidade(numero));
      } catch (Exception e){
        e.printStackTrace();
      }

    }

  }

  /* ***************************************************************
   * Metodo: rotacionar
   * Funcao: Recebe por parametro um inteiro que representa em graus qual sera
   * a rotacao que o veiculo deve fazer e rotaciona a imagem
   * Parametros: int graus, que corresponde a quantidade de graus que o kart devera rotacionar
   * Retorno: void
   ***************************************************************** */
  public void rotacionar(int graus){
    Platform.runLater(() -> kart.setRotate(graus));
  }
  /* ***************************************************************
   * Metodo: setters de X e Y
   * Funcao: Fazem a atualizacao por passagem de parametro do valor de x e y dos karts
   * Parametros: int x e y, que correspondem aos valores de x e y
   * Retorno: void
   ***************************************************************** */
  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

}
