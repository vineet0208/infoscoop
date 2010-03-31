ALTER TABLE IS_ACCESSLOGS RENAME TO IS_ACCESSLOGS_2_0;
ALTER TABLE IS_ACCOUNTS RENAME TO IS_ACCOUNTS_2_0;
ALTER TABLE IS_ADMINROLES RENAME TO IS_ADMINROLES_2_0;
ALTER TABLE IS_AUTHCREDENTIALS DROP INDEX is_authCredentials_uid;
ALTER TABLE IS_AUTHCREDENTIALS RENAME TO IS_AUTHCREDENTIALS_2_0;
ALTER TABLE IS_CACHES DROP INDEX is_caches_uid;
ALTER TABLE IS_CACHES DROP INDEX is_caches_url;
ALTER TABLE IS_CACHES RENAME TO IS_CACHES_2_0;
ALTER TABLE IS_FORBIDDENURLS RENAME TO IS_FORBIDDENURLS_2_0;
ALTER TABLE IS_GADGETS DROP INDEX is_gadgets_type;
ALTER TABLE IS_GADGETS DROP INDEX is_gadgets_path;
ALTER TABLE IS_GADGETS DROP INDEX is_gadgets_name;
ALTER TABLE IS_GADGETS RENAME TO IS_GADGETS_2_0;
ALTER TABLE IS_GADGET_ICONS RENAME TO IS_GADGET_ICONS_2_0;
ALTER TABLE IS_HOLIDAYS RENAME TO IS_HOLIDAYS_2_0;
ALTER TABLE IS_I18N RENAME TO IS_I18N_2_0;
ALTER TABLE IS_I18NLASTMODIFIED RENAME TO IS_I18NLASTMODIFIED_2_0;
ALTER TABLE IS_I18NLOCALES RENAME TO IS_I18NLOCALES_2_0;
ALTER TABLE IS_KEYWORDS DROP INDEX is_keywords_uid;
ALTER TABLE IS_KEYWORDS DROP INDEX is_keywords_type;
ALTER TABLE IS_KEYWORDS DROP INDEX is_keywords_keyword;
ALTER TABLE IS_KEYWORDS DROP INDEX is_keywords_date;
ALTER TABLE IS_KEYWORDS RENAME TO IS_KEYWORDS_2_0;
ALTER TABLE IS_LOGS DROP INDEX is_logs_uid;
ALTER TABLE IS_LOGS DROP INDEX is_logs_type;
ALTER TABLE IS_LOGS DROP INDEX is_logs_url;
ALTER TABLE IS_LOGS DROP INDEX is_logs_rssurl;
ALTER TABLE IS_LOGS DROP INDEX is_logs_date;
ALTER TABLE IS_LOGS RENAME TO IS_LOGS_2_0;
ALTER TABLE IS_MENUCACHES RENAME TO IS_MENUCACHES_2_0;
ALTER TABLE IS_MENUS RENAME TO IS_MENUS_2_0;
ALTER TABLE IS_MENUS_TEMP DROP INDEX is_menus_temp_lastmodified;
ALTER TABLE IS_MENUS_TEMP RENAME TO IS_MENUS_TEMP_2_0;
ALTER TABLE IS_MESSAGES DROP INDEX is_messages_from;
ALTER TABLE IS_MESSAGES DROP INDEX is_messages_to;
ALTER TABLE IS_MESSAGES DROP INDEX is_messages_posted_time;
ALTER TABLE IS_MESSAGES DROP INDEX is_messages_type;
ALTER TABLE IS_MESSAGES RENAME TO IS_MESSAGES_2_0;
ALTER TABLE IS_PORTALADMINS RENAME TO IS_PORTALADMINS_2_0;
ALTER TABLE IS_PORTALLAYOUTS RENAME TO IS_PORTALLAYOUTS_2_0;
ALTER TABLE IS_PREFERENCES RENAME TO IS_PREFERENCES_2_0;
ALTER TABLE IS_PROPERTIES DROP INDEX is_properties_advanced;
ALTER TABLE IS_PROPERTIES RENAME TO IS_PROPERTIES_2_0;
ALTER TABLE IS_PROXYCONFS RENAME TO IS_PROXYCONFS_2_0;
ALTER TABLE IS_RSSCACHES RENAME TO IS_RSSCACHES_2_0;
ALTER TABLE IS_SEARCHENGINES RENAME TO IS_SEARCHENGINES_2_0;
ALTER TABLE IS_SESSIONS DROP INDEX is_sessions_sessionId;
ALTER TABLE IS_SESSIONS DROP INDEX is_sessions_loginDateTime;
ALTER TABLE IS_SESSIONS RENAME TO IS_SESSIONS_2_0;
ALTER TABLE IS_SYSTEMMESSAGES DROP INDEX is_systemmessages_to;
ALTER TABLE IS_SYSTEMMESSAGES DROP INDEX is_systemmessages;
ALTER TABLE IS_SYSTEMMESSAGES RENAME TO IS_SYSTEMMESSAGES_2_0;
ALTER TABLE IS_TABLAYOUTS RENAME TO IS_TABLAYOUTS_2_0;
ALTER TABLE IS_TABS RENAME TO IS_TABS_2_0;
ALTER TABLE IS_USERPREFS DROP INDEX is_userprefs_fk_widget_id;
ALTER TABLE IS_USERPREFS DROP INDEX is_userprefs_name;
ALTER TABLE IS_USERPREFS DROP INDEX is_userprefs_value;
ALTER TABLE IS_USERPREFS RENAME TO IS_USERPREFS_2_0;
ALTER TABLE IS_WIDGETCONFS RENAME TO IS_WIDGETCONFS_2_0;
ALTER TABLE IS_WIDGETS DROP INDEX is_widgets_tabId;
ALTER TABLE IS_WIDGETS DROP INDEX is_widgets_parentId;
ALTER TABLE IS_WIDGETS DROP INDEX is_widgets_deleteDate;
ALTER TABLE IS_WIDGETS DROP INDEX is_widgets_type;
ALTER TABLE IS_WIDGETS RENAME TO IS_WIDGETS_2_0;