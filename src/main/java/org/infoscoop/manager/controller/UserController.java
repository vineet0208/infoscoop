package org.infoscoop.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.infoscoop.account.DomainManager;
import org.infoscoop.dao.DomainDAO;
import org.infoscoop.dao.GroupDAO;
import org.infoscoop.dao.PropertiesDAO;
import org.infoscoop.dao.UserDAO;
import org.infoscoop.dao.model.Domain;
import org.infoscoop.dao.model.Group;
import org.infoscoop.dao.model.Properties;
import org.infoscoop.dao.model.User;
import org.infoscoop.request.ProxyRequest;
import org.infoscoop.web.ProxyServlet;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	private static Log log = LogFactory.getLog(UserController.class);

	@RequestMapping(method = RequestMethod.GET)
	public void index(Model model) throws Exception {
	}

	@RequestMapping(method = RequestMethod.GET)
	public void sync(Model model,
			@RequestParam(value = "acl", required = false) boolean acl,
			HttpServletRequest request) throws Exception {
		log.info("UserController.sync is called.");
		Integer domainId = DomainManager.getContextDomainId();
		Domain domain = DomainDAO.newInstance().get(domainId);
		String domainName = domain.getName();

		long start = System.currentTimeMillis();

		Properties property = PropertiesDAO.newInstance().findProperty("appsServiceURL");
		String url = property.getValue();
		if (!url.endsWith("/"))
			url += "/";
		
		String response = singedRequest(url + "usergroup", request);
		JSONObject json = new JSONObject(response);

		log.info("start to insert users.");
		UserDAO userDAO = UserDAO.newInstance();
		JSONArray usersJ = json.getJSONArray("users");
		int user_count = 0;
		for (int i = 0; i < usersJ.length(); i++) {
			JSONObject userJ = usersJ.getJSONObject(i);
			String account = userJ.getString("login");
			String suspended = userJ.getString("suspended");
			String admin = userJ.getString("admin");
			String fname = userJ.getString("familyName");
			String gname = userJ.getString("givenName");
			String name = fname + " " + gname;
			String email = account + "@" + domainName;
			User user = userDAO.getByEmail(email, domainId);
			if (suspended == "false"){
				if (user == null)
					user = new User();
				user.setEmail(email);
				user.setName(name);
				if (admin == "true")
					user.setAdmin(1);
				else
					user.setAdmin(0);
				userDAO.save(user);
				log.info(user.getEmail() + " is saved.");
				user_count ++;
			} else {
				if (user != null)
					userDAO.delete(user);
			}
		}
		model.addAttribute("userCount", user_count);

		log.info("start to insert groups.");
		GroupDAO groupDAO = GroupDAO.newInstance();
		JSONArray groupsJ = json.getJSONArray("groups");
		for (int i = 0; i < groupsJ.length(); i++) {
			JSONObject groupJ = groupsJ.getJSONObject(i);
			String email = groupJ.getString("groupId");
			String name = groupJ.getString("groupName");
			String description = groupJ.getString("description");
			Group group = groupDAO.getByEmail(email);
			if (group == null)
				group = new Group();
			group.setEmail(email);
			group.setName(name);
			group.setDescription(description);

			JSONArray membersJ = groupJ.getJSONArray("members");
			for (int j = 0; j < membersJ.length(); j++) {
				JSONObject memberJ = membersJ.getJSONObject(j);
				String memberId = memberJ.getString("memberId");
				if (memberId.equals("*")) {
					List<User> allusers = userDAO.all();
					for (User u : allusers) {
						group.addToUsers(u);
					}
					break;
				}
				User user = userDAO.getByEmail(memberId, domainId);
				if (user != null)
					group.addToUsers(user);
				//else
				//	System.out.println("User " + memberId.split("@")[0] + " is missing.");
			}
			groupDAO.save(group);
			log.info(group.getEmail() + " is saved.");
		}
		model.addAttribute("groupCount", groupsJ.length());

		long time = System.currentTimeMillis() - start;
		log.info(usersJ.length() + " users and " + groupsJ.length()
				+ " groups have been synchronized. It took " + time
				+ " millisecond.");
		
		// create and share special docs.
		singedRequest(url + "usergroup?mode=docs" + (acl ? "&acl=true" : ""),
				request);
		
		log.info("The special docs have been created"
				+ (acl ? " and shared." : "."));
	}
	
	private String singedRequest(String url, HttpServletRequest request)
			throws Exception {
		ProxyRequest proxyRequest = null;
		try {
			proxyRequest = new ProxyRequest(url, "NoOperation");
			proxyRequest.setLocales(request.getLocales());
			proxyRequest.setPortalUid((String) request.getSession()
					.getAttribute("Uid"));
			proxyRequest.setTimeout(ProxyServlet.DEFAULT_TIMEOUT);
			proxyRequest.putRequestHeader("authType", "signed");

			int statusCode = proxyRequest.executeGet();

			if (statusCode != 200)
				throw new Exception("url=" + proxyRequest.getProxy().getUrl()
						+ ", statucCode=" + statusCode);

			if (log.isInfoEnabled())
				log.info("gadget url : " + proxyRequest.getProxy().getUrl());

			return proxyRequest.getResponseBodyAsStringWithAutoDetect();
		} finally {
			if (proxyRequest != null)
				proxyRequest.close();
		}
	}
}