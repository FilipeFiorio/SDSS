package template;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import br.com.davidbuzatto.jsge.geom.Rectangle;
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
    
    private final Color corBackground = new Color(60,60,75);
    private final Color corBotaoSelecionado = new Color(100, 100, 100, 100 );
    
    private RoundRectangle botaoArvoreBinaria;
    private RoundRectangle botaoArvoreAVL;
    private RoundRectangle botaoArvoreRN;
    
    private Image logo;
    
    private Image imgArvoreBinaria;
    private Image imgArvoreAVL;
    private Image imgArvoreRN;
   
    
    public JanelaPrincipal() {
        
        super(
            1280,                 // largura                      / width
            720,                 // algura                       / height
            "SDSS - v1.0",      // título                       / title
            60,                  // quadros por segundo desejado / target FPS
            true,                // suavização                   / antialiasing
            true,               // redimensionável              / resizable
            false,               // tela cheia                   / full screen
            false,               // sem decoração                / undecorated
            false,               // sempre no topo               / always on top
            false                // fundo invisível              / invisible background
        );
        
        setIconImage(logo.buffImage);
        
    }
    
    @Override
    public void create() {
        
        botaoArvoreBinaria = new RoundRectangle(11, 19, 74, 101, 20);
        botaoArvoreAVL = new RoundRectangle(11, 125, 74, 101, 20);
        botaoArvoreRN = new RoundRectangle(11, 231, 74, 101, 20);
        
        logo = loadImage("resources/images/iconeSimulador.png");
        
        imgArvoreBinaria = loadImage("resources/images/BinaryTree.png");
        imgArvoreAVL = loadImage("resources/images/AVLTree.png");
        imgArvoreRN = loadImage("resources/images/arvoreRN.png");
        
    }

    @Override
    public void update( double delta ) {
        
        if(isMouseButtonPressed(MOUSE_BUTTON_LEFT)) {
            if(mouseIn(botaoArvoreBinaria)) {
                abrirJanela(new ArvoreBinaria(imgArvoreBinaria));
            } else if(mouseIn(botaoArvoreAVL)) {
                abrirJanela(new ArvoreAVL(imgArvoreAVL));
            } else if(mouseIn(botaoArvoreRN)) {
                abrirJanela(new ArvoreRN(imgArvoreRN));
            }
        }
        
    }
 
    @Override
    public void draw() {
        
        
        clearBackground(corBackground);
        fillRectangle(0, 0, 96, getScreenHeight(), corBackground.darker());
        // seta a fonte da janela para inter
        setFontName("inter");
        
        //Resposta visual do mouse em cima do botao
        if(mouseIn(botaoArvoreBinaria)) {
            botaoArvoreBinaria.fill(this, corBotaoSelecionado);
        } else if(mouseIn(botaoArvoreAVL)) {
            botaoArvoreAVL.fill(this, corBotaoSelecionado);
        } else if(mouseIn(botaoArvoreRN)) {
            botaoArvoreRN.fill(this, corBotaoSelecionado);
        }
        
        // Criando botao para a arvore binaria
        drawText("Binary", 28.5, 96 , 12, LIGHTGRAY);
        drawText("Tree", 33.5, 108, 12, LIGHTGRAY);   
        drawImage(imgArvoreBinaria, 16, 24);
        
        // Criando botao para a arvore AVL
        drawText("AVL", 36, 202, 12, LIGHTGRAY);
        drawText("Tree", 33.5, 214, 12, LIGHTGRAY);
        drawImage(imgArvoreAVL, 16, 130);
        
        // Criando botao para a arvore Rubro negra
        drawText("Red-Black", 16.5, 308, 12, LIGHTGRAY);
        drawText("Tree", 33.5, 320, 12, LIGHTGRAY);
        drawImage(imgArvoreRN, 16, 236);
        
        drawLine(10, 335, 86, 335, corBotaoSelecionado);
        
       
        
    
    }
    
    // metodo para verificar se o mouse clicou nos botoes criados manualmente
    private boolean mouseIn(RoundRectangle r) {
        
        double mouseX = getMouseX();
        double mouseY = getMouseY();
        
        return mouseX >= r.x && mouseX <= r.width + r.x && 
               mouseY >= r.y && mouseY <= r.height + r.y;
    }
    
    private void abrirJanela(EngineFrame janela) {
        janela.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
  
    public static void main( String[] args ) {
        new JanelaPrincipal();
    }
    
}
