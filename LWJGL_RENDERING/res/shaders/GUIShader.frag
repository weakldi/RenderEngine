
#version 330 core
in vec2 pass_uv;

out vec4 outColor;



uniform sampler2D textureSampler;
void main(void){
    outColor =  texture2D(textureSampler,pass_uv);
}