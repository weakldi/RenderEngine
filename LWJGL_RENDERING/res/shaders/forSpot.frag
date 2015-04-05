#version 330 core
in vec2 pass_uv;
in vec3 pass_normal;
in vec3 worldPos;
in vec4 shadowCoord;

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
uniform float cutoff;
uniform vec3 spotDir;
uniform sampler2D shadowMap;

float linstep(float low, float high, float v){
    return clamp((v-low)/(high-low), 0.0, 1.0);
}



float calcShadow(sampler2D shadowMap, vec4 shadowCoord){
	vec3 shadowMapCoord = shadowCoord.xyz/shadowCoord.w ;
	vec2 moments = texture2D(shadowMap,shadowMapCoord.xy).xy;
	float p = step(shadowMapCoord.z ,moments.x);
	float variance = max(moments.y - moments.x * moments.x,0.00002);
	
	float d = shadowMapCoord.z - moments.x;
	float pMax = linstep(0.2, 1.0, variance / (variance + d*d));
	
	//return min(max(p, pMax),1.0);
	return p;
}

void main(void){
	vec3 lightDir = normalize(worldPos-lightPos);
	float spotFactor = dot(lightDir,spotDir);
	vec3 diffuseColor = vec3(0,0,0);
	float shadowInt = 0.5;
	if(spotFactor > cutoff){
		vec3 toLightDir = lightPos - worldPos;
		float distanceToLight = length(toLightDir);
		float attenuationInt = attenuation.x + attenuation.y*distanceToLight+attenuation.z*distanceToLight*distanceToLight+0.00001;
		float diffuse = dot( normalize( pass_normal), normalize(toLightDir));
		
	
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
			diffuseColor = ((diffuseColor+specColor)/attenuationInt) * (1.0 - (1.0 - spotFactor) / (1.0 - cutoff));
			shadowInt = calcShadow(shadowMap,shadowCoord);
		}
	}
	
   vec3 finalColor =  color*texture2D(textureSampler,pass_uv).xyz *diffuseColor*shadowInt;
    outColor = vec4(finalColor,1);
}