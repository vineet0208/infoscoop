package org.infoscoop.dao.model;

// Generated 2010/03/29 15:54:59 by Hibernate Tools 3.3.0.GA

import java.io.UnsupportedEncodingException;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * IsUserprefs generated by hbm2java
 */
@Entity
@Table(name = "is_userprefs", uniqueConstraints = @UniqueConstraint(columnNames = {
		"fk_widget_id", "name" }))
public class UserPref implements java.io.Serializable {

	private UserprefId id;
	private Widget isWidgets;

	public UserPref() {
	}

	public UserPref(UserprefId id, Widget widget) {
		this.id = id;
		this.isWidgets = widget;
	}

	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "fkWidgetId", column = @Column(name = "fk_widget_id", nullable = false)),
			@AttributeOverride(name = "name", column = @Column(name = "name", nullable = false)),
			@AttributeOverride(name = "value", column = @Column(name = "value", length = 4000)),
			@AttributeOverride(name = "longValue", column = @Column(name = "long_value", length = 65535)) })
	public UserprefId getId() {
		return this.id;
	}

	public void setId(UserprefId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_widget_id", nullable = false, insertable = false, updatable = false)
	public Widget getWidget() {
		return this.isWidgets;
	}

	public void setWidget(Widget isWidgets) {
		this.isWidgets = isWidgets;
	}

	
	@Transient
	public String getValue() {
		String shortValue = getId().getValue();

		return (shortValue == null ? getId().getLongValue() : shortValue);
	}

	public void setValue(String value) {
		int length;
		try {
			length = value.getBytes("UTF-8").length;
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex);
		}

		if (length < 4000) {
			getId().setValue(value);
			getId().setLongValue(null);
		} else {
			getId().setValue(null);
			getId().setLongValue(value);
		}
	}

	public boolean hasLongValue() {
		return (getId().getValue() == null);
	}
}