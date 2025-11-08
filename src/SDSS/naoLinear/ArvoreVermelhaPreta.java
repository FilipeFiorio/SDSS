package SDSS.naoLinear;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.util.ArrayList;
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

    private void atualizarPosicoes(Node atual, double x, double y, double desvioX) {
        if (atual == null) {
            return;
        }

        atual.setCentroX(x);
        atual.setCentroY(y);

        //comeca a aprtir do no errado, e vai ate chegar numa folha
        if (atual.getFilhoEsquerda() != null) {
            atualizarPosicoes(atual.getFilhoEsquerda(), x - desvioX, y + distanciaY, desvioX / 1.75);
        }

        if (atual.getFilhoDireita() != null) {
            atualizarPosicoes(atual.getFilhoDireita(), x + desvioX, y + distanciaY, desvioX / 1.75);
        }
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

        // trecho para verificar ocorrencias proibidas da arvore VP, caso ache alguma ocorrencia ja arruma
        
        //Verifica se o filho da direita e vermelho e o da esquerda nn --> rotaciona para a esquerda
        if (verificarVermelho(atual.getFilhoDireita()) && !verificarVermelho(atual.getFilhoEsquerda())) {
            atual = rotacaoEsquerda(atual);
        }

        //verifica se tem 2 nos vermelhos seguidos na esquerda --> rotaciona para a direita
        if (verificarVermelho(atual.getFilhoEsquerda()) && verificarVermelho(atual.getFilhoEsquerda().getFilhoEsquerda())) {
            atual = rotacaoDireita(atual);
        }

        //verifica se o no da direita e da esquerda sao vermelhos --> troca de cor
        if (verificarVermelho(atual.getFilhoEsquerda()) && verificarVermelho(atual.getFilhoDireita())) {
            inverterCores(atual);
        }

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
    public void delete() {

    }

    @Override
    public void transformacao1(List<Node> listaNode) {
        transformacao(new JanelaArvore("Árvore Binária de Busca", new ArvoreBinariaBusca(new ArrayList<>(this.listaNode))));
    }

    @Override
    public void transformacao2(List<Node> listaNode) {
        transformacao(new JanelaArvore("Árvore AVL", new ArvoreAVL(new ArrayList<>(listaNode))));
    }

    private void transformacao(EngineFrame e) {
        e.setIconImage(logo.buffImage);
        e.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}
