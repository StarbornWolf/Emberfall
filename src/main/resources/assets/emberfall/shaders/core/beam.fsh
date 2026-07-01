#version 150

#moj_import <lodestone:common_math.glsl>

uniform sampler2D Sampler0;
uniform sampler2D SceneDepthBuffer;

uniform float LumiTransparency;
uniform float DepthFade;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
uniform mat4 InvProjMat;
uniform vec2 ScreenSize;

in float vertexDistance;
in vec4 vertexColor;
in vec2 texCoord0;
in vec4 viewSpacePos;

out vec4 fragColor;

void main() {

vec2 screenUV = gl_FragCoord.xy / ScreenSize;

vec4 tex = texture(Sampler0, texCoord0);

// =====================================================
// ✨ CORE COLOR BUILD
// =====================================================
vec4 color = transformColor(tex, LumiTransparency, vertexColor, ColorModulator);

// 🔥 BOOST 1: emissive lift (this is what makes it "wisp-like")
color.rgb *= 1.35;

// ⚪ BOOST 2: white-hot center illusion
float core = tex.a;
color.rgb += vec3(core * 0.35);

// =====================================================
// 🌫 FOG (keeps tall beam readable)
// =====================================================
fragColor = applyFog(color, FogStart, FogEnd, FogColor, vertexDistance);

// =====================================================
// 📉 DEPTH FADE (fixes block clipping feel)
// =====================================================
float sceneDepthClip = getDepth(SceneDepthBuffer, screenUV);
vec3 sceneViewSpace = viewSpaceFromDepth(sceneDepthClip, screenUV, InvProjMat);

float depthFade = applyDepthFade(sceneViewSpace.z, viewSpacePos.z, DepthFade);

// 🔥 soften instead of hard multiply (prevents "cut into blocks")
fragColor.rgb = mix(fragColor.rgb, fragColor.rgb * 0.2, 1.0 - depthFade);

fragColor.a *= depthFade;

// =====================================================
// ✨ FINAL GLOW PUSH (important for wisp feel)
// =====================================================
fragColor.rgb *= 1.15;
}