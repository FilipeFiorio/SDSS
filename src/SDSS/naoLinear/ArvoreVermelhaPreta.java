package SDSS.naoLinear;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.util.List;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author filipe
 */
public class ArvoreVermelhaPreta extends Arvore {

    public ArvoreVermelhaPreta() {

    }

    public ArvoreVermelhaPreta(List<Node> listaNode) {
        this.listaNode = listaNode;
    }

    @Override
    public void put(int valor) {
        raiz = inserir(raiz, valor);
        raiz.setCor(EngineFrame.BLACK); // raiz sempre preta
        atualizarPosicoes(raiz, 640, 60, distanciaX * 0.6);
    }

    private Node inserir(Node atual, int valor) {
        if (atual == null) {
            Node novo = new Node(valor, EngineFrame.RED, 0, 0);
            listaNode.add(novo);
            return novo;
        }

        /*se o Node pai nao for nulo: se o valor (ja que foi criado um Node nvo, pq atual == null)
            * for menor que o do pai é setado como filho da esquerda, se for maior é setado com filho da direita, 
            * se for igual nada é inserido
         */
        if (valor < atual.getValor()) {
            atual.setFilhoEsquerda(inserir(atual.getFilhoEsquerda(), valor));
        } else if (valor > atual.getValor()) {
            atual.setFilhoDireita(inserir(atual.getFilhoDireita(), valor));
        } else {
            return atual;
        }

        corrigirCoresERotacoes(atual);

        return atual;
    }

    private boolean verificarVermelho(Node n) {
        if (n == null) {
            return false;
        }
        return n.getCor() == EngineFrame.RED;
    }

    //Mesma rotaçao usada na arvore AVL
    private Node rotacaoEsquerda(Node a) {
        Node b = a.getFilhoDireita();
        a.setFilhoDireita(b.getFilhoEsquerda());
        b.setFilhoEsquerda(a);
        b.setCor(a.getCor());
        a.setCor(EngineFrame.RED);
        return b;
    }

    private Node rotacaoDireita(Node a) {
        Node b = a.getFilhoEsquerda();
        a.setFilhoEsquerda(b.getFilhoDireita());
        b.setFilhoDireita(a);
        b.setCor(a.getCor());
        a.setCor(EngineFrame.RED);
        return b;
    }

    // inverte as core sdos nos
    private void inverterCores(Node n) {
        n.setCor(EngineFrame.RED);
        n.getFilhoEsquerda().setCor(EngineFrame.BLACK);
        n.getFilhoDireita().setCor(EngineFrame.BLACK);
    }

    @Override
    public void delete(int valor) {
        raiz = remover(raiz, valor);
        atualizarPosicoes(raiz, 640, 60, distanciaX);
    }

    private Node remover(Node atual, int valor) {
        if (atual == null) {
            return null;
        }

        if (valor < atual.getValor()) {
            atual.setFilhoEsquerda(remover(atual.getFilhoEsquerda(), valor));
        } else if (valor > atual.getValor()) {
            atual.setFilhoDireita(remover(atual.getFilhoDireita(), valor));
        } else {
            listaNode.remove(atual);

            if (atual.getFilhoEsquerda() == null) {
                return atual.getFilhoDireita();
            }
            if (atual.getFilhoDireita() == null) {
                return atual.getFilhoEsquerda();
            }

            Node sucessor = getMenor(atual.getFilhoDireita());
            atual.setValor(sucessor.getValor());
            atual.setFilhoDireita(remover(atual.getFilhoDireita(), sucessor.getValor()));
        }

        atual = corrigirCoresERotacoes(atual);

        return atual;
    }

    //Verifica ocorrencias proibidas na AVP e corrige
    private Node corrigirCoresERotacoes(Node node) {
        
        //Verifica se o filho da direita e vermelho e o da esquerda nn --> rotaciona para a esquerda
        if (verificarVermelho(node.getFilhoDireita()) && !verificarVermelho(node.getFilhoEsquerda())) {
            node = rotacaoEsquerda(node);
        }

        //verifica se tem 2 nos vermelhos seguidos na esquerda --> rotaciona para a direita
        if (verificarVermelho(node.getFilhoEsquerda()) && verificarVermelho(node.getFilhoEsquerda().getFilhoEsquerda())) {
            node = rotacaoDireita(node);
        }

        //verifica se o no da direita e da esquerda sao vermelhos --> troca de cor
        if (verificarVermelho(node.getFilhoEsquerda()) && verificarVermelho(node.getFilhoDireita())) {
            inverterCores(node);
        }

        return node;
    }

    @Override
    public void transformacao1(List<Node> listaNode) {
        ArvoreBinariaBusca nova = new ArvoreBinariaBusca();
        for (Integer v : getValores()) {
            nova.put(v);
        }
        transformacao(new JanelaArvore("Árvore Binária de Busca", nova));
    }

    @Override
    public void transformacao2(List<Node> listaNode) {
        ArvoreAVL nova = new ArvoreAVL();
        for (Integer v : getValores()) {
            nova.put(v);
        }
        transformacao(new JanelaArvore("Árvore AVL", nova));
    }

    private void transformacao(EngineFrame e) {
        e.setIconImage(logo.buffImage);
        e.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}
