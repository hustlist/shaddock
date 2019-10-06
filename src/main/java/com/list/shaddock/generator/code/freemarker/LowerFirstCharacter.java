package com.list.shaddock.generator.code.freemarker;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class LowerFirstCharacter implements TemplateDirectiveModel {

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
			body.render(new LowerCaseFilterWriter(env.getOut()));
		}else {
			throw new RuntimeException("missing body");
		}
	}

	
	private static class LowerCaseFilterWriter extends Writer {
		
		private final Writer out;
		
		public LowerCaseFilterWriter(Writer out) {
			this.out = out;
		}

		@Override
		public void write(char[] cbuf, int off, int len) throws IOException {
			cbuf[0] = Character.toLowerCase(cbuf[0]);
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
