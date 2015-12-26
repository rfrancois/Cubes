import com.jogamp.opengl.GLAutoDrawable;

public interface Object3D {

	public void render(GLAutoDrawable drawable);
	
	public void update(GLAutoDrawable drawable);

	public void init(GLAutoDrawable drawable);
}
