package org.infoscoop.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.infoscoop.command.XMLCommandProcessor;
import org.infoscoop.command.util.XMLCommandUtil;
import org.infoscoop.dao.GadgetDAO;
import org.infoscoop.dao.GadgetInstanceDAO;
import org.infoscoop.dao.TabTemplateDAO;
import org.infoscoop.dao.WidgetConfDAO;
import org.infoscoop.dao.model.GadgetInstance;
import org.infoscoop.dao.model.MenuItem;
import org.infoscoop.dao.model.TabTemplate;
import org.infoscoop.dao.model.TabTemplateParsonalizeGadget;
import org.infoscoop.service.GadgetService;
import org.infoscoop.service.TabLayoutService;
import org.infoscoop.util.SpringUtil;
import org.infoscoop.util.XmlUtil;
import org.json.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@Controller
public class TabController {
	private static Log log = LogFactory.getLog(TabController.class);
	
	@RequestMapping
	public void index(Model model)throws Exception {
		List<TabTemplate> tabs = TabTemplateDAO.newInstance().all();
		System.out.print(tabs);
		model.addAttribute("tabs", tabs);
	}

	@RequestMapping
	public void showAddTab(Model model)
			throws Exception {
		TabTemplate tab = new TabTemplate();
		tab.setName("新しい名前");
		tab.setLayout("<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">	<tr>		<td width=\"75%\">			<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">				<tr>					<td style=\"width:33%\">						<div class=\"static_column\" style=\"width: 99%; height:82px; min-height: 1px;\"></div>					</td>					<td>						<div style=\"width:10px\">&nbsp;</div>					</td>					<td style=\"width:33%\">						<div class=\"static_column\" style=\"width: 99%; height:82px; min-height: 1px;\"></div>					</td>					<td>						<div style=\"width:10px\">&nbsp;</div>					</td>					<td style=\"width:34%\">						<div class=\"static_column\" style=\"width: 99%; height:82px; min-height: 1px;\"></div>					</td>				</tr>			</table>		</td>	</tr></table>");
		TabTemplateDAO.newInstance().save(tab);
		model.addAttribute(tab);
	}

	@RequestMapping
	public String deleteTab(@RequestParam("id") String tabId,
			Model model) throws Exception {
		TabTemplate tab = TabTemplateDAO.newInstance().get(tabId);
		TabTemplateDAO.newInstance().delete(tab);
		return "redirect:index";
	}
	
	@RequestMapping
	public void selectGadgetType(HttpServletRequest request, @RequestParam("id") String containerId,
			Model model) throws Exception {
		model.addAttribute("containerId", containerId);
		model.addAttribute("gadgetConfs", GadgetService.getHandle().getGadgetConfs(request.getLocale()));
	}
	
	@RequestMapping
	public void showGadgetDialog(HttpServletRequest request, @RequestParam("type") String type, Model model)throws Exception {
		MenuItem menuItem = new MenuItem();
		//		menuItem.setType(type);
		model.addAttribute("menuItem", menuItem);
		
		//TODO 
		Element conf = null;
		if (type.startsWith("upload__")) {
			conf = GadgetDAO.newInstance().getGadgetElement(type.substring(8));
		} else {
			conf = WidgetConfDAO.newInstance().getElement(type);
		}
		model.addAttribute("conf", conf.getOwnerDocument());
	
	}

	@RequestMapping
	public void listGadgetInstances(){
	}

	@RequestMapping(method = RequestMethod.POST)
	public void submitGadgetSettings(GadgetInstance gadget)throws Exception {
		TabLayoutService.getHandle().insertStaticGadget("temp", gadget);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void addTab(TabTemplate tab, Model model)throws Exception {
		
		TabTemplateDAO.newInstance().save(tab);
		model.addAttribute(tab);
	}

	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	public void comsrv(HttpServletRequest request) throws Exception{
		String uid = (String) request.getSession().getAttribute("Uid");
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(request.getInputStream());
		Element root = doc.getDocumentElement();
		this.executeCommand(uid, root);
	}
	
	@RequestMapping
	public void widsrv(@RequestParam("tabId") String tabId, Model model){
		TabTemplate tab = TabTemplateDAO.newInstance().get(tabId);
		model.addAttribute(tab);
	}
	
	/**
	 * Copy from CommandExecutionService
	 * @param uid
	 * @param commandXML
	 * @throws Exception 
	 */
	private void executeCommand(String uid, Element commandXML) throws Exception{
		NodeList commandList = commandXML.getElementsByTagName("command");
		
		XMLCommandProcessor[] commands = new XMLCommandProcessor[commandList.getLength()];
		
		for (int i = 0; i < commandList.getLength(); i++) {
			Element commandEl = (Element)commandList.item(i);

			if(log.isDebugEnabled())
				log.debug("Command Elememt: " + XmlUtil.xmlSerialize(commandEl));
			
			
			String type = commandEl.getAttribute("type");
			
			XMLCommandProcessor command;
			try{
				command = (XMLCommandProcessor)SpringUtil.getBean("manager" + type);
			}catch(BeansException e){
				log.error("Unexpected error occurred.", e);
				continue;
			}
			command.initialize(uid, commandEl);
			commands[i] = command;
		    //XMLCommandProcessor command = getCommand(context, type, resultList);
			if (command != null) {
				if(log.isInfoEnabled())
					log.info("uid:[" + uid + "]: doPost: "
							+ command.getClass().getName());
				command.execute();
			}else{
				log.error("Command " + type + " is not exist.");
			}
		}
	}
	
	public static class AddWidget extends XMLCommandProcessor{

		AddWidget() {
			super();
		}

		@Override
		public void execute() throws Exception {

	        String commandId = super.commandXml.getAttribute("id").trim();
	        String widgetId = super.commandXml.getAttribute("widgetId").trim();
	        String tabId = super.commandXml.getAttribute("tabId").trim();
	        String targetColumn = super.commandXml.getAttribute("targetColumn").trim();
	        String parent = super.commandXml.getAttribute("parent").trim();
	        String sibling = super.commandXml.getAttribute("sibling").trim();
	        String menuid = super.commandXml.getAttribute("menuId").trim();

	        if(log.isInfoEnabled()){
	        	log.info("uid:[" + uid + "]: processXML: widgetId:[" + widgetId
	                	+ "], tabId:[" + tabId + "], targetColumn:[" + targetColumn + 
	                	"], parent:[" + parent + "], sibling:[" + sibling + "]");
	        }

	        if (widgetId == null || widgetId == "") {
	            String reason = "It's an unjust widgetId．widgetId:[" + widgetId + "]";
	            log.error("Failed to execute the command of AddWidget： " + reason);
	            this.result = XMLCommandUtil.createResultElement(uid, "processXML",
	                    log, commandId, false, reason);
	            return;
	        }

	        if (targetColumn != null && !"".equals(targetColumn) && !XMLCommandUtil.isNumberValue(targetColumn)) {
	        	String reason = "It's an unjust value of column．targetColumn:[" + targetColumn + "]";
	            log.error("Failed to execute the command of AddWidget： " + reason);
	            this.result = XMLCommandUtil.createResultElement(uid, "processXML",
	                    log, commandId, false, reason);
	            return;
	        }
	        
	        
	        // convert the JSON to XML.
	        String confJSONStr = super.commandXml.getAttribute("widgetConf");
	        JSONObject confJson = null;
	        try {
	    		confJson = new JSONObject(confJSONStr);
	    	} catch (Exception e) {
	    		log.error("", e);
	            String reason = "The information of widget is unjust.";
	            log.error("Failed to execute the command of AddWidget： " + reason);
	            this.result = XMLCommandUtil.createResultElement(uid, "processXML",
	                    log, commandId, false, reason);
	            throw e;
			}
	    	
	    	try{
	    		GadgetInstance ginst = new GadgetInstance();
	    		TabTemplateParsonalizeGadget gadget = new TabTemplateParsonalizeGadget();
	    		
	    		TabTemplateDAO tabDAO = TabTemplateDAO.newInstance();
	    		TabTemplate tab = tabDAO.get(tabId);
	    		
	    		TabTemplateParsonalizeGadget nextSibling = null;
	        	if( parent != null && !"".equals( parent )) {
	        		//newNextSibling = tabDAO.getSubWidgetBySibling( uid,tabId,sibling,parent,widgetId );
	        		nextSibling = null;
	        	} else if(sibling != null && !"".equals(sibling)){
	        		log.info("Find sibling: "+sibling+" of "+targetColumn );
	        		nextSibling = tabDAO.getColumnWidgetBySibling( tabId,sibling,Integer.valueOf( targetColumn ) );
	        	}
	        	
	        	if(nextSibling != null){
	        		gadget.setSibling( nextSibling );
	        		log.info("Replace siblingId of [" + gadget.getSibling() + "] to " + widgetId );
	 //       		WidgetDAO.newInstance().updateWidget(uid, tabId, newNextSibling);
	        	}
	        	
	    		
	    		if(targetColumn != null && !"".equals(targetColumn)){
	    			gadget.setColumnNum(new Integer(targetColumn));
	    		}
	    		gadget.setSibling(nextSibling);;
	    		if(confJson.has("title"))
	    			ginst.setTitle(confJson.getString("title"));
	    		if(confJson.has("href"))
	    			ginst.setHref(confJson.getString("href"));
	    		if(confJson.has("type"))
	    			ginst.setType(confJson.getString("type"));
	    		/*
	    		if(confJson.has("property"))
	    			widget.setUserPrefsJSON(confJson.getJSONObject("property"));
	    		if (confJson.has("ignoreHeader"))
	    			widget.setIgnoreHeader(confJson.getBoolean("ignoreHeader"));
	    		if (confJson.has("noBorder"))
	    			widget.setIgnoreHeader(confJson.getBoolean("noBorder"));
				*/
	    		gadget.setFkGadgetInstance(ginst);
	    		//GadgetInstanceDAO.newInstance().save(ginst);
	    		tab.getTabTemplateParsonalizeGadgets().add(gadget);
	    		gadget.setFkTabTemplate(tab);
	    		TabTemplateDAO.newInstance().save(tab);
	    	} catch (Exception e) {
	    		log.error("", e);
	            String reason = "Failed to save the widget.";
	            log.error("Failed to execute the command of AddWidget： " + reason);
	            this.result = XMLCommandUtil.createResultElement(uid, "processXML",
	                    log, commandId, false, reason);
	            throw e;
			}
	    	 


	        this.result = XMLCommandUtil.createResultElement(uid, "processXML",
	                log, commandId, true, null);
		}
		
	}
}
