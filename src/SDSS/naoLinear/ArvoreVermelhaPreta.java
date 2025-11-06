package SDSS.naoLinear;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.util.List;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author filipe
 */
public class ArvoreVermelhaPreta extends Arvore{
    
    public ArvoreVermelhaPreta() {
        
    }
    
    public ArvoreVermelhaPreta(List<Node> listaNode) {
        this.listaNode = listaNode;
    }
    
    @Override
    public void put() {
        
    }
    
    @Override
    public void delete() {
        
    }
    
    @Override
    public void transformacao1(List<Node> listaNode) {
        transformacao(new JanelaArvore("Árvore Binária de Busca", new ArvoreBinariaBusca(listaNode)));
    }
    
    @Override
    public void transformacao2(List<Node> listaNode) {
        transformacao(new JanelaArvore("Árvore AVL", new ArvoreAVL(listaNode)));
    }
    
    private void transformacao(EngineFrame e) {
        e.setIconImage(logo.buffImage);
        e.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    
}
