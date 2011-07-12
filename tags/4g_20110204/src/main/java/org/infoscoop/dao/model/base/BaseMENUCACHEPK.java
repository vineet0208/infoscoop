package org.infoscoop.dao.model.base;

import java.io.Serializable;


public abstract class BaseMENUCACHEPK implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private java.lang.Integer fkDomainId;
	private java.lang.String urlKey;
	private java.lang.String uid;


	public BaseMENUCACHEPK () {}
	
	public BaseMENUCACHEPK (
		java.lang.Integer fkDomainId,
		java.lang.String urlKey,
		java.lang.String uid) {

		this.setFkDomainId(fkDomainId);
		this.setUrlKey(urlKey);
		this.setUid(uid);
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
	 * Return the value associated with the column: URL_KEY
	 */
	public java.lang.String getUrlKey () {
		return urlKey;
	}

	/**
	 * Set the value related to the column: URL_KEY
	 * @param urlKey the URL_KEY value
	 */
	public void setUrlKey (java.lang.String urlKey) {
		this.urlKey = urlKey;
	}



	/**
	 * Return the value associated with the column: `UID`
	 */
	public java.lang.String getUid () {
		return uid;
	}

	/**
	 * Set the value related to the column: `UID`
	 * @param uid the `UID` value
	 */
	public void setUid (java.lang.String uid) {
		this.uid = uid;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.infoscoop.dao.model.MENUCACHEPK)) return false;
		else {
			org.infoscoop.dao.model.MENUCACHEPK mObj = (org.infoscoop.dao.model.MENUCACHEPK) obj;
			if (null != this.getFkDomainId() && null != mObj.getFkDomainId()) {
				if (!this.getFkDomainId().equals(mObj.getFkDomainId())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getUrlKey() && null != mObj.getUrlKey()) {
				if (!this.getUrlKey().equals(mObj.getUrlKey())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getUid() && null != mObj.getUid()) {
				if (!this.getUid().equals(mObj.getUid())) {
					return false;
				}
			}
			else {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			if (null != this.getFkDomainId()) {
				sb.append(this.getFkDomainId().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getUrlKey()) {
				sb.append(this.getUrlKey().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getUid()) {
				sb.append(this.getUid().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}