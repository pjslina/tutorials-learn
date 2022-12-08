/**
 * 
 */
package com.panjin.boot.jdbi.service;

import com.panjin.boot.jdbi.dao.CarMakerDao;
import com.panjin.boot.jdbi.dao.CarModelDao;
import com.panjin.boot.jdbi.domain.CarMaker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Philippe
 *
 */
@Service
public class CarMakerService {
    
    private CarMakerDao carMakerDao;
    private CarModelDao carModelDao;

    public CarMakerService(CarMakerDao carMakerDao,CarModelDao carModelDao) {
        
        this.carMakerDao = carMakerDao;
        this.carModelDao = carModelDao;
    }
    
    @Transactional(rollbackFor = RuntimeException.class)
    public int bulkInsert(CarMaker carMaker) {
        Long carMakerId;
        if (carMaker.getId() == null ) {
            carMakerId = carMakerDao.insert(carMaker);
            carMaker.setId(carMakerId);
        }

        // Make sure all models belong to the same maker
        carMaker.getModels().forEach(m -> {
            m.setMakerId(carMaker.getId());
            carModelDao.insert(m);
        });        
        
        return carMaker.getModels().size();
    }
}
