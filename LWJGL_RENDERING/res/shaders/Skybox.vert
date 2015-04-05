#version 330

layout(location = 0)in vec3 pos;
out vec3 textureCoords;

uniform mat4 projMat;
uniform mat4 viewMat;

void main(void){
	
	gl_Position =  projMat* viewMat * vec4(pos, 1.0); 
	textureCoords = pos;
	
}