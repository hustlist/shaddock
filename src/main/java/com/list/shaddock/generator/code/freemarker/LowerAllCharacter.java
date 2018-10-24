package com.list.shaddock.generator.code.freemarker;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class LowerAllCharacter implements TemplateDirectiveModel {

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
			body.render(new LowerAllCaseFilterWriter(env.getOut()));
		}else {
			throw new RuntimeException("missing body");
		}
	}

	
	private static class LowerAllCaseFilterWriter extends Writer {
		
		private final Writer out;
		
		public LowerAllCaseFilterWriter(Writer out) {
			this.out = out;
		}

		@Override
		public void write(char[] cbuf, int off, int len) throws IOException {
			for(int i = 0; i <len; i++) {
				cbuf[i] = Character.toLowerCase(cbuf[i]);
			}
			out.write(String.valueOf(cbuf).trim());
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
