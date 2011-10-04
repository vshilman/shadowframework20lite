package shadow.pipeline.loader.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

import shadow.pipeline.SFInstancedParameter;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineElement;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.loader.SFLineParser;
import shadow.pipeline.loader.SFParsableElement;
import shadow.pipeline.parameters.SFParameteri;
import shadow.system.SFException;

public class SFParamParser implements SFLineParser {

	private static HashMap<String, Short> types = new HashMap<String, Short>();

	static {
		types.put("float", SFParameteri.GLOBAL_FLOAT);
		types.put("float2", SFParameteri.GLOBAL_FLOAT2);
		types.put("float3", SFParameteri.GLOBAL_FLOAT3);
		types.put("float4", SFParameteri.GLOBAL_FLOAT4);
		types.put("<>", SFParameteri.GLOBAL_GENERIC);
	}

	@Override
	public SFParsableElement parseLine(SFParsableElement component,
			StringTokenizer lineToken, int codeLine) throws SFException {

		if (component != null) {
			if (lineToken.hasMoreElements()) {
				String params = lineToken.nextToken();
				if (lineToken.hasMoreElements()) {
					String as = lineToken.nextToken();
					if (as.equalsIgnoreCase("as")) {
						if (lineToken.hasMoreElements()) {
							String paramType = lineToken.nextToken();
							// TODO : param definition goes here
							Collection<String> pars = extractNames(params);
							Vector<SFParameteri> params_ = new Vector<SFParameteri>();//[pars.length];

							StringTokenizer tok2 = new StringTokenizer(
									paramType, ":");
							SFPipelineElement module = SFPipeline
									.getModule(tok2.nextToken());
							SFProgramComponent cmp = (SFProgramComponent) component;

//							short type = SFParameteri.GLOBAL_FLOAT;
//							if (tok2.hasMoreTokens()) {
//								type = types.get(tok2.nextToken());
//							}

							SFPipelineStructure sfPs = (SFPipelineStructure) module;
							Collection<SFParameteri> parameters = sfPs.getAllParameters();

							Iterator<String> parsIterator=pars.iterator();
							for (Iterator<SFParameteri> iterator = parameters.iterator(); iterator.hasNext();) {
								SFParameteri sfParameteri = (SFParameteri) iterator.next();

								params_.add(new SFInstancedParameter(sfParameteri,parsIterator.next()));
								/*if (sfParameteri.getType() == SFParameteri.GLOBAL_UNIDENTIFIED) {
									params_.add(new SFParameter(pars[index], type));
								} else {
								}*/
							}
							
							

//							try {
								SFPipelineStructureInstance instance = new SFPipelineStructureInstance(
										(SFPipelineStructure) module, params_);
								//instance.setInstancedParameter(type);
								cmp.addStructureInstance(instance);
//							} catch (SFPipelineStructureException e) {
//								e.printStackTrace();
//							}

						} else {
							throw new SFException(
									"("
											+ codeLine
											+ "): param command miss parameter type definition");
						}
					} else {
						throw new SFException("(" + codeLine
								+ "): undefined keyword " + as
								+ " in param command");
					}
				} else {
					throw new SFException(
							"("
									+ codeLine
									+ "):  param command miss parameter type definition");
				}
			} else {
				throw new SFException("(" + codeLine
						+ "):  cannot use param command without parameters");
			}
		}

		return component;
	}

	private Collection<String> extractNames(String params) {
		StringTokenizer tokenizer = new StringTokenizer(params, ",");
		ArrayList<String> strings = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			strings.add(tokenizer.nextToken());
		}
		return strings;
	}
}
