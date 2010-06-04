package org.infoscoop.admin.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.infoscoop.service.CacheService;

public class RemoveCacheByUrlfCommand implements ICommand {

	private static Log log = LogFactory.getLog(RemoveCacheByUrlfCommand.class);

	public CommandResponse execute(HttpServletRequest request, HttpServletResponse response) {
		
		String url = request.getParameter("url");
		if(url == null){
			return new CommandResponse(false, "Must specify url.");
		}
		CacheService.getHandle().deleteCacheByUrl(url);
		return new CommandResponse(true, null);
	}
}