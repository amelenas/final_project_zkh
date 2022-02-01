package by.stepanovich.zkh.service;

import by.stepanovich.zkh.entity.SiteOfWork;
import by.stepanovich.zkh.entity.TypeOfWork;
import by.stepanovich.zkh.service.exception.ServiceException;

import java.util.List;

public interface WorkService {
    List<SiteOfWork> findAllSitesOfWork() throws ServiceException;

    List<TypeOfWork> findAllTypesOfWork() throws ServiceException;
}
