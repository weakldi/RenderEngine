#version 330 core
in vec2 pass_uv;

out vec4 color;

uniform sampler2D filterTexture;
uniform float minValue;
void main(void){
	vec3 colorValue = texture2D(filterTexture, pass_uv).rgb;
	float intensity = dot(colorValue, vec3(0.33333,0.33333,0.33333));
	color = vec4(0,0,0,1.0);
	if(intensity > minValue){
		color = vec4(colorValue,1.0);
	}
}