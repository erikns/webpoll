package no.hib.megagruppe.webpoll.fakes;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import no.hib.megagruppe.webpoll.data.ResponseRepository;
import no.hib.megagruppe.webpoll.entities.ResponseEntity;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;

public class TestResponseRepository implements ResponseRepository {
	private Map<Integer, ResponseEntity> responses;
	private Integer counter;
	
	public TestResponseRepository() {
		responses = new HashMap<>();
		counter = 0;
		
	}

	@Override
	public ResponseEntity add(ResponseEntity entity) {
		responses.put(counter++, entity);
		return entity;
	}

	@Override
	public ResponseEntity findById(int id) {
        ResponseEntity result = null;
        for (ResponseEntity survey : responses.values()) {
            result = survey;
        }
        return result;
	}

	@Override
	public List<ResponseEntity> findAll() {
        List<ResponseEntity> result = new ArrayList<>();
        for (ResponseEntity e : responses.values()) {
            result.add(e);
        }
        return result;
	}

	@Override
	public ResponseEntity update(ResponseEntity entity) {
        ResponseEntity current = findById(entity.getId());
        if (current != null) {
            responses.put(entity.getId(), entity);
            return entity;
        } else {
            return null;
        }
	}

	@Override
	public void remove(ResponseEntity entity) {
		responses.remove(entity.getId());	
	}
	
}