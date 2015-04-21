#version 330 core
in vec2 pass_uv;

out vec4 color;

uniform sampler2D texture1;
uniform sampler2D texture2;

void main(void){
	vec3 color1 = texture2D(texture1,pass_uv).rgb;
	// vec3 color2 = texture2D(texture2,pass_uv).rgb;
	// if(color2!=vec3(0,0,0)&&color1!=color2){
		// color1+=color2*2;
	// }
	color = vec4(color1,1);
}