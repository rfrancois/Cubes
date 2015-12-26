import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Cube implements Object3D {

	/**
	 * Cube position
	 */
	private float x, y, l;
	/**
	 * Cube rotation
	 */
	private float rx, ry, rl;
	/**
	 * Cube size
	 */
	private float size;
	private boolean satellite;
	private int iTexture = 0;

	public Cube(float x, float y, float l, float rx, float ry, float rl, boolean satellite,
			float size) {
		this.x = x;
		this.y = y;
		this.l = l;
		this.rx = rx;
		this.ry = ry;
		this.rl = rl;
		this.satellite = satellite;
		this.size = size;
		Render3D.addObject3D(this);
	}
	
	public void init(GLAutoDrawable drawable) {
		try {
			File file = new File("img/monalichat.jpg");
			Texture texture = TextureIO.newTexture(file, true);
			iTexture = texture.getTextureObject();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void render(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2();

		gl.glLoadIdentity();
		float angle = (System.currentTimeMillis()/10)%360;
		
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glBindTexture(GL2.GL_TEXTURE_2D, iTexture);

		// Cube position
		if(!satellite) {
			gl.glTranslatef(x, y, l);
			gl.glRotatef(angle, rx, ry, rl);
		}
		else {
			//gl.glPushMatrix();
			// Turn around 0;0 point
			gl.glTranslatef(0, 0, l);
			// How to turn
			gl.glRotatef(angle, rx, ry, rl);
			// Where to start
			gl.glTranslatef(x, y, 0);
			//gl.glPopMatrix();
		}

		gl.glBegin(GL2.GL_QUADS);

		// Front
		//gl.glColor3f(0f, 1f, 1f);
		gl.glTexCoord2d(1, 1);
		gl.glVertex3f(size, size, size);
		gl.glTexCoord2d(0, 1);
		gl.glVertex3f(-size, size, size); 
		gl.glTexCoord2d(0, 0);
		gl.glVertex3f(-size, -size, size); 
		gl.glTexCoord2d(1, 0);
		gl.glVertex3f(size, -size, size);

		// Back
		//gl.glColor3f(0.5f, 1f, 0.5f);
		gl.glTexCoord2d(1, 1);
		gl.glVertex3f(size, size, -size);
		gl.glTexCoord2d(0, 1);
		gl.glVertex3f(-size, size, -size);
		gl.glTexCoord2d(0, 0);
		gl.glVertex3f(-size, -size, -size); 
		gl.glTexCoord2d(1, 0);
		gl.glVertex3f(size, -size, -size);

		// Left
		//gl.glColor3f(1f, 1f, 0f);
		gl.glTexCoord2d(1, 1);
		gl.glVertex3f(size, size, size); 
		gl.glTexCoord2d(0, 1);
		gl.glVertex3f(size, size, -size); 
		gl.glTexCoord2d(0, 0);
		gl.glVertex3f(size, -size, -size);
		gl.glTexCoord2d(1, 0);
		gl.glVertex3f(size, -size, size);

		// Right
		//gl.glColor3f(1f, 0f, 0f);
		gl.glTexCoord2d(1, 1);
		gl.glVertex3f(-size, size, size);
		gl.glTexCoord2d(0, 1);
		gl.glVertex3f(-size, size, -size); 
		gl.glTexCoord2d(0, 0);
		gl.glVertex3f(-size, -size, -size); 
		gl.glTexCoord2d(1, 0);
		gl.glVertex3f(-size, -size, size);

		// Top
		//gl.glColor3f(1f, 0f, 1f);
		gl.glTexCoord2d(1, 1);
		gl.glVertex3f(-size, size, -size); 
		gl.glTexCoord2d(0, 1);
		gl.glVertex3f(size, size, -size); 
		gl.glTexCoord2d(0, 0);
		gl.glVertex3f(size, size, size); 
		gl.glTexCoord2d(1, 0);
		gl.glVertex3f(-size, size, size);

		// Bottom
		//gl.glColor3f(0f, 0f, 1f);
		gl.glTexCoord2d(1, 1);
		gl.glVertex3f(size, -size, size); 
		gl.glTexCoord2d(0, 1);
		gl.glVertex3f(-size, -size, size); 
		gl.glTexCoord2d(0, 0);
		gl.glVertex3f(-size, -size, -size); 
		gl.glTexCoord2d(1, 0);
		gl.glVertex3f(size, -size, -size);

		gl.glEnd();
	}

	public void update(GLAutoDrawable drawable) {
		
	}
}
