package SDSS.naoLinear;

import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
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
    public void put() {
        int valor = (int)(Math.random() * 100);
        raiz = inserir(raiz, valor);
        atualizarPosicoes(raiz, 640, 60, distanciaX * 0.6);
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

    private int altura(Node n) {
        if (n == null) return 0;
        return 1 + Math.max(altura(n.getFilhoEsquerda()), altura(n.getFilhoDireita()));
    }

    private int getBalanceamento(Node n) {
        if (n == null) return 0;
        return altura(n.getFilhoEsquerda()) - altura(n.getFilhoDireita());
    }

    // Rotação EE (direita)
    private Node rotacaoDireita(Node a) {
        Node b = a.getFilhoEsquerda();
        Node temp = b.getFilhoDireita();
        b.setFilhoDireita(a);
        a.setFilhoEsquerda(temp);
        return b;
    }

    // Rotação DD (esquerda)
    private Node rotacaoEsquerda(Node a) {
        Node b = a.getFilhoDireita();
        Node temp = b.getFilhoEsquerda();
        b.setFilhoEsquerda(a);
        a.setFilhoDireita(temp);
        return b;
    }

    // Atualiza posições visuais
    private void atualizarPosicoes(Node atual, double x, double y, double desvioX) {
        if (atual == null) return;

        atual.setCentroX(x);
        atual.setCentroY(y);

        if (atual.getFilhoEsquerda() != null) {
            atualizarPosicoes(atual.getFilhoEsquerda(), x - desvioX, y + distanciaY, desvioX / 1.8);
        }

        if (atual.getFilhoDireita() != null) {
            atualizarPosicoes(atual.getFilhoDireita(), x + desvioX, y + distanciaY, desvioX / 1.8);
        }
    }

    @Override
    public void delete() {
        // deletar depois se precisar
    }

    @Override
    public void transformacao1(List<Node> listaNode) {
        transformacao(new JanelaArvore("Árvore Binária de Busca", new ArvoreBinariaBusca(listaNode)));
    }

    @Override
    public void transformacao2(List<Node> listaNode) {
        transformacao(new JanelaArvore("Árvore Vermelha e Preta", new ArvoreVermelhaPreta(listaNode)));
    }

    private void transformacao(EngineFrame e) {
        e.setIconImage(logo.buffImage);
        e.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
