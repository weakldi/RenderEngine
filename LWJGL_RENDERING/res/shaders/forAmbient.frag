#version 330 core
in vec2 pass_uv;

out vec4 outColor;

uniform vec3 color;
uniform vec3 ambientLightIntensity;
uniform sampler2D textureSampler;
void main(void){
    vec3 finalColor =  color*texture2D(textureSampler,pass_uv).xyz *ambientLightIntensity;
    outColor = vec4(finalColor,1);
}