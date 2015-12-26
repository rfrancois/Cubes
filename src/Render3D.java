import java.util.ArrayList;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.fixedfunc.GLMatrixFunc;
import com.jogamp.opengl.glu.GLU;

public class Render3D implements GLEventListener {
	
	/**
	 * List with all the objects created
	 */
	private static ArrayList<Object3D> objects3D = new ArrayList<Object3D>();
	
	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub       
		GL2 gl = drawable.getGL().getGL2();

		// Clean buffers
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepth(1.0f);

		// Activate Z-buffer
		gl.glEnable(gl.GL_DEPTH_TEST);
		
		for(Object3D object : objects3D) {
        	object.init(drawable);
        }
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		update(drawable);
		render(drawable);
	}

	public void render(GLAutoDrawable drawable) {
		
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		
        for(Object3D cube : objects3D) {
        	cube.render(drawable);
        }
	}
	
	public void update(GLAutoDrawable drawable) {
		for(Object3D object : objects3D) {
        	object.update(drawable);
        }
	}
	
	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		GL2 gl = drawable.getGL().getGL2();
		GLU glu = GLU.createGLU(gl);

		// Parameters
		float fovY = 50.0f;
		float aspect = (float) width / (float) height;
		float zNear = 1f;
		float zFar = 1000f;

		// Perspective
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(fovY, aspect, zNear, zFar);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
	}
	
	public static void addObject3D(Object3D object3D) {
		objects3D.add(object3D);
	}

}
