#version 330 core

layout(location = 0) in vec3 pos;
layout(location = 1)in vec3 normal;
layout(location = 2)in vec2 uv;



uniform mat4 projMat;
uniform mat4 viewMat;
uniform mat4 modelMat;


void main(void){
    vec4 position =projMat*viewMat*modelMat*vec4(pos,1);
    gl_Position = position;
    
}