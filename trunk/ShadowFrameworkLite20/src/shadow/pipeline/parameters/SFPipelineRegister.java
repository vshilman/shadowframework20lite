/*
	The Shadow Framework 1.0 Lead Version - a complete framework for Real Time Graphics based on OpenGL 2.0
    Copyright (C) 2010  Alessandro Martinelli  <alessandro.martinelli@unipv.it>

    This file is part of The Shadow Framework.

    The Shadow Framework is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The Shadow Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Shadow Framework.  If not, see <http://www.gnu.org/licenses/>.
*/
package shadow.pipeline.parameters;

import java.util.HashMap;

import shadow.system.SFException;

/**
 * @author Alessandro Martinelli
 */
public class SFPipelineRegister extends SFParameter{

	public static final short READ_ON_TESSELLATION=1;
	public static final short WRITE_ON_TESSELLATION=2;
	public static final short READ_ON_PRIMITIVE=4;
	public static final short WRITE_ON_PRIMITIVE=8;
	public static final short READ_ON_TRANSFORM=16;
	public static final short WRITE_ON_TRANSFORM=32;
	public static final short READ_ON_MATERIAL=64;
	public static final short WRITE_ON_MATERIAL=128;
	public static final short READ_ON_LIGHTING=256;
	public static final short WRITE_ON_LIGHTING=512;
	public static final short READ_ALL=READ_ON_LIGHTING+READ_ON_MATERIAL+READ_ON_PRIMITIVE+READ_ON_TESSELLATION+READ_ON_TRANSFORM;
	public static final short WROTE_BY_TESSELLATION=READ_ON_LIGHTING+READ_ON_MATERIAL+READ_ON_PRIMITIVE+READ_ON_TRANSFORM+WRITE_ON_TESSELLATION;
	public static final short WROTE_BY_PRIMITIVE=READ_ON_LIGHTING+READ_ON_MATERIAL+READ_ON_TRANSFORM+WRITE_ON_PRIMITIVE;
	public static final short WROTE_BY_TRANSFORM=READ_ON_LIGHTING+READ_ON_MATERIAL+WRITE_ON_TRANSFORM;
	public static final short WROTE_BY_MATERIAL=READ_ON_LIGHTING+WRITE_ON_MATERIAL;
	
	private static HashMap<String, SFPipelineRegister> predefinedGlobalV=new HashMap<String, SFPipelineRegister>();
	
	static{
		predefinedGlobalV.put("uv",new SFPipelineRegister(GLOBAL_FLOAT, "uv",WRITE_ON_TESSELLATION));
		predefinedGlobalV.put("uvw",new SFPipelineRegister(GLOBAL_FLOAT, "uvw",WRITE_ON_TESSELLATION));
		predefinedGlobalV.put("w",new SFPipelineRegister(GLOBAL_FLOAT, "w",WRITE_ON_TESSELLATION));
		predefinedGlobalV.put("u",new SFPipelineRegister(GLOBAL_FLOAT, "u",WRITE_ON_TESSELLATION));
		predefinedGlobalV.put("v",new SFPipelineRegister(GLOBAL_FLOAT, "v",WRITE_ON_TESSELLATION));
		predefinedGlobalV.put("w",new SFPipelineRegister(GLOBAL_FLOAT, "w",WRITE_ON_TESSELLATION));
		predefinedGlobalV.put("<>",new SFPipelineRegister(GLOBAL_GENERIC, "<>",0));
		//predefinedGlobalV.put("uvw",new SFPipelineRegister(GLOBAL_FLOAT4, "uvw",READ_ON_PRIMITIVE));
		predefinedGlobalV.put("projection",new SFPipelineRegister(GLOBAL_MATRIX4, "projection",READ_ALL));
		predefinedGlobalV.put("modelview",new SFPipelineRegister(GLOBAL_MATRIX4, "modelview",READ_ALL));
		predefinedGlobalV.put("vectorsModelview",new SFPipelineRegister(GLOBAL_MATRIX4, "vectorsModelview",READ_ALL));
		predefinedGlobalV.put("projection",new SFPipelineRegister(GLOBAL_MATRIX4, "projection",READ_ALL));
		predefinedGlobalV.put("position",new SFPipelineRegister(GLOBAL_FLOAT3, "position",WROTE_BY_TRANSFORM));
		predefinedGlobalV.put("Color1",new SFPipelineRegister(GLOBAL_FLOAT4, "Color1",READ_ALL));
		predefinedGlobalV.put("LightDir1",new SFPipelineRegister(GLOBAL_FLOAT4, "LightDir1",READ_ALL));
		predefinedGlobalV.put("normal",new SFPipelineRegister(GLOBAL_FLOAT3, "normal",WROTE_BY_TRANSFORM));
		predefinedGlobalV.put("color",new SFPipelineRegister(GLOBAL_FLOAT4, "color",WROTE_BY_MATERIAL));	
		predefinedGlobalV.put("P0",new SFPipelineRegister(GLOBAL_FLOAT3, "P0",WROTE_BY_PRIMITIVE));
		predefinedGlobalV.put("N0",new SFPipelineRegister(GLOBAL_FLOAT3, "N0",WROTE_BY_PRIMITIVE));
		predefinedGlobalV.put("uvw",new SFPipelineRegister(GLOBAL_FLOAT3, "uvw",WROTE_BY_TESSELLATION));
		predefinedGlobalV.put("P",new SFPipelineRegister(GLOBAL_FLOAT3, "P",WROTE_BY_TRANSFORM));
		predefinedGlobalV.put("N",new SFPipelineRegister(GLOBAL_FLOAT3, "N",WROTE_BY_TRANSFORM));
		predefinedGlobalV.put("fColor",new SFPipelineRegister(GLOBAL_FLOAT4, "fColor",WROTE_BY_TESSELLATION));
	};
	
	private int use=0;
	
	public SFPipelineRegister(short type, String name,int use) {
		super();
		this.name = name;
		this.type = type;
		this.use=use;
	}
	
	public int getUse() {
		return use;
	}
	
	public static SFPipelineRegister getFromName(String name) throws SFException{
		SFPipelineRegister globalV=predefinedGlobalV.get(name);
		if(globalV==null)
			throw new SFException("No Predefined Global Variable having name "+name);
		return globalV;
	}
}


///**
// * 
// * SFGlobal should be user defined.
// * @author Alessandro Martinelli
// */
//public enum SFGlobalV{
//	
//	//Parameters for Tex Coord
//	Atx0("uniform vec2 ","Atx0"),
//	Btx0("uniform vec2 ","Btx0"),
//	Ctx0("uniform vec2 ","Ctx0"),
//	Dtx0("uniform vec2 ","Dtx0"),
//	Etx0("uniform vec2 ","Etx0"),
//	Ftx0("uniform vec2 ","Ftx0"),
//	Gtx0("uniform vec2 ","Gtx0"),
//	Htx0("uniform vec2 ","Htx0"),
//	Itx0("uniform vec2 ","Itx0"),
//	Ltx0("uniform vec2 ","Ltx0"),
//	Mtx0("uniform vec2 ","Mtx0"),
//	Ntx0("uniform vec2 ","Ntx0"),
//
//	Atx1("uniform vec2 ","Atx1"),
//	Btx1("uniform vec2 ","Btx1"),
//	Ctx1("uniform vec2 ","Ctx1"),
//	Dtx1("uniform vec2 ","Dtx1"),
//	
//	Atx2("uniform vec2 ","Atx2"),
//	Btx2("uniform vec2 ","Btx2"),
//	Ctx2("uniform vec2 ","Ctx2"),
//	Dtx2("uniform vec2 ","Dtx2"),
//
//	Atx3("uniform vec2 ","Atx3"),
//	Btx3("uniform vec2 ","Btx3"),
//	Ctx3("uniform vec2 ","Ctx3"),
//	Dtx3("uniform vec2 ","Dtx3"),
//
//	Atx4("uniform vec2 ","Atx4"),
//	Btx4("uniform vec2 ","Btx4"),
//	Ctx4("uniform vec2 ","Ctx4"),
//	Dtx4("uniform vec2 ","Dtx4"),
//
//	Atx5("uniform vec2 ","Atx5"),
//	Btx5("uniform vec2 ","Btx5"),
//	Ctx5("uniform vec2 ","Ctx5"),
//	Dtx5("uniform vec2 ","Dtx5"),
//
//	Atx6("uniform vec2 ","Atx6"),
//	Btx6("uniform vec2 ","Btx6"),
//	Ctx6("uniform vec2 ","Ctx6"),
//	Dtx6("uniform vec2 ","Dtx6"),
//
//	Atx7("uniform vec2 ","Atx7"),
//	Btx7("uniform vec2 ","Btx7"),
//	Ctx7("uniform vec2 ","Ctx7"),
//	Dtx7("uniform vec2 ","Dtx7"),
//	
//	//Parameters for Vertices position
//	A("uniform vec3 ","A"),
//	B("uniform vec3 ","B"),
//	C("uniform vec3 ","C"),
//	D("uniform vec3 ","D"),
//	E("uniform vec3 ","E"),
//	F("uniform vec3 ","F"),
//	G("uniform vec3 ","G"),
//	H("uniform vec3 ","H"),
//	I("uniform vec3 ","I"),
//	L("uniform vec3 ","L"),
//	M("uniform vec3 ","M"),
//	N("uniform vec3 ","N"),
//	O("uniform vec3 ","O"),
//	P("uniform vec3 ","P"),
//	Q("uniform vec3 ","Q"),
//	R("uniform vec3 ","R"),
//	S("uniform vec3 ","S"),
//	T("uniform vec3 ","T"),
//	
//	//Parameters for Normals
//	An("uniform vec3 ","An"),
//	Bn("uniform vec3 ","Bn"),
//	Cn("uniform vec3 ","Cn"),
//	Dn("uniform vec3 ","Dn"),
//	En("uniform vec3 ","En"),
//	Fn("uniform vec3 ","Fn"),
//	Gn("uniform vec3 ","Gn"),
//	Hn("uniform vec3 ","Hn"),
//	In("uniform vec3 ","In"),
//	Ln("uniform vec3 ","Ln"),
//	Mn("uniform vec3 ","Mn"),
//	Nn("uniform vec3 ","Nn"),
//	On("uniform vec3 ","On"),
//	Pn("uniform vec3 ","Pn"),
//	Qn("uniform vec3 ","Qn"),
//	Rn("uniform vec3 ","Rn"),
//	Sn("uniform vec3 ","Sn"),
//	Tn("uniform vec3 ","Tn"),
//	
//	//Tangent vector - t direction
//	At("uniform vec3 ","At"),
//	Bt("uniform vec3 ","Bt"),
//	Ct("uniform vec3 ","Ct"),
//	Dt("uniform vec3 ","Dt"),
//	Et("uniform vec3 ","Et"),
//	Ft("uniform vec3 ","Ft"),
//	Gt("uniform vec3 ","Gt"),
//	Ht("uniform vec3 ","Ht"),
//	It("uniform vec3 ","It"),
//	Lt("uniform vec3 ","Lt"),
//	Mt("uniform vec3 ","Mt"),
//	Nt("uniform vec3 ","Nt"),
//	Ot("uniform vec3 ","Ot"),
//	Pt("uniform vec3 ","Pt"),
//	Qt("uniform vec3 ","Qt"),
//	Rt("uniform vec3 ","Rt"),
//	St("uniform vec3 ","St"),
//	Tt("uniform vec3 ","Tt"),
//	
//	//tangent vector - u direction 
//	Au("uniform vec3 ","Au"),
//	Bu("uniform vec3 ","Bu"),
//	Cu("uniform vec3 ","Cu"),
//	Du("uniform vec3 ","Du"),
//	Eu("uniform vec3 ","Eu"),
//	Fu("uniform vec3 ","Fu"),
//	Gu("uniform vec3 ","Gu"),
//	Hu("uniform vec3 ","Hu"),
//	Iu("uniform vec3 ","Iu"),
//	Lu("uniform vec3 ","Lu"),
//	Mu("uniform vec3 ","Mu"),
//	Nu("uniform vec3 ","Nu"),
//	Ou("uniform vec3 ","Ou"),
//	Pu("uniform vec3 ","Pu"),
//	Qu("uniform vec3 ","Qu"),
//	Ru("uniform vec3 ","Ru"),
//	Su("uniform vec3 ","Su"),
//	Tu("uniform vec3 ","Tu"),
//
//	//tangent vector - u direction 
//	Av("uniform vec3 ","Av"),
//	Bv("uniform vec3 ","Bv"),
//	Cv("uniform vec3 ","Cv"),
//	Dv("uniform vec3 ","Dv"),
//	Ev("uniform vec3 ","Ev"),
//	Fv("uniform vec3 ","Fv"),
//	Gv("uniform vec3 ","Gv"),
//	Hv("uniform vec3 ","Hv"),
//	Iv("uniform vec3 ","Iv"),
//	Lv("uniform vec3 ","Lv"),
//	Mv("uniform vec3 ","Mv"),
//	Nv("uniform vec3 ","Nv"),
//	Ov("uniform vec3 ","Ov"),
//	Pv("uniform vec3 ","Pv"),
//	Qv("uniform vec3 ","Qv"),
//	Rv("uniform vec3 ","Rv"),
//	Sv("uniform vec3 ","Sv"),
//	Tv("uniform vec3 ","Tv"),
//
//	//Position Model, value1f
//	a("uniform float ","a"),
//	b("uniform float ","b"),
//	c("uniform float ","c"),
//	d("uniform float ","d"),
//	e("uniform float ","e"),
//	f("uniform float ","f"),
//	g("uniform float ","g"),
//	h("uniform float ","h"),
//	i("uniform float ","i"),
//	l("uniform float ","l"),
//	m("uniform float ","m"),
//	n("uniform float ","n"),
//	o("uniform float ","o"),
//	p("uniform float ","p"),
//	q("uniform float ","q"),
//	r("uniform float ","r"),
//	s("uniform float ","s"),
//	t("uniform float ","t"),
//	
//
//	//Normals Model, value1f
//	an("uniform float ","an"),
//	bn("uniform float ","bn"),
//	cn("uniform float ","cn"),
//	dn("uniform float ","dn"),
//	en("uniform float ","en"),
//	fn("uniform float ","fn"),
//	gn("uniform float ","gn"),
//	hn("uniform float ","hn"),
//	in("uniform float ","in"),
//	ln("uniform float ","ln"),
//	mn("uniform float ","mn"),
//	nn("uniform float ","nn"),
//	on("uniform float ","on"),
//	pn("uniform float ","pn"),
//	qn("uniform float ","qn"),
//	rn("uniform float ","rn"),
//	sn("uniform float ","sn"),
//	tn("uniform float ","tn"),
//
//	shColor0("uniform vec3 ","shColor0"),
//	shColor1("uniform vec3 ","shColor1"),
//	shColor2("uniform vec3 ","shColor2"),
//	shColor3("uniform vec3 ","shColor3"),
//	shColor4("uniform vec3 ","shColor4"),
//	shColor5("uniform vec3 ","shColor5"),
//	shColor6("uniform vec3 ","shColor6"),
//	shColor7("uniform vec3 ","shColor7"),
//	
//	shininess("uniform float ","shininess"),
//	
//	shSampler0("uniform sampler2D ","shSampler0"),
//	shSampler1("uniform sampler2D ","shSampler1"),
//	shSampler2("uniform sampler2D ","shSampler2"),
//	shSampler3("uniform sampler2D ","shSampler3"),
//	shSampler4("uniform sampler2D ","shSampler4"),
//	shSampler5("uniform sampler2D ","shSampler5"),
//	shSampler6("uniform sampler2D ","shSampler6"),
//	shSampler7("uniform sampler2D ","shSampler7"),
//	
//	shPosition("varying vec3 ","shPosition"),
//	shNormal("varying vec3 ","shNormal"),
//	shDu("varying vec3 ","shDu"),
//	shDv("varying vec3 ","shDv"),
//	shEyeVect("varying vec3 ","shEyeVect"),
//	bumpMap("uniform sampler2D ","bumpMap"),
//	normalMap("uniform sampler2D ","normalMap"),
//	txoMap("uniform sampler2D ","txoMap"),
//	heightMap("uniform sampler2D ","heightMap"),
//	sss("uniform float ","sss"),
//	sss_att("uniform float ","sss_att"),
//	opacity("uniform float ","opacity"),
//	d_w("uniform float ","d_w"),
//	d_h("uniform float ","d_h"),
//	eps("uniform float ","eps");
//	
//	
//	private String type;
//	private String name;
//
//	private SFGlobalV(String type, String name) {
//		this.type = type;
//		this.name = name;
//	}
//
//	public String getType() {
//		return type;
//	}
//
//
//
//	public String getName() {
//		return name;
//	}
//
//
//	public String toString() {
//		return type+name+";\n";
//	}
//	
//	public int getSamplerIndex(){
//		switch(this){
//			case shSampler0: return 0;
//			case shSampler1: return 1;
//			case shSampler2: return 2;
//			case shSampler3: return 3;
//			case shSampler4: return 4;
//			case shSampler5: return 5;
//			case shSampler6: return 6;
//			case shSampler7: return 7;
//		}
//		return 0;
//	}
//	
//	public static SFGlobalV getSamplerByIndex(int code){
//		switch(code){
//			case 0: return shSampler0;
//			case 1: return shSampler1;
//			case 2: return shSampler2;
//			case 3: return shSampler3;
//			case 4: return shSampler4;
//			case 5: return shSampler5;
//			case 6: return shSampler6;
//			case 7: return shSampler7;
//		}
//		return shSampler0;
//	}
//}
