package com.list.shaddock.generator.code.freemarker;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class DealSpecialCharacter implements TemplateDirectiveModel {

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		if(!params.isEmpty()) {
			throw new TemplateModelException("This directive doesn't allow parameters.");
		}
		
		if(loopVars.length != 0) {
			throw new TemplateModelException("This directive doesn't allow loop variables.");
		}
		
		if(body != null) {
			body.render(new SpecialCharFilterWriter(env.getOut()));
		}else {
			throw new RuntimeException("missing body");
		}
	}

	
	private static class SpecialCharFilterWriter extends Writer {
		
		private final Writer out;
		
		public SpecialCharFilterWriter(Writer out) {
			super();
			this.out = out;
		}

		@Override
		public void write(char[] cbuf, int off, int len) throws IOException {
			String ret = null;
			String param = String.valueOf(cbuf);
			if(param != null && param.length() > 0) {
				String[] strs = param.split("_");
				ret = strs[0];
				for(int i = 0; i < strs.length; i++) {
					if(strs[i] != null && strs[i].length() > 0) {
						ret += strs[i].substring(0, 1).toUpperCase() + strs[i].substring(1);
					}
				}
			}
			out.write(String.valueOf(ret).trim());
		}

		@Override
		public void flush() throws IOException {
			out.flush();
		}

		@Override
		public void close() throws IOException {
			out.close();
		}
		
	}
}
