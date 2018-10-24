package com.list.shaddock.common.locale;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import com.alibaba.druid.util.StringUtils;
import com.list.shaddock.common.consts.SysGlobalConst;
import com.list.shaddock.config.MsgLanguageConfig;

@Component("localeResolver")
public class LocalMessageSourceBean implements LocaleResolver {
	
	private final Logger logger = LogManager.getLogger(LocalMessageSourceBean.class);
	
	private final String REGX = "_";
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MsgLanguageConfig msgLan;
	
	/**
	 * 按指定的语言Key获取相应的国际化信息
	 * @param msgKey
	 * @return
	 */
	public String getMessage(String msgKey) {
		return messageSource.getMessage(msgKey, null,LocaleContextHolder.getLocale());
	}
	
	/**
	 * 按语言参数获取对应的语言配置local，默认为本地操作系统的区域语言
	 * @param lan
	 * @return
	 */
	private Locale getLocale(String lan) {
		Locale loc = Locale.getDefault();
		String lanStr = msgLan.getLanMap().get(lan);
		if(!StringUtils.isEmpty(lanStr)) {
			String[] lanCty = lanStr.split(REGX);
			if(lanCty.length == 2) {
				loc = new Locale(lanCty[0],lanCty[1]);
			}else {
				loc = new Locale(lanCty[0]);
			}
		}
		return loc;
	}

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String lan = request.getHeader(SysGlobalConst.HTTP_HEADER_X_TENANT_ID);
		return getLocale(lan);
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		
	}

}
