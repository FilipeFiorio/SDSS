package SDSS.naoLinear;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ArvoreBinariaBusca extends Arvore {


    public ArvoreBinariaBusca() {}

    public ArvoreBinariaBusca(List<Node> listaNode) {
        this.listaNode = listaNode;
        if (!listaNode.isEmpty()) {
            raiz = listaNode.get(0);
        }
    }

    @Override
    public void put(int valor) {
        raiz = inserir(raiz, null, valor, 640, 60, distanciaX);
    }
    
    
    /*Precisa melhorar o posicionamento dos nos no eixo X, 
    esta ficando com um posicionamento estranho, tem sobreposicao de nos
    */
    private Node inserir(Node atual, Node pai, int valor, double x, double y, double desvioX) {

        if (atual == null) {
            Node novo = new Node(valor, EngineFrame.BLACK, x, y);
            listaNode.add(novo);

            /*se o Node pai nao for nulo: se o valor (ja que foi criado um Node nvo, pq atual == null)
            * for menor que o do pai é setado como filho da esquerda, se for maior é setado com filho da direita, 
            * se for igual nada é inserido
            */
            if (pai != null) {
                if (valor < pai.getValor()) {
                    pai.setFilhoEsquerda(novo);
                } else if(valor > pai.getValor()) {
                    pai.setFilhoDireita(novo);
                } else {
                    return null;
                }
            }

            return novo;
        }

        if (valor < atual.getValor()) {
            atual.setFilhoEsquerda(inserir(
                atual.getFilhoEsquerda(),
                atual,
                valor,
                x - desvioX,
                y + distanciaY,
                desvioX / 1.75
            ));
        } else if(valor > atual.getValor()) {
            atual.setFilhoDireita(inserir(
                atual.getFilhoDireita(),
                atual,
                valor,
                x + desvioX,
                y + distanciaY,
                desvioX / 1.75
            ));
        }
        
        return atual;
    }

    @Override
    public void delete() {}

    @Override
    public void transformacao1(List<Node> listaNode) {
        transformacao(new JanelaArvore("Árvore AVL", new ArvoreAVL(new ArrayList<>(this.listaNode))));
    }

    @Override
    public void transformacao2(List<Node> listaNode) {
        transformacao(new JanelaArvore("Árvore Vermelha e Preta", new ArvoreVermelhaPreta(new ArrayList<>(this.listaNode))));
    }

    private void transformacao(EngineFrame e) {
        e.setIconImage(logo.buffImage);
        e.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
