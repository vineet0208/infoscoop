package org.infoscoop.dao;

import java.util.Date;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.infoscoop.dao.model.Message;
import org.infoscoop.util.SpringUtil;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MessageDAO extends HibernateDaoSupport {
	private static Log log = LogFactory.getLog(MessageDAO.class);

	public static MessageDAO newInstance() {
		return (MessageDAO) SpringUtil.getContext().getBean("messageDAO");
	}

	public void insert(Message msg) {
		if (msg.getFrom() == null)
			throw new RuntimeException("uid must be set.");
		msg.setPostedTime(new Date());
		super.getHibernateTemplate().save(msg);
	}

	public List<Message> selectAll(String myuid, long offset, int limit) {
		return super.getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(Message.class)
					.add( Expression.or(
							Expression.in(Message.PROP_TYPE,new String[] {
									Message.MESSAGE_PUBLIC,
									Message.FYI_PUBLIC
							}),
							Expression.eq(Message.PROP_TO, myuid)
					)).add( Expression.lt( Message.PROP_ID,offset ))
					.addOrder( Order.desc(Message.PROP_POSTED_TIME)), 0, limit);
	}

	public List<Message> selectByUid(String uid, long offset, int limit) {
		return super.getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(Message.class).add(
						Expression.eq(Message.PROP_FROM, uid)).add(
						Expression.in(Message.PROP_TYPE, new String[] {
								Message.MESSAGE_PUBLIC, Message.MESSAGE_FROM,
								Message.FYI_PUBLIC, Message.FYI_PUBLIC,
								Message.MESSAGE_BROADCAST })).add(
						Expression.lt(Message.PROP_ID, offset)).addOrder(
						Order.desc(Message.PROP_POSTED_TIME)),0, limit);
	}

	public List<Message> selectByTo(String to, long offset, int limit) {
		return super.getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(Message.class).add(
						Expression.or(Expression.eq(Message.PROP_TO, to),
								Expression.eq(Message.PROP_TYPE,
										Message.MESSAGE_BROADCAST))).add(
						Expression.lt(Message.PROP_ID, offset)).addOrder(
						Order.desc(Message.PROP_POSTED_TIME)), 0, limit);
	}

	public List<Message> selectByUids(String myuid, String[] uidArr,
			long offset, int limit) {
		return super.getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(Message.class)
						.add( Expression.and(
								Expression.in( Message.PROP_FROM,uidArr),
								Expression.or(
										Expression.in( Message.PROP_TYPE, new String[] {
												Message.MESSAGE_PUBLIC,
												Message.FYI_PUBLIC }),
										Expression.eq(Message.PROP_TO,myuid)
								)
						)).add( Expression.lt( Message.PROP_ID,offset ))
						.addOrder(Order.desc(Message.PROP_POSTED_TIME)),0, limit);
	}

	public List<Message> selectByType(String type, long offset, int limit) {
		return super.getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(Message.class).add(
						Expression.eq(Message.PROP_TYPE, type)).add(
						Expression.lt(Message.PROP_ID, offset)).addOrder(
						Order.desc(Message.PROP_POSTED_TIME)), 0, limit);
	}
}