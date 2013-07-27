package com.brtracker.services.async;

import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.LocaleUtils;

import com.brtracker.shared.payload.controller.data.Account;
import com.brtracker.shared.payload.controller.data.User;
import com.brtracker.shared.utils.ConversionUtils;
import com.brtracker.shared.utils.UnitType;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;

public class RegionalSettingsHelper {

	
	public static RegionalSettings get (Account account, User user) throws JSONMapperException{
		
		String accSettings = account.getAttributes();
		Map<String,String> accMap = (Map<String, String>) JSONMapper.toObject(accSettings, Map.class);
		String strUnitType = accMap.get("MEASUREMENT_TYPE");
		String strTimezone = accMap.get("TIMEZONE");
		String strLocale = account.getLocale();
		
		String usrSettings = account.getAttributes().trim();
		if (usrSettings != null || !usrSettings.equals("")) {
		Map<String,String> usrMap = (Map<String, String>) JSONMapper.toObject(usrSettings, Map.class);
		String usrUnitType = accMap.get("MEASUREMENT_TYPE");
		if (usrUnitType != null)strUnitType = usrUnitType;
		String usrTimezone = accMap.get("TIMEZONE");
		if (usrTimezone != null)strTimezone = usrTimezone;
		String usrLocale = accMap.get("ACCOUNT_LOCALE");
		if (usrLocale != null) strLocale = usrLocale;
		}
		UnitType unitType = ConversionUtils.getUnitType(strUnitType);
		TimeZone timezone = TimeZone.getTimeZone(strTimezone);
		Locale locale = LocaleUtils.toLocale(strLocale);
		RegionalSettings settings = new RegionalSettings();
		settings.setLocale(locale);
		settings.setTimezone(timezone);
		settings.setUnitType(unitType);
		return settings;
	
	}
}
