package template;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;

/**
 *
 * @author filipe
 */
public class JanelaArvore extends EngineFrame{
    
    public JanelaArvore(String title) {
    
            super(
            640,                 // largura                      / width
            360,                 // algura                       / height
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
        
    }
    
    @Override
    public void update(double delta) {
        
    }
    
    @Override
    public void draw() {
        
    }
}
