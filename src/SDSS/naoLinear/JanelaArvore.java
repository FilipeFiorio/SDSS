package SDSS.naoLinear;

import br.com.davidbuzatto.jsge.core.Camera2D;
import br.com.davidbuzatto.jsge.core.engine.EngineFrame;
import static br.com.davidbuzatto.jsge.core.engine.EngineFrame.*;
import br.com.davidbuzatto.jsge.geom.Rectangle;
import br.com.davidbuzatto.jsge.geom.RoundRectangle;
import br.com.davidbuzatto.jsge.imgui.GuiButton;
import br.com.davidbuzatto.jsge.math.Vector2;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Janela base para as visualizações de árvore. Mantive a estrutura da sua
 * versão e adicionei hooks para desenhar árvores.
 */
public class JanelaArvore extends EngineFrame {

    private Camera2D camera;
    private Rectangle bordaCamera;
    private FocoCamera foco;
    private RoundRectangle botaoHambHitBox;
    private RoundRectangle bordaMenu;

    private GuiButton botaoPut;
    private GuiButton botaoDelete;
    private GuiButton botaoCriarAleatorio;
    private GuiButton botaoLimpar;
    private GuiButton botaoTransformacao1;
    private GuiButton botaoTransformacao2;

    private List<GuiButton> listaBotoes;

    private boolean mostrarMenu;

    private Arvore arvore;
    private String t1;
    private String t2;

    private final Color corBackground = new Color(230, 230, 255); // fundo claro
    private final Color corBotao = new Color(42, 42, 52);

    public JanelaArvore(String title, Arvore arvore) {
        super(
                854, // largura
                480, // altura
                title, // título
                60, // target FPS
                true, // antialiasing
                false, // resizable
                false, // full screen
                false, // undecorated
                false, // always on top
                false // invisible background
        );

        this.arvore = arvore;

    }

    @Override
    public void create() {

        useAsDependencyForIMGUI();

        
        //Criação dos botoes e da parte interativa do simulador
        botaoHambHitBox = new RoundRectangle(getScreenWidth() - 50, 20, 25, 28, 1);
        bordaMenu = new RoundRectangle(getScreenWidth() - 300, 70, 280, 160, 10);
        mostrarMenu = false;

        botaoPut = new GuiButton(getScreenWidth() - 290, 80, 125, 40, "PUT");
        botaoDelete = new GuiButton(getScreenWidth() - 155, 80, 125, 40, "DELETE");
        botaoLimpar = new GuiButton(getScreenWidth() - 290, 130, 125, 40, "LIMPAR");
        botaoCriarAleatorio = new GuiButton(getScreenWidth() - 155, 130, 125, 40, "CRIAR ALEATÓRIO");
        botaoTransformacao1 = new GuiButton(getScreenWidth() - 290, 180, 125, 40, null);
        botaoTransformacao2 = new GuiButton(getScreenWidth() - 155, 180, 125, 40, null);

        listaBotoes = new ArrayList<>();
        listaBotoes.add(botaoPut);
        listaBotoes.add(botaoDelete);
        listaBotoes.add(botaoLimpar);
        listaBotoes.add(botaoCriarAleatorio);
        listaBotoes.add(botaoTransformacao1);
        listaBotoes.add(botaoTransformacao2);

        foco = new FocoCamera(new Vector2(getScreenWidth() / 2, getScreenHeight() / 2), new Vector2(10, 10), 500);
        camera = new Camera2D(new Vector2(foco.pos.x, foco.pos.y), new Vector2(0, 0), 0, 1);
        bordaCamera = new Rectangle(0, 0, getScreenWidth(), getScreenHeight());

    }

    @Override
    public void update(double delta) {

        // Acho que da pra melhorar, mas funciona
        // Modo de trocar o texto dos botoes dependendo da Arvore
        if (t1 == null || t2 == null) {
            if (arvore instanceof ArvoreBinariaBusca) {
               botaoTransformacao1.setText("Para AVL");
               botaoTransformacao2.setText("Para V e P");
            } else if (arvore instanceof ArvoreAVL) {
                botaoTransformacao1.setText("Para ABB");
                botaoTransformacao2.setText("Para V e P");
            } else if (arvore instanceof ArvoreVermelhaPreta) {
                botaoTransformacao1.setText("Para ABB");
                botaoTransformacao2.setText("Para AVL");
            }
        }

        for (GuiButton b : listaBotoes) {
            b.update(delta);
            b.setTextColor(WHITE);
            b.setBackgroundColor(corBotao);
            b.setEnabled(mostrarMenu);
            b.setVisible(mostrarMenu);
        }

        if (mouseIn(botaoHambHitBox)) {
            if (isMouseButtonPressed(MOUSE_BUTTON_LEFT) && mostrarMenu == false) {
                mostrarMenu = true;
            } else if (isMouseButtonPressed(MOUSE_BUTTON_LEFT) && mostrarMenu == true) {
                mostrarMenu = false;
            }
        }

        foco.update(delta, bordaCamera, this);

        if (isKeyDown(KEY_DELETE)) {
            camera.rotation--;
        } else if (isKeyDown(KEY_PAGE_DOWN)) {
            camera.rotation++;
        }

        if (isKeyDown(KEY_KP_ADD) || isKeyDown(KEY_EQUAL)) {
            camera.zoom += 0.01;
        } else if (isKeyDown(KEY_KP_SUBTRACT) || isKeyDown(KEY_MINUS)) {
            camera.zoom -= 0.01;
            if (camera.zoom < 0.1) {
                camera.zoom = 0.1;
            }
        }

        if (isKeyPressed(KEY_R)) {
            camera.rotation = 0;
            camera.zoom = 1;
            foco.pos.x = getScreenWidth() / 2;
            foco.pos.y = getScreenHeight() / 2;
        }

        if (botaoPut.isMousePressed()) {
            arvore.put();
        } else if (botaoDelete.isMousePressed()) {
            arvore.delete();
        } else if (botaoLimpar.isMousePressed()) {
            arvore.limpar();
        } else if (botaoCriarAleatorio.isMousePressed()) {
            arvore.criarAletorio();
        } else if (botaoTransformacao1.isMousePressed()) {
            arvore.transformacao1(arvore.listaNode);
        } else if (botaoTransformacao2.isMousePressed()) {
            arvore.transformacao2(arvore.listaNode);
        }

        atualizarCamera();
    }

    @Override
    public void draw() {

        System.out.println(arvore.getClass());

        clearBackground(corBackground);

        setFontName(FONT_SANS_SERIF);
        setFontStyle(FONT_BOLD);

        beginMode2D(camera);

        // área desenhável (pode ser usada pela ArvoreBinaria)
        bordaCamera.fill(this, GREEN);

        endMode2D();

        if (mouseIn(botaoHambHitBox)) {
            for (int i = 1; i <= 3; i++) {
                fillRoundRectangle(getScreenWidth() - 48, 20 + i * 6, 20, 4, 5, LIGHTGRAY);
            }
        } else {
            for (int i = 1; i <= 3; i++) {
                fillRoundRectangle(getScreenWidth() - 48, 20 + i * 6, 20, 4, 5, GRAY);
            }
        }

        if (mostrarMenu) {
            bordaMenu.fill(this, corBackground);
            bordaMenu.draw(this, BLACK);
            for (GuiButton b : listaBotoes) {
                b.draw();
            }
        }
    }

    protected void atualizarCamera() {
        camera.target.x = foco.pos.x;
        camera.target.y = foco.pos.y;
        camera.offset.x = getScreenWidth() / 2;
        camera.offset.y = getScreenHeight() / 2;
    }

    protected boolean mouseIn(RoundRectangle r) {
        double mouseX = getMouseX();
        double mouseY = getMouseY();
        return mouseX >= r.x && mouseX <= r.width + r.x
                && mouseY >= r.y && mouseY <= r.height + r.y;
    }
    
    public static void main(String[] args) {
        new JanelaArvore("teste", new ArvoreBinariaBusca());
    }

}
