package SDSS;

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
    private double espacamento;
    
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
        espacamento = (getScreenWidth() - 50) / 3;
        
        botaoArvoreBinaria = new RoundRectangle(xIniBotao, yIniBotao, espacamento, 580, 20);
        botaoArvoreAVL = new RoundRectangle(xIniBotao + espacamento + 10, yIniBotao, espacamento, 580, 20);
        botaoArvoreRN = new RoundRectangle(xIniBotao + 2 * espacamento + 10, yIniBotao, espacamento, 580, 20);
        
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
                    //abrirJanela(new Pilha());
                } else if (mouseIn(botaoFila)) {
                    //abrirJanela(new Fila());
                } else if (mouseIn(botaoDeque)) {
                    //abrirJanela(new Deque());
                } else if (mouseIn(botaoLista)) {
                    //abrirJanela(new Fila());
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
            System.out.println(measureText("Rubro-Negra", 24));

            // Criando botao para a arvore AVL
            drawImagemArvore(xIniBotao + espacamento + 10, yIniBotao, LIGHTGRAY, LIGHTGRAY);
            drawText("Árvore", 184.5 + espacamento + 10, 550, 24, LIGHTGRAY);
            drawText("AVL", 200 + espacamento + 10, 580, 24, LIGHTGRAY);

            // Criando botao para a arvore Rubro negra
            drawImagemArvore(xIniBotao + 2 * espacamento + 10, yIniBotao, RED.darker(), GRAY.darker());
            drawText("Árvore", 184.5 + 2 * espacamento + 10, 550, 24, LIGHTGRAY );
            drawText("Rubro-Negra", 147.5 + 2 * espacamento + 10, 580, 24, LIGHTGRAY );
            
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
    
    private void drawImagemArvore(double xInicio, double yInicio, Color corPai, Color corFilho) {

        //Coordenas do no pai
        double centroPaiX = xInicio + espacamento / 2;
        double centroPaiY = yInicio + espacamento / 4;
        
        //coordenadas base do no filho
        double centroFilhoX = xInicio + espacamento / 4;
        double centroFilhoY = 2 * centroPaiY;
        
        //desenha as linhas que conectam o pai com os filhos
        drawLine(centroPaiX, centroPaiY, centroFilhoX, centroFilhoY, LIGHTGRAY);
        drawLine(centroPaiX, centroPaiY, centroFilhoX + espacamento / 2, centroFilhoY, LIGHTGRAY);
        
        //desenha o nó pai
        fillCircle(centroPaiX, centroPaiY, 48, corPai);

        //desenha o filho da esquerda
        fillCircle(centroFilhoX, centroFilhoY, 48, corFilho);

        //desenha o filho da direita
        fillCircle(centroFilhoX + espacamento / 2, centroFilhoY, 48, corFilho);

    }
    
    public static void main(String[] args) {
        new JanelaPrincipal();
    }
    
}
