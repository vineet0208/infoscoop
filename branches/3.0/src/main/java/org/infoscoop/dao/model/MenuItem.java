package org.infoscoop.dao.model;

import java.util.ArrayList;
import java.util.List;

import org.infoscoop.account.DomainManager;
import org.infoscoop.dao.model.base.BaseMenuItem;

public class MenuItem extends BaseMenuItem {
	private static final long serialVersionUID = 1L;

	/*[CONSTRUCTOR MARKER BEGIN]*/
	public MenuItem () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public MenuItem (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public MenuItem (
		java.lang.Integer id,
		org.infoscoop.dao.model.MenuTree fkMenuTree,
		java.lang.String menuId,
		java.lang.String title,
		java.lang.Integer menuOrder,
		java.lang.Integer accessLevel,
		java.lang.Integer alert) {

		super (
			id,
			fkMenuTree,
			menuId,
			title,
			menuOrder,
			accessLevel,
			alert);
	}

/*[CONSTRUCTOR MARKER END]*/

	protected void initialize (){
		super.setFkDomainId(DomainManager.getContextDomainId());
	}
	
	private List<MenuItem> childItems = new ArrayList<MenuItem>();

	public void setChildItems(List<MenuItem> childItems) {
		this.childItems = childItems;
	}

	public void addChildItem(MenuItem item) {
		this.childItems.add(item);
	}

	public List<MenuItem> getChildItems() {
		return this.childItems;
	}
	
	public boolean isPublishBool() {
		return super.getAccessLevel() == 1;
	}

	public void setPublishBool(boolean publish) {
		super.setAccessLevel(publish ? 1 : 0);
	}

	public void toggolePublish() {
		setPublishBool(!isPublishBool());
	}

	public boolean isSpecialAccess() {
		return super.getAccessLevel() == 2;
	}

	public boolean isPrivateBool() {
		return (super.getAccessLevel() == 0);
	}
}