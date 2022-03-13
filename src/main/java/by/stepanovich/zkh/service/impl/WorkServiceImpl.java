package by.stepanovich.zkh.service.impl;

import by.stepanovich.zkh.dao.WorkDao;
import by.stepanovich.zkh.dao.exception.DaoException;
import by.stepanovich.zkh.dao.factory.DaoFactory;
import by.stepanovich.zkh.entity.SiteOfWork;
import by.stepanovich.zkh.entity.TypeOfWork;
import by.stepanovich.zkh.service.WorkService;
import by.stepanovich.zkh.service.exception.ServiceException;

import java.util.List;
import java.util.Set;

public class WorkServiceImpl implements WorkService {
    private final WorkDao workDao = DaoFactory.getInstance().getWorkDao();

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

    @Override
    public List<Long> findPerformerForWork(long typeOfWorksId, long siteOfWork) throws ServiceException {
        try {
            return workDao.findPerformerForWork(typeOfWorksId, siteOfWork);
        } catch (DaoException e) {
            throw new ServiceException("Exception in findPerformerForWork method ", e);
        }
    }

    @Override
    public boolean registerRunWork(long userId, long orderId) throws ServiceException {
        try {
            return workDao.registerRunWork(userId, orderId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in registerRunWork method ", e);
        }

    }

    @Override
    public List<Long> findWorkPerformerId(long orderId) throws ServiceException {
        try {
            return workDao.findWorkPerformerId(orderId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in findWorkPerformerId method ", e);
        }
    }

    @Override
    public List<Long> findAllOrdersByEmployeeId(long employeeId) throws ServiceException {
        try {
            return workDao.findAllOrdersByEmployeeId(employeeId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in findAllOrdersByEmployeeId method ", e);
        }
    }

    @Override
    public boolean cancelEmployeeOrder(long userId, long orderId) throws ServiceException {
        try {
            return workDao.cancelEmployeeOrder(userId, orderId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in cancelEmployeeOrder method ", e);
        }
    }

    @Override
    public boolean cancelAllEmployeeOrder(long EmployeeId) throws ServiceException {
        try {
            return workDao.cancelAllEmployeeOrder(EmployeeId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in cancelAllEmployeeOrder method ", e);
        }
    }

    @Override
    public boolean registerEmployee(long siteOfWorkId, long typeOfWorkId, long employeeId) throws ServiceException {
        try {
            return workDao.registerEmployee(siteOfWorkId, typeOfWorkId, employeeId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in registerEmployee method ", e);
        }
    }

    @Override
    public Set<Long> findAllNewEmployeeId() throws ServiceException {
        try {
            return workDao.findAllNewEmployeeId();
        } catch (DaoException e) {
            throw new ServiceException("Exception in findAllNewEmployeeId method ", e);
        }
    }

    @Override
    public boolean changeEmployeeStatus(long userId, boolean isActive, boolean isOnApproval) throws ServiceException {
        try {
            return workDao.changeEmployeeStatus(userId, isActive, isOnApproval);
        } catch (DaoException e) {
            throw new ServiceException("Exception in changeEmployeeStatus method ", e);
        }
    }
}
