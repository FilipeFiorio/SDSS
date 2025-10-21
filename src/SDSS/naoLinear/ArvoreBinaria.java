package SDSS.naoLinear;

import aaa.*;

/**
 *
 * @author filipe
 */
public class ArvoreBinaria extends JanelaArvore{
    
    public ArvoreBinaria() {
        super("Árvore Binária");
    }
    
    @Override
    public void create() {
        super.create();
        
    }
    
    @Override
    public void update(double delta) {
        super.update(delta);
        
    }
    
    @Override
    public void draw() {
        super.draw();
        
        drawArvore(nodes);
    }
}
