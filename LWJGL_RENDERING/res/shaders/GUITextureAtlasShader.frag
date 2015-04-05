
#version 330 core
in vec2 pass_uv;

out vec4 outColor;


uniform vec2 offset;
uniform vec3 color;
uniform float rows;
uniform sampler2D textureSampler;
void main(void){
	vec2 uv = (pass_uv / rows) + offset;
    outColor = vec4(color,1.0 )* texture2D(textureSampler,uv);
}