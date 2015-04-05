#version 330 core

layout(location = 0) in vec3 pos;
layout(location = 1)in vec3 normal;
layout(location = 2)in vec2 uv;

out vec4 shadowCoord;
out vec2 pass_uv;
out vec3 pass_normal;

uniform mat4 projMat;
uniform mat4 viewMat;
uniform mat4 modelMat;
uniform mat4 depthModelMat;
uniform mat4 depthProjMat;
uniform mat4 depthViewMat;

const mat4 biasMat = mat4(
  0.5, 0.0, 0.0, 0.0,
  0.0, 0.5, 0.0, 0.0,
  0.0, 0.0, 0.5, 0.0,
  0.5, 0.5, 0.5, 1.0);

void main(void){
    vec4 position =projMat*viewMat*modelMat*vec4(pos,1);
    gl_Position = position;
    pass_uv = uv;
    pass_normal = (modelMat*vec4(normal,0.0)).xyz;
	shadowCoord =  depthProjMat * depthViewMat * depthModelMat* vec4(pos,1);
}