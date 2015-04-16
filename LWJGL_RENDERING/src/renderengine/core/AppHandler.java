package renderengine.core;

import renderengine.shader.BloomPassOneShader;
import renderengine.shader.FXAAShader;
import renderengine.shader.ForAmbientShader;
import renderengine.shader.ForDiractionalShader;
import renderengine.shader.ForPointShader;
import renderengine.shader.ForSpotShader;
import renderengine.shader.GUIShader;
import renderengine.shader.GUITextureAtlasShader;
import renderengine.shader.GaussBlurShader;
import renderengine.shader.GrayScaleShader;
import renderengine.shader.MixShader;
import renderengine.shader.ShadowMapShader;
import renderengine.shader.SkyBoxShader;
//Besitzt Objekte von wichtigen Shadern und eine Instanz der Main-Klasse.
public class AppHandler {
	public static MainApplication mainApp;
	public static ForAmbientShader ambientShader;
	public static ForDiractionalShader diractionalShader;
	public static ForPointShader pointShader;
	public static ForSpotShader spotShader;
	public static SkyBoxShader skyShader;
	public static ShadowMapShader shadowShader;
	public static GUIShader guiShader;
	public static FXAAShader fxaaShader;
	public static GaussBlurShader blurShader;
	public static GUITextureAtlasShader textShader;
	public static GrayScaleShader grayScale;
	public static BloomPassOneShader bloomeOne;
	public static MixShader mixShader;
}
