package com.practicaltask.subscriber;

import com.practicaltask.config.CommonResponse;
import com.practicaltask.config.ResponseUtilities;
import com.practicaltask.constants.StringConstants;
import com.practicaltask.enums.Gender;
import com.practicaltask.exception.GeneralException;
import com.practicaltask.subscriber.converter.SubscriberConverter;
import com.practicaltask.subscriber.dto.SubscriberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberService {

    private SubscriberRepository subscriberRepository;
    private SubscriberConverter subscriberConverter;

    @Autowired
    public SubscriberService(SubscriberRepository subscriberRepository, SubscriberConverter subscriberConverter) {
        this.subscriberRepository = subscriberRepository;
        this.subscriberConverter = subscriberConverter;
    }

    public CommonResponse<SubscriberDTO> createSubscriber(SubscriberDTO subscriberDTO, Boolean createMode) {
        String responseMessage = "";
        Subscriber subscriber = null;
        if (createMode) {
            subscriber = subscriberRepository.findByMobile(subscriberDTO.getMobile());
            if (subscriber != null) {
                throw new GeneralException("Subscriber with mobile " + subscriberDTO.getMobile() + " already exist", HttpStatus.BAD_REQUEST);
            }
            subscriber = subscriberRepository.findByEmail(subscriberDTO.getEmail());
            if (subscriber != null) {
                throw new GeneralException("subscriber with email " + subscriberDTO.getEmail() + " already exist", HttpStatus.BAD_REQUEST);
            }
            subscriber = new Subscriber();
            if (subscriberDTO.getEmail() != null) {
                subscriber.setEmail(subscriberDTO.getEmail());
            }
            if (subscriberDTO.getMobile() != null) {
                subscriber.setMobile(subscriberDTO.getMobile());
            }
            responseMessage = StringConstants.SUBSCRIBER_CREATED_SUCCESSFULLY;
        } else {
            if (subscriberDTO.getId() == null) {
                throw new GeneralException("Id should be not null", HttpStatus.BAD_REQUEST);
            }
            subscriber = subscriberRepository.findByID(subscriberDTO.getId());
            if (subscriber == null) {
                throw new GeneralException("subscriber not found with id " + subscriberDTO.getId(), HttpStatus.BAD_REQUEST);
            }
            responseMessage = StringConstants.SUBSCRIBER_UPDATED_SUCCESSFULLY;
        }

        if (subscriberDTO.getName() != null) {
            subscriber.setName(subscriberDTO.getName());
        }
        //male or female
        if (subscriberDTO.getGender() != null) {
            if (subscriberDTO.getGender().equalsIgnoreCase(Gender.MALE.name())) {
                subscriber.setGender(Gender.MALE.name().toUpperCase());
            } else if (subscriberDTO.getGender().equalsIgnoreCase(Gender.FEMALE.name())) {
                subscriber.setGender(Gender.FEMALE.name().toUpperCase());
            } else {
                throw new GeneralException("Invalid Gender", HttpStatus.BAD_REQUEST);
            }
        }
        if (subscriberDTO.getAddressLine1() != null) {
            subscriber.setAddressLine1(subscriberDTO.getAddressLine1());
        }
        if (subscriberDTO.getAddressLine2() != null) {
            subscriber.setAddressLine2(subscriberDTO.getAddressLine2());
        }
        if (subscriberDTO.getCity() != null) {
            subscriber.setCity(subscriberDTO.getCity());
        }
        if (subscriberDTO.getState() != null) {
            subscriber.setState(subscriberDTO.getState());
        }
        if (subscriberDTO.getCountry() != null) {
            subscriber.setCountry(subscriberDTO.getCountry());
        }
        if (subscriberDTO.getZipcode() != null) {
            subscriber.setZipcode(subscriberDTO.getZipcode());
        }
        Subscriber savedSubscriber = subscriberRepository.save(subscriber);
        SubscriberDTO dto = subscriberConverter.convert(savedSubscriber);
        CommonResponse response = new CommonResponse(dto);
        return ResponseUtilities.createSucessResponseWithMessage(response, responseMessage);
    }

    public CommonResponse deleteSubscriber(String subscriberId) {
        Subscriber subscriber = subscriberRepository.findByID(Long.parseLong(subscriberId));
        if (subscriber == null) {
            throw new GeneralException("Subscriber with id " + subscriberId + " not exist", HttpStatus.BAD_REQUEST);
        }
        try {
            subscriberRepository.delete(subscriber);
        } catch (Exception e) {
            throw new GeneralException(e.getCause().getCause().getMessage(), HttpStatus.BAD_REQUEST);
        }
        CommonResponse response = new CommonResponse("");
        return ResponseUtilities.createSucessResponseWithMessage(response, StringConstants.SUBSCRIBER_DELETED_SUCCESSFULLY);
    }

    public CommonResponse getAllSubscribers() {
        List<Subscriber> subscriberList = subscriberRepository.findAll();
        List<SubscriberDTO> dtoList = subscriberConverter.convertList(subscriberList);
        CommonResponse response = new CommonResponse(dtoList);
        return ResponseUtilities.createSuccessResponse(response);
    }


}
