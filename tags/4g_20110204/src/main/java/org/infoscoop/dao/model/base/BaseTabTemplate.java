package org.infoscoop.dao.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the IS_TAB_TEMPLATES table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="IS_TAB_TEMPLATES"
 */

public abstract class BaseTabTemplate  implements Serializable {

	public static String REF = "TabTemplate";
	public static String PROP_PUBLISH = "publish";
	public static String PROP_UPDATED_AT = "UpdatedAt";
	public static String PROP_TAB_ID = "TabId";
	public static String PROP_EDITOR = "Editor";
	public static String PROP_NUMBER_OF_COLUMNS = "NumberOfColumns";
	public static String PROP_NAME = "Name";
	public static String PROP_STATUS = "Status";
	public static String PROP_LAYOUT = "Layout";
	public static String PROP_ORDER_INDEX = "OrderIndex";
	public static String PROP_AREA_TYPE = "AreaType";
	public static String PROP_ID = "Id";
	public static String PROP_COLUMN_WIDTH = "ColumnWidth";
	public static String PROP_FK_DOMAIN_ID = "FkDomainId";


	// constructors
	public BaseTabTemplate () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTabTemplate (java.lang.Integer id) {
		initialize();
		this.setId(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BaseTabTemplate (
		java.lang.Integer id,
		java.lang.String tabId,
		java.lang.Integer orderIndex,
		java.lang.String name,
		java.lang.Integer status) {
		initialize();

		this.setId(id);
		this.setTabId(tabId);
		this.setOrderIndex(orderIndex);
		this.setName(name);
		this.setStatus(status);
	}

	protected void initialize () {
		this.publish = Integer.valueOf(0);
	}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Integer fkDomainId;
	private java.lang.String tabId;
	private java.lang.Integer orderIndex;
	private java.lang.String name;
	private java.lang.Integer areaType;
	private java.lang.Integer publish;
	private java.lang.String layout;
	private java.lang.Integer numberOfColumns;
	private java.lang.String columnWidth;
	private java.lang.Integer status;
	private java.util.Date updatedAt;

	// many to one
	private org.infoscoop.dao.model.User editor;

	// collections
	private java.util.Set<org.infoscoop.dao.model.TabTemplatePersonalizeGadget> tabTemplatePersonalizeGadgets;
	private java.util.Set<org.infoscoop.dao.model.TabTemplateStaticGadget> tabTemplateStaticGadgets;
	private java.util.Set<org.infoscoop.dao.model.Role> roles;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: fk_domain_id
	 */
	public java.lang.Integer getFkDomainId () {
		return fkDomainId;
	}

	/**
	 * Set the value related to the column: fk_domain_id
	 * @param fkDomainId the fk_domain_id value
	 */
	public void setFkDomainId (java.lang.Integer fkDomainId) {
		this.fkDomainId = fkDomainId;
	}



	/**
	 * Return the value associated with the column: tab_id
	 */
	public java.lang.String getTabId () {
		return tabId;
	}

	/**
	 * Set the value related to the column: tab_id
	 * @param tabId the tab_id value
	 */
	public void setTabId (java.lang.String tabId) {
		this.tabId = tabId;
	}



	/**
	 * Return the value associated with the column: order_index
	 */
	public java.lang.Integer getOrderIndex () {
		return orderIndex;
	}

	/**
	 * Set the value related to the column: order_index
	 * @param orderIndex the order_index value
	 */
	public void setOrderIndex (java.lang.Integer orderIndex) {
		this.orderIndex = orderIndex;
	}



	/**
	 * Return the value associated with the column: name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: name
	 * @param name the name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}



	/**
	 * Return the value associated with the column: area_type
	 */
	public java.lang.Integer getAreaType () {
		return areaType;
	}

	/**
	 * Set the value related to the column: area_type
	 * @param areaType the area_type value
	 */
	public void setAreaType (java.lang.Integer areaType) {
		this.areaType = areaType;
	}



	/**
	 * Return the value associated with the column: publish
	 */
	public java.lang.Integer getPublish () {
		return publish;
	}

	/**
	 * Set the value related to the column: publish
	 * @param publish the publish value
	 */
	public void setPublish (java.lang.Integer publish) {
		this.publish = publish;
	}



	/**
	 * Return the value associated with the column: layout
	 */
	public java.lang.String getLayout () {
		return layout;
	}

	/**
	 * Set the value related to the column: layout
	 * @param layout the layout value
	 */
	public void setLayout (java.lang.String layout) {
		this.layout = layout;
	}



	/**
	 * Return the value associated with the column: number_of_columns
	 */
	public java.lang.Integer getNumberOfColumns () {
		return numberOfColumns;
	}

	/**
	 * Set the value related to the column: number_of_columns
	 * @param numberOfColumns the number_of_columns value
	 */
	public void setNumberOfColumns (java.lang.Integer numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}



	/**
	 * Return the value associated with the column: column_width
	 */
	public java.lang.String getColumnWidth () {
		return columnWidth;
	}

	/**
	 * Set the value related to the column: column_width
	 * @param columnWidth the column_width value
	 */
	public void setColumnWidth (java.lang.String columnWidth) {
		this.columnWidth = columnWidth;
	}



	/**
	 * Return the value associated with the column: status
	 */
	public java.lang.Integer getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: status
	 * @param status the status value
	 */
	public void setStatus (java.lang.Integer status) {
		this.status = status;
	}



	/**
	 * Return the value associated with the column: updated_at
	 */
	public java.util.Date getUpdatedAt () {
		return updatedAt;
	}

	/**
	 * Set the value related to the column: updated_at
	 * @param updatedAt the updated_at value
	 */
	public void setUpdatedAt (java.util.Date updatedAt) {
		this.updatedAt = updatedAt;
	}



	/**
	 * Return the value associated with the column: fk_editor_id
	 */
	public org.infoscoop.dao.model.User getEditor () {
		return editor;
	}

	/**
	 * Set the value related to the column: fk_editor_id
	 * @param editor the fk_editor_id value
	 */
	public void setEditor (org.infoscoop.dao.model.User editor) {
		this.editor = editor;
	}



	/**
	 * Return the value associated with the column: TabTemplatePersonalizeGadgets
	 */
	public java.util.Set<org.infoscoop.dao.model.TabTemplatePersonalizeGadget> getTabTemplatePersonalizeGadgets () {
		return tabTemplatePersonalizeGadgets;
	}

	/**
	 * Set the value related to the column: TabTemplatePersonalizeGadgets
	 * @param tabTemplatePersonalizeGadgets the TabTemplatePersonalizeGadgets value
	 */
	public void setTabTemplatePersonalizeGadgets (java.util.Set<org.infoscoop.dao.model.TabTemplatePersonalizeGadget> tabTemplatePersonalizeGadgets) {
		this.tabTemplatePersonalizeGadgets = tabTemplatePersonalizeGadgets;
	}

	public void addToTabTemplatePersonalizeGadgets (org.infoscoop.dao.model.TabTemplatePersonalizeGadget tabTemplatePersonalizeGadget) {
		if (null == getTabTemplatePersonalizeGadgets()) setTabTemplatePersonalizeGadgets(new java.util.TreeSet<org.infoscoop.dao.model.TabTemplatePersonalizeGadget>());
		getTabTemplatePersonalizeGadgets().add(tabTemplatePersonalizeGadget);
	}



	/**
	 * Return the value associated with the column: TabTemplateStaticGadgets
	 */
	public java.util.Set<org.infoscoop.dao.model.TabTemplateStaticGadget> getTabTemplateStaticGadgets () {
		return tabTemplateStaticGadgets;
	}

	/**
	 * Set the value related to the column: TabTemplateStaticGadgets
	 * @param tabTemplateStaticGadgets the TabTemplateStaticGadgets value
	 */
	public void setTabTemplateStaticGadgets (java.util.Set<org.infoscoop.dao.model.TabTemplateStaticGadget> tabTemplateStaticGadgets) {
		this.tabTemplateStaticGadgets = tabTemplateStaticGadgets;
	}

	public void addToTabTemplateStaticGadgets (org.infoscoop.dao.model.TabTemplateStaticGadget tabTemplateStaticGadget) {
		if (null == getTabTemplateStaticGadgets()) setTabTemplateStaticGadgets(new java.util.TreeSet<org.infoscoop.dao.model.TabTemplateStaticGadget>());
		getTabTemplateStaticGadgets().add(tabTemplateStaticGadget);
	}



	/**
	 * Return the value associated with the column: Roles
	 */
	public java.util.Set<org.infoscoop.dao.model.Role> getRoles () {
		return roles;
	}

	/**
	 * Set the value related to the column: Roles
	 * @param roles the Roles value
	 */
	public void setRoles (java.util.Set<org.infoscoop.dao.model.Role> roles) {
		this.roles = roles;
	}

	public void addToRoles (org.infoscoop.dao.model.Role role) {
		if (null == getRoles()) setRoles(new java.util.TreeSet<org.infoscoop.dao.model.Role>());
		getRoles().add(role);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.infoscoop.dao.model.TabTemplate)) return false;
		else {
			org.infoscoop.dao.model.TabTemplate tabTemplate = (org.infoscoop.dao.model.TabTemplate) obj;
			if (null == this.getId() || null == tabTemplate.getId()) return false;
			else return (this.getId().equals(tabTemplate.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}