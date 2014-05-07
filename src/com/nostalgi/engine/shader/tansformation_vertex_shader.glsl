#version 330
uniform mat4 modelView;
uniform mat4 projection;

in vec4 in_Color;
in vec4 in_Position;
in vec2 texCoord;


out vec4 vertColor;

void main(void)
{	
	gl_Position = projection * modelView * in_Position;
	vertColor = in_Color;
}