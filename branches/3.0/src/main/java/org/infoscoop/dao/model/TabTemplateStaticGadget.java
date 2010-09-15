package org.infoscoop.dao.model;

import org.infoscoop.dao.TabTemplateStaticGadgetDAO;
import org.infoscoop.dao.model.base.BaseTabTemplateStaticGadget;



public class TabTemplateStaticGadget extends BaseTabTemplateStaticGadget {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public TabTemplateStaticGadget () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public TabTemplateStaticGadget (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public TabTemplateStaticGadget (
		java.lang.Integer id,
		org.infoscoop.dao.model.GadgetInstance fkGadgetInstance,
		java.lang.String containerId) {

		super (
			id,
			fkGadgetInstance,
			containerId);
	}

/*[CONSTRUCTOR MARKER END]*/

	private String tabTemplateId;
	
	public String getTabTemplateId(){
		return this.tabTemplateId;
	}

	public void setTabTemplateId(String tabId){
		this.tabTemplateId = tabId;
	}
	
	public TabTemplateStaticGadget createTemp(
			TabTemplate tabCopy)throws CloneNotSupportedException{
		TabTemplateStaticGadget SGClone = new TabTemplateStaticGadget();
		
		SGClone.setContainerId(this.getContainerId());
		SGClone.setFkGadgetInstance(this.getFkGadgetInstance());
		SGClone.setFkTabTemplate(tabCopy);
		
		//TabTemplateStaticGadgetDAO.newInstance().save(SGClone);
		
		return SGClone;
	}
}