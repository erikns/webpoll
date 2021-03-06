package no.hib.megagruppe.webpoll.fakes;

import no.hib.megagruppe.webpoll.data.SurveyRepository;
import no.hib.megagruppe.webpoll.entities.SurveyEntity;

import java.util.*;

public class TestSurveyRepository implements SurveyRepository {
    Map<Integer, SurveyEntity> surveys;
    private int nextId;

    public TestSurveyRepository() {
        surveys = new HashMap<>();
    }

    @Override
    public SurveyEntity findByCode(String code) {
        SurveyEntity result = null;
        for (SurveyEntity e : surveys.values()) {
            if (e.getCode().equals(code)) {
                result = e;
                break;
            }
        }
        return result;
    }

    @Override
    public SurveyEntity add(SurveyEntity entity) {
        entity.setId(nextId++);
        return surveys.put(entity.getId(), entity);
    }

    @Override
    public SurveyEntity findById(int id) {
        return surveys.get(id);
    }

    @Override
    public List<SurveyEntity> findAll() {
        List<SurveyEntity> result = new ArrayList<>();

        Set<Integer> keys = surveys.keySet();
        for (int k : keys) {
            result.add(surveys.get(k));
        }

        return result;
    }

    @Override
    public SurveyEntity update(SurveyEntity entity) {
        return surveys.put(entity.getId(), entity);
    }

    @Override
    public void remove(SurveyEntity entity) {
        if (surveys.containsKey(entity.getId())) {
            surveys.remove(entity.getId());
        }
    }

	@Override
	public List<SurveyEntity> findAllByUser(Integer userID) {
		List<SurveyEntity> surveysByOwner = new ArrayList<>();
		for(SurveyEntity survey : surveys.values()){
			if(survey.getOwner().getId().equals(userID)){
				surveysByOwner.add(survey);
			}
		}
		return surveysByOwner;
	}

	@Override
	public Long numberOfResponses(SurveyEntity survey) {
		Long count = new Long(0);
		for(SurveyEntity surveyInRepo : surveys.values()){
			if(surveyInRepo.getId() == survey.getId()){
				if(surveyInRepo.getResponses() != null){
					count = Long.sum(count, surveyInRepo.getResponses().size());
				}
			}
		}
		return count;
	}

	@Override
	public boolean existsActiveSurveyWithCode(String code) {
		boolean exists = false;
		for(SurveyEntity surveyInRepo : surveys.values()){
			exists = exists || surveyInRepo.getCode().equals(code);
		}
		return exists;
	}
}
