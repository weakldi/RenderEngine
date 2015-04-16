#version 330 core
in vec2 pass_uv;

out vec4 color;

uniform sampler2D texture1;
uniform sampler2D texture2;

void main(void){
	
	color = vec4(pass_uv,0,1);
}