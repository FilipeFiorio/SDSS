package SDSS.naoLinear;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ArvoreAVL extends Arvore {

    public ArvoreAVL() {}

    public ArvoreAVL(List<Node> listaNode) {
        this.listaNode = listaNode;
        if (!listaNode.isEmpty()) {
            raiz = listaNode.get(0);
        }
    }

    @Override
    public void put(int valor) {
        raiz = inserir(raiz, valor);
        
        List<Node> listaTemp = new ArrayList<>(listaNode);
        listaNode = listaTemp;
        
        atualizarPosicoes(raiz, 640, 60, distanciaX);
    }

    private Node inserir(Node atual, int valor) {
        if (atual == null) {
            Node novo = new Node(valor, EngineFrame.BLACK, 0, 0);
            listaNode.add(novo);
            return novo;
        }

        if (valor < atual.getValor()) {
            atual.setFilhoEsquerda(inserir(atual.getFilhoEsquerda(), valor));
        } else if (valor > atual.getValor()) {
            atual.setFilhoDireita(inserir(atual.getFilhoDireita(), valor));
        } else {
            return atual; // evita duplicados
        }

        int fb = getBalanceamento(atual);

        // EE
        if (fb > 1 && valor < atual.getFilhoEsquerda().getValor()) {
            return rotacaoDireita(atual);
        }

        // DD
        if (fb < -1 && valor > atual.getFilhoDireita().getValor()) {
            return rotacaoEsquerda(atual);
        }

        // ED
        if (fb > 1 && valor > atual.getFilhoEsquerda().getValor()) {
            atual.setFilhoEsquerda(rotacaoEsquerda(atual.getFilhoEsquerda()));
            return rotacaoDireita(atual);
        }

        // DE
        if (fb < -1 && valor < atual.getFilhoDireita().getValor()) {
            atual.setFilhoDireita(rotacaoDireita(atual.getFilhoDireita()));
            return rotacaoEsquerda(atual);
        }

        return atual;
    }



    private int getBalanceamento(Node n) {
        if (n == null) return 0;
        return getAlturaArvore(n.getFilhoEsquerda()) - getAlturaArvore(n.getFilhoDireita());
    }

    // Rotação EE (direita)
    private Node rotacaoDireita(Node a) {
        Node b = a.getFilhoEsquerda();
        Node temp = b.getFilhoDireita();
        b.setFilhoDireita(a);
        a.setFilhoEsquerda(temp);
        atualizarPosicoes(raiz, 640, 60, distanciaX);
        return b;
    }

    // Rotação DD (esquerda)
    private Node rotacaoEsquerda(Node a) {
        Node b = a.getFilhoDireita();
        Node temp = b.getFilhoEsquerda();
        b.setFilhoEsquerda(a);
        a.setFilhoDireita(temp);
        atualizarPosicoes(raiz, 640, 60, distanciaX);
        return b;
    }

    // Atualiza posições visuais
    private void atualizarPosicoes(Node atual, double x, double y, double desvioX) {
        if (atual == null) return;

        atual.setCentroX(x);
        atual.setCentroY(y);

        if (atual.getFilhoEsquerda() != null) {
            atualizarPosicoes(atual.getFilhoEsquerda(), x - desvioX, y + distanciaY, desvioX / 1.75);
        }

        if (atual.getFilhoDireita() != null) {
            atualizarPosicoes(atual.getFilhoDireita(), x + desvioX, y + distanciaY, desvioX / 1.75);
        }
    }

    @Override
    public void delete() {
        // deletar depois se precisar
    }

    @Override
    public void transformacao1(List<Node> listaNode) {
        transformacao(new JanelaArvore("Árvore Binária de Busca", new ArvoreBinariaBusca(new ArrayList<>(this.listaNode))));
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
