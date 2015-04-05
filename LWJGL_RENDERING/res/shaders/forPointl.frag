#version 330 core
in vec2 pass_uv;
in vec3 pass_normal;
in vec3 toLight;
out vec4 outColor;

uniform vec3 color;
uniform vec3 lightInt;
uniform vec3 attenuation;

void main(void){
    float toLightDistance = length(toLight);
    float attenuationFactor = attenuation.x + (attenuation.y*toLightDistance) + (attenuation.z*toLightDistance*toLightDistance);
    vec3 surfaceNormal = normalize(pass_normal);
    vec3 lightDirN = normalize(toLight);
    float dotProduct = dot(surfaceNormal,lightDirN);
    float brightness = max(dotProduct,0.0);
    vec3 finalColor = color*((lightInt*brightness)/attenuationFactor);
    outColor = vec4(finalColor,1);
}