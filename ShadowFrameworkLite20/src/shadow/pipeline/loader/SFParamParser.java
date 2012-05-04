package shadow.pipeline.loader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import shadow.pipeline.builder.SFIPipelineBuilder;
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
	public void parseLine(SFIPipelineBuilder builder, StringTokenizer lineToken,
			int codeLine) throws SFException {
	
		if (builder.getComponent() != null) {
			if (lineToken.hasMoreElements()) {
				String params = lineToken.nextToken();
				if (lineToken.hasMoreElements()) {
					String as = lineToken.nextToken();
					if (as.equalsIgnoreCase("as")) {
						if (lineToken.hasMoreElements()) {
							String paramType = lineToken.nextToken();
							
							StringTokenizer tok2 = new StringTokenizer(
									paramType, ":");
							String moduleString=tok2.nextToken();
							
							List<String> pars = extractNames(params);
							
							builder.buildParamRule(moduleString, pars);

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

	}

	private List<String> extractNames(String params) {
		StringTokenizer tokenizer = new StringTokenizer(params, ",");
		ArrayList<String> strings = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			strings.add(tokenizer.nextToken());
		}
		return strings;
	}
}
