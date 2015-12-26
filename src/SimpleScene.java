
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;


// esling@ircam.fr
public class SimpleScene {
    public static void main(String[] args) {
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);
        
        canvas.addGLEventListener(new Render3D());
        
        Frame frame = new Frame("AWT Window Test");
        frame.setSize(500, 500);
        frame.add(canvas);
        frame.setVisible(true);
        
        //Rotate right cube
    	new Cube(0.0f, -1.0f, -7.0f, 0.0f, 1.0f, 0.0f, false, 0.5f);
    	// Rotate left cube
    	new Cube(0.0f, 1.3f, -7.0f, 0.0f, -1.0f, 0.0f, false, 0.3f);
    	// Satellite cube
    	new Cube(0.0f, -2.7f, -7.0f, 0, 0, -1f, true, 0.2f);
        
        // by default, an AWT Frame doesn't do anything when you click
        // the close button; this bit of code will terminate the program when
        // the window is asked to close
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        // Call display function 50 times per second
        FPSAnimator fpsAnimator = new FPSAnimator(canvas, 50);
        fpsAnimator.start();
    }
}
