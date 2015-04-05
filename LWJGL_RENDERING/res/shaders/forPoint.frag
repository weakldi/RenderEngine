#version 330 core
in vec2 pass_uv;
in vec3 pass_normal;
in vec3 worldPos;
out vec4 outColor;

uniform vec3 color;
uniform vec3 lightInt;
uniform vec3 lightPos;
uniform vec3 attenuation;
uniform float specularInt;
uniform float speculaExp;
uniform vec3 camPos;
uniform sampler2D textureSampler;
uniform float range;



void main(void){
	vec3 toLightDir = lightPos - worldPos;
	float distanceToLight = length(toLightDir);
	float attenuationInt = attenuation.x + attenuation.y*distanceToLight+attenuation.z*distanceToLight*distanceToLight+0.00001;
	float diffuse = dot( normalize( pass_normal), normalize(toLightDir));
	vec3 diffuseColor = vec3(0,0,0);
	
	if(diffuse > 0 && distanceToLight <= range){
		vec3 specColor = vec3(0,0,0);
		diffuseColor = (lightInt * diffuse);
		
		vec3 eyeDir = normalize(camPos - worldPos);
		vec3 reflectDir = normalize(reflect(toLightDir, normalize( pass_normal)));
		
		float specFactor = dot(eyeDir,reflectDir);
		specFactor = pow(specFactor,speculaExp);
		if(specFactor>0){
			specColor = lightInt*specularInt*specFactor;
		}
		diffuseColor = (diffuseColor+specColor)/attenuationInt;
	}
	
	
   vec3 finalColor =  color*texture2D(textureSampler,pass_uv).xyz *diffuseColor;
    outColor = vec4(finalColor,1);
}