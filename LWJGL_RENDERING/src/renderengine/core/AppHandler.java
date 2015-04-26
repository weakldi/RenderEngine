package renderengine.core;

import renderengine.shader.BloomPassOneShader;
import renderengine.shader.FXAAShader;
import renderengine.shader.ForDiractionalShader;
import renderengine.shader.GUIShader;
import renderengine.shader.GUITextureAtlasShader;
import renderengine.shader.GaussBlurShader;
import renderengine.shader.GrayScaleShader;
import renderengine.shader.MixShader;
import renderengine.shader.SkyBoxShader;
//Besitzt Objekte von wichtigen Shadern und eine Instanz der Main-Klasse.
public class AppHandler {
	public static MainApplication mainApp;
	
	public static ForDiractionalShader diractionalShader;
	

	public static SkyBoxShader skyShader;
	
	public static GUIShader guiShader;
	public static FXAAShader fxaaShader;
	public static GaussBlurShader blurShader;
	public static GUITextureAtlasShader textShader;
	public static GrayScaleShader grayScale;
	public static BloomPassOneShader bloomeOne;
	public static MixShader mixShader;
	

}
