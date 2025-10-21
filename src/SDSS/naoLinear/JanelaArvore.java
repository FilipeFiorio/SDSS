package SDSS.naoLinear;

import aaa.*;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author filipe
 */
public class JanelaArvore extends EngineFrame{
    
    protected List<Node> nodes;
    
    public JanelaArvore(String title) {
    
            super(
            854,                 // largura                      / width
            480,                 // algura                       / height
            title,               // título                       / title
            60,                  // quadros por segundo desejado / target FPS
            true,                // suavização                   / antialiasing
            true,                // redimensionável              / resizable
            false,               // tela cheia                   / full screen
            false,               // sem decoração                / undecorated
            false,               // sempre no topo               / always on top
            false                // fundo invisível              / invisible background
        );
    }
    
    @Override
    public void create() {
        
        nodes = new ArrayList<>();
        
    }
    
    @Override
    public void update(double delta) {
        
    }
    
    @Override
    public void draw() {
        
        drawLine(getScreenWidth() - 100, 0, getScreenWidth() - 100, getScreenHeight(), BLACK);
    }
    
    public void drawArvore(List<Node> nodes) {
        for(Node n : nodes){
            if(n.getNodeEsquerda() != null) {
                Node novoNode = n.getNodeEsquerda();
                drawLine(n.getCentroX(), n.getCentroY(), novoNode.getCentroX(), novoNode.getCentroY(), BLACK);
            }
            
            if(n.getNodeDireita()!= null) {
                Node novoNode = n.getNodeDireita();
                drawLine(n.getCentroX(), n.getCentroY(), novoNode.getCentroX(), novoNode.getCentroY(), BLACK);
            }
            n.drawNode(this);
        }
    }
    
    public void adicionarNode() {
        
    }
    
    public void removerNode() {
        
    }
    
}
