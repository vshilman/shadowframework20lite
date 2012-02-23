package shadow.pipeline.loader.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import shadow.pipeline.SFInstancedParameter;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineElement;
import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPipelineGridInstance;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.loader.SFLineParser;
import shadow.pipeline.loader.SFParsableElement;
import shadow.pipeline.parameters.SFParameteri;
import shadow.system.SFException;

public class SFGridParser implements SFLineParser {

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
							List<String> pars = extractNames(params);
							Vector<SFParameteri> params_ = new Vector<SFParameteri>();

							StringTokenizer tok2 = new StringTokenizer(
									paramType, ":");
							SFPipelineElement module = SFPipeline
									.getModule(tok2.nextToken());
							SFProgramComponent cmp = (SFProgramComponent) component;

//							short type = SFParameteri.GLOBAL_FLOAT;
//							if (tok2.hasMoreTokens()) {
//								type = types.get(tok2.nextToken());
//							}

							SFPipelineGrid sfPs = (SFPipelineGrid) module;
							List<SFParameteri> parameters = sfPs.getAllParameters();
							
							Iterator<String> parsIterator=pars.iterator();
							for (Iterator<SFParameteri> iterator = parameters.iterator(); iterator.hasNext();) {
								SFParameteri sfParameteri = (SFParameteri) iterator.next();
								params_.add(new SFInstancedParameter(sfParameteri,parsIterator.next()));
								//params_.add(new SFParameter(parsIterator.next(), SFParameteri.GLOBAL_UNIDENTIFIED));
							}

//							try {
								SFPipelineGridInstance instance = new SFPipelineGridInstance(
										(SFPipelineGrid) module, params_);
								//instance.setInstancedParameter(type);
								
								cmp.setGridInstance(instance);
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

	private List<String> extractNames(String params) {
		StringTokenizer tokenizer = new StringTokenizer(params, ",");
		LinkedList<String> strings = new LinkedList<String>();
		while (tokenizer.hasMoreTokens()) {
			strings.add(tokenizer.nextToken());
		}
		return strings;
	}
}
