package com.digout.webapp.service.validator;

import com.digout.webapp.repository.model.Model;
import com.digout.webapp.service.exeption.EmptyResultException;
import com.digout.webapp.service.exeption.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResultValidator <T>{

    public void verifyNotNullOrEmptyList (List<T> modelList) throws EmptyResultException {
        if (modelList == null || modelList.isEmpty()) {
            throw new EmptyResultException(Model.getTableName());
        }
    }

    public void verifyNotNull (T model, int id) throws NotFoundException {
        if (model == null) {
            throw new NotFoundException(Model.getTableName(), id);
        }
    }
}
