#version 330

in vec3 textureCoords;
out vec4 out_Color;

uniform samplerCube cubeMap;

void main(void){
    out_Color = texture(cubeMap,textureCoords);
	//out_Color = vec4(1,0.5,0.25,1);
}