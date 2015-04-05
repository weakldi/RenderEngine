#version 330 core
in vec2 pass_uv;
in vec3 pass_normal;
in vec4 shadowCoord;

out vec4 outColor;

uniform vec3 color;
uniform vec3 lightInt;
uniform vec3 lightDir;
uniform sampler2D textureSampler;
uniform sampler2D shadowMap;


float calcShadow(sampler2D shadowMap, vec4 shadowCoord){
	vec3 shadowMapCoord = shadowCoord.xyz/shadowCoord.w ;
	vec2 moments = texture2D(shadowMap,shadowMapCoord.xy).xy;
	float p = step(shadowMapCoord.z ,moments.x);
	//float variance = max(moments.y - moments.x * moments.x,0.00002);
	
	//float d = shadowMapCoord.z - moments.x;
	//float pMax = variance / (variance + d*d);
	
	//return min(max(p, pMax),1.0);
	
	return p;
	
}
void main(void){
	
	float diffuse = dot( normalize( pass_normal),- normalize(lightDir));
	vec3 diffuseColor = vec3(0,0,0);
	if(diffuse > 0){
		diffuseColor = lightInt * diffuse; 
	}
	vec3 lightColor =  color*texture2D(textureSampler,pass_uv).xyz *diffuseColor;
	
    vec3 finalColor = lightColor* calcShadow(shadowMap,shadowCoord);
	outColor = vec4(finalColor,1);
}