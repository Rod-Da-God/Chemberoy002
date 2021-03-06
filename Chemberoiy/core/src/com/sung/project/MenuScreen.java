package com.sung.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen  implements Screen {

    float delay = 1;


    Main menuScreen;

    Vector3 vector3 = new Vector3();


    SpriteBatch sBatch;
    OrthographicCamera oCamera;

    Texture startButton;
    Texture exitButton;
    Texture background;
    Sprite S_startButton;
    Sprite S_exitButton;
    Sprite S_background;

     static float BUTTON_SIZE = 800f;
     static float START_BUTTON = 2.7f;
     static float EXIT_BUTTON = 4.2f;

    MenuScreen(Main menuScreen) {

        this.menuScreen = menuScreen;


        float heightY = Gdx.graphics.getHeight();
        float weightX = Gdx.graphics.getWidth();

        oCamera = new OrthographicCamera(heightY, weightX);
        oCamera.setToOrtho(false);
        sBatch = new SpriteBatch();


        startButton = new Texture("ban1.jpg");
        exitButton = new Texture("exitButton.jpg");
        background = new Texture("background.jpg");



        S_startButton = new Sprite(startButton);
        S_exitButton = new Sprite(exitButton);
        S_background = new Sprite(background);


        S_startButton.setPosition(S_startButton.getWidth() * (weightX / BUTTON_SIZE), S_startButton.getHeight() * (weightX / BUTTON_SIZE));
        S_exitButton.setSize(S_exitButton.getWidth() * (weightX / BUTTON_SIZE), S_exitButton.getHeight() * (weightX / BUTTON_SIZE));
        S_background.setSize(weightX, heightY);
        S_startButton.setPosition((weightX / 2f - S_startButton.getWidth() / 2), weightX / START_BUTTON);
        S_exitButton.setPosition((weightX / 2f - S_exitButton.getWidth() / 2), weightX / EXIT_BUTTON);
        S_background.setAlpha(0.8f);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sBatch.setProjectionMatrix(oCamera.combined);
        sBatch.begin();
        S_background.draw(sBatch);
        S_startButton.draw(sBatch);
        S_exitButton.draw(sBatch);
        touch();
        sBatch.end();
    }


    void touch(){
        if(Gdx.input.justTouched()) {
            vector3.set(Gdx.input.getX(),Gdx.input.getY(), 0);
            oCamera.unproject(vector3);
            float touchX = vector3.x;
            float touchY= vector3.y;
            if((touchX>=S_startButton.getX()) && touchX<= (S_startButton.getX()+S_startButton.getWidth()) && (touchY>=S_startButton.getY()) && touchY<=(S_startButton.getY()+S_startButton.getHeight()) ){
                Gdx.app.debug("GameScreen", "my error message");
                menuScreen.setScreen( new Main());
                Gdx.app.debug("GameScreen", "error");
            }
            else if((touchX>=S_exitButton.getX()) && touchX<= (S_exitButton.getX()+S_exitButton.getWidth()) && (touchY>=S_exitButton.getY()) && touchY<=(S_exitButton.getY()+S_exitButton.getHeight()) ){
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        startButton.dispose();
        exitButton.dispose();
        sBatch.dispose();

    }
}
