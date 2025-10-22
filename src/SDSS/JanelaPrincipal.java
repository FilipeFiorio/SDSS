package SDSS;

import SDSS.linear.Deque;
import SDSS.linear.Fila;
import SDSS.linear.Lista;
import SDSS.linear.Pilha;
import SDSS.naoLinear.ArvoreAVL;
import SDSS.naoLinear.ArvoreBinaria;
import SDSS.naoLinear.ArvoreRN;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.geom.RoundRectangle;
import br.com.davidbuzatto.jsge.image.Image;
import java.awt.Color;

/**
 * Modelo de projeto básico da JSGE.
 *
 * JSGE basic project template.
 *
 * @author Prof. Dr. David Buzatto
 */
public class JanelaPrincipal extends EngineFrame {
    
    private final Color corBackground = new Color(60, 60, 75);
    private final Color corBotaoSelecionado = new Color(100, 100, 100, 100);
    
    private double xIniBotao;
    private double yIniBotao;
    private double espacamentoLinear;
    private double espacamentoNaoLinear;
    
    private RoundRectangle botaoPilha;
    private RoundRectangle botaoFila;
    private RoundRectangle botaoDeque;
    private RoundRectangle botaoLista;
    
    private RoundRectangle botaoArvoreBinaria;
    private RoundRectangle botaoArvoreAVL;
    private RoundRectangle botaoArvoreRN;
    
    private RoundRectangle abaNaoLineares;
    private RoundRectangle abaLineares;
    
    private boolean mostrarPainelLinear;
    
    private final Image logo = loadImage("resources/images/iconeSimulador.png");
    
    public JanelaPrincipal() {
        
        super(
                1280, // largura                      / width
                720, // algura                       / height
                "SDSS - v2.0", // título                       / title
                60, // quadros por segundo desejado / target FPS
                true, // suavização                   / antialiasing
                false, // redimensionável              / resizable
                false, // tela cheia                   / full screen
                false, // sem decoração                / undecorated
                false, // sempre no topo               / always on top
                false // fundo invisível              / invisible background
        );
        
        setIconImage(logo.buffImage);
    }
    
    @Override
    public void create() {
        
        mostrarPainelLinear = true;
        
        xIniBotao = 20;
        yIniBotao = 120;
        espacamentoLinear = (getScreenWidth() - 50 ) / 4;
        espacamentoNaoLinear = (getScreenWidth() - 50) / 3;
        
        botaoArvoreBinaria = new RoundRectangle(xIniBotao, yIniBotao, espacamentoNaoLinear, 580, 20);
        botaoArvoreAVL = new RoundRectangle(xIniBotao + espacamentoNaoLinear + 10, yIniBotao, espacamentoNaoLinear, 580, 20);
        botaoArvoreRN = new RoundRectangle(xIniBotao + 2 * espacamentoNaoLinear + 10, yIniBotao, espacamentoNaoLinear, 580, 20);
        
        botaoPilha = new RoundRectangle( xIniBotao, yIniBotao, espacamentoLinear, 580, 20);
        botaoFila = new RoundRectangle( xIniBotao + espacamentoLinear + 10, yIniBotao, espacamentoLinear, 580, 20);
        botaoDeque = new RoundRectangle( xIniBotao + 2 * espacamentoLinear + 10, yIniBotao, espacamentoLinear, 580, 20);
        botaoLista = new RoundRectangle( xIniBotao + 3 * espacamentoLinear + 10, yIniBotao, espacamentoLinear, 580, 20);
        
        abaLineares = new RoundRectangle(10, 10, getScreenWidth() / 2 - 12.5, 95, 20);
        abaNaoLineares = new RoundRectangle(getScreenWidth() / 2 + 2.5, 10, getScreenWidth() / 2 - 15, 95, 20);

    }
    
    @Override
    public void update(double delta) {
        
        if (mouseIn(abaLineares)) {
            if (isMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
                mostrarPainelLinear = true;
            }
        } else if (mouseIn(abaNaoLineares)) {
            if (isMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
                mostrarPainelLinear = false;
            }
        }
        
        if (isMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            if (mostrarPainelLinear) {
                if (mouseIn(botaoPilha)) {
                    abrirJanela(new Pilha());
                } else if (mouseIn(botaoFila)) {
                    abrirJanela(new Fila());
                } else if (mouseIn(botaoDeque)) {
                    abrirJanela(new Deque());
                } else if (mouseIn(botaoLista)) {
                    abrirJanela(new Lista());
                }
            } else if (!mostrarPainelLinear) {
                if (mouseIn(botaoArvoreBinaria)) {
                    abrirJanela(new ArvoreBinaria());
                } else if (mouseIn(botaoArvoreAVL)) {
                    abrirJanela(new ArvoreAVL());
                } else if (mouseIn(botaoArvoreRN)) {
                    abrirJanela(new ArvoreRN());
                }
            }
        }
        
    }
    
    @Override
    public void draw() {
        
        clearBackground(corBackground);
        
        fillRoundRectangle(10, 110, getScreenWidth() - 20, getScreenHeight() - 120, 20, corBackground.darker());
        
        if (mostrarPainelLinear) {
            abaLineares.fill(this, corBackground.darker());
        } else {
            abaNaoLineares.fill(this, corBackground.darker());
        }

        // seta a fonte da janela para inter
        setFontName("inter");

        //Resposta visual do mouse em cima do botao, na parte das abas mostra qual aba esta selecionada
        if (mouseIn(abaLineares) && !mostrarPainelLinear) {
            abaLineares.fill(this, corBotaoSelecionado);
        } else if (mouseIn(abaNaoLineares) && mostrarPainelLinear) {
            abaNaoLineares.fill(this, corBotaoSelecionado);
        }
        
        if (!mostrarPainelLinear) {
            
            if (mouseIn(botaoArvoreBinaria)) {
                botaoArvoreBinaria.fill(this, corBotaoSelecionado);
            } else if (mouseIn(botaoArvoreAVL)) {
                botaoArvoreAVL.fill(this, corBotaoSelecionado);
            } else if (mouseIn(botaoArvoreRN)) {
                botaoArvoreRN.fill(this, corBotaoSelecionado);
            }

            // Criando botao para a arvore binaria
            drawImagemArvore(xIniBotao, yIniBotao, LIGHTGRAY, LIGHTGRAY);
            drawText("Árvore",184.5, 550, 24, LIGHTGRAY);
            drawText("Binária",184, 580, 24, LIGHTGRAY);

            // Criando botao para a arvore AVL
            drawImagemArvore(xIniBotao + espacamentoNaoLinear + 10, yIniBotao, LIGHTGRAY, LIGHTGRAY);
            drawText("Árvore", 184.5 + espacamentoNaoLinear + 10, 550, 24, LIGHTGRAY);
            drawText("AVL", 200 + espacamentoNaoLinear + 10, 580, 24, LIGHTGRAY);
            //Desenhando o simbolo da balança
            fillTriangle(
                    xIniBotao + 1.5 * espacamentoNaoLinear + 10, 
                    yIniBotao + 340, 
                    xIniBotao + 1.5 * espacamentoNaoLinear - 10, 
                    yIniBotao + 380 , 
                    xIniBotao + 1.5 * espacamentoNaoLinear + 30, 
                    yIniBotao + 380, 
                    LIGHTGRAY
            );
            drawLine(
                    xIniBotao + 1.5 * espacamentoNaoLinear - 30,
                    yIniBotao + 330,
                    xIniBotao + 1.5 * espacamentoNaoLinear + 50,
                    yIniBotao + 350,
                    LIGHTGRAY
            );
            drawLine(
                    xIniBotao + 1.5 * espacamentoNaoLinear - 29,
                    yIniBotao + 331,
                    xIniBotao + 1.5 * espacamentoNaoLinear + 49,
                    yIniBotao + 351,
                    LIGHTGRAY
            );
            drawLine(
                    xIniBotao + 1.5 * espacamentoNaoLinear - 30, 
                    yIniBotao + 330, 
                    xIniBotao + 1.5 * espacamentoNaoLinear - 40, 
                    yIniBotao + 350, 
                    LIGHTGRAY
            );
            drawLine(
                    xIniBotao + 1.5 * espacamentoNaoLinear - 30, 
                    yIniBotao + 330, 
                    xIniBotao + 1.5 * espacamentoNaoLinear - 20, 
                    yIniBotao + 350, 
                    LIGHTGRAY
            );
            fillCircleSector(
                    xIniBotao + 1.5 * espacamentoNaoLinear - 30,
                    yIniBotao + 350, 
                    10, 
                    0, 
                    180, 
                    LIGHTGRAY
            );
            drawLine(
                    xIniBotao + 1.5 * espacamentoNaoLinear + 50, 
                    yIniBotao + 350, 
                    xIniBotao + 1.5 * espacamentoNaoLinear + 40, 
                    yIniBotao + 370, 
                    LIGHTGRAY
            );
            drawLine(
                    xIniBotao + 1.5 * espacamentoNaoLinear + 50, 
                    yIniBotao + 350, 
                    xIniBotao + 1.5 * espacamentoNaoLinear + 60, 
                    yIniBotao + 370, 
                    LIGHTGRAY
            );
            fillCircleSector(
                    xIniBotao + 1.5 * espacamentoNaoLinear + 50,
                    yIniBotao + 370,
                    10,
                    0,
                    180,
                    LIGHTGRAY
            );

            // Criando botao para a arvore Rubro negra
            drawImagemArvore(xIniBotao + 2 * espacamentoNaoLinear + 10, yIniBotao, RED.darker(), LIGHTGRAY);
            drawText("Árvore", 184.5 + 2 * espacamentoNaoLinear + 10, 550, 24, LIGHTGRAY );
            drawText("Rubro-Negra", 147.5 + 2 * espacamentoNaoLinear + 10, 580, 24, LIGHTGRAY );
            
        } else {
            
            if(mouseIn(botaoPilha)) {
                botaoPilha.fill(this, corBotaoSelecionado);
            } else if(mouseIn(botaoFila)) {
                botaoFila.fill(this, corBotaoSelecionado);
            } else if(mouseIn(botaoDeque)) {
                botaoDeque.fill(this, corBotaoSelecionado);
            } else if(mouseIn(botaoLista)) {
                botaoLista.fill(this, corBotaoSelecionado);
            }
            
            //Criando Bootao Pilha
            drawImagemEstruturaLinear(xIniBotao, "Pilha");
            
            //Criando Botao fila
            drawImagemEstruturaLinear(xIniBotao + espacamentoLinear + 10, "Fila");
            
            //Criando Botao Deque
            drawImagemEstruturaLinear(xIniBotao + 2 * espacamentoLinear + 10, "Deque");
            
            //Criando Botao Lista
            drawImagemEstruturaLinear(xIniBotao + 3 * espacamentoLinear + 10, "Lista");
        }
        
        drawText("Não Lineares", 888.5, 50, 24, LIGHTGRAY);
        
        drawText("Lineares", 268.5, 50, 24, LIGHTGRAY);
        
        
    }

    // metodo para verificar se o mouse clicou nos botoes criados manualmente
    private boolean mouseIn(RoundRectangle r) {
        
        double mouseX = getMouseX();
        double mouseY = getMouseY();
        
        return mouseX >= r.x && mouseX <= r.width + r.x
                && mouseY >= r.y && mouseY <= r.height + r.y;
    }
    
    private void abrirJanela(EngineFrame janela) {
        janela.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void drawImagemArvore(double xInicio, double yInicio, Color LinhaEsquerda, Color linhaDireita) {

        //Coordenas do no pai
        double centroPaiX = xInicio + espacamentoNaoLinear / 2;
        double centroPaiY = yInicio + espacamentoNaoLinear / 4;
        
        //coordenadas base do no filho
        double centroFilhoX = xInicio + espacamentoNaoLinear / 4;
        double centroFilhoY = 2 * centroPaiY;
        
        //desenha as linhas que conectam o pai com os filhos
        drawLine(centroPaiX, centroPaiY, centroFilhoX, centroFilhoY, LinhaEsquerda);
        drawLine(centroPaiX + 1, centroPaiY + 1, centroFilhoX + 1, centroFilhoY + 1, LinhaEsquerda);
        drawLine(centroPaiX, centroPaiY, centroFilhoX + espacamentoNaoLinear / 2, centroFilhoY, linhaDireita);
        drawLine(centroPaiX - 1, centroPaiY  - 1, centroFilhoX + espacamentoNaoLinear / 2 - 1, centroFilhoY - 1, linhaDireita);
        
        //desenha o nó pai
        fillCircle(centroPaiX, centroPaiY, 48, LIGHTGRAY);

        //desenha o filho da esquerda
        fillCircle(centroFilhoX, centroFilhoY, 48, LIGHTGRAY);

        //desenha o filho da direita
        fillCircle(centroFilhoX + espacamentoNaoLinear / 2, centroFilhoY, 48, LIGHTGRAY);

    }
    
    private void drawImagemEstruturaLinear(double xInicio, String nome) {
        
        double yBotao = yIniBotao + 72;
        double xBotao = xInicio + espacamentoLinear / 4;
        double altura = 300;
        
        drawRoundRectangle(xBotao, yBotao, espacamentoLinear / 2, altura, 10, LIGHTGRAY );
        drawRoundRectangle(xBotao + 1, yBotao + 1, espacamentoLinear / 2 - 2, altura - 2, 10, LIGHTGRAY );
        
        for(int i = 1; i <= 3; i++) {
            fillRoundRectangle(xBotao + 5, yBotao + altura - 35 * i, espacamentoLinear / 2 - 10, 30 , 10, LIGHTGRAY);
        }
        
        drawText(
                nome,
                xInicio + (espacamentoLinear - measureText(nome, 24)) / 2,
                550,
                24,
                LIGHTGRAY
        );
    }
    
    public static void main(String[] args) {
        new JanelaPrincipal();
    }
    
}
