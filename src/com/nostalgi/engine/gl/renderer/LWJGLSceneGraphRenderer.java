package com.nostalgi.engine.gl.renderer;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glEnable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Map.Entry;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.glu.GLU;

import com.nostalgi.engine.camera.ICamera;
import com.nostalgi.engine.gl.IShader;
import com.nostalgi.engine.gl.IShaderFactory;
import com.nostalgi.engine.gl.IShaderProgram;
import com.nostalgi.engine.gl.exceptions.ShaderFactoryIsNotSetException;
import com.nostalgi.engine.scene.Geometry;
import com.nostalgi.engine.scene.IGameScene;
import com.nostalgi.engine.scene.IMaterial;
import com.nostalgi.engine.scene.IMatrixStack;
import com.nostalgi.engine.scene.ISpatial;
import com.nostalgi.engine.scene.MatrixStack;
import com.nostalgi.engine.scene.buffer.IndexBuffer;
import com.nostalgi.engine.scene.buffer.VertexBuffer;
import com.nostalgi.engine.scene.geometry.IMesh;
import com.nostalgi.math.FastMath;
import com.nostalgi.math.ITransformation;
import com.nostalgi.math.Matrix4f;
import com.nostalgi.math.Quaternion;
import com.nostalgi.math.Vector3f;

public class LWJGLSceneGraphRenderer implements IGLRenderer {

	private IMatrixStack modelViewMatrixStack;
	private Matrix4f projectionMatrix;
	private IShaderFactory shaderFactory;
	private ICamera camera;
	
	private int projectionLocation;
	private int modelViewLocation;
	private int transformationVsId;
	private int fragmentShaderId;
	
	private int currentShaderProgram;
	
	private IShaderProgram sceneShaderProgram;
	
	
	public static final String MODELVIEW_UNIFORM = "modelView";
	public static final String PROJECTION_UNIFORM = "projection";
	public static final String POSITION_ATTRIBUTE = "position";
	public static final String TEXTURE_ATTRIBUTE = "textCoord";
	
	
	
	public LWJGLSceneGraphRenderer(IShaderFactory shaderFactory) {
		this.shaderFactory = shaderFactory;
		this.modelViewMatrixStack = new MatrixStack(); // initialize the matrix stack.
		
		// Create a projection matrix.
		this.projectionMatrix = new Matrix4f();
		this.projectionMatrix.loadIdentity();
	}
	
	@Override
	public void begin() {
		
		this.setupSceneShaders();
		this.setupProjection();
		// Enable depth buffer
		GL11.glEnable(GL11.GL_DEPTH_TEST);

	    // Enable back face culling
	    glEnable(GL11.GL_CULL_FACE);
	}
	
	@Override
	public void clear() {
		GL11.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void cleanUp() {
		this.cleanUpShaders();
	}

	@Override
	public void setCamera(ICamera camera) {
		this.camera = camera;
	}
	
	@Override
	public ICamera getCamera() {
		return this.camera;
	}
	
	@Override
	public void setShaderFactory(IShaderFactory factory) {
		this.shaderFactory = factory;
	}
	
	public IShaderFactory getShaderFactory() {
		return this.shaderFactory;
	}
	
	@Override
	public IShader createShader(int type, String source) throws ShaderFactoryIsNotSetException {
		
		IShader shader;
		
		if(this.shaderFactory != null) {
			shader = this.shaderFactory.createShader(type, source);
			
		} else {
			throw new ShaderFactoryIsNotSetException();
		}
		
		return shader;
	}
	
	@Override
	public IShader createShader(int type, File source) throws ShaderFactoryIsNotSetException, FileNotFoundException {
		
		BufferedReader reader = new BufferedReader(new FileReader(source));
		
		String line = null;
		StringBuilder builder = new StringBuilder();
		String ls = System.getProperty("line.separator");
		
		try {
			while((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(ls);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return this.createShader(type, builder.toString());
	}
	
	private void setupProjection() {
		this.projectionMatrix = this.camera.getProjectionMatrix();
	}
	
	private void setupSceneShaders() {
		
		this.sceneShaderProgram = this.shaderFactory.createShaderProgram();
		
		int id = this.loadShader(
				"src/com/nostalgi/engine/shader/tansformation_vertex_shader.glsl", 
				GL20.GL_VERTEX_SHADER);
		
		if(id != 0) {
			this.sceneShaderProgram.attachShader(id);
			this.transformationVsId = id;
		}
		
		id = this.loadShader(
				"src/com/nostalgi/engine/shader/default_fragment_shader.glsl", 
				GL20.GL_FRAGMENT_SHADER);
		
		if(id != 0) {
			this.sceneShaderProgram.attachShader(id);
			this.fragmentShaderId = id;
		}
		

	    GL20.glBindAttribLocation(this.sceneShaderProgram.getProgramId(), 0, "in_Position");
		GL20.glBindAttribLocation(this.sceneShaderProgram.getProgramId(), 1, "in_Color");
	    
		GL20.glLinkProgram(this.sceneShaderProgram.getProgramId());
		GL20.glValidateProgram(this.sceneShaderProgram.getProgramId());
		
		this.projectionLocation = GL20.glGetUniformLocation(
				sceneShaderProgram.getProgramId(), "projection");
		
		this.modelViewLocation = GL20.glGetUniformLocation(
				sceneShaderProgram.getProgramId(), "modelView");
		
		
		
		
		this.exitOnGlError("setupShaders");
	}
	
	private void exitOnGlError(String errorMessage) {
		int errorValue = GL11.glGetError();
		if (errorValue != GL11.GL_NO_ERROR) {
			String errorString = GLU.gluErrorString(errorValue);
			
			System.err.println("ERROR - #" + errorValue + " " + errorMessage + ": " + errorString);
		if (Display.isCreated()) Display.destroy();
			System.exit(-1);
		}
	}
	
	private int loadShader(String shaderSrcPath, int type) {
		int id = 0;
		try {
			IShader shader = this.createShader(type, 
					new File(shaderSrcPath));
			
			id = shader.getShaderId();
			
		} catch (ShaderFactoryIsNotSetException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public void renderScene(IGameScene scene) {
		
		
	}
	
	@Override
	public void renderSpatial(ISpatial spatial) {
		
	    GL11.glDepthMask(true);
	    
		Matrix4f modelViewMatrix = new Matrix4f();
		
		Matrix4f projection = camera.getProjectionMatrix();
		
		Vector3f cameraPosition = camera.getPosition().clone();
		Quaternion cameraRotation = camera.getRotation().clone();
		cameraRotation.negate();
		cameraPosition.negate();
		
		ITransformation spatialTransform = spatial.getWorldTransformation();
		Vector3f spatialTranslation = spatialTransform.getTranslation().clone();
		
		Matrix4f viewMatrix = new Matrix4f();
		
		viewMatrix.translateLocal(cameraRotation.mult(cameraPosition));
		
		modelViewMatrix.multLocal(viewMatrix);
		modelViewMatrix.translateLocal(spatialTranslation);
		modelViewMatrix.multLocal(spatialTransform.getRotation());
		modelViewMatrix.scale(spatialTransform.getScale());
		
		if(spatial instanceof Geometry) {
			int vao;
			int vbo;
			int indexes;
			
			// Draw
		    this.useShaderProgram(this.sceneShaderProgram);
			
			IMesh mesh = ((Geometry) spatial).getMesh();
			IMaterial material = ((Geometry)spatial).getMaterial();
			
			VertexBuffer vertices = (VertexBuffer) mesh
					.getBuffer(VertexBuffer.BUFFER_TYPE_POSITION);
			
			IndexBuffer indices = (IndexBuffer) mesh
					.getBuffer(VertexBuffer.BUFFER_TYPE_INDEX);
			
			// Bind all buffers;
			vao = GL30.glGenVertexArrays();
			GL30.glBindVertexArray(vao);
			
			vbo = GL15.glGenBuffers();
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
			GL15.glBufferData(
					GL15.GL_ARRAY_BUFFER, vertices.getBuffer(), GL15.GL_STREAM_DRAW);
			GL20.glVertexAttribPointer(0,vertices.getComponents(), GL11.GL_FLOAT, false, vertices.getStride(),0);
			
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
			
			
			if (material != null) {
				int vbocId = GL15.glGenBuffers();
				
				GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbocId);
				GL15.glBufferData(GL15.GL_ARRAY_BUFFER, material.getColor().getFloatBuffer(), GL15.GL_STATIC_DRAW);
				GL20.glVertexAttribPointer(1, 1, GL11.GL_FLOAT, false, 0, 0);
				
				GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
				
				this.exitOnGlError("Render spatial - Material");
			}
			
			GL30.glBindVertexArray(0);
			
			
			indexes = GL15.glGenBuffers();
		    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexes);
		    GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, 
		    		(ByteBuffer)indices.getBuffer(),GL15.GL_STATIC_DRAW);
		    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		    
		    
		    GL20.glUniformMatrix4(projectionLocation, false, projection.toFloatBuffer(true));
		    GL20.glUniformMatrix4(modelViewLocation, false, modelViewMatrix.toFloatBuffer(true));
		    
		    // Bind to the VAO that has all the information about the vertices
		    GL30.glBindVertexArray(vao);
		    GL20.glEnableVertexAttribArray(0);
		    GL20.glEnableVertexAttribArray(1);
		    
		    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexes);
		      // Draw the vertices
		    
		    GL11.glDrawElements(GL11.GL_TRIANGLES, indices.getIndexCount(),GL11.GL_UNSIGNED_BYTE, 0);
		    
	      // Put everything back to default (deselect)
		    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		    GL20.glDisableVertexAttribArray(0);
		    GL20.glDisableVertexAttribArray(1);
		    GL30.glBindVertexArray(0);
		    this.useShaderProgram(null);
		    
		    this.exitOnGlError("Render spatial - END");
		}
		
		
		for(ISpatial node : spatial.getChildren()) {
			this.renderSpatial(node);
		}
		
		
	}

	private void drawMesh(IMesh mesh) {
		
	}
	
	private void useShaderProgram(IShaderProgram program) {
		if(program != null ) {
			if(this.currentShaderProgram != program.getProgramId()) {
				this.currentShaderProgram = program.getProgramId();
				GL20.glUseProgram(program.getProgramId());
			}
		} else {
			this.currentShaderProgram = 0;
			GL20.glUseProgram(0);
		}
	}
	
	
	private void cleanUpShaders() {
		GL20.glUseProgram(0);
		GL20.glDeleteProgram(this.sceneShaderProgram.getProgramId());
		GL20.glDeleteShader(this.transformationVsId);
	}

}
