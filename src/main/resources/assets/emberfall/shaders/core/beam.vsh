#version 150

#moj_import <lodestone:common_math.glsl>

in vec3 Position;
in vec2 UV0;
in vec4 Color;
in ivec2 UV2;

uniform sampler2D Sampler2;

uniform mat4 ModelViewMat;
uniform mat3 IViewRotMat;
uniform mat4 ProjMat;
uniform int FogShape;

out vec4 vertexColor;
out float vertexDistance;
out vec2 texCoord0;
out vec4 viewSpacePos;

void main() {
vec4 localSpacePos = vec4(Position, 1.0);
viewSpacePos = ModelViewMat * localSpacePos;
vec4 clipSpacePos = ProjMat * viewSpacePos;
gl_Position = clipSpacePos;

vec4 texColor = texelFetch(Sampler2, UV2 / 16, 0);

// ✨ BOOST: makes beam behave more like emissive particle
vec4 boosted = texColor * Color;
boosted.rgb *= 1.25; // <<< KEY: extra glow push

vertexColor = boosted;

vertexDistance = fogDistance(ModelViewMat, IViewRotMat, Position, FogShape);
texCoord0 = UV0;
}