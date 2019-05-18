package com.juegoejemplo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, imgBomba, imgGB, imgPersonaje;//creamos una textura/imagen
	Texture [] personajeframes;
	Sprite spriteBom;
	int movimientoY;
	Rectangle rectanglePersona, rectangleBomba;//Creamos una figura de tipo Rectalngulo, para asignarsela a una dibujo, y controlar la colision entre otro objetos
	int contador, contadorFrames, contadorBombas, bombaX = 0;
	//Para cambiar la orientacion, hay que ir al AndroidManifest y cambiamos android:screenOrientation="landscape" a "portrait"
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		imgBomba = new Texture("bomb.png");
		imgGB = new Texture("bg.png");
		imgPersonaje = new Texture("frame-1.png");
		spriteBom = new Sprite(imgBomba);
		spriteBom.setPosition(400, 500);
		//Primero le asignamos las dimensiones actuales del objeto, le damos las medidas del del objeto Rectangulo y que se dibuje acorde a las dimensiones de la foto con .getHeight() y . getWidth()
		rectanglePersona = new Rectangle();
		rectangleBomba = new Rectangle();
		personajeframes = new Texture[4];
		personajeframes[0] = new Texture("frame-1.png");
		personajeframes[1] = new Texture("frame-2.png");
		personajeframes[2] = new Texture("frame-3.png");
		personajeframes[3] = new Texture("frame-4.png");

		bombaX = Gdx.graphics.getWidth()/2;

		movimientoY = Gdx.graphics.getHeight()/2 - 200;
		contador = 0;
		contadorFrames = 0;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



		//Ajustar tiempo de refresco en la pantalla
		contadorFrames = contadorFrames%60;
		if(Gdx.input.isTouched()){
			movimientoY+=50;
		}
		if(contadorFrames == 0){
			contador++;
			//Controloar cuando se toca la pantalla
		}else {
			contador = 0;
		}

		//generar bonbas
		contadorBombas++;
		contadorBombas = contadorBombas%4;
		if (contadorBombas == 0){
			bombaX -= 10;
		}

		//Detectar colision entre personaje y bomba

		if(Intersector.overlaps(rectangleBomba, rectanglePersona)){
			Gdx.app.log("choque", "han chocado");
		}

		if(contador == personajeframes.length){
			contador = 0;
		}
		batch.begin();//Desde aqui...


		if(movimientoY > 0){
			movimientoY -= 5;
		}
		batch.draw(imgGB, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(personajeframes[contador], Gdx.graphics.getWidth()/2 -150, movimientoY);
		batch.draw(img, 0, 0);
		batch.draw(imgBomba, Gdx.graphics.getWidth(), 200);
		spriteBom.draw(batch);
		rectanglePersona.set(Gdx.graphics.getWidth()/2 -150, movimientoY, imgPersonaje.getWidth(),imgPersonaje.getHeight());
		rectangleBomba.set(0 , 0, imgBomba.getWidth(), imgBomba.getHeight());

		batch.end();//...hasta aqui, puedo dibujar


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
