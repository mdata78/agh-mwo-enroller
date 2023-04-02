package com.company.enroller.persistence;

import java.util.Collection;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Participant;

@Component("participantService")
public class ParticipantService {

	DatabaseConnector connector;

	public ParticipantService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Participant> getAll() {
		String hql = "FROM Participant";
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}

    public Participant findByLogin(String login) {
		String hql = "FROM Participant WHERE login =: login";
		Query query = connector.getSession().createQuery(hql);
		query.setParameter("login", login);
		return (Participant) connector.getSession().get(Participant.class, login);

		//List<Participant> results = query.getResultList();
		//if(!result.isEmpty()){
		//return results.get(0);
		//} else {
		//return null;
		//}
    }

	public Participant add(Participant participant) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().save(participant);
		transaction.commit();
		return participant;
	}

	public void delete(Participant participant) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().delete(participant);
		transaction.commit();
		}

	public void update(Participant foundParticipant) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().update(foundParticipant);
		transaction.commit();
	}
}
