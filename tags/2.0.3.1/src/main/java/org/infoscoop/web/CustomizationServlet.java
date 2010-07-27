package org.infoscoop.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.infoscoop.dao.model.Portallayout;
import org.infoscoop.service.PortalLayoutService;
import org.infoscoop.service.TabLayoutService;
import org.infoscoop.util.I18NUtil;
import org.infoscoop.util.SpringUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class CustomizationServlet extends HttpServlet {
	private static final long serialVersionUID = "org.infoscoop.web.CustomizationServlet"
			.hashCode();
	private Configuration cfg;

	private static Log log = LogFactory.getLog(CustomizationServlet.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

    public void init() {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(
                getServletContext(), "WEB-INF/templates");
    }

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		long start = System.currentTimeMillis();
		String uid = (String) request.getSession().getAttribute("Uid");

		if (log.isInfoEnabled()) {
			log.info("uid:[" + uid + "]: doPost");
		}

		response.setContentType("application/json; charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");

		Writer writer = response.getWriter();
		try {
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("request", request);
			root.put("session", request.getSession());

			String customFtl = getCustomizationFtl( root );

			customFtl = I18NUtil.resolve(I18NUtil.TYPE_LAYOUT,
					customFtl, request.getLocale());

			writer.write( customFtl );
		} catch (Exception e){
			log.error("--- unexpected error occurred.", e);
			response.sendError(500);
		}

		long end = System.currentTimeMillis();
		if (log.isDebugEnabled())
			log.debug("--- doPost: " + (end - start));

	}

	private String getCustomizationFtl( Map<String,Object> root ) throws ParserConfigurationException, Exception{
		JSONObject layoutJson = new JSONObject();
		Map<String, String> CustomizationMap = TabLayoutService.getHandle().getMyTabLayoutHTML();


		//int staticPanelCount = 0;
		for(Iterator<Map.Entry<String, String>> ite = CustomizationMap.entrySet().iterator();ite.hasNext();){
			Map.Entry entry = ite.next();
			String key = (String)entry.getKey();
			String value = (String)entry.getValue();
			if( value == null )
				value = "";

			if("commandbar".equals(key.toLowerCase())){
				layoutJson.put("commandbar", value);
			}else {
				layoutJson.put("staticPanel" + key, value);
			}
		}

		// get the information of static layout.
		PortalLayoutService service = (PortalLayoutService)SpringUtil.getBean("PortalLayoutService");
		List<Portallayout> layoutList = service.getPortalLayoutList();
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		for(Iterator<Portallayout> layoutIt = layoutList.iterator();layoutIt.hasNext();){
			Portallayout portalLayout = layoutIt.next();

			String name = portalLayout.getName();
			if(name.equals("javascript"))
				continue;

			String layout;
			boolean isIframeToolBar = name.toLowerCase().equals("contentfooter");
			if(isIframeToolBar){
				String tempLayout = "<temp>" + portalLayout.getLayout() + "</temp>";
				Document ifdoc = db.parse(new ByteArrayInputStream(tempLayout.getBytes("UTF-8")));
				Element ifroot = ifdoc.getDocumentElement();
				NodeList icons = ifroot.getElementsByTagName("icon");

				JSONArray iconsJson = new JSONArray();
				for(int i=0;i<icons.getLength();i++){
					Element icon = ( Element )icons.item(i);

					JSONObject iconJson = new JSONObject();
					if( icon.hasAttribute("type"))
						iconJson.put("type",icon.getAttribute("type"));

					NodeList nodeList = icon.getChildNodes();
					for(int j = 0; j < nodeList.getLength(); j++){
						if(nodeList.item(j).getNodeType() == Node.CDATA_SECTION_NODE){
							iconJson.put("html",nodeList.item(j).getNodeValue());
							break;
						}
					}

					iconsJson.put( iconJson );
				}
				layout = iconsJson.toString();
			}else {
				layout = portalLayout.getLayout();
				if( layout == null )
					layout = "";
			}

			try {
				Writer out = new StringWriter();
				Template t = new Template("portalLayout_template", new StringReader( layout ) ,cfg);

				t.setTemplateExceptionHandler(
					new TemplateExceptionHandler() {
						public void handleTemplateException(TemplateException templateexception, Environment environment, Writer writer){
							log.error("--- templete error occurred", templateexception);
						}
				});
				t.process( root, out );

				layoutJson.put(name, (isIframeToolBar)? new JSONArray(out.toString()) : out.toString() );
			} catch( freemarker.core.ParseException ex ) {
				layoutJson.put( name,(isIframeToolBar)? new JSONArray(layout) : layout );
			}
		}

		return "IS_Customization = " + layoutJson.toString() + ";";
	}

}