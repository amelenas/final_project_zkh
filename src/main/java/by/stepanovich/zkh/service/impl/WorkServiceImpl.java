package by.stepanovich.zkh.service.impl;

import by.stepanovich.zkh.dao.WorkDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.dao.impl.WorkDAOImpl;
import by.stepanovich.zkh.entity.SiteOfWork;
import by.stepanovich.zkh.entity.TypeOfWork;
import by.stepanovich.zkh.service.WorkService;
import by.stepanovich.zkh.service.exception.ServiceException;

import java.util.List;

public class WorkServiceImpl implements WorkService {
    private WorkDao workDao = new WorkDAOImpl();
    public WorkServiceImpl(WorkDao workDao) {
        this.workDao=workDao;
    }

    public WorkServiceImpl() {
    }

    @Override
    public List<SiteOfWork> findAllSitesOfWork() throws ServiceException {
        List<SiteOfWork> siteOfWorks;
        try {
            siteOfWorks = workDao.findAllSitesOfWork();

        } catch (DaoException e) {
            throw new ServiceException("Exception in findAllSitesOfWork method ", e);
        }
        return siteOfWorks;
    }

    @Override
    public List<TypeOfWork> findAllTypesOfWork() throws ServiceException {
        List<TypeOfWork> typeOfWorks;
        try {
            typeOfWorks = workDao.findAllTypesOfWork();
        } catch (DaoException e) {
            throw new ServiceException("Exception in findAllTypesOfWork method ", e);
        }
        return typeOfWorks;
    }
}
