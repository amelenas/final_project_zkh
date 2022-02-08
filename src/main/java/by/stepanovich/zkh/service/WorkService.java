package by.stepanovich.zkh.service;

import by.stepanovich.zkh.entity.SiteOfWork;
import by.stepanovich.zkh.entity.TypeOfWork;
import by.stepanovich.zkh.service.exception.ServiceException;

import java.util.List;
import java.util.Set;

public interface WorkService {
    List<SiteOfWork> findAllSitesOfWork() throws ServiceException;

    List<TypeOfWork> findAllTypesOfWork() throws ServiceException;

    List<Long>findPerformerForWork(long typeOfWorksId, long siteOfWork) throws ServiceException;

    boolean registerRunWork(long userId, long orderId) throws ServiceException;

    List<Long> findWorkPerformerId(long orderId) throws ServiceException;

    List<Long> findAllOrdersByEmployeeId (long employeeId) throws ServiceException;

    boolean cancelEmployeeOrder(long userId, long orderId) throws ServiceException;

    boolean cancelAllEmployeeOrder(long EmployeeId) throws ServiceException;

    boolean registerEmployee(long siteOfWorkId, long typeOfWorkId, long employeeId) throws ServiceException;

    Set<Long> findAllNewEmployeeId() throws ServiceException;

    boolean changeEmployeeStatus(long userId, boolean isActive, boolean isOnApproval) throws ServiceException;
}
