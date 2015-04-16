#version 330 core
in vec2 pass_uv;

out vec4 color;

uniform sampler2D filterTexture;

void main(void){
	
	color = vec4(vec3(dot(texture2D(filterTexture, pass_uv).rgb, vec3(0.29,0.59,0.12))),1.0);
	
}